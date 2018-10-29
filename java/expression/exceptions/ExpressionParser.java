package expression.exceptions;

import expression.*;

import java.util.ArrayDeque;
import java.util.Deque;

public class ExpressionParser implements Parser {
    private Deque<Number> constantStack;
    private Deque<String> variableStack;
    private Deque<Token> tokenStack;
    private String expression;
    private Boolean expectBinary;

    @Override
    public CommonExpression parse(String expression) throws ParseException {
        this.expression = expression;
        expectBinary = false;
        constantStack = new ArrayDeque<>();
        variableStack = new ArrayDeque<>();
        tokenStack = new ArrayDeque<>();

        toPostfixNotation(new StringTokenizer(expression));

        CommonExpression result = getExpression();
        if (!tokenStack.isEmpty()) {
            throw new ParseException("Illegal expression: " + this.expression);
        }

        return result;

    }

    private void toPostfixNotation(Tokenizer tokenizer) throws ParseException {
        Deque<Token> operationStack = new ArrayDeque<>();
        while (tokenizer.hasMoreTokens()) {
            int offsetBegin = tokenizer.position();

            Token token;
            try {
                token = tokenizer.nextToken();
            } catch (InvalidPresentationException e) {
                throw new InvalidPresentationException(e.getMessage(), expression, offsetBegin);
            }
            int offsetEnd = tokenizer.position();
            checkCorrectness(token, offsetBegin, offsetEnd);
            switch (token) {
                case CONST:
                    tokenStack.push(token);
                    constantStack.push(tokenizer.constantValue());
                    break;
                case VARIABLE:
                    tokenStack.push(token);
                    variableStack.push(tokenizer.variableName());
                    break;
                case LBRACK:
                    operationStack.push(token);
                    break;
                case RBRACK:
                    while (!operationStack.isEmpty() && operationStack.peek() != Token.LBRACK) {
                        tokenStack.push(operationStack.pop());
                    }
                    if (operationStack.isEmpty()) {
                        throw new StructureException("Missing opening parenthesis", expression, offsetBegin, offsetEnd);
                    } else {
                        operationStack.pop();
                    }
                    break;
                default:
                    while (shouldPush(operationStack, token)) {
                        tokenStack.push(operationStack.pop());
                    }
                    operationStack.push(token);
                    break;
            }
        }
        if (!expectBinary) {
            throw new StructureException("Missing last argument", expression, expression.length(), expression.length());
        }
        while (!operationStack.isEmpty()) {
            tokenStack.push(operationStack.pop());
        }
    }

    private void checkCorrectness(Token token, int offsetBegin, int offsetEnd) throws StructureException {
        switch (token) {
            case OR:
            case XOR:
            case AND:
            case ADD:
            case SUB:
            case DIV:
            case MUL:
            case LOG:
            case POW:
                if (!expectBinary) {
                    throw new StructureException("Unexpected binary operation", expression, offsetBegin, offsetEnd);
                }
                expectBinary = false;
                break;
            case NEG:
            case POW10:
            case LOG10:
            case LBRACK:
                if (expectBinary) {
                    throw new StructureException("Expected binary operation", expression, offsetBegin, offsetEnd);
                }
                expectBinary = false;
                break;
            case RBRACK:
                if (!expectBinary) {
                    throw new StructureException("Unexpected right parenthesis", expression, offsetBegin, offsetEnd);
                }
                expectBinary = true;
                break;
            case CONST:
            case VARIABLE:
                if (expectBinary) {
                    throw new StructureException("Expected binary operation", expression, offsetBegin, offsetEnd);
                }
                expectBinary = true;
                break;
            default:
                throw new StructureException("Unknown symbol", expression, offsetBegin, offsetEnd);
        }
    }

    private boolean shouldPush(Deque<Token> operationStack, Token token) {
        return !operationStack.isEmpty()
                && operationStack.peek() != Token.LBRACK
                && (token.getPrecedence() < operationStack.peek().getPrecedence()
                || token.getPrecedence() == operationStack.peek().getPrecedence() && !token.isUnary());
    }

    private CommonExpression getExpression() throws ParseException {
        if (tokenStack.isEmpty()) {
            throw new IllegalStateException("Token stack is empty");
        }
        Token token = tokenStack.pop();
        switch (token) {
            case CONST:
                return new Const(constantStack.pop());
            case VARIABLE:
                return new Variable(variableStack.pop());
            case NEG:
                return new CheckedNegate(getExpression());
            case LOG10:
                return new Log10(getExpression());
            case POW10:
                return new Pow10(getExpression());
            case LBRACK:
                throw new ParseException("Missing closing parenthesis: " + expression);
            default:
                CommonExpression operand2 = getExpression();
                CommonExpression operand1 = getExpression();
                switch (token) {
                    case OR:
                        return new Or(operand1, operand2);
                    case XOR:
                        return new Xor(operand1, operand2);
                    case AND:
                        return new And(operand1, operand2);
                    case ADD:
                        return new CheckedAdd(operand1, operand2);
                    case SUB:
                        return new CheckedSubtract(operand1, operand2);
                    case MUL:
                        return new CheckedMultiply(operand1, operand2);
                    case DIV:
                        return new CheckedDivide(operand1, operand2);
                    case POW:
                        return new Pow(operand1, operand2);
                    case LOG:
                        return new Log(operand1, operand2);
                    default:
                        throw new ParseException("Unexpected token in token stack: " + token.name());
                }
        }
    }
}
