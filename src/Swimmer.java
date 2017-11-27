import java.util.ArrayList;

public class Swimmer {
    private String name;
    private String address;
    private int age;
    private MembershipType membershipType;
    private int price;
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

        switch (membershipType) {
            case PASSIVE:
                this.price = 500;
                break;
            case ACTIVE_JUNIOR:
                this.price = 1000;
                break;
            case ACTIVE_SENIOR:
                this.price = 1600;
                break;
            case ACTIVE_PENSIONER:
                this.price = 1200;
                break;
            case PASSIVE_PENSIONER:
                this.price = 375;
                break;
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
                '}';
    }
}
