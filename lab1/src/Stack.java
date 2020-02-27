public class Stack {
    private char[] stack;
    private int pos;

    // 1-arg Constructor
    // Receives the length of the word and allocates memory for that word
    public Stack(int wordLength) {
        pos = 0;
        stack = new char[wordLength];
    }

    public void push(char letter) {
        stack[pos] = letter;
        pos++;
    }

    public char pop() {
        char temp;
        pos--;
        temp = stack[pos];
        stack[pos] = '\u0000';   //set value to empty char
        return temp;
    }

    public char[] getStack() {
        return stack;
    }

    public void show() {
        for (int i = 0; i < stack.length; i++) {
            System.out.println(stack[i]);
        }
    }


}
