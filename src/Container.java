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

    //AUTHOR(S): MHP, ECS, CPS
    public void newSwimmer() {
        int id = 0;

        System.out.println("Skriv venligst svømmerens navn: ");
        String name = scanner.nextLine();
        System.out.println("Skriv venligst svømmerens adresse: ");
        String address = scanner.nextLine();
        System.out.println("Skriv venligst svømmerens fødselsår: ");
        int birthYear = scanner.nextInt();
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
                membershipType = MembershipType.COMPETITION_SENIOR;
                break;

            case 2:
                membershipType = MembershipType.EXERCISE_SENIOR;
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

        swimmers.add(new Swimmer(id, name, address, birthYear, membershipType));
        printSwimmers();
        fh.writeToFile(swimmers, "swimmers.txt");
    }

    //AUTHOR(S): ECS
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

    //AUTHOR(S): ECS, CPS
    public void addTrainingResults() {
        int dayOfYear;
        int year;

        printSwimmers();
        System.out.println("Vælg en svømmer via ID#");
        intSelector = scanner.nextInt();
        scanner.nextLine();
        for (Swimmer swimmer:swimmers) {
            if (intSelector == swimmer.getId()) {
                selectedSwimmer = swimmer;
            }
        }

        System.out.println("Hvor mange dage siden blev tiden sat?");
        int daysSinceTraining = scanner.nextInt();
        if (daysSinceTraining > LocalDate.now().getDayOfYear()) {
            year = LocalDate.now().getYear() - 1;
            dayOfYear = 365 + LocalDate.now().getDayOfYear() - daysSinceTraining;
        } else {
            year = LocalDate.now().getYear();
            dayOfYear = LocalDate.now().getDayOfYear() - daysSinceTraining;
        }

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

        selectedSwimmer.getTrainingResults().add(new TrainingResult(time, style, dayOfYear, year));
        System.out.println(selectedSwimmer.getTrainingResults());
        selectedSwimmer.updatePB();
        fh.writeToFile(swimmers,"swimmers.txt");
    }

    //AUTHOR(S): ECS
    //TODO Finish this method, so that it actually does something
    public void listBestSwimmers(MembershipType ageGroup, SwimStyle style) {
        Swimmer[] bestInClass = new Swimmer[5];
        Swimmer[] bestInClassHelper;

        for (Swimmer swimmer:swimmers) {
            if (swimmer.getMembershipType() == ageGroup) {
                switch (style) {
                    case BUTTERFLY:
                        if (swimmer.getButterflyPB() != null) {
                            for (int i = 0; i < 5; i++) {
                                if (bestInClass[i] == null) {
                                    bestInClass[i] = swimmer;
                                    break;
                                } else if (swimmer.getButterflyPB().getTime() <
                                        bestInClass[i].getButterflyPB().getTime()) {
                                    bestInClassHelper = bestInClass;
                                    for (int j = 3; j >= i; j--) {
                                        bestInClass[j+1] = bestInClassHelper[j];
                                    }
                                    bestInClass[i] = swimmer;
                                    break;
                                }
                            }
                        }
                        break;

                    case CRAWL:
                        if (swimmer.getCrawlPB() != null) {
                            for (int i = 0; i < 5; i++) {
                                if (bestInClass[i] == null) {
                                    bestInClass[i] = swimmer;
                                    break;
                                } else if (swimmer.getCrawlPB().getTime() <
                                        bestInClass[i].getCrawlPB().getTime()) {
                                    bestInClassHelper = bestInClass;
                                    for (int j = 3; j >= i; j--) {
                                        bestInClass[j+1] = bestInClassHelper[j];
                                    }
                                    bestInClass[i] = swimmer;
                                    break;
                                }
                            }
                        }
                        break;

                    case BACKSTROKE:
                        if (swimmer.getBackstrokePB() != null) {
                            for (int i = 0; i < 5; i++) {
                                if (bestInClass[i] == null) {
                                    bestInClass[i] = swimmer;
                                    break;
                                } else if (swimmer.getBackstrokePB().getTime() <
                                        bestInClass[i].getBackstrokePB().getTime()) {
                                    bestInClassHelper = bestInClass;
                                    for (int j = 3; j >= i; j--) {
                                        bestInClass[j+1] = bestInClassHelper[j];
                                    }
                                    bestInClass[i] = swimmer;
                                    break;
                                }
                            }
                        }
                        break;

                    case BREASTSTROKE:
                        if (swimmer.getBreaststrokePB() != null) {
                            for (int i = 0; i < 5; i++) {
                                if (bestInClass[i] == null) {
                                    bestInClass[i] = swimmer;
                                    break;
                                } else if (swimmer.getBreaststrokePB().getTime() <
                                        bestInClass[i].getBreaststrokePB().getTime()) {
                                    bestInClassHelper = bestInClass;
                                    for (int j = 3; j >= i; j--) {
                                        bestInClass[j+1] = bestInClassHelper[j];
                                    }
                                    bestInClass[i] = swimmer;
                                    break;
                                }
                            }
                        }
                        break;

                    case DOG_PADDLE:
                        if (swimmer.getDogPaddlePB() != null) {
                            for (int i = 0; i < 5; i++) {
                                if (bestInClass[i] == null) {
                                    bestInClass[i] = swimmer;
                                    break;
                                } else if (swimmer.getDogPaddlePB().getTime() <
                                        bestInClass[i].getDogPaddlePB().getTime()) {
                                    bestInClassHelper = bestInClass;
                                    for (int j = 3; j >= i; j--) {
                                        bestInClass[j+1] = bestInClassHelper[j];
                                    }
                                    bestInClass[i] = swimmer;
                                    break;
                                }
                            }
                        }
                        break;
                }
            }
        }
        for (Swimmer competitor:bestInClass) {
            System.out.println(competitor);
        }
    }

    //AUTHOR(S): ECS
    public void printSwimmers() {
        for (Swimmer swimmer:swimmers) {
            System.out.println(swimmer);
        }
    }

    //AUTHOR(S): ECS
    //TODO Expand to include membershiptype and pricing
    public void updateSwimmerAge() {
        for (Swimmer swimmer:swimmers) {
            swimmer.setAge(LocalDate.now().getYear() - swimmer.getBIRTH_YEAR());
        }
    }
}

