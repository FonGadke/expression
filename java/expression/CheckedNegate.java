package expression;

import expression.exceptions.OverflowError;

public class CheckedNegate extends UnaryOperation {

    public CheckedNegate(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected double apply(double x) {
        return -x;
    }

    @Override
    protected int apply(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowError("integer negation overflow");
        }
        return -x;
    }
}
