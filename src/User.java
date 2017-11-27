public class User
{
    private String userLoginName;
    private String userPass;
    private String name;
    private UserRole userRole;


    public User(String userLoginName, String userPass, String name, UserRole userRole)
    {
        this.userLoginName = userLoginName;
        this.userPass = userPass;
        this.name = name;
        this.userRole = userRole;
    }


    public String getUserLoginName()
    {
        return this.userLoginName;
    }

    public String getUserPass()
    {
        return this.userPass;
    }

    public String getName()
    {
        return this.name;
    }

    public UserRole getUserRole()
    {
        return this.userRole;
    }
}

