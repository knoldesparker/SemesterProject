import java.io.Serializable;

public class CompetitionResult extends SwimResult implements Serializable{
    private String competition;
    private int position;
    private SwimStyle style;

    public CompetitionResult(double time, SwimStyle style, String competition, int position, SwimStyle style1) {
        super(time, style);
        this.competition = competition;
        this.position = position;
        this.style = style1;
    }
}
