package expression.exceptions;

public class DivisionByZeroError extends ArithmeticError {
    public DivisionByZeroError(String message) {
        super(message);
    }
}
