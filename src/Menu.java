import java.util.ArrayList;
import java.util.Scanner;

public class Menu
{
    private int selecter;
    private boolean isRunning = true;
    private Scanner scanner = new Scanner(System.in);
    private UserContainer userContainer = new UserContainer();
    private User user = null;
    Container container = new Container();



    public void mainMenu()
    {
        while (isRunning)
        {

            System.out.println("#Velkommen til Svømmeklubben Delfinens Administationssystem#");
            System.out.println("Indtast Brugernavn:");
            String validUserName = scanner.nextLine();
            System.out.println("Indtast kodeord:");
            String validUserPass = scanner.nextLine();
                try
                {
                    user = userLogin(validUserName, validUserPass);
                }
                catch (IllegalArgumentException eIA)
                {
                    System.out.println("Forkert bruger eller pass");
                    continue;
                }

                if (user.getUserRole() == UserRole.FOREMAN)
                {
                    makeForemanMenu();
                }



        }
    }
    //returnerer kunde hvis korrekt, returnerer null hvis forkert
    public User userLogin (String username, String userpass)
    {
        ArrayList<User> users = userContainer.getUsers();
        for (int i = 0; i < users.size() ; i++)
        {

            User tempUser = users.get(i);

            if (username.equals(tempUser.getUserLoginName()) && userpass.equals(tempUser.getUserPass()))
            {
                System.out.println(username);
                System.out.println(userpass);
                return users.get(i);
            }
        }
        throw new IllegalArgumentException();
    }

    public void makeForemanMenu ()
    {
        if (user.getUserRole() == UserRole.FOREMAN)
        {
            while (isRunning)
            {

                System.out.println("This is the Foreman menu");
                System.out.println("Velkommen " + user.getName());
                System.out.println("1. Opret medlem");
                System.out.println("2. Rediger medlem");

                selecter = scanner.nextInt();
                switch (selecter)
                {
                    case 1:
                        System.out.println("Opret medlem");
                        container.newSwimmer();
                        break;

                    case 2:
                        System.out.println("Rediger medlem");
                        break;

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
            System.out.println("This is the Cashir menu");
        }
    }

    public void makeTrainerMenu ()
    {
        if (user.getUserRole() == UserRole.TRAINER)
        {
            System.out.println("This is the Trainer menu");
        }

    }


}