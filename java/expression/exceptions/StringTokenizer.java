package expression.exceptions;

public class StringTokenizer extends AbstractTokenizer {
    final private char[] array;
    private int pointer = 0;

    public StringTokenizer(String string) {
        array = string.toCharArray();
    }

    @Override
    protected char sym() {
        return array[pointer];
    }

    @Override
    protected char peek() {
        return array[pointer + 1];
    }

    @Override
    protected void take() {
        ++pointer;
    }

    @Override
    public int position() {
        return pointer;
    }

    @Override
    protected boolean end() {
        return pointer >= array.length;
    }
}
