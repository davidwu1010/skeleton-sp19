public class OffByOne implements CharacterComparator{

    /** Return true if char x and char y are off by one*/
    @Override
    public boolean equalChars(char x, char y) {
        int offset = x - y;
        return offset == 1 || offset == -1;
    }
}
