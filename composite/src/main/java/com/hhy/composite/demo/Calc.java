package com.hhy.composite.demo;

/**
 * <p>
 * 描述: 计算器
 * </p>
 *
 * @Author huhongyuan
 */
public class Calc {
    public static void main(String[] args) {
        PlusExpression plusExpression1 = new PlusExpression(new NumberExpression(1), new NumberExpression(1));
        PlusExpression plusExpression2 = new PlusExpression(new NumberExpression(2), new NumberExpression(3));
        System.out.println(new PlusExpression(plusExpression1, plusExpression2).getValue());
    }
    interface Expression {
        int getValue();
    }

    static class NumberExpression implements Expression {
        final int value;

        public NumberExpression(int value) {
            this.value = value;
        }

        @Override
        public int getValue() {
            return value;
        }
    }

    static class PlusExpression implements Expression {
        final Expression left;
        final Expression right;

        public PlusExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int getValue() {
            return left.getValue() + right.getValue();
        }
    }


}
