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
//        container.swimmers = container.fh.readFromFile("swimmers.txt");
        while (isRunning) {

            System.out.println("#Velkommen til Svømmeklubben Delfinens Administationssystem#");
            System.out.println("Indtast Brugernavn:");
            String validUserName = scanner.nextLine();
            System.out.println("Indtast kodeord:");
            String validUserPass = scanner.nextLine();
                try {
                    user = userLogin(validUserName, validUserPass);
                }
                catch (IllegalArgumentException eIA)
                {
                    System.out.println("Forkert bruger eller pass");
                    continue;
                }
                    makeForemanMenu();
                    makeCashirMenu();




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
        if (user.getUserRole() == UserRole.FOREMAN) {
            while (isRunning) {

                System.out.println("This is the Foreman menu");
                System.out.println("Velkommen " + user.getName());
                System.out.println("1. Opret medlem");
                System.out.println("2. Rediger medlem");
                System.out.println("3. Se medlemmer");
                System.out.println("4. Tilføj Træningsresultat");
                System.out.println("5. Tilføj Stævne");

                selector = scanner.nextInt();
                switch (selector)
                {
                    case 1:
                        System.out.println("Opret medlem");
                        container.newSwimmer();
                        break;

                    case 2:
                        System.out.println("Rediger medlem");
                        break;

                    case 3:
                        System.out.println("Se medlemmer");
                        System.out.println(container.swimmers);
                        break;

                    case 4:
                        System.out.println("Tilføj Træning");
                        break;

                    case 5:
                        System.out.println("Tilføj Stævne");

                    default:
                        isRunning = false;
                        break;

                }
            }
        }

    }



    public void makeCashirMenu()
    {
        if (user.getUserRole() == UserRole.CASSIR)
        {
            while (isRunning)
            {
                System.out.println("This is the Cashir menu");
                System.out.println("Velkommen " + user.getName());
                System.out.println("1. Se Restance");
                System.out.println("2. Se medlemmer");

                selector = scanner.nextInt();
                switch (selector)
                {
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
    }

    public void makeTrainerMenu ()
    {
        if (user.getUserRole() == UserRole.TRAINER)
        {
            System.out.println("This is the Trainer menu");
            System.out.println();

                selector = scanner.nextInt();
                while (isRunning)
                {

                }
        }

    }


}