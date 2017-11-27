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
        users.add(new User("1234","1234", "dumdum", UserRole.FOREMAN));
        users.add(new User("134","1234", "dumdum", UserRole.FOREMAN));
        users.add(new User("12346","1234", "dumdum", UserRole.FOREMAN));
        users.add(new User("123","1234", "dumdum", UserRole.FOREMAN));
        users.add(new User("12345","1234", "dumdum", UserRole.FOREMAN));
    }
}
