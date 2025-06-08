package com.hhy.design.composite.calculator;

import com.hhy.design.composite.demo.Calc;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public abstract class BinaryExpression implements Expression {
    final Expression left;
    final Expression right;

    protected BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
