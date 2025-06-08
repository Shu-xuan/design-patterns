package com.hhy.facade.demo;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class MyTomcat implements ServerFacade{
    void initEngine() {
        System.out.println("初始化Tomcat引擎");
    }

    void initWeb() {
        System.out.println("加载Web应用");
    }

    @Override
    public void start() {
        initEngine();
        initWeb();
    }
}
