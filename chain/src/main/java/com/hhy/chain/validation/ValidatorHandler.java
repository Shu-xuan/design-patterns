package com.hhy.chain.validation;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public interface ValidatorHandler {
    void validate(Object value, ValidatorContext context);
}
