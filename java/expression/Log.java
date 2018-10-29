package expression;

import expression.exceptions.ArithmeticError;
import expression.exceptions.IllegalArgumentException;

public class Log extends BinaryOperation {

    public Log(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected double calculation(double first, double second) {
        throw new ArithmeticError("We can't do that yet");
    }

    @Override
    protected int calculation(int first, int second) {
        if (first <= 0) {
            throw new IllegalArgumentException("logarithm of non-positive number");
        }
        if (second <= 1) {
            throw new IllegalArgumentException("logarithm base is non-positive number or one");
        }

        int result = 0;
        while (first >= second) {
            first /= second;
            result++;
        }
        return result;
    }
}
