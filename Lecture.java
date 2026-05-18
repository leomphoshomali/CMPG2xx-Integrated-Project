import java.util.ArrayList;
import java.io.Serializable;
import javafx.scene.control.Alert;

public class Lecture extends User implements AcademicObserver, Serializable 
{
    private int yearLevel;
    private String degree;
    private Module module;
    
    private ArrayList<User> students = new ArrayList<>();;
    private String title;
    private UserManager userManager = new UserManager();

    public Lecture(String firstName, String lastName, String userid, String role, String gender, String email, String password)
    {
        super(firstName, lastName, userid, role, gender, email, password);
        module = new Module();
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


    public Module getTeachingModule()
    {
        return module;
    }

    public void setTeachingModule(Module module)
    {
       this.module = module; 
    }

    public ArrayList<User> getAllStudents(){
        return students;
    }
     public void addAllStudents(){
        UserManager u = new UserManager();
        ArrayList<User> allUsers = u.getUsers();
        students = new ArrayList<>();
        
        ArrayList<Module> studentModules = new ArrayList<>();
        for (int i = 0; i < allUsers.size(); i++){
            User student = allUsers.get(i);
            
            if (student instanceof Student){
                studentModules = ((Student)allUsers.get(i)).getEnrolledModules();
                for (int k = 0; k < studentModules.size(); k++){
                    if (studentModules.get(k).getModuleCode().equals(module.getModuleCode())){
                        students.add(student);
                    }
                }
            }
        }
         
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

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
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


    public void sendAnnouncement(Announcement a){
        String moduleCode = module.getModuleCode();
        String instructor = super.getFirstName() + " " + super.getLastName(); 
        
        //GUIManager gui = new GUIManager();
        //gui.addAnnouncement(a);
        
        //userManager.loadUsers();
        ArrayList<User> users = userManager.getUsers();
        try{
            if (!users.isEmpty()){
                for (int i = 0; i < users.size(); i++){
                //((Student)users.get(i)).addAnnuncement(a);
                User user = users.get(i);
                if (user instanceof Student){
                    ArrayList<Module> modules = ((Student)user).getEnrolledModules();
                    
                    for (int k = 0; k < modules.size(); k++){
                        if (modules.get(k).getModuleCode().equals(module.getModuleCode())){
                            ((Student)user).addAnnuncement(a);
                        }
                    }
                }
                }
                userManager.saveUsers();
            }
        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        
    }
    

    @Override
    public String toString()
    {
        return super.toString();
    }

}
