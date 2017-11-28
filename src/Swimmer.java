import java.util.ArrayList;

public class Swimmer {
    private String name;
    private String address;
    private int age;
    private MembershipType membershipType;
    private double price;
    private ArrayList<TrainingResult> trainingResults = new ArrayList<>();
    private TrainingResult butterflyPB;
    private TrainingResult crawlPB;
    private TrainingResult backstrokePB;
    private TrainingResult breaststrokePB;
    private TrainingResult dogPaddlePB;

    public Swimmer(String name, String address, int age, MembershipType membershipType) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.membershipType = membershipType;

        if (this.membershipType != MembershipType.PASSIVE) {
            if (age < 18) {
                this.price = 1000;
            } else {
                this.price = 1600;
            }
        } else {
            this.price = 500;
        }

        if (this.age >= 60) {
            this.price = this.price * 0.75;
        }
    }

    @Override
    public String toString()
    {
        return "Swimmer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", membershipType=" + membershipType +
                ", price=" + price +
                ", trainingResults=" + trainingResults +
                ", butterflyPB=" + butterflyPB +
                ", crawlPB=" + crawlPB +
                ", backstrokePB=" + backstrokePB +
                ", breaststrokePB=" + breaststrokePB +
                ", dogPaddlePB=" + dogPaddlePB +
                "}";
    }
}
