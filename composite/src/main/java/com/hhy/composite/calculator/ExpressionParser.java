package com.hhy.composite.calculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 描述: 表达式解析器
 * </p>
 *
 * @Author huhongyuan
 */
public class ExpressionParser {
    private int pos = 0;
    /**
     * 中缀表达式
     */
    private String infixExpression;

    public ExpressionParser(String infixExpression) {
        this.infixExpression = infixExpression;
    }

    private List<String> toSuffix() {
        List<String> suffix = new ArrayList<>();
        LinkedList<String> stack = new LinkedList<>();
        while (pos < infixExpression.length()) {
            char element = infixExpression.charAt(pos);
            if (element == '(') {
                stack.addLast(element + "");
            } else if (element == ')') {
                while (!stack.getLast().equals("(")) {
                    suffix.add(stack.removeLast());
                }
                // 删掉括号
                stack.removeLast();
            } else if (element == '*' || element == '/') {
                while ((!stack.isEmpty()) && (stack.getLast().equals("*") || stack.getLast().equals("/"))) {
                    suffix.add(stack.removeLast());
                }
                stack.addLast(element + "");
            } else if (element == '+' || element == '-') {
                while (topIsOperator(stack)) {
                    suffix.add(stack.removeLast());
                }
                stack.addLast(element + "");
            } else if (Character.isDigit(element)) {
                StringBuilder builder = new StringBuilder();
                while (pos < infixExpression.length() && Character.isDigit(infixExpression.charAt(pos))) {
                    builder.append(infixExpression.charAt(pos));
                    ++pos;
                }
                --pos;
                suffix.add(builder.toString());
            } else {
                throw new IllegalArgumentException("非法字符!");
            }
            ++pos;
        }
        while (!stack.isEmpty()) {
            suffix.add(stack.removeLast());
        }
        return suffix;
    }

    public Expression parse() {
        List<String> suffix = toSuffix();
        LinkedList<Expression> stack = new LinkedList<>();
        for (String item : suffix) {
            if ("+".equals(item)) {
                Expression right = stack.removeLast();
                Expression left = stack.removeLast();
                stack.addLast(new AddExpression(left, right));
            } else if("-".equals(item)) {
                Expression right = stack.removeLast();
                Expression left = stack.removeLast();
                stack.addLast(new SubExpression(left, right));
            } else if("*".equals(item)) {
                Expression right = stack.removeLast();
                Expression left = stack.removeLast();
                stack.addLast(new MultiplyExpression(left, right));
            } else if ("/".equals(item)) {
                Expression right = stack.removeLast();
                Expression left = stack.removeLast();
                stack.addLast(new DivisionExpression(left, right));
            } else {
                stack.addLast(new NumberExpression(Integer.parseInt(item)));
            }
        }
        return stack.removeLast();
    }

    private boolean topIsOperator(LinkedList<String> stack) {
        if (stack.isEmpty()) {
            return false;
        }
        return "+-*/".contains(stack.getLast());
    }
}
