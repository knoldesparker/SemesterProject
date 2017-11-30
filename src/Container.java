import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Container {
    ArrayList<Swimmer> swimmers = new ArrayList<>();
    FileHandler fh = new FileHandler();
    Scanner scanner = new Scanner(System.in);

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
        int answer = scanner.nextInt();
        scanner.nextLine();
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
        for (Swimmer swimmer:swimmers) {
            System.out.println(swimmer);
        }

       fh.writeToFile(swimmers, "swimmers.txt");
    }

    public void editSwimmer() {
        Swimmer selectedSwimmer = new Swimmer(0,"","",0,MembershipType.PASSIVE);
        int intSelection;
        String strSelection;

        for (Swimmer swimmer:swimmers) {
            System.out.println(swimmer);
        }
        System.out.println("Indtast ID# på den svømmer, du ønsker at redigere:");
        intSelection = scanner.nextInt();
        scanner.nextLine();

        for (Swimmer swimmer:swimmers) {
            if (intSelection == swimmer.getId()) {
                selectedSwimmer = swimmer;
                System.out.println(selectedSwimmer.getName() + " valgt.");
            }
        }

        System.out.println("Vælg hvilken ting De vil ændre på:\n" +
                "[1] Navn\n" +
                "[2] Adresse\n" +
                "[3] Medlemskabstype\n" +
                "[4] Slet medlem");
        intSelection = scanner.nextInt();
        scanner.nextLine();
        switch (intSelection) {
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
                intSelection = scanner.nextInt();
                switch (intSelection) {
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
                strSelection = scanner.nextLine();
                switch (strSelection) {
                    case "y":
                        swimmers.remove(selectedSwimmer);
                        break;

                    default:
                        break;
                }
        }
        fh.writeToFile(swimmers,"swimmers.txt");
    }

    public void updateSwimmerAge() {
        for (Swimmer swimmer:swimmers) {
            swimmer.setAge(LocalDate.now().getYear() - swimmer.getBIRTHYEAR());
        }
    }
}

