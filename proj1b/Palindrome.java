public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (char c: word.toCharArray()) {
            deque.addLast(c);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        if (word.length() <= 1) return true;

        Deque<Character> deque = wordToDeque(word);
        while (deque.size() > 1) {
            char front = deque.removeFirst();
            char end = deque.removeLast();
            if (front != end) return false;
        }

        return true;
    }
}
