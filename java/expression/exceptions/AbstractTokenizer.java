package expression.exceptions;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class AbstractTokenizer implements Tokenizer {
    private static Set<String> ALLOWED_NAMES = new HashSet<>();

    static {
        ALLOWED_NAMES.add("x");
        ALLOWED_NAMES.add("y");
        ALLOWED_NAMES.add("z");
    }

    private Number constantValue = null;
    private String variableName = null;
    private boolean expectBinary = false;

    @Override
    public Number constantValue() {
        return constantValue;
    }

    @Override
    public String variableName() {
        return variableName;
    }

    @Override
    public boolean hasMoreTokens() {
        skipWhitespaces();
        return !end();
    }

    @Override
    public Token nextToken() throws ParseException {
        if (!hasMoreTokens()) {
            throw new NoSuchElementException();
        }
        skipWhitespaces();

        Token result;
        switch (sym()) {
            case '&':
                result = Token.AND;
                expectBinary = false;
                break;
            case '|':
                result = Token.OR;
                expectBinary = false;
                break;
            case '^':
                result = Token.XOR;
                expectBinary = false;
                break;
            case '+':
                result = Token.ADD;
                expectBinary = false;
                break;
            case '-':
                if (expectBinary) {
                    result = Token.SUB;
                    expectBinary = false;
                } else if (!end() && Character.isDigit(peek())) {
                    getValue();
                    expectBinary = true;
                    return Token.CONST;
                } else {
                    result = Token.NEG;
                    expectBinary = false;
                }
                break;
            case '*':
                expectBinary = false;
                if (peek() == '*') {
                    result = Token.POW;
                    take();
                } else {
                    result = Token.MUL;
                }
                break;
            case '/':
                expectBinary = false;
                if (peek() == '/') {
                    result = Token.LOG;
                    take();
                } else {
                    result = Token.DIV;
                }
                break;
            case '(':
                result = Token.LBRACK;
                expectBinary = false;
                break;
            case ')':
                result = Token.RBRACK;
                expectBinary = true;
                break;
            default:
                if (Character.isAlphabetic(sym())) {
                    getName();
                    switch (variableName) {
                        case "log10":
                            expectBinary = false;
                            return Token.LOG10;
                        case "pow10":
                            expectBinary = false;
                            return Token.POW10;
                        default:
                            expectBinary = true;
                            if (!ALLOWED_NAMES.contains(variableName)) {
                                throw new InvalidPresentationException("Invalid variable name: " + variableName);
                            }
                            return Token.VARIABLE;
                    }
                } else if (Character.isDigit(sym())) {
                    expectBinary = true;
                    getValue();
                    return Token.CONST;
                } else {
                    throw new InvalidPresentationException("Unidentified character: " + sym());
                }
        }
        take();
        return result;
    }

    private void skipWhitespaces() {
        while (!end() && Character.isWhitespace(sym())) {
            take();
        }
    }

    private void getName() {
        StringBuilder sb = new StringBuilder();
        if (!end() && Character.isAlphabetic(sym())) {
            sb.append(sym());
            take();
        }
        while (!end() && Character.isLetterOrDigit(sym())) {
            sb.append(sym());
            take();
        }
        variableName = sb.toString();
    }

    private void getValue() throws ParseException {
        StringBuilder sb = new StringBuilder();
        if (sym() == '-') {
            sb.append('-');
            take();
        }
        boolean trashInDigit = false;
        while (!end() && (Character.isDigit(sym()) || Character.isAlphabetic(sym()))) {
            sb.append(sym());
            if (Character.isAlphabetic(sym())) {
                trashInDigit = true;
            }
            take();
        }
        String numberPresentation = sb.toString();
        try {
            constantValue = Integer.parseInt(numberPresentation);
        } catch (NumberFormatException e) {
            if (trashInDigit) {
                throw new InvalidPresentationException("Can't parse number: " + numberPresentation);
            } else {
                throw new InvalidPresentationException("Constant overflow : " + numberPresentation);
            }
        }
    }

    protected abstract char sym();

    protected abstract char peek();

    protected abstract void take();

    protected abstract boolean end();

}
