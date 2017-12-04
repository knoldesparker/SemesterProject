import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Container {
    FileHandler fh = new FileHandler();
    ArrayList<Swimmer> swimmers = fh.readFromFile("swimmers.txt");
    Scanner scanner = new Scanner(System.in);
    int intSelector;
    String strSelector;
    SwimStyle style;
    Swimmer selectedSwimmer = null;

    public void newSwimmer() {
        int id = 0;

        System.out.println("Skriv venligst svømmerens navn: ");
        String name = scanner.nextLine();
        System.out.println("Skriv venligst svømmerens adresse: ");
        String address = scanner.nextLine();
        System.out.println("Skriv venligst svømmerens fødselsår: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Vælg venligst medlemskabstype:\n" +
                "[1] Konkurrencesvømmer\n" +
                "[2] Motionssvømmer\n" +
                "[3] Passivt medlemskab: ");
        intSelector = scanner.nextInt();
        scanner.nextLine();
        MembershipType membershipType = null;

        switch (intSelector) {
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

        for (int i = 1; i <= 10000; i++) {
            boolean idGood = true;
            for (Swimmer swimmer:swimmers) {
                if (i == swimmer.getId()) {
                    idGood = false;
                }
            }
            if (idGood) {
                id = i;
                break;
            }
        }

        swimmers.add(new Swimmer(id, name, address, age, membershipType));
        printSwimmers();
        fh.writeToFile(swimmers, "swimmers.txt");
    }

    public void editSwimmer() {
        printSwimmers();
        System.out.println("Indtast ID# på den svømmer, du ønsker at redigere:");
        intSelector = scanner.nextInt();
        scanner.nextLine();

        for (Swimmer swimmer:swimmers) {
            if (intSelector == swimmer.getId()) {
                selectedSwimmer = swimmer;
                System.out.println(selectedSwimmer.getName() + " valgt.");
            }
        }

        System.out.println("Vælg hvilken ting De vil ændre på:\n" +
                "[1] Navn\n" +
                "[2] Adresse\n" +
                "[3] Medlemskabstype\n" +
                "[4] Slet medlem");
        intSelector = scanner.nextInt();
        scanner.nextLine();
        switch (intSelector) {
            case 1:
                String oldName = selectedSwimmer.getName();
                System.out.println("Indtast svømmerens nye navn:");
                selectedSwimmer.setName(scanner.nextLine());
                System.out.println("Navn skiftet fra " + oldName + " til " + selectedSwimmer.getName() + '.');
                break;

            case 2:
                String oldAddress = selectedSwimmer.getAddress();
                System.out.println("Indtast svømmerens nye adresse:");
                selectedSwimmer.setAddress(scanner.nextLine());
                System.out.println("Adresse skiftet fra " + oldAddress + " til " + selectedSwimmer.getAddress() + '.');
                break;

            case 3:
                MembershipType oldMT = selectedSwimmer.getMembershipType();
                System.out.println("Den nuværende madlemskabstype er " + oldMT + ".\n" +
                        "Vælg ny medlemskabstype:\n" +
                        "[1] Konkurrencesvømmer\n" +
                        "[2] Motionssvømmer\n" +
                        "[3] Passivt medlem");
                intSelector = scanner.nextInt();
                switch (intSelector) {
                    case 1:
                        if (selectedSwimmer.getAge() < 18) {
                            selectedSwimmer.setMembershipType(MembershipType.COMPETITION_JUNIOR);
                        } else {
                            selectedSwimmer.setMembershipType(MembershipType.COMPETITION_SENIOR);
                        }
                        System.out.println("Medlemskab ændret til " + selectedSwimmer.getMembershipType());
                        break;

                    case 2:
                        if (selectedSwimmer.getAge() < 18) {
                            selectedSwimmer.setMembershipType(MembershipType.EXERCISE_JUNIOR);
                        } else {
                            selectedSwimmer.setMembershipType(MembershipType.EXERCISE_SENIOR);
                        }
                        System.out.println("Medlemskab ændret til " + selectedSwimmer.getMembershipType());
                        break;

                    case 3:
                        selectedSwimmer.setMembershipType(MembershipType.PASSIVE);
                        System.out.println("Medlemskab ændret til " + selectedSwimmer.getMembershipType());
                        break;
                }

                if (selectedSwimmer.getMembershipType() != MembershipType.PASSIVE) {
                    if (selectedSwimmer.getAge() < 18) {
                        selectedSwimmer.setPrice(1000);
                    } else {
                        selectedSwimmer.setPrice(1600);
                    }
                } else {
                    selectedSwimmer.setPrice(500);
                }
                if (selectedSwimmer.getAge() >= 60) {
                    selectedSwimmer.setPrice(selectedSwimmer.getPrice() * 0.75);
                }
                
                System.out.println("Medlemskabstype skiftet fra " + oldMT + " til " +
                        selectedSwimmer.getMembershipType() + '.');
                break;
                
            case 4:
                System.out.println("Er du sikker på, at du vil fjerne " + selectedSwimmer.getName() +
                        " fra systemet? (y/N)");
                strSelector = scanner.nextLine();
                switch (strSelector) {
                    case "y":
                        swimmers.remove(selectedSwimmer);
                        break;

                    default:
                        break;
                }
        }
        fh.writeToFile(swimmers,"swimmers.txt");
    }

    public void addtrainingresults() {
        printSwimmers();
        System.out.println("Vælg en svømmer via ID#");
        intSelector = scanner.nextInt();
        scanner.nextLine();
        for (Swimmer swimmer:swimmers) {
            if (intSelector == swimmer.getId()) {
                selectedSwimmer = swimmer;
            }
        }

        System.out.println("Indtast dato for træning (DDMMYY)");
        String dateStr = scanner.nextLine();
        int dateD = Character.getNumericValue(dateStr.charAt(0)) * 10 +
                Character.getNumericValue(dateStr.charAt(1));
        int dateM = Character.getNumericValue(dateStr.charAt(2)) * 10 +
                Character.getNumericValue(dateStr.charAt(3));
        int dateY = Character.getNumericValue(dateStr.charAt(4)) * 10 +
                Character.getNumericValue(dateStr.charAt(5));

        System.out.println("Vælg en stilart\n" +
                "[1] Butterfly\n" +
                "[2] Crawl\n" +
                "[3] Rygsvømning\n" +
                "[4] Brystsvømning\n" +
                "[5] Hundesvømning");
        SwimStyle style = null;
        intSelector = scanner.nextInt();
        scanner.nextLine();
        switch (intSelector) {
            case 1:
                style = SwimStyle.BUTTERFLY;
                break;

            case 2:
                style = SwimStyle.CRAWL;
                break;

            case 3:
                style = SwimStyle.BACKSTROKE;
                break;

            case 4:
                style = SwimStyle.BREASTSTROKE;
                break;

            case 5:
                style = SwimStyle.DOG_PADDLE;
                break;
        }

        System.out.println("Indtast den svømmede tid i sekunder med decimaler");
        double time = scanner.nextDouble();

        selectedSwimmer.getTrainingResults().add(new TrainingResult(time, style, dateD, dateM, dateY));
        System.out.println(selectedSwimmer.getTrainingResults());
        fh.writeToFile(swimmers,"swimmers.txt");
    }

    public void printSwimmers() {
        for (Swimmer swimmer:swimmers) {
            System.out.println(swimmer);
        }
    }

    public void updateSwimmerAge() {
        for (Swimmer swimmer:swimmers) {
            swimmer.setAge(LocalDate.now().getYear() - swimmer.getBIRTHYEAR());
        }
    }
}

