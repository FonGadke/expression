package expression;

import expression.exceptions.OverflowError;

public class CheckedSubtract extends BinaryOperation {

    public CheckedSubtract(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int calculation(int first, int second) {
        int result = first - second;
        if (((first ^ second) & (result ^ first)) < 0) {
            throw new OverflowError("integer subtraction overflow");
        }

        return result;
    }

    @Override
    protected double calculation(double first, double second) {
        return first - second;
    }
}
