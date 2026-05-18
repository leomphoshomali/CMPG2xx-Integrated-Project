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
    
    public ArrayList<User> getUsers(String fileName) {

    ArrayList<User> users = new ArrayList<>();

    try (ObjectInputStream read =
             new ObjectInputStream(new FileInputStream(fileName))) {

        while (true) {
            try {
                User u = (User) read.readObject();

                // DECRYPT sensitive data AFTER loading
                if (u.getEmail() != null) {
                    u.setEmail(AuthSystem.decrypt(u.getEmail()));
                }

                users.add(u);
                

            } catch (EOFException eof) {
                break; // end of file reached
            }
        }

    } catch (FileNotFoundException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error: " + e.getMessage());
        alert.showAndWait();
    } catch (IOException e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error: " + e.getMessage());
        alert.showAndWait();
    } catch (Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error: " + e.getMessage());
        alert.showAndWait();
    }

    return users;
    }
  
    public void saveUsersToFile(ArrayList<User> users, String fileName){
        
        
        try{
            if (fileName == null || fileName.isEmpty())
            {
                throw new FileNotFoundException("The file ('" + fileName + "' could not be found.");
            }
            
            ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(fileName));
            
            for (User u : users) 
            {
                // Save original email temporarily
                String originalEmail = u.getEmail();
            
                // Encrypt ONLY for file storage
                if (originalEmail != null) 
                {
                    u.setEmail(AuthSystem.encrypt(originalEmail));
                }
            
                // Write encrypted object to file
                write.writeObject(u);
            
                // Restore original email in memory
                u.setEmail(originalEmail);
            }
            write.close();
            
            //CheckSum
            String checksum = ChecksumUtil.generateChecksum(fileName);
            System.out.println("Users file checksum: " + checksum);
            
        }
        catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }
    
    public void saveModulesToFile(ArrayList<Module> modules, String fileName)
    {
        
        
        try{
            
            if (fileName == null || fileName.isEmpty()){
                throw new FileNotFoundException("The file ('" + fileName + "' could not be found.");
            }
            
            ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(fileName));
            
            for (int i = 0; i < modules.size(); i++)
            {
                write.writeObject(modules.get(i));
            }
            
            //Closed the file 
            write.close();
            
            //CheckSum
            String checksum = ChecksumUtil.generateChecksum(fileName);
            System.out.println("Modules file checksum: " + checksum);
        }
        catch (FileNotFoundException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error: " + e.getMessage());
        alert.showAndWait();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }
    
    public ArrayList<Module> getModules(String fileName) {

    ArrayList<Module> modules = new ArrayList<>();

    try (ObjectInputStream read =
             new ObjectInputStream(new FileInputStream(fileName))) {

        while (true) {
            try {
                Module m = (Module) read.readObject();
                modules.add(m);

            } catch (EOFException eof) {
                break;
            }
        }
        
        

        } catch (FileNotFoundException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error: " + e.getMessage());
        alert.showAndWait();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
        
        return modules;
    
    }
 } 
