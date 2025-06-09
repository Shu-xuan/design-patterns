package com.hhy.chain.validation;

import com.hhy.chain.annotation.Length;
import com.hhy.chain.annotation.Max;
import com.hhy.chain.annotation.Min;
import com.hhy.chain.exception.ValidatorException;

import java.lang.reflect.Field;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class Validator {
    public void validate(Object bean) throws ValidatorException, IllegalAccessException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            ValidatorChain chain = buildValidatorChain(field);
            chain.validate(field.get(bean));
        }
    }

    private ValidatorChain buildValidatorChain(Field field) {
        ValidatorChain chain = new ValidatorChain();
        Max max = field.getAnnotation(Max.class);
        Min min = field.getAnnotation(Min.class);
        Length length = field.getAnnotation(Length.class);
        if (max != null) {
            chain.addLastHandler(new MaxValidatorHandler(max.value()));
        }
        if (min != null) {
            chain.addLastHandler(new MinValidatorHandler(min.value()));
        }
        if (length != null) {
            chain.addLastHandler(new LengthValidatorHandler(length.value()));
        }
        return chain;
    }
}
