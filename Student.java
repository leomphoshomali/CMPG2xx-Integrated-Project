import java.util.ArrayList;

public class Student extends User {
    private int yearLevel;
    private String degree;
    private ArrayList<Module> enrolledModules;
    private String faculty;

    public Student(String firstName, String lastName, String userid, String role, String gender, String email, String password) {
        super(firstName, lastName, userid, role, gender, email, password);
        this.enrolledModules = new ArrayList<>();
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public ArrayList<Module> getEnrolledModules() {
        return enrolledModules;
    }

    public void addEnrolledModule(Module module) {
        enrolledModules.add(module);
    }

    public boolean removeEnrolledModule(Module module) {
        return enrolledModules.remove(module);
    }
}
