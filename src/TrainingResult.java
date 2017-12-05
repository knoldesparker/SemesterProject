import java.io.Serializable;

public class TrainingResult extends SwimResult implements Serializable{
    private int dayOfYear;
    private int year;

    public TrainingResult(double time, SwimStyle style, int dayOfYear, int year) {
        super(time, style);
        this.dayOfYear = dayOfYear;
        this.year = year;
    }

    public int getDayOfYear() {
        return dayOfYear;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return '{' +
                super.toString() +
                '}';
    }
}
