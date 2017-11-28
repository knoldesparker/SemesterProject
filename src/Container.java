import java.util.ArrayList;
import java.util.Scanner;

public class Container {
    ArrayList<Swimmer> swimmers = new ArrayList<>();
//    FileHandler fh = new FileHandler();

    public void newSwimmer() {
//        swimmers = fh.readFromFile("swimmers.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Skriv venligst dit navn: ");
        String name = scanner.nextLine();
        System.out.println("Skriv venligst din adresse: ");
        String address = scanner.nextLine();
        System.out.println("Skriv venligst din alder: ");
        int age = scanner.nextInt();
        System.out.println("Vælg venligst hvorvidt du ønsker at være [1]konkurrencesvømmer, [2]motionssvømmer eller " +
                "have et [3]passivt medlemskab: ");
        int answer = scanner.nextInt();
        MembershipType membershipType = null;

        switch (answer) {
            case 1:
                if (age < 18) {
                    membershipType = MembershipType.COMPETITION_JUNIOR;
                } else {
                    membershipType = MembershipType.COMPETITION_SENIOR;
                }
                break;

            case 2:
                if (age < 18) {
                    membershipType = MembershipType.EXERCISE_JUNIOR;
                } else {
                    membershipType = MembershipType.EXERCISE_SENIOR;
                }
                break;


            case 3:
                membershipType = MembershipType.PASSIVE;
                break;
        }

        swimmers.add(new Swimmer(name, address, age, membershipType));
        for (Swimmer swimmer:swimmers) {
            System.out.println(swimmer);
        }
        System.out.println();
//        fh.writeToFile(swimmers, "swimmers.txt");
    }



}

