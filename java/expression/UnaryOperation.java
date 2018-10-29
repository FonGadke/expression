package expression;

public abstract class UnaryOperation implements CommonExpression {
    final private CommonExpression operand;

    UnaryOperation(CommonExpression operand) {
        assert operand != null : "Element is null";

        this.operand = operand;
    }

    @Override
    public int evaluate(int x) {
        return apply(operand.evaluate(x));
    }

    @Override
    public double evaluate(double x) {
        return apply(operand.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return apply(operand.evaluate(x, y, z));
    }

    protected abstract double apply(double x);

    protected abstract int apply(int x);

}
