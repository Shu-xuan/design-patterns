package com.hhy.design.composite.calculator;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class SubExpression extends BinaryExpression {

    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int getValue() {
        return left.getValue() - right.getValue();
    }
}
