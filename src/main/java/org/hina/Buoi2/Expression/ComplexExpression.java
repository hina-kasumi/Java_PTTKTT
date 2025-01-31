package org.hina.Buoi2.Expression;

import org.hina.Buoi2.Expression.Operator.Add;
import org.hina.Buoi2.Expression.Operator.Division;
import org.hina.Buoi2.Expression.Operator.Product;
import org.hina.Buoi2.Expression.Operator.Substract;

import java.util.Stack;

public class ComplexExpression implements Expression {
    private final String[] tokenArray;

    public ComplexExpression(String complexExpression) {
        tokenArray = complexExpression.split("\\s+");//xóa khoảng trắng
    }

    public static boolean isOperator(String s) {
        return switch (s) {
            case "+", "-", "*", "/" -> true;
            default -> false;
        };
    }

    public static Expression getOperator(String s, Expression left, Expression right) {
        return switch (s) {
            case "+" -> new Add(left, right);
            case "-" -> new Substract(left, right);
            case "*" -> new Product(left, right);
            case "/" -> new Division(left, right);
            default -> null;
        };
    }

    @Override
    public int interpret() {
        Stack<Expression> stack = new Stack<>();
        for (String s : tokenArray) {
            if (isOperator(s)) {
                Expression rightExpression = stack.pop();
                Expression leftExpression = stack.pop();
                Expression operator = getOperator(s, leftExpression, rightExpression);
                assert operator != null;
                int result = operator.interpret();
                stack.add(new Number(result));
            } else {
                if (s.compareTo("") == 0)
                    continue;
                Expression i = new Number(Integer.parseInt(s));
                stack.push(i);
            }
        }

        return stack.pop().interpret();
    }

    public static void main(String[] args) {
        String tokenString = "7 3 / 5 *";
        ComplexExpression suffixExp = new ComplexExpression(tokenString);
        int result = suffixExp.interpret();
        System.out.println("Ket qua tinh " + tokenString + " la: " + result);
    }
}
