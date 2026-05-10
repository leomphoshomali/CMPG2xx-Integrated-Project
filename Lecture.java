import java.util.ArrayList;
import java.io.Serializable;

public class Lecture extends User implements AcademicObserver, Serializable 
{
    private int yearLevel;
    private String degree;
    private Module module;
    private String faculty;
    private ArrayList<User>students;
    private String title;

    public Lecture(String firstName, String lastName, String userid, String role, String gender, String email, String password)
    {
        super(firstName, lastName, userid, role, gender, email, password);
    }

    public int getYearLevel()
    {
        return yearLevel;
    }

    public void setYearLevel(int yearLevel)
    {
        this.yearLevel = yearLevel;
    }

    public String getDegree()
    {
        return degree;
    }

    public void setDegree(String degree)
    {
        this.degree = degree;
    }

    public String getFaculty()
    {
        return faculty;
    }

    public void setFaculty(String faculty)
    {
        this.faculty = faculty;
    }

    public Module getTeachingModule()
    {
        return module;
    }

    public void setTeachingModule(Module module)
    {
       this.module = module; 
    }

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
        String out = " ";
        for (int i = 0; i < students.size(); i++)
            {
                if (students.get(i).getUserId().equals(student.getUserId()))
                {
                    for (int k = 0; k < student.getEnrolledModules().size(); k++)
                        {
                            Module module = student.getEnrolledModules().get(k);

                            out += "Module: " + module.getModuleCode() + "\n";
                            out += "Module Name: " + module.getModuleName() + "\n";
                        }
                }
            }
            return out;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public Student findStudent(String studentId)
    {
        return ((Student)students.get(0));
    }

    public void sendAnnouncement(String title, String contentWords)
    {
        String moduleCode = module.getModuleCode();
        String instructor = super.getFirstName() + " " + super.getLastName();
        Announcement a = new Announcement(title, contentWords, moduleCode, instructor);
        GUIManager gui = new GUIManager();
        gui.addAnnouncement(a);
    }

    @Override
    public String toString()
    {
        return super.toString();
    }

}
