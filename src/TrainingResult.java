import java.io.Serializable;

public class TrainingResult extends SwimResult implements Serializable{
    private int day;
    private int month;
    private int year;

    public TrainingResult(double time, SwimStyle style, int day, int month, int year) {
        super(time, style);
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return "TrainingResult{" +
                "date=" + day + "/" + month + "-20" + year +
                super.toString() +
                '}';
    }
}
