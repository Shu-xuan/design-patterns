package com.hhy.chain.validation;

import com.hhy.chain.exception.ValidatorException;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class MaxValidatorHandler implements ValidatorHandler{
    private int max;

    public MaxValidatorHandler(int max) {
        this.max = max;
    }

    @Override
    public void validate(Object value, ValidatorContext context) {
        if (value instanceof Integer) {
            System.out.println("校验最大值ing...");
            int intValue = Integer.parseInt(value.toString());
            if (intValue > max) {
                context.appendError("你的值: " + intValue + " 不能大于 " + max);
                context.interruptChain();
            }
        }
        context.put("name", "hhy");
        context.doNext(value);
    }
}
