package com.hhy.chain;

import com.hhy.chain.annotation.Length;
import com.hhy.chain.annotation.Max;
import com.hhy.chain.annotation.Min;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class User {
    @Length(4)
    private final String name;
    @Max(15)
    @Min(20)
    private final Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

}
