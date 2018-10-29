package expression;

import expression.exceptions.ArithmeticError;

public class Xor extends BinaryOperation {

    public Xor(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int calculation(int first, int second) {
        return first ^ second;
    }

    @Override
    protected double calculation(double first, double second) {
        throw new ArithmeticError("Can't apply binary operation to double");
    }

}
