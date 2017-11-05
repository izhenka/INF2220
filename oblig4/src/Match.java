public class Match {
    int index;
    String match;

    @Override
    public String toString() {
        return "" + index + ": " + match;
    }

    public Match(int index, String match) {
        this.index = index;
        this.match = match;
    }
}
