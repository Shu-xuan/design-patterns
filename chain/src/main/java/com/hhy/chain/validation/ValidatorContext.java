package com.hhy.chain.validation;

import com.hhy.chain.Main;
import com.hhy.chain.exception.ValidatorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述: 校验上下文
 * </p>
 *
 * @Author huhongyuan
 */
public class ValidatorContext {
    private final List<String> errorMessageList = new ArrayList<>();
    private final Map<String, Object> data = new HashMap<>();
    private boolean needBreak = false;
    private int index = 0;
    private Object value;

    public ValidatorContext(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public boolean isNeedBreak() {
        return needBreak;
    }

    public void interruptChain() {
        this.needBreak = true;
    }

    public void doNext(Object value) {
        ++index;
        this.value = value;
    }

    public int currentIndex() {
        return index;
    }

    public void appendError(String errMsg) {
        errorMessageList.add(errMsg);
    }

    public Object get(String key) {
        return this.data.get(key);
    }

    public void put(String key, String value) {
        this.data.put(key, value);
    }

    public void throwExceptionsIfPresent() throws ValidatorException {
        if (errorMessageList.isEmpty()) {
            return;
        }
        throw new ValidatorException(errorMessageList.toString());
    }
}
