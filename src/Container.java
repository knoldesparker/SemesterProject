import java.util.ArrayList;
import java.util.Scanner;

public class Container
{
    ArrayList<Swimmer> swimmers = new ArrayList<>();


    public void newSwimmer()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Skriv venligst dit navn: ");
        String name = scanner.nextLine();
        System.out.println("Skriv venligst din adresse: ");
        String address = scanner.nextLine();
        System.out.println("Skriv venligst din alder: ");
        int age = scanner.nextInt();
        System.out.println("Vælg venligst hvorvidt du ønsker [1]aktivt eller [2]passivt medlemskab: ");
        int answer = scanner.nextInt();
        MembershipType membershipType = null;
        switch (answer)
        {
            case 1:
                if (age < 18)
                {
                    membershipType = MembershipType.ACTIVE_JUNIOR;
                }
                else if (age >= 60)
                {
                    membershipType = MembershipType.ACTIVE_PENSIONER;
                }
                else
                {
                    membershipType = MembershipType.ACTIVE_SENIOR;
                }
                break;

            case 2:
                if (age >= 60)
                {
                    membershipType = MembershipType.PASSIVE_PENSIONER;
                }
                else
                {
                    membershipType = MembershipType.PASSIVE;
                }
                break;
        }
        swimmers.add(new Swimmer(name, address, age, membershipType));
        System.out.println(swimmers);

    }



}

