package expression.exceptions;

import expression.CommonExpression;

public interface Parser {
    CommonExpression parse(String expression) throws ParseException;
}
