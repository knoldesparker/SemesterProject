import java.util.ArrayList;
import java.util.Scanner;

public class Menu
{
    private int selecter;
    private boolean isRunning = true;
    private Scanner scanner = new Scanner(System.in);
    private UserContainer userContainer = new UserContainer();
    private User user = null;



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
            } catch (IllegalArgumentException eIA) {
                System.out.println("Forkert bruger eller pass");
                continue;
            }
            if (user != null)
            {
                selecter = 1;
            }else
            {
                continue;
            }

            switch (selecter)
            {
                case 0:

                    break;

                case 1:
                    System.out.println("Velkommen " + user.getName());
                    isRunning = false;
                    break;


            }


        }
    }
    //returnerer kunde hvis korrekt, returnerer null hvis forkert
    public User userLogin (String username, String userpass)
    {
        ArrayList<User> users = userContainer.getUsers();
        for (int i = 0; i < users.size() ; i++)
        {
            // siger mit Kunde object er mit kunde array
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


}