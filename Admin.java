import java.util.ArrayList;
/**
 * An Admin Class for that extends User class
 * Creates and Deletes accounts for users of the App 
 */

public class Admin extends User
{
    //Fields  
    private int adminLevel;
    private ArrayList <String> permission;
    private String lastLoginTime;
    private String createdBy;

    //Default construct 
    public Admin()
    {
        ArrayList <String> Permission;
    }
    
    //Parametised Constructor 
    public Admin(String firstName, String lastName, String userId, String role, String gender, String email, String password,int adminLevel, String lastLoginTime,String createdBy)
    {
        super(firstName, lastName, userId, role, gender, email, password);
        setAdminLevel(adminLevel);
        setLastLoginTime(lastLoginTime);
        setCreatedBy(createdBy);
        //
    }
    
    // Setters 
    
    public void setAdminLevel(int adminLevel)
    {
        this.adminLevel = adminLevel;
    }
    public void setLastLoginTime(String lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }
     public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }
    public void setPermission(ArrayList <String> permission)
    {
        this.permission = permission;
    }
    
    //Getters
    public int getAdminLevel()
    {
         return adminLevel;
    }
    public String getLastLoginTime()
    {
         return lastLoginTime;
    }
     public String getCreatedBy()
    {
         return createdBy;
    }
    public ArrayList <String> getPermission()
    {
        return permission;
    }
}