import java.util.ArrayList;
import java.io.Serializable;
public abstract class User implements Serializable, Comparable<User>
{
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String role;
    private String faculty;
    //CMPG215
    private String passwordHash;
    private String bio;
     //add the firstName
    public User(String firstName, String lastName, String userId, String role, String gender, String email, String passwordHash){
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.role = role;
        this.passwordHash = passwordHash;
        
    }
      public void setFaculty(String faculty){
        this.faculty = faculty;
    }
     public void setRole(String role){
        this.role = role;
    }
    public String getFaculty(){
        return faculty;
    }
    public void setBio(String bio){
        this.bio = bio;
    }
    
    public String getPasswordHash(){
        return passwordHash;
    }
      public void setRole(String role){
        this.role = role;
    }
    
    
    public void setPasswordHash(String passwordHash){
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
    public String setFirstName(){
        return firstName;
    }
    
    //215
    
    
    //getters
    public String getUserId(){
        return userId;
    }
    
    public String getFirstName(){
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
    public int compareTo(User other) {
    
        String fullName = firstName + " " + lastName;
        String otherFullName = other.getFirstName() +  " " + other.getLastName();
        
        int compare = fullName.compareTo(otherFullName);
        return compare;
    }
    
    @Override
    public String toString(){
        //String out = String.format("Name & Surname: %s\n\n\nUserID: %s\n\n\nRole: %s\n\n\nGender: %s\n\n\nBio: %s\n\n\nFaculty:%s", (firstName.toLowerCase() + " " + lastName.toLowerCase()), userId.toLowerCase(), role.toLowerCase(), gender.toLowerCase(), bio.toLowerCase(), faculty.toLowerCase());
        String out = firstName + lastName + userId + faculty + role + gender + bio;
        out = out.toLowerCase();
        return out;
    }
}
