import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Swimmer implements Serializable {
    private int id;
    private String name;
    private String address;
    private final int BIRTH_YEAR;
    private int age;
    private MembershipType membershipType;
    private double price;
    private ArrayList<TrainingResult> trainingResults = new ArrayList<>();
    private TrainingResult butterflyPB;
    private TrainingResult crawlPB;
    private TrainingResult backstrokePB;
    private TrainingResult breaststrokePB;
    private TrainingResult dogPaddlePB;
    private ArrayList<CompetitionResult> competitionResults = new ArrayList<>();
    private boolean hasPaid = false;

    //AUTHOR(S): ECS, CPS, MHP, JHH
    public Swimmer(int id, String name, String address, int birthYear, MembershipType membershipType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.BIRTH_YEAR = birthYear;
        this.age = LocalDate.now().getYear() - BIRTH_YEAR;
        this.membershipType = membershipType;

        if (age < 18 && this.membershipType == MembershipType.COMPETITION_SENIOR) {
            this.membershipType = MembershipType.COMPETITION_JUNIOR;
        } else if (age < 18 && this.membershipType == MembershipType.EXERCISE_SENIOR) {
            this.membershipType = MembershipType.EXERCISE_JUNIOR;
        }

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

    //AUTHOR(S): ECS, CPS, MHP, JHH
    public void updatePB() {
        for (TrainingResult tr:trainingResults) {
            if ((tr.getYear() == LocalDate.now().getYear() &&
                    tr.getDayOfYear() + 30 >= LocalDate.now().getDayOfYear()) ||
                    (LocalDate.now().getDayOfYear() < 30 &&
                            tr.getYear() + 1 == LocalDate.now().getYear() &&
                            tr.getDayOfYear() + 30 >= LocalDate.now().getDayOfYear() + 365)) {
                if (tr.getStyle() == SwimStyle.BUTTERFLY) {
                    if (butterflyPB == null || tr.getTime() < butterflyPB.getTime()) {
                        butterflyPB = tr;
                    } else if ((butterflyPB.getYear() == LocalDate.now().getYear() &&
                            butterflyPB.getDayOfYear() + 30 < LocalDate.now().getDayOfYear()) ||
                            (LocalDate.now().getDayOfYear() < 30 &&
                                    butterflyPB.getYear() + 1 == LocalDate.now().getYear() &&
                                    butterflyPB.getDayOfYear() + 30 < LocalDate.now().getDayOfYear() + 365)) {
                        butterflyPB = tr;
                    }
                }

                if (tr.getStyle() == SwimStyle.CRAWL) {
                    if (crawlPB == null || tr.getTime() < crawlPB.getTime()) {
                        crawlPB = tr;
                    } else if ((crawlPB.getYear() == LocalDate.now().getYear() &&
                            crawlPB.getDayOfYear() + 30 < LocalDate.now().getDayOfYear()) ||
                            (LocalDate.now().getDayOfYear() < 30 &&
                                    crawlPB.getYear() + 1 == LocalDate.now().getYear() &&
                                    crawlPB.getDayOfYear() + 30 < LocalDate.now().getDayOfYear() + 365)) {
                        crawlPB = tr;
                    }
                }

                if (tr.getStyle() == SwimStyle.BACKSTROKE) {
                    if (backstrokePB == null || tr.getTime() < backstrokePB.getTime()) {
                        backstrokePB = tr;
                    } else if ((backstrokePB.getYear() == LocalDate.now().getYear() &&
                            backstrokePB.getDayOfYear() + 30 < LocalDate.now().getDayOfYear()) ||
                            (LocalDate.now().getDayOfYear() < 30 &&
                                    backstrokePB.getYear() + 1 == LocalDate.now().getYear() &&
                                    backstrokePB.getDayOfYear() + 30 < LocalDate.now().getDayOfYear() + 365)) {
                        backstrokePB = tr;
                    }
                }

                if (tr.getStyle() == SwimStyle.BREASTSTROKE) {
                    if (breaststrokePB == null || tr.getTime() < breaststrokePB.getTime()) {
                        breaststrokePB = tr;
                    } else if ((breaststrokePB.getYear() == LocalDate.now().getYear() &&
                            breaststrokePB.getDayOfYear() + 30 < LocalDate.now().getDayOfYear()) ||
                            (LocalDate.now().getDayOfYear() < 30 &&
                                    breaststrokePB.getYear() + 1 == LocalDate.now().getYear() &&
                                    breaststrokePB.getDayOfYear() + 30 < LocalDate.now().getDayOfYear() + 365)) {
                        breaststrokePB = tr;
                    }
                }

                if (tr.getStyle() == SwimStyle.DOG_PADDLE) {
                    if (dogPaddlePB == null || tr.getTime() < dogPaddlePB.getTime()) {
                        dogPaddlePB = tr;
                    } else if ((dogPaddlePB.getYear() == LocalDate.now().getYear() &&
                            dogPaddlePB.getDayOfYear() + 30 < LocalDate.now().getDayOfYear()) ||
                            (LocalDate.now().getDayOfYear() < 30 &&
                                    dogPaddlePB.getYear() + 1 == LocalDate.now().getYear() &&
                                    dogPaddlePB.getDayOfYear() + 30 < LocalDate.now().getDayOfYear() + 365)) {
                        dogPaddlePB = tr;
                    }
                }
            }
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

    public int getBIRTH_YEAR() {
        return BIRTH_YEAR;
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

    public ArrayList<TrainingResult> getTrainingResults() {
        return trainingResults;
    }

    public TrainingResult getButterflyPB() {
        return butterflyPB;
    }

    public TrainingResult getCrawlPB() {
        return crawlPB;
    }

    public TrainingResult getBackstrokePB() {
        return backstrokePB;
    }

    public TrainingResult getBreaststrokePB() {
        return breaststrokePB;
    }

    public TrainingResult getDogPaddlePB() {
        return dogPaddlePB;
    }

    public ArrayList<CompetitionResult> getCompetitionResults() {
        return competitionResults;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    @Override
    public String toString() {
        return "Swimmer{" +
                "ID#='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", membershipType=" + membershipType +
                ", price=" + price +
                ", butterflyPB=" + butterflyPB +
                ", crawlPB=" + crawlPB +
                ", backstrokePB=" + backstrokePB +
                ", breaststrokePB=" + breaststrokePB +
                ", dogPaddlePB=" + dogPaddlePB;
    }
}
