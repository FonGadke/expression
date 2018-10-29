package expression.exceptions;

public class InvalidPresentationException extends ParseException {
    public InvalidPresentationException(String message) {
        super(message);
    }

    public InvalidPresentationException(String message, String context, int offset) {
        super(String.format("%s: %s<INVALID>-> %s", message, context.substring(0, offset), context.substring(offset)));
    }
}
