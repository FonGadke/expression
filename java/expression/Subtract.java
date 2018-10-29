package expression;

public class Subtract extends BinaryOperation {

    public Subtract(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int calculation(int x, int y) {
        return (x - y);
    }

    @Override
    protected double calculation(double first, double second) {
        return (first - second);
    }
}
