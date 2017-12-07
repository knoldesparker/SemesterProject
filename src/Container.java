import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Container {
    FileHandler fh = new FileHandler();
    ArrayList<Swimmer> swimmers = fh.readFromFile("swimmers.txt");
    Scanner scanner = new Scanner(System.in);
    int intSelector;
    String strSelector;
    SwimStyle style;
    Swimmer selectedSwimmer = null;
    int dayOfYear;
    int year;

    // This method is called in the menu. It creates a new swimmer and adds it to the list of swimmers
    //AUTHOR(S): MHP, ECS, CPS
    public void newSwimmer() {
        // Parameters for Swimmer()
        int id = 0;
        int birthYear;
        String name;
        String address;
        MembershipType membershipType = null;

        System.out.println("Skriv venligst svømmerens navn:");
        name = scanner.nextLine();
        System.out.println("Skriv venligst svømmerens adresse:");
        address = scanner.nextLine();
        System.out.println("Skriv venligst svømmerens fødselsår:");
        try {
            birthYear = scanner.nextInt();
        } catch (InputMismatchException iME) {          // Makes sure it doesn't crash when input isn't an int
            System.out.println("Invalid input");
            return;
        } finally {
            scanner.nextLine();         // Workaround for the nextInt() bug
        }
        System.out.println("Vælg venligst medlemskabstype:\n" +
                "[1] Konkurrencesvømmer\n" +
                "[2] Motionssvømmer\n" +
                "[3] Passivt medlemskab: ");
        try {
            intSelector = scanner.nextInt();            // Selector for the following switch statement
        } catch (InputMismatchException iME) {          // Makes sure it doesn't crash when input isn't an int
            System.out.println("Invalid input");
            return;
        } finally {
            scanner.nextLine();         // Workaround for the nextInt() bug
        }

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

        for (int i = 1; i <= 10000; i++) {          // This for loop grants the new swimmer an available ID#
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

    // This method is called in the menu. It lets the the foreman edit swimmers
    //AUTHOR(S): ECS
    public void editSwimmer() {
        printSwimmers();
        System.out.println("Vælg en svømmer via ID#");
        try {
            intSelector = scanner.nextInt();
        } catch (InputMismatchException iME) {          // Makes sure it doesn't crash when input isn't an int
            System.out.println("Invalid input");
            return;
        } finally {
            scanner.nextLine();         // Workaround for the nextInt() bug
        }

        // Puts selected swimmer in temporary variable
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
        try {
            intSelector = scanner.nextInt();
        } catch (InputMismatchException iME) {          // Makes sure it doesn't crash when input isn't an int
            System.out.println("Invalid input");
            return;
        } finally {
            scanner.nextLine();         // Workaround for the nextInt() bug
        }
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
                try {
                    intSelector = scanner.nextInt();
                } catch (InputMismatchException iME) {          // Makes sure it doesn't crash when input isn't an int
                    System.out.println("Invalid input");
                    return;
                } finally {
                    scanner.nextLine();         // Workaround for the nextInt() bug
                }
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

                // Updates the price
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
                // Defaults to not deleting, as this action is irreversible
                switch (strSelector) {
                    case "y":
                        swimmers.remove(selectedSwimmer);
                        break;

                    default:
                        break;
                }
            default:
                break;
        }
        fh.writeToFile(swimmers,"swimmers.txt");
    }

    // This method is called in the menu. It lets the trainers add training results
    //AUTHOR(S): ECS, CPS
    public void addTrainingResults() {
        // Parameters for TrainingResult()
        int daysSinceTraining = 0;
        double time;

        printSwimmers();
        System.out.println("Vælg en svømmer via ID#");
        try {
            intSelector = scanner.nextInt();
        } catch (InputMismatchException iME) {
            System.out.println("Ivalid swimmer ID#");
            return;
        } finally {
            scanner.nextLine();
        }

        // Puts selected swimmer in temporary variable
        for (Swimmer swimmer:swimmers) {
            if (intSelector == swimmer.getId()) {
                selectedSwimmer = swimmer;
            }
        }

        System.out.println("Hvor mange dage siden blev tiden sat?");
        try {
            daysSinceTraining = scanner.nextInt();
        } catch (InputMismatchException iME) {          // Makes sure it doesn't crash when input isn't an int
            System.out.println("Invalid input");
            return;
        } finally {
            scanner.nextLine();         // Workaround for the nextInt() bug
        }

        // Calculates the date of the training result
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
        try {
            intSelector = scanner.nextInt();
        } catch (InputMismatchException iME) {          // Makes sure it doesn't crash when input isn't an int
            System.out.println("Invalid input");
            return;
        } finally {
            scanner.nextLine();         // Workaround for the nextInt() bug
        }
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

            default:
                System.out.println("Invalid input");
                break;
        }

        // Stops the method if the input doesn't match one of the five switch cases
        if (style == null) {
            return;
        }

        System.out.println("Indtast den svømmede tid i sekunder med decimaler (brug punktum som komma");
        try {
            time = scanner.nextDouble();
        } catch (InputMismatchException iME) {          // Makes sure it doesn't crash when input isn't an int
            System.out.println("Invalid input");
            return;
        } finally {
            scanner.nextLine();         // Workaround for the nextInt() bug
        }

        selectedSwimmer.getTrainingResults().add(new TrainingResult(time, style, dayOfYear, year));
        System.out.println(selectedSwimmer.getTrainingResults());
        selectedSwimmer.updatePB();         // Keeps the personal bests up to date
        fh.writeToFile(swimmers,"swimmers.txt");
    }

    // This method is called in the menu. It prints the top five of a given age group and swim style to the console
    //AUTHOR(S): ECS
    public void listBestSwimmers(MembershipType ageGroup, SwimStyle style) {
        Swimmer[] bestInClass = new Swimmer[5];         // The array that gets printed to the console
        Swimmer[] bestInClassHelper;            // The helper array, which we use to bump swimmers down one spot

        // Looks through all the swimmers for swimmers eligible for competition in the selected age group and swim style
        for (Swimmer swimmer:swimmers) {
            if (swimmer.getMembershipType() == ageGroup) {
                switch (style) {
                    case BUTTERFLY:
                        // Checks if the swimmer has a personal best in the selected swim style
                        if (swimmer.getButterflyPB() != null) {
                            for (int i = 0; i < 5; i++) {           // Looks through the array bestInClass
                                if (bestInClass[i] == null) {           // Takes an open spot if available
                                    bestInClass[i] = swimmer;
                                    break;
                                } else if (swimmer.getButterflyPB().getTime() <         // Bumps swimmers down to make
                                        bestInClass[i].getButterflyPB().getTime()) {    //  room for the new swimmer, if
                                    bestInClassHelper = bestInClass;                    //  (s)he's faster
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

        // Prints the top 5 to the console
        for (Swimmer competitor:bestInClass) {
            System.out.println(competitor);
        }
    }

    // This method is called in the menu. It lets the trainers add competition results
    //AUTHOR(S): ECS
    public void addCompetitionResult() {
        SwimStyle style = null;
        int placement;
        double time;

        // Prints all the competition swimmers to the console
        for (Swimmer swimmer:swimmers) {
            if (swimmer.getMembershipType() == MembershipType.COMPETITION_JUNIOR ||
                    swimmer.getMembershipType() == MembershipType.COMPETITION_SENIOR) {
                System.out.println(swimmer);
            }
        }

        System.out.println("Vælg en svømmer via ID#");
        try {
            intSelector = scanner.nextInt();
        } catch (InputMismatchException iME) {          // Makes sure it doesn't crash when input isn't an int
            System.out.println("Invlid input");
            return;
        } finally {
            scanner.nextLine();         // Workaround for the nextInt() bug
        }

        // Puts selected swimmer in temporary variable
        for (Swimmer swimmer:swimmers) {
            if (intSelector == swimmer.getId()) {
                selectedSwimmer = swimmer;
            }
        }

        System.out.println("Hvilket stævne er resultatet fra?");
        String competition = scanner.nextLine();

        System.out.println("Vælg en stilart\n" +
                "[1] Butterfly\n" +
                "[2] Crawl\n" +
                "[3] Rygsvømning\n" +
                "[4] Brystsvømning\n" +
                "[5] Hundesvømning");
        try {
            intSelector = scanner.nextInt();
        } catch (InputMismatchException iME) {          // Makes sure it doesn't crash when input isn't an int
            System.out.println("Invalid input");
            return;
        } finally {
            scanner.nextLine();         // Workaround for the nextInt() bug
        }
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

        System.out.println("Hvilken placering fik svømmeren?");
        try {
            placement = scanner.nextInt();
        } catch (InputMismatchException iME) {          // Makes sure it doesn't crash when input isn't an int
            System.out.println("Invalid input");
            return;
        } finally {
            scanner.nextLine();         // Workaround for the nextInt() bug
        }

        System.out.println("Hvilken tid blev sat?");
        try {
            time = scanner.nextDouble();
        } catch (InputMismatchException iME) {          // Makes sure it doesn't crash when input isn't an int
            System.out.println("Invalid input");
            return;
        } finally {
            scanner.nextLine();         // Workaround for the nextInt() bug
        }

        selectedSwimmer.getCompetitionResults().add(new CompetitionResult(time,style,competition,placement));
        fh.writeToFile(swimmers,"swimmers.txt");
    }

    // This method is called in the menu. It prints a list of the swimmers, who haven't paid, to the console
    //AUTHOR(S): ECS
    public void listArrears() {
        System.out.println("Disse svømmere er i restance:");
        for (Swimmer swimmer:swimmers) {
            if (!swimmer.isHasPaid()) {
                System.out.println(" - ID#" + swimmer.getId() + " " + swimmer.getName() + ", " + swimmer.getAddress() +
                        ", " + swimmer.getAge() + " år");
            }
        }
        System.out.println();
    }

    // This method is called in the menu. It lets the cashiers edit arrears status of the swimmers
    //AUTHOR(S): ECS, CPS
    public void editArrears() {
        System.out.println("Vælg en svømmer via ID#");
        try {
            intSelector = scanner.nextInt();
        } catch (InputMismatchException iME) {          // Makes sure it doesn't crash when input isn't an int
            System.out.println("Invalid input");
            return;
        } finally {
            scanner.nextLine();         // Workaround for the nextInt() bug
        }

        // Executes the above selection
        for (Swimmer swimmer:swimmers) {
            if (intSelector == swimmer.getId() && !swimmer.isHasPaid()) {
                System.out.println("Har " + swimmer.getName() + " betalt sit kontingent? (y/N)");
                strSelector = scanner.nextLine();
                // Defaults to not updating hasPaid, as this action is irreversible
                switch (strSelector) {
                    case "y":
                        swimmer.setHasPaid(true);
                        System.out.println(swimmer.getName() + " er ikke længere i restance");
                        break;

                    default:
                        break;
                }
            }
        }
        fh.writeToFile(swimmers,"swimmers.txt");
    }

    // Prints the arrayList of all swimmers to the console in a prettier way
    //AUTHOR(S): ECS
    public void printSwimmers() {
        for (Swimmer swimmer:swimmers) {
            System.out.println(swimmer);
        }
    }

    // This method is called when the program is started. It updates age, membership type and arrears status of swimmers
    //AUTHOR(S): ECS
    public void updateSwimmer() {
        for (Swimmer swimmer:swimmers) {
            int oldAge = swimmer.getAge();          // Variable for checking if anything happened
            swimmer.setAge(LocalDate.now().getYear() - swimmer.getBIRTH_YEAR());            // Updates age

            // If
            if (oldAge != swimmer.getAge()) {           // Makes sure that it only executes if age was changed
                swimmer.setHasPaid(false);          // Resets arrears

                // Updates membership type if relevant
                if (oldAge < 18 && swimmer.getAge() >= 18) {
                    if (swimmer.getMembershipType() == MembershipType.COMPETITION_JUNIOR) {
                        swimmer.setMembershipType(MembershipType.COMPETITION_SENIOR);
                        swimmer.setPrice(1600);
                        System.out.println("OBS!\n" +
                                swimmer.getName() + "er fyld 18, og er blevet ændret til senior!\n");
                    } else if (swimmer.getMembershipType() == MembershipType.EXERCISE_JUNIOR) {
                        swimmer.setMembershipType(MembershipType.EXERCISE_SENIOR);
                        swimmer.setPrice(1600);
                        System.out.println("OBS!\n" +
                                swimmer.getName() + "er fyld 18, og er blevet ændret til senior!\n");
                    }
                } else if (oldAge < 60 && swimmer.getAge() >= 60) {
                    swimmer.setPrice(swimmer.getPrice() * 0.75);
                    System.out.println("OBS!\n" +
                            swimmer.getName() + "har fået tildelt aldersrabat\n");
                }
            }
        }
        fh.writeToFile(swimmers,"swimmers.txt");
    }
}

