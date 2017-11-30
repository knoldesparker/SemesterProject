import java.io.Serializable;

public class TrainingResult extends SwimResult implements Serializable{
    private String date;

    public TrainingResult(double time, String date) {
        super(time);
        this.date = date;
    }
}
