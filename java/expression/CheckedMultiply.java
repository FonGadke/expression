package expression;

import expression.exceptions.OverflowError;

public class CheckedMultiply extends BinaryOperation {

    public CheckedMultiply(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int calculation(int first, int second) {
        int result = first * second;
        if ((second != 0 && result / second != first) || (first == Integer.MIN_VALUE && second == -1)) {
            throw new OverflowError("integer multiplication overflow");
        }
        return result;
    }

    @Override
    protected double calculation(double first, double second) {
        return first * second;
    }
}
