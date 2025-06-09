package com.hhy.chain;

import com.hhy.chain.validation.Validator;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        User wxz = new User("wxz5", 17);
        Validator validator = new Validator();
        validator.validate(wxz);
    }
}
