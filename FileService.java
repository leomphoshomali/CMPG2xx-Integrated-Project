import java.io.IOException;
import java.io.Serializable;
import java.io.FileNotFoundException;
import javafx.scene.control.Alert;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class FileService implements Serializable
{
    public FileService()
    {
        
    }
    
    public ArrayList<User> getUsers(String fileName)
    {
        return null;
    }
    
    public void saveUsersToFile(ArrayList<User> users, String fileName)
    {
        
    }
}
