import java.io.Serializable;
import java.util.ArrayList;

public class Swimmer implements Serializable {
    private int id;
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

    public Swimmer(int id, String name, String address, int age, MembershipType membershipType) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "Swimmer{" +
                "ID#='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", membershipType=" + membershipType +
                ", price=" + price +
                ", trainingResults=" + trainingResults +
                ", butterflyPB=" + butterflyPB +
                ", crawlPB=" + crawlPB +
                ", backstrokePB=" + backstrokePB +
                ", breaststrokePB=" + breaststrokePB +
                ", dogPaddlePB=" + dogPaddlePB + "\n";
    }
}
