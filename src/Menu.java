import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private int selector;
    private boolean isRunning = true;
    private boolean isLoggedIn = false;
    private boolean isSelecting = false;
    private Scanner scanner = new Scanner(System.in);
    private UserContainer userContainer = new UserContainer();
    private User user = null;
    Container container = new Container();

    //AUTHOR(S): CPS, ECS
    public void mainMenu() {
        container.updateSwimmer();
        // Splash screen ASCII dolphins copied from ascii.co.uk/art/dolphin
        System.out.println("            __|\\\n" +
                "         .-'    '-.\n" +
                "        / .--, _ a L\n" +
                "      .J (  '-' \"'--'\n" +
                "     '-'-.)  .~~~~~~~~~~~~~~~~~~~~.\n" +
                "             |   Velkommen  til   |     __\n" +
                "             |      Delfinen      | ,.-'e ''-'7\n" +
                "             |     svømmeklub     |  '--.    (\n" +
                "             |                    |      ),   \\\n" +
                "             '~~~~~~~~~~~~~~~~~~~~'      ` )  :\n" +
                "                                      ,__.'_.'\n" +
                "                                      '-, (\n" +
                "                                        '--'");
        while (isRunning) {
            System.out.println("[1] Log ind\n" +
                    "[2] Luk programmet");
            try {
                selector = scanner.nextInt();
            } catch (InputMismatchException iME) {
            }
            scanner.nextLine();
            switch (selector) {
                case 1:
                    System.out.println("Indtast Brugernavn:");
                    String validUserName = scanner.nextLine();
                    System.out.println("Indtast kodeord:");
                    String validUserPass = scanner.nextLine();
                    try {
                        user = userLogin(validUserName, validUserPass);
                    } catch (IllegalArgumentException eIA) {
                        System.out.println("Forkert brugernavn eller password");
                        continue;
                    }
                    if (user.getUserRole() == UserRole.FOREMAN) {
                        makeForemanMenu();
                    } else if (user.getUserRole() == UserRole.CASHIER) {
                        makeCashierMenu();
                    } else if (user.getUserRole() == UserRole.TRAINER) {
                        makeTrainerMenu();
                    }
                    break;

                case 2:
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    //AUTHOR(S): CPS
    //returnerer kunde hvis korrekt, returnerer null hvis forkert
    public User userLogin (String username, String userpass) {
        ArrayList<User> users = userContainer.getUsers();
        for (int i = 0; i < users.size() ; i++) {

            User tempUser = users.get(i);

            if (username.equals(tempUser.getUserLoginName()) && userpass.equals(tempUser.getUserPass())) {
                System.out.println(username);
                System.out.println(userpass);
                return users.get(i);
            }
        }
        throw new IllegalArgumentException();
    }

    //AUTHOR(S): CPS, ECS
    public void makeForemanMenu () {
        isLoggedIn = true;
        while (isLoggedIn) {
            System.out.println("This is the Foreman menu\n" +
                    "Velkommen " + user.getName() + "\n" +
                    "[1] Opret medlem\n" +
                    "[2] Rediger medlem\n" +
                    "[3] Se medlemmer\n" +
                    "[4] Log ud\n" +
                    "[5] Log ud og luk programmet");

            try {
            selector = scanner.nextInt();
            } catch (InputMismatchException iME) {
                selector = -1;
            }
            scanner.nextLine();
            switch (selector) {
                case 1:
                    System.out.println("Opret medlem");
                    container.newSwimmer();
                    break;

                case 2:
                    System.out.println("Rediger medlem");
                    container.editSwimmer();
                    break;

                case 3:
                    System.out.println("Se medlemmer");
                    container.printSwimmers();
                    break;

                case 4:
                    isLoggedIn = false;
                    break;

                case 5:
                    isLoggedIn = false;
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    //AUTHOR(S): CPS, ECS
    public void makeCashierMenu() {
        isLoggedIn = true;
        while (isLoggedIn) {
            System.out.println("This is the cashier menu\n" +
                    "Velkommen " + user.getName() + "\n" +
                    "[1] Ret restance\n" +
                    "[2] Se restance\n" +
                    "[3] Log ud\n" +
                    "[4] Log ud og luk programmet");

            try {
            selector = scanner.nextInt();
            } catch (InputMismatchException iME) {
                selector = -1;
            }
            scanner.nextLine();
            switch (selector) {
                case 1:
                    System.out.println("Ret restance");
                    container.listArrears();
                    container.editArrears();
                    break;

                case 2:
                    System.out.println("Se restance");
                    container.listArrears();
                    break;

                case 3:
                    isLoggedIn = false;
                    break;

                case 4:
                    isLoggedIn = false;
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }

    }

    //AUTHOR(S): CPS, ECS
    public void makeTrainerMenu () {
        isLoggedIn = true;
        while (isLoggedIn) {
            System.out.println("This is the Trainer menu\n" +
                    "Velkommen " + user.getName() + "\n" +
                    "[1] Tilføj nye træningsresultater\n" +
                    "[2] Se liste over bedste svømmere\n" +
                    "[3] Tilføj stævneresultat\n" +
                    "[4] Se Stævneresultater\n" +
                    "[5] Log ud\n" +
                    "[6] Log ud og luk programmet");

            try {
            selector = scanner.nextInt();
            } catch (InputMismatchException iME) {
                selector = -1;
            }
            scanner.nextLine();
            switch (selector) {
                case 1:
                    container.addTrainingResults();
                    break;

                case 2:
                    MembershipType ageGroupSelector = null;

                    isSelecting = true;
                    while (isSelecting) {
                        System.out.println("Vælg en aldersgruppe:\n" +
                                "[1] Junior\n" +
                                "[2] senior");
                        try {
                            selector = scanner.nextInt();
                        } catch (InputMismatchException iME) {
                            selector = -1;
                        }
                        scanner.nextLine();
                        switch (selector) {
                            case 1:
                                ageGroupSelector = MembershipType.COMPETITION_JUNIOR;
                                isSelecting = false;
                                break;

                            case 2:
                                ageGroupSelector = MembershipType.COMPETITION_SENIOR;
                                isSelecting = false;
                                break;

                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                    }

                    isSelecting = true;
                    while (isSelecting) {
                        System.out.println("Vælg en svømmestil:\n" +
                                "[1] Butterfly\n" +
                                "[2] Crawl\n" +
                                "[3] Rygsvømning\n" +
                                "[4] Brystsvømning\n" +
                                "[5] Hundesvømning");
                        try {
                            selector = scanner.nextInt();
                        } catch (InputMismatchException iME) {
                            selector = -1;
                        }
                        scanner.nextLine();

                        switch (selector) {
                            case 1:
                                container.listBestSwimmers(ageGroupSelector, SwimStyle.BUTTERFLY);
                                isSelecting = false;
                                break;

                            case 2:
                                container.listBestSwimmers(ageGroupSelector, SwimStyle.CRAWL);
                                isSelecting = false;
                                break;

                            case 3:
                                container.listBestSwimmers(ageGroupSelector, SwimStyle.BACKSTROKE);
                                isSelecting = false;
                                break;

                            case 4:
                                container.listBestSwimmers(ageGroupSelector, SwimStyle.BREASTSTROKE);
                                isSelecting = false;
                                break;

                            case 5:
                                container.listBestSwimmers(ageGroupSelector, SwimStyle.DOG_PADDLE);
                                isSelecting = false;
                                break;

                            default:
                                System.out.println("Invalid Input");
                        }
                    }
                    break;

                case 3:
                    container.addCompetitionResult();
                    break;

                case 4:
                    for (Swimmer swimmer:container.swimmers) {
                        if ((swimmer.getMembershipType() == MembershipType.COMPETITION_JUNIOR ||
                                swimmer.getMembershipType() == MembershipType.COMPETITION_SENIOR) &&
                                !swimmer.getCompetitionResults().isEmpty()) {
                            System.out.println(swimmer.getName() + " - " + swimmer.getCompetitionResults());
                        }
                    }
                    break;

                case 5:
                    isLoggedIn = false;
                    break;

                case 6:
                    isLoggedIn = false;
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}