package com.hhy.design.iterator;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class User implements Iterable<Object> {
    private String userName;
    private Integer age;

    public User(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public Iterator iterator() {
        return new UserIte(this);
    }

    static class UserIte implements Iterator<Object> {
        private User user; // 需要传入具体的 User 实例
        private int currentIndex = 0;
        private int count;
        private Field[] fields;

        public UserIte(User user) {
            this.user = user;
            this.fields = user.getClass().getDeclaredFields();
            this.count = fields.length;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < count;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException("无更多元素");
            }
            Field field = fields[currentIndex++];
            field.setAccessible(true);
            try {
                return field.get(user); // 获取字段的值
            } catch (IllegalAccessException e) {
                throw new RuntimeException("访问字段: [" + field.getName() + "] 时出错", e);
            }
        }
    }

}
