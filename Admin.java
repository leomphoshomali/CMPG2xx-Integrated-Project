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

    public ArrayList<User> getAllStudents()
    {
        UserManager u = new UserManager();
        ArrayList<User> allUsers = u.getUsers();
        students = new ArrayList<>();
        for (int i = 0; i < allUsers.size(); i++)
        {
            if (allUsers.get(i) instanceof Student)
            {
                User student = allUsers.get(i);
                students.add(student);
            }
        }
        return students;
    }
    
    public String getStudentPerformance(Student student)
    {
        String out = "";
        for (int i =0; i < students.size(); i++){
            if (students.get(i).getUserId().equals(student.getUserId()))
            {
                for (int k = 0; k < student.getEnrolledModules().size(); k++)
                {
                    
                }
            }
        }
        return out;
    }  
    
    public Student findStudent(String studentId)
    {
        return ((Student)students.get(0));
    }
}