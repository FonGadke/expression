package expression;

import expression.exceptions.OverflowError;
import expression.exceptions.IllegalArgumentException;

public class Pow10 extends UnaryOperation {

    public Pow10(CommonExpression operand) {
        super(operand);
    }

    @Override
    protected double apply(double x) {
        return Math.pow(10, x);
    }

    @Override
    protected int apply(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("negative exponent is not allowed");
        }
        if (x >= 10) {
            throw new OverflowError("exponent overflow");
        }
        return pow10(x);
    }

    private int pow10(int x) {
        if (x == 0) {
            return 1;
        }
        if ((x & 1) == 0) {
            int result = pow10(x >> 1);
            return result * result;
        } else {
            return 10 * pow10(x - 1);
        }
    }

}
