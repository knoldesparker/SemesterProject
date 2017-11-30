import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private int selector;
    private boolean isRunning = true;
    private Scanner scanner = new Scanner(System.in);
    private UserContainer userContainer = new UserContainer();
    private User user = null;
    Container container = new Container();

    public void mainMenu() {
        container.swimmers = container.fh.readFromFile("swimmers.txt");
        container.updateSwimmerAge();
        System.out.println("#Velkommen til Svømmeklubben Delfinens Administationssystem#");
        while (isRunning) {
            System.out.println("Indtast Brugernavn:");
            String validUserName = scanner.nextLine();
            System.out.println("Indtast kodeord:");
            String validUserPass = scanner.nextLine();
            try {
                user = userLogin(validUserName, validUserPass);
            }
            catch (IllegalArgumentException eIA) {
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
        }
    }
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

    public void makeForemanMenu () {
        while (isRunning) {
            System.out.println("This is the Foreman menu\n" +
                    "Velkommen " + user.getName() + "\n" +
                    "[1] Opret medlem\n" +
                    "[2] Rediger medlem\n" +
                    "[3] Se medlemmer\n" +
                    "[4] Tilføj Træningsresultat\n" +
                    "[5] Tilføj Stævne\n" +
                    "[6] Log ud");

            selector = scanner.nextInt();
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
                    for (Swimmer swimmer:container.swimmers) {
                        System.out.println(swimmer);
                    }
                    break;

                case 4:
                    System.out.println("Tilføj Træning");
                    break;

                case 5:
                    System.out.println("Tilføj Stævne");

                case 6:
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    public void makeCashierMenu() {
        while (isRunning) {
            System.out.println("This is the Cashir menu\n" +
                    "Velkommen " + user.getName() + "\n" +
                    "[1] Se Restance\n" +
                    "[2] Se medlemmer");

            selector = scanner.nextInt();
            switch (selector) {
                case 1:
                    System.out.println("Se Restance");
                    break;

                case 2:
                    System.out.println("Se medlemmer");
                    break;

                default:
                    isRunning = false;
                    break;
            }
        }

    }

    public void makeTrainerMenu () {
        System.out.println("This is the Trainer menu");
        System.out.println();

        selector = scanner.nextInt();
        while (isRunning) {

        }
    }
}