import java.io.Serializable;

public class CompetitionResult extends SwimResult implements Serializable{
    private String competition;
    private int position;

    public CompetitionResult(double time, SwimStyle style, String competition, int position) {
        super(time, style);
        this.competition = competition;
        this.position = position;
    }

    @Override
    public String toString() {
        return "{" +
                "competition='" + competition + '\'' +
                ", position=" + position +
                ", " + super.toString() +
                '}';
    }
}
