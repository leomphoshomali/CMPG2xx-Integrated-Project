import java.util.Random;
import java.util.ArrayList;
import java.io.Serializable;
import org.mindrot.jbcrypt.BCrypt; //Added this for BCrypt
import javafx.scene.control.Alert;


public class UserManager implements Serializable
{
    private static ArrayList<User> users = new ArrayList<>();
    private FileService files = new FileService();
    private String userFileName = "files\\Users.ser";
    
    //Fields for tracking 
    private User currentUser;
    
    private static final int MAX_ATTEMPTS = 5;
    private static final long LOCK_TIME = 15 * 60 * 1000; // 15 minutes
    
    private static int failedAttempts = 0;
    private static long lockoutEndTime = 0;
    
    public UserManager(){
        
    }
    
    public int getFailedAttemps(){
        return failedAttempts;
        
    }
    
    public int getMAX_ATTEMPTS(){
        return MAX_ATTEMPTS;
    }
    
    public long getLOCK_TIME(){
        return LOCK_TIME;
    }
    
    public void loadUsers()
    {
        users = files.getUsers(userFileName);

        
        if (users.isEmpty())
        {
            //Used Bcrypt Salting 
            
        
            GUIManager guiManager = new GUIManager();
            users.add(guiManager.createUser("John", "Doe", "Admin", "john.doe@gmail.com", "Secure@1234Pass", "Male"));
            
        }
    }
    
    public void saveUsers()
    {
        try 
        {

            files.saveUsersToFile(users, userFileName);
            

        } 
        catch (Exception e) 
        {
            System.out.println("Save error: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Save error: " + e.getMessage());
            alert.showAndWait();
        }
    }
    
    public ArrayList<User> getUsers()
    {
        return users;
    }
    
    public void addUser(User u)
    {
        // ONLY ADMIN CAN ADD USERS
        // if (currentUser == null || !currentUser.getRole().equalsIgnoreCase("Admin"))
        // {
            // System.out.println("Access denied: Admin only");
            // return;
        // }

        users.add(u);
     }
    
    public boolean removeUser(String userId)
    {
        // RBAC CHECK (ONLY ADMIN CAN REMOVE USERS)
        // currentUser = user;
        // if (currentUser == null || !currentUser.getRole().equalsIgnoreCase("Admin"))
        // {
            // System.out.println("Access denied: Admin only");
            // return false;
        // }

        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getUserId().equalsIgnoreCase(userId)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public String generateUserId(String role)
    {
        Random r = new Random();
        String id = "";
        
        if (role.equalsIgnoreCase("Student"))
        {
            id += "S-";
        }
        else if (role.equalsIgnoreCase("Lecture"))
        {
            id += "E-";
        } 
        else
        {
            id += "A-";
        }
        
        for (int i = 0; i< 8; i++)
        {
            id += r.nextInt(9 - 1 + 1) + 1;
        }
        
        return id;
    }
    
    //Addedtracking variables 
    public boolean isAccountLocked()
    {
        if (System.currentTimeMillis() < lockoutEndTime) 
        {
            return true;
        }
        return false;
    }
    
    //Added lock Check Method 
    private void resetLockIfNeeded()
    {
        if (lockoutEndTime != 0 && System.currentTimeMillis() >= lockoutEndTime) 
        {
            failedAttempts = 0;
            lockoutEndTime = 0;
        }
    }

    
    User validateUserSubmission(String userId, String password)
    {
    resetLockIfNeeded();

    if (isAccountLocked())
    {
        Logger.log(userId, "LOGIN BLOCKED - ACCOUNT LOCKED");
        return null;
    }

    for (int i = 0; i < users.size(); i++)
    {
        User user = users.get(i);

        // FIRST check matching ID
        if (user.getUserId().equalsIgnoreCase(userId))
        {
            // THEN check password

            if (BCrypt.checkpw(password, user.getPasswordHash()))
            {
                failedAttempts = 0;

                Logger.log(userId, "LOGIN SUCCESS");

                return user;
            }
            else
                {
                    failedAttempts++;
                    
                    Logger.log(userId, "LOGIN FAILED - WRONG PASSWORD");
                
                    if (failedAttempts >= MAX_ATTEMPTS)
                    {
                        lockoutEndTime = System.currentTimeMillis() + LOCK_TIME;
                        Logger.log(userId, "ACCOUNT LOCKED");
                    }
                
                    return null;
                }
        }
    }

    Logger.log(userId, "LOGIN FAILED - USER NOT FOUND");

    return null;
    }
    
    public User login(String userId, String password)
    {
        User user = validateUserSubmission(userId, password);
    
        if (user != null)
        {
            currentUser = user;
        }
    
        return user;
     }   
    
}