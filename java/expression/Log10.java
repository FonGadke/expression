package expression;

import expression.exceptions.IllegalArgumentException;

public class Log10 extends UnaryOperation {

    public Log10(CommonExpression operand) {
        super(operand);
    }

    @Override
    protected double apply(double x) {
        return Math.log10(x);
    }

    @Override
    protected int apply(int x) {
        if (x <= 0) {
            throw new IllegalArgumentException("logarithm of non-positive number");
        }
        return String.valueOf(x).length() - 1;
    }
}
