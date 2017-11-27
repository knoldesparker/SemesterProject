import java.util.ArrayList;

public class UserContainer
{
    private ArrayList<User> users = new ArrayList<>();

    public UserContainer()
    {
        initializeUsers();
    }

    public ArrayList<User> getUsers()
    {
        return users;
    }

    private void initializeUsers(){
        users.add(new User("forman","1234", "Freja", UserRole.FOREMAN));
        users.add(new User("brit","1234", "Brit", UserRole.CASSIR));
        users.add(new User("else","1234", "Else", UserRole.CASSIR));
        users.add(new User("lars","1234", "Lars", UserRole.TRAINER));
        users.add(new User("john","1234", "John", UserRole.TRAINER));
    }
}
