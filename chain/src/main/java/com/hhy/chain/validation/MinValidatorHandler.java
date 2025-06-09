package com.hhy.chain.validation;

import com.hhy.chain.exception.ValidatorException;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class MinValidatorHandler implements ValidatorHandler{
    private int min;

    public MinValidatorHandler(int min) {
        this.min = min;
    }

    @Override
    public void validate(Object value, ValidatorContext context) {
        Object name = context.get("name");
        if (name != null) {
            System.out.println("之前有 [" + name + "] 校验过");
        }
        if (value instanceof Integer) {
            System.out.println("校验最小值ing...");
            int intValue = Integer.parseInt(value.toString());
            if (intValue < min) {
                context.appendError("你的值: " + intValue + " 不能小于 " + min);
                context.interruptChain();
            }
        }
        context.doNext(value);
    }
}
