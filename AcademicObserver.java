import java.util.ArrayList;

public interface AcademicObserver
{
    public ArrayList<User> getAllStudents();
    public String getStudentPerformance(Student student);
    public User findStudent(String studentId);
}