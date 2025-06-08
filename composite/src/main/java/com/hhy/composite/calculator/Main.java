package com.hhy.composite.calculator;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser("1+15*(9+4+(1+5))+6");
        System.out.println(parser.parse().getValue());
    }
}
