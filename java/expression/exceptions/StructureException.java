package expression.exceptions;

public class StructureException extends ParseException {
    public StructureException(String message, String context, int offsetBegin, int offsetEnd) {
        super(String.format("%s: %s<ERROR-> %s <-ERROR>%s"
                , message
                , context.substring(0, offsetBegin)
                , context.substring(offsetBegin, offsetEnd)
                , context.substring(offsetEnd)));
    }
}
