public class OffByN implements CharacterComparator{
    private int N;
    public OffByN(int N) {
        this.N = N;
    }

    /** Return true if char x and char y are off by n */
    @Override
    public boolean equalChars(char x, char y) {
        int offset = x - y;
        return offset == N || offset == -N;
    }
}
