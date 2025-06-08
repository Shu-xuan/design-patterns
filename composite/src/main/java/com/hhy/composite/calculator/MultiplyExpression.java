package com.hhy.composite.calculator;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class MultiplyExpression extends BinaryExpression {

    public MultiplyExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int getValue() {
        return left.getValue() * right.getValue();
    }
}
