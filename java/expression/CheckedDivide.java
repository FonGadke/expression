package expression;

import expression.exceptions.DivisionByZeroError;
import expression.exceptions.OverflowError;

public class CheckedDivide extends BinaryOperation {

    public CheckedDivide(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int calculation(int first, int second) {
        if (second == 0) {
            throw new DivisionByZeroError(String.format("attempted division by zero: %d / %d", first, second));
        }
        if (first == Integer.MIN_VALUE && second == -1) {
            throw new OverflowError("integer division overflow");
        }
        return first / second;
    }

    @Override
    protected double calculation(double first, double second) {
        return first / second;
    }
}
