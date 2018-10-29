package expression;

public class Negate extends UnaryOperation {

    public Negate(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected double apply(double x) {
        return -x;
    }

    @Override
    protected int apply(int x) {
        return -x;
    }

}
