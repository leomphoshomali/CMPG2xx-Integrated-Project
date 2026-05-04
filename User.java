import java.util.ArrayList;
public abstract class User
{
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String role;
    //CMPG215
    private String passwordHash;
    private String bio;
    
    public User(String firstName, String lastName, String userId, String role, String gender, String email, String passwordHash){
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.role = role;
        
    }
}