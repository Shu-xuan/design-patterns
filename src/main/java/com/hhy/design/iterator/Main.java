package com.hhy.design.iterator;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class Main {
    public static void main(String[] args) {
//        test01();
//        test02();
        test03();
    }

    public static void test03() {
        File file = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("iterator.user")).getFile());
        System.out.println(file.toPath().toAbsolutePath());
        UserFile users = new UserFile(file, 5);
        for (User user : users) {
//            System.out.println(user);
        }
    }

    public static void test02() {
        User hhy = new User("hhy", 22);
        for (Object o : hhy) {
            System.out.println(o);
        }
    }

    public static void test01() {
        List<User> userList = new ArrayList<>();
        User zzy = new User("zzy", 22);
        User hhy = new User("hhy", 22);
        userList.add(zzy);
        userList.add(hhy);
        // java.util.ConcurrentModificationException
        /**
         * 去看生成的 Main.class 字节码文件可以发现
         * 增强 for 循环实际上是在 while 调用迭代器#hasNext和#next 方法
         */
//        for (User user : userList) {
//            if (user.getAge() == 22) {
//                userList.add(new User("hhy", 22));
//            }
//        }
        // 与上面代码等价
        Iterator<User> userIterator = userList.iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            if (user.getAge() == 22) {
                // 报错的原因在于 next()函数中的一个判断，而进行判断的值又会在 add 方法中被修改
                // 所以如果 add 之后不再去访问这个迭代器，是不会报错的
                userList.add(new User("hhy", 22));
            }
        }
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
