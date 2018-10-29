package expression.exceptions;

public enum Token {
    OR, XOR, AND, SUB, ADD, DIV, MUL, NEG, LOG10, POW10, POW, LOG, LBRACK, RBRACK, CONST, VARIABLE;

    int getPrecedence() {
        switch (this) {
            case OR:
                return 0;
            case XOR:
                return 1;
            case AND:
                return 2;
            case ADD:
            case SUB:
                return 3;
            case DIV:
            case MUL:
                return 6;
            case POW:
            case LOG:
                return 8;
            case NEG:
            case LOG10:
            case POW10:
                return 9;
            case LBRACK:
            case RBRACK:
                return 12;
            case CONST:
            case VARIABLE:
                return 15;
            default:
                throw new RuntimeException("Not all cases covered");
        }
    }

    boolean isUnary() {
        switch (this) {
            case NEG:
            case POW10:
            case LOG10:
                return true;
            default:
                return false;
        }
    }

}
