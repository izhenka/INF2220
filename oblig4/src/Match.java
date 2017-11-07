import java.util.Objects;

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

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Match)){
            return false;
        }

        Match otherMatch = (Match) object;
        return this.index == otherMatch.index && this.match.equals(otherMatch.match);

    }
}
