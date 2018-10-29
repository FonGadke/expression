package expression;

import expression.exceptions.IllegalArgumentException;
import expression.exceptions.ArithmeticError;
import expression.exceptions.OverflowError;

public class Pow extends BinaryOperation {

    public Pow(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected double calculation(double first, double second) {
        throw new ArithmeticError("We can't do that yet");
    }

    @Override
    protected int calculation(int first, int second) {
        if (second < 0) {
            throw new IllegalArgumentException("negative exponent is not allowed");
        }
        if (first == 0 && second == 0) {
            throw new IllegalArgumentException("0 ** 0 isn't defined");
        }
        int result = 1;
        for (int i = 0; i < second; i++) {
            int tmp = result * first;
            if (first != 0 && (tmp / first != result) || (result == Integer.MIN_VALUE && first == -1)) {
                throw new OverflowError("integer pow overflow");
            }
            result = tmp;
        }
        return result;
    }
}
