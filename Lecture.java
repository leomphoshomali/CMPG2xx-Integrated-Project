import java.Util.ArrayList;
import java.io.Serializable;

public class Lecture extends User implements AcademicObserver, Serializable 
{
    Private int yearLevel;
    private String degree;
    private Module module;
    private String faculty;
    private ArrayList<User>students;
    private String tittle;

    public Lecture(String firstName, String lastName, String userid, String role, String gender, String email, String password)
    {
        super(firstName, lastName, userid, role, gender, email, password);
    }
}
