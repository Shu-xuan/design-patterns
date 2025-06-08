package com.hhy.design.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class Main {
    public static void main(String[] args) {
        test02();
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
        for (User user : userList) {
            if (user.getAge() == 22) {
                userList.add(new User("hhy", 22));
            }
        }
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
