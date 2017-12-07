import java.io.Serializable;

public class SwimResult implements Serializable {
    private double time;
    private SwimStyle style;

    public SwimResult(double time, SwimStyle style) {
        this.time = time;
        this.style = style;
    }

    public double getTime() {
        return time;
    }

    public SwimStyle getStyle() {
        return style;
    }

    @Override
    public String toString() {
        return "time=" + time +
                ", style=" + style;
    }
}
