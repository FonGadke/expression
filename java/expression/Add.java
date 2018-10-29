package expression;

public class Add extends BinaryOperation {

    public Add(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int calculation(int x, int y) {
        return (x + y);
    }

    @Override
    protected double calculation(double first, double second) {
        return (first + second);
    }
}
