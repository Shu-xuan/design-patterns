package com.hhy.design.adapter;

/**
 * <p>
 * 描述: 适配器测试
 * </p>
 *
 * @Author huhongyuan
 */
public class AdapterTest {
    public static void main(String[] args) {
        TypeC2USBAdapter adapter = new TypeC2USBAdapter(new TypeC());
        adapter.chargeWithUSB();
    }
}
