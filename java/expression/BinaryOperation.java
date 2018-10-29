package expression;

public abstract class BinaryOperation implements CommonExpression {
    private CommonExpression first;
    private CommonExpression second;

    public BinaryOperation(CommonExpression first, CommonExpression second) {
        assert first != null : "First element is null";
        assert second != null : "Second element is null";

        this.first = first;
        this.second = second;
    }

    @Override
    public int evaluate(int x) {
        return this.calculation(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public double evaluate(double x) {
        return this.calculation(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (this.calculation(first.evaluate(x, y, z), second.evaluate(x, y, z)));
    }

    protected abstract int calculation(int first, int second);

    protected abstract double calculation(double first, double second);
}
