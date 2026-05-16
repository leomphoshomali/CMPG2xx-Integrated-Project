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
    public FileService(){
        
    }
    
    public ArrayList<User> getUsers(String fileName){
        ArrayList<User> users = new ArrayList<>();
        try{
            
            
            boolean endOfFile = false;
            try {
                ObjectInputStream read = new ObjectInputStream(new FileInputStream(fileName));
                
                
                
                while (!endOfFile){
                    users.add(((User)read.readObject()));
                }
            } catch(EOFException eof){
                endOfFile = true;
            }
            
            
        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        
        return users;
    }
    
    public void saveUsersToFile(ArrayList<User> users, String fileName){
        
        
        try{
            if (fileName == null || fileName.isEmpty()){
                throw new FileNotFoundException("The file ('" + fileName + "' could not be found.");
            }
            
            ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(fileName));
            
            
            for (int i = 0; i < users.size(); i++){
                write.writeObject(users.get(i));
            }
            
            
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro saving files: " + e.getMessage());
            alert.showAndWait();
        }
    }
}


