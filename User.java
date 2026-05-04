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
    
    public void setBio(String bio){
        this.bio = bio;
    }
    
    public String getPasswordHash(){
        return passwordHash;
    }
    
    public void setPasswordHasg(String passwordHash){
        this.passwordHash = passwordHash;
    }
    
    public String getBio(){
        return bio;
    }
    
    //setters
    public void setUserId(String userId){
        this.userId = userId;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setLastName(String surname){
        this.lastName = lastName;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    
    
    public void setGender(String gender){
        this.gender = gender;
    }
    
    //215
    
    
    //getters
    public String getUserId(){
        return userId;
    }
    
    public String setFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getEmail(){
        return email;
        
    }
    
    public String getGender(){
        return gender;
    }
    
    public String getRole(){
        return role;
    }
    
    @Override
    public String toString(){
        String out = String.format("Name & Surname: %s\nUserId: %s\nRole: %s\nGender: %s", (firstName + " " + lastName), userId, role, gender);
        
        return out;
    }
}