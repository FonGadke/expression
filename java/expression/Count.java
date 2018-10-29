package expression;

import expression.exceptions.ArithmeticError;

public class Count extends UnaryOperation {

    public Count(CommonExpression operand) {
        super(operand);
    }

    @Override
    protected int apply(int x) {
        int result = 0;
        while (x != 0) {
            result++;
            x &= (x - 1);
        }
        return result;
    }

    @Override
    protected double apply(double x) {
        throw new ArithmeticError("Can't apply binary operation to double");
    }
}
