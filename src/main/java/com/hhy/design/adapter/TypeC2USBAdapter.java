package com.hhy.design.adapter;

/**
 * <p>
 * 描述: 适配器类
 * </p>
 *
 * @Author huhongyuan
 */
public class TypeC2USBAdapter implements USB{
    private TypeC typeC;

    public TypeC2USBAdapter(TypeC typeC) {
        this.typeC = typeC;
    }

    @Override
    public void chargeWithUSB() {
        typeC.chargeWithTypeC();
    }
}
