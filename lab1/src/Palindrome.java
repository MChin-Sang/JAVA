public class Palindrome {
    private Stack stack;
    private char[] word;

    /**
     * Constructor receives the String from the command line as an argument
     * uses the stack container and pushes each char into the stack
     * clones the stack char[] using the getStack()
     *
     */
    public Palindrome(String word) {
        stack = new Stack(word.length());
        for(int i = 0; i < word.length(); i++) {
            stack.push(word.charAt(i));
        }
        this.word = stack.getStack().clone();
    }

    /**
     * Uses the pop method which returns a char and assigns it to the temp
     * compares each letter in the field array to the temp and will set same to false if not the same
     */
    public boolean isPalindrome() {
        boolean same = true;
        char temp;
        for(int i = 0; i < word.length && same; i++) {
            temp = stack.pop();
            if (word[i] != temp) {
                same = false;
            }
        }
        return same;
    }

    /**
     * Main Method
     */
    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome(args[0]);
        if (palindrome.isPalindrome())
            System.out.println("The word " + args[0] + " IS A palindrome");
        else
            System.out.println("The word " + args[0] + " IS NOT a palindrome");
    } // end of main
}
