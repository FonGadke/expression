package expression.exceptions;

public interface Tokenizer {

    Token nextToken() throws ParseException;

    boolean hasMoreTokens();

    Number constantValue();

    String variableName();

    int position();

}
