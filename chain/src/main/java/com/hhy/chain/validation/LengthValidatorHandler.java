package com.hhy.chain.validation;

import com.hhy.chain.exception.ValidatorException;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class LengthValidatorHandler implements ValidatorHandler{
    private int len;

    public LengthValidatorHandler(int len) {
        this.len = len;
    }

    @Override
    public void validate(Object value, ValidatorContext context) {
        if (value instanceof String) {
            System.out.println("校验元素长度ing...");
            int length = ((String) value).length();
            if (length != len) {
                context.appendError("你的字符串长度为: " + length + " ，必须等于 " + len);
                context.interruptChain();
            }
        }
        context.doNext(value);
    }
}
