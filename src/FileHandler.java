import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    //AUTHOR(S): ECS, CPS; (THJ)
    //We copied the main structure from a code example on Fronter, but edited it to fit our program
    public void writeToFile(ArrayList<Swimmer> swimmers, String path) {
        try {
            File file = new File(path);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(swimmers);
            oos.close();
        }
        catch (FileNotFoundException eFNF) {
            eFNF.printStackTrace();
        }
        catch (IOException eIO) {
            eIO.printStackTrace();
        }
    }

    //AUTHOR(S): ECS, CPS; (THJ)
    //We copied the main structure from a code example on Fronter, but edited it to fit our program
    public ArrayList<Swimmer> readFromFile(String path) {
        ArrayList<Swimmer> tempSwimmers = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);


            tempSwimmers = (ArrayList<Swimmer>) ois.readObject();
            ois.close();
        }
        catch (FileNotFoundException eFNF) {
            System.out.println("Idiot");
        }
        catch (IOException eIO) {
            System.out.println("idiot 2");
            eIO.printStackTrace();
        }catch (ClassNotFoundException eCNF){
            System.out.println("3");
        }

        return tempSwimmers;
    }
}
