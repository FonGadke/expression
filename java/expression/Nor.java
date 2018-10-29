package expression;

import expression.exceptions.ArithmeticError;

public class Nor extends UnaryOperation {

    public Nor(CommonExpression operand) {
        super(operand);
    }

    @Override
    protected int apply(int x) {
        return ~x;
    }

    @Override
    protected double apply(double x) {
        throw new ArithmeticError("Can't apply binary operation to double");
    }
}
