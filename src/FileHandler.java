import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public void writeToFile(ArrayList<Swimmer> swimmers, String path) {
        // Create it by creating a File file to a FileOutputStream then to a OutputStreamWriter
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

        // Stream write the text to the file

        // Remember to close the Stream
    }

    public ArrayList<Swimmer> readFromFile(String path) {
        ArrayList<Swimmer> tempSwimmers = new ArrayList<>();
        // Create a read stream from a file by a File and FileInputStream
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
