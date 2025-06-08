package com.hhy.facade;

import com.hhy.facade.plugin.MyPlugin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
@RestController
public class TimeController {
    private MyPlugin myPlugin;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/time")
    public String getTime() {
        if (myPlugin != null) {
            myPlugin.beforeGetTime();
        }
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    // 实现了我们插件的 jar 包，必须要拥有一个文件叫 hhy.nb, 该文件的内容都是实现了插件的类的全限定名
    @GetMapping("/load-plugin/{path}")
    public String loadPlugin(@PathVariable("path") String path) {
        File file = new File(path);
        try (URLClassLoader loader = new URLClassLoader(new URL[]{file.toPath().toUri().toURL()});
             InputStream hhyNB = loader.getResourceAsStream("hhy.nb");
             ByteArrayOutputStream result = new ByteArrayOutputStream()
        ){
            byte[] buffer = new byte[1024];
            int length;
            while ((length = hhyNB.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            String myPluginFullClassName = result.toString(StandardCharsets.UTF_8.name());

            Class<?> aClass = loader.loadClass(myPluginFullClassName.trim());
            Constructor<?> constructor = aClass.getConstructor();
            myPlugin = (MyPlugin) constructor.newInstance();
            return "类: [" + aClass.getCanonicalName() + "] 加载成功!";
        } catch (Exception e) {
            return "加载失败!";
        }
    }

    public static void main(String[] args) {
        String rootPath = System.getProperty("user.dir"); // 项目根目录
        System.out.println(rootPath);
    }
}
