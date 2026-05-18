import java.util.ArrayList;
/**
 * Updated version of Admin An  extends User class and impliments Academic Observer 
 *  
 */
public class Admin extends User implements AcademicObserver
{
    //Fields  

    private ArrayList<User> students;
    private String lastLoginTime;
    private String createdBy;
// Setters
    public void setLastLoginTime(String lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }
     public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }


    //getters
    public ArrayList<User> getAllStudents(){
        addAllStudents();
        return students;
    }
    public String getLastLoginTime()
    {
         return lastLoginTime;
    }
     public String getCreatedBy()
    {
         return createdBy;
    }
    
    
      public String getStudentPerformance(User student){
        User searchStudent = findStudent(student.getUserId());
        String info = "";
        
        if (searchStudent != null){
            ArrayList<StudentAssignment> assignments = ((Student)searchStudent).getAssignments();
            if (assignments != null){
                for (int i = 0; i < assignments.size(); i++){
                    info += "Module: " + assignments.get(i).getModuleCode() + "Mark obtained: " + assignments.get(i).getMarksObtained() + "\n";
                    
                }
            }
            
        }
        
        return info;
    }
    
    
    public User findStudent(String studentId){
        User student = null;
        for (int i = 0; i < students.size(); i++){
            if (students.get(i).getUserId().equals(studentId)){
                student = students.get(i);
            }
        }
        return student;
    }

    public void addAllStudents(){
        UserManager u = new UserManager();
        ArrayList<User> allUsers = u.getUsers();
        students = new ArrayList<>();
        
        ArrayList<Module> studentModules = new ArrayList<>();
        for (int i = 0; i < allUsers.size(); i++){
            User student = allUsers.get(i);
            
            if (student instanceof Student){
                students.add(student);
            }
        }
         
    }
}
