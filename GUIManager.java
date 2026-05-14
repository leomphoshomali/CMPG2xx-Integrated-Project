import javafx.scene.control.ComboBox;
import java.util.ArrayList;
public class GUIManager
{
    private ArrayList<Announcement> announcements = new ArrayList<>();
    UserManager userManager = new UserManager();
    ModuleManager moduleManager = new ModuleManager();
    public boolean isInputValid(String in){
        if (in == null){
            return false;
        } 
        if (in.trim().equals("")){
            return false;
        } if (in.equalsIgnoreCase("Select your role") || in.equalsIgnoreCase("Select your gender")) {
            return false;
        }
        if (in.equalsIgnoreCase("Select your year level") || in.equalsIgnoreCase("Select your faculty")) {
            return false;
        }
        return true;
    }
    
    
    public ComboBox<String> configureRoleComboBox(){
        ComboBox<String> m = new ComboBox<>();
        m.setValue("Select your role");
        m.getItems().addAll("Student", "Lecturer");
        return m;
    }
    
    public ComboBox<String> configureGenderComboBox(){
        ComboBox<String> m = new ComboBox<>();
        m.setValue("Select your gender");
        m.getItems().addAll("Male", "Female", "Other");
        return m;
    }
    
    public ComboBox<String> configureTitleComboBox(){
        
        ComboBox<String> m = new ComboBox<>();
        m.setValue("Select your title");
        m.getItems().add("Doctor");
        m.getItems().add("Lecturer");
        m.getItems().add("Professor");
        m.getItems().add("Other");
        return m;
    }
    
    public ComboBox<String> configureYearLevelComboBox(){
        ComboBox<String> m  = new ComboBox<>();
        m.getItems().clear();
        m.setValue("Select your year level");
        m.getItems().add("1");
        m.getItems().add("2");
        m.getItems().add("3");
        m.getItems().add("4");
        return m;
    }

  public ComboBox<String> populateModuleComboBox(){
        
        ComboBox<String> m = new ComboBox<>();
        m.setValue("Select your module");
        
        for (int i = 0; i < moduleManager.getModules().size(); i++){
            
            m.getItems().add(moduleManager.getModules().get(i).getModuleName());
        }
        
        return m;
        
        
    }
    
    // public ComboBox<String> populateModuleComboBox(Student u){
        
        // ComboBox<String> m = new ComboBox<>();
        // m.setValue("Select your module");
        
        // for (int i = 0; i < modulem.getModules().size(); i++){
            // String level = " (Year " + modulem.getModules().get(i).getYearLevel() + ") ";
            // String qualify = "";
            // //modulem.getModules().get(i).getFaculty().equals(u.getFaculty()) && u.getYearLevel() == modulem.getModules().get(i).getYearLevel()
            // if (modulem.getModules().get(i).getFaculty().equals(u.getFaculty()) && u.getYearLevel() == modulem.getModules().get(i).getYearLevel()){
                // qualify = "- QUALIFY";
            // } else {
                // qualify = "- NOT QUALIFY";
            // }
            // m.getItems().add(modulem.getModules().get(i).getModuleName() + level + qualify);
        // }
        
        // return m;
        
        
    // }
    
    public ComboBox<String> configureFacultyComboBox(){
        ComboBox<String> f = new ComboBox<>();
        f.setValue("Select your faculty");
        
        f.getItems().addAll("School of Mathematical Sciences and Statistics", 
        "Faculty of Natural and Agricultural Sciences", "Faculty of Humanities", 
        "Faculty of Commerce", "Faculty of Health Sciences");
        return f;
    }
    
    public User registerUserModules(User u, ArrayList<String> userModules) throws InvalidModuleSelectionException {
        ArrayList<Module> modules = moduleManager.getModules();
        for (int i = 0; i < userModules.size(); i++){
            
            if (!isInputValid(userModules.get(i))){
                throw new InvalidModuleSelectionException("Please fill in the missing modules");
            }
            
            for (int k = 0; k < modules.size(); k++){
                Module module = modules.get(k);
                
                if (userModules.get(i).equals(module.getModuleName())){
                        ((Student)u).addEnrolledModule(module);
                    }
                } 
            }
            // for (int k = 0; k < modules.size(); k++){
                // Module module = modules.get(k);
                
                // if (userModules.get(i).equalsIgnoreCase(module.getModuleName()) && u.getFaculty().equalsIgnoreCase(module.getFaculty())){
                    // if (u instanceof Student){
                        // if (u.getYearLevel() == module.getYearLevel()){
                            // u.addEnrolledModule(module);
                        // } else {
                            // throw new CustomInputException("The modules you've entered are not on your yearbook.");
                        // }
                    // } else {
                        // u.addEnrolledModule(module);
                    // }
                // } else {
                    // throw new CustomInputException("The modules you've entered are not part of your faculty (" + u.getFaculty() + "). Please enter the correct modules.");
                // }
            // }
            return u;
        }
        
        
    
    public User createUser(String name, String surname, String role, String email, String password, String gender){
        String userId = userManager.generateUserId(role);
        User u;
        if (role.equalsIgnoreCase("Student")){
            u = new Student(name, surname, userId, role, gender, email, password);
            
        } else {
            u = new Lecture(name, surname, userId, role, gender, email, password);
        }
        return u;
    }
    
    // ComboBox<String> cbxTasks = new ComboBox<>();
    // ComboBox<String> cbxProfile = new ComboBox<>();
    // ComboBox<String> cbxModules = new ComboBox<>();
    // ComboBox<String> cbxHelp = new ComboBox<>();
    // ComboBox<String> cbxNotifications = new ComboBox<>();
    // ComboBox<String> cbxLogout = new ComboBox<>();
    
    public void addAnnouncement(Announcement a){
        announcements.add(a);
    }
    
    public void findAvailableAnnouncements(User s){
        ArrayList<Module> module = ((Student)s).getEnrolledModules();
        for (int i = 0; i < announcements.size(); i++){
            if (announcements.get(i).getModuleCode().equals(module.get(i).getModuleCode())){
                ((Student)s).addAnnouncement(announcements.get(i));
            }
        }
    }
    
    public ComboBox<String> configureTasksComboBox(User s){
        ComboBox<String> m = new ComboBox<>();
        m.setValue("Tasks");
        if (s instanceof Student){
            m.getItems().addAll("Pending tasks", "Completed tasks");
        } else {
            m.getItems().addAll("Add new task", "Uploaded tasks");
        }
            
        return m;
    }
}
