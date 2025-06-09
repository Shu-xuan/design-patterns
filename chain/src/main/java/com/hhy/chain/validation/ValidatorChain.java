package com.hhy.chain.validation;

import com.hhy.chain.exception.ValidatorException;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class ValidatorChain {
    private final List<ValidatorHandler> handlerChain = new ArrayList<>();

    public void validate(Object value) {
        if (handlerChain.isEmpty()) return;
        ValidatorContext validatorContext = new ValidatorContext(value);
        while (true) {
            int index = validatorContext.currentIndex();
            ValidatorHandler handler = handlerChain.get(index);
            handler.validate(validatorContext.getValue(), validatorContext);
            if (index == validatorContext.currentIndex() || validatorContext.currentIndex() == handlerChain.size()) break;
        }
        validatorContext.throwExceptionsIfPresent();
    }

    public void addLastHandler(ValidatorHandler handler) {
        this.handlerChain.add(handler);
    }


}
