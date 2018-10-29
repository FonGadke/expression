package expression;

import expression.exceptions.OverflowError;

public class CheckedAdd extends BinaryOperation {

    public CheckedAdd(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int calculation(int first, int second) {
        if ((first > 0 && second > 0 && Integer.MAX_VALUE - first < second) ||
            (first < 0 && second < 0 && Integer.MIN_VALUE - first > second)) {
            throw new OverflowError("integer addition overflow");
        }
        return first + second;
    }

    @Override
    protected double calculation(double first, double second) {
        return first + second;
    }
}
