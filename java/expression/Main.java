package expression;

import expression.exceptions.*;
import expression.exceptions.IllegalArgumentException;
//import expression.parser.*;

public class Main {
    public static void main(String[] args) {

        int argument = 12;
        CommonExpression expression = null;
        try {
            expression = new ExpressionParser().parse("50 + log10 (123)");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(expression.evaluate(argument));
        } catch (DivisionByZeroError e) {
            System.out.println("division by zero");
        } catch (OverflowError e) {
            System.out.println("overflow");
        } catch (IllegalArgumentException e) {
            System.out.println("illegal argument");
        } catch (ArithmeticError e) {
            e.printStackTrace();
        }

/**
        CommonExpression exception = new ExpressionParser().parse("x -count y");
        System.out.println(exception.evaluate(0, 0, 1));
**/
    }
}
