import javafx.scene.control.ComboBox;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import java.io.Serializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GUIManager implements Serializable
{
    //private static ArrayList<Announcement> announcements = new ArrayList<>();
    private UserManager userManager = new UserManager();
    private ModuleManager moduleManager = new ModuleManager();
    //private FileService files = new FileService();
    
    
    //private String userFileName = "C:\\Users\\mphos\\OneDrive\\Desktop\\CMPG2XX Integrated Project\\Final Project\\CMPG2xx-Integrated-Project\\Users.ser";
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
    
    public void assignStudentAssignment(ArrayList<String> questions, ArrayList<String> answers, ArrayList<Double> marks, User lecture, double maxMarks, String title, String dueDate, String dateOpened){
        ArrayList<User> students = new ArrayList<>();
        ((Lecture)lecture).addAllStudents();
        students = ((Lecture)lecture).getAllStudents();
        
        
        if (!students.isEmpty()){
            for (int i = 0; i < students.size(); i++){
                
                //ArrayList<Module> modules = ((Student)students.get(i)).getEnrolledModules();
                
                StudentAssignment assignment = new StudentAssignment(title, ((Lecture)lecture).getTeachingModule(), maxMarks, dueDate, dateOpened);
                for (int j = 0; j < questions.size(); j++){
                    assignment.addQuestionAnswer(questions.get(j), answers.get(j), marks.get(j));
                }
                ((Student)students.get(i)).addAssignment(assignment);
                students.get(i).setBio("This is just a demo");
                saveUser(students.get(i));
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Array is empty");
            a.showAndWait();
        }
    }
    
    public ComboBox<String> configureRoleComboBox(){
        ComboBox<String> m = new ComboBox<>();
        m.setValue("Select your role");
        m.getItems().addAll("Student", "Lecture");
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
        m.getItems().add("Lecture");
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
    
    // ComboBox<String> cbxTasks = new ComboBox<>();
    // ComboBox<String> cbxProfile = new ComboBox<>();
    // ComboBox<String> cbxModules = new ComboBox<>();
    // ComboBox<String> cbxHelp = new ComboBox<>();
    // ComboBox<String> cbxNotifications = new ComboBox<>();
    // ComboBox<String> cbxLogout = new ComboBox<>();
    
    
    
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
    public ComboBox<String> configureProfileComboBox(){
        ComboBox<String> m = new ComboBox<>();
        m.setValue("Profile");
        m.getItems().addAll("View profile", "Edit profile");
        return m;
    }
    public ComboBox<String> configureModulesComboBox(){
        ComboBox<String> m = new ComboBox<>();
        m.setValue("Modules");
        
        m.getItems().addAll("View modules", "Edit modules");
        return m;
    }
    
    // public void addAnnouncement(Announcement a){
        // announcements.add(a);
    // }
    
    public User createUser(String name, String surname, String role, String email, String password, String gender){
        String userId = userManager.generateUserId(role);
        User u;
        if (role.equalsIgnoreCase("Student")){
            
            u = new Student(name, surname, userId, role, gender, email, password);
            
        } else {
            u = new Lecture(name, surname, userId, role, gender, email, password);
            
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Your new UserID is ('" + userId + "').");
        alert.showAndWait();
        
        return u;
        
    }
    
    // public User setStudentAnnouncements(User s){
        // ArrayList<Module> module = ((Student)s).getEnrolledModules();
        
        // for (int i = 0; i < module.size(); i++){
            // for (int k = 0; k < announcements.size(); k++){
                // if (announcements.get(k).getModuleCode().equals(module.get(i).getModuleCode())){
                    // ((Student)s).addAnnuncement(announcements.get(k));
                // }
            // }
        // }
        // return s;
        
    // }
    public ComboBox<String> configureNotificationsComboBox(User s){
        ComboBox<String> m = new ComboBox<>();
        m.getItems().clear();
        m.setValue("Notifications");
        if (s instanceof Student){
            
            ArrayList<Announcement> studentAnnouncements = ((Student)s).getAnnouncements();
            if (studentAnnouncements.isEmpty()){
                        m.getItems().add("No announcements to see");
            } else {
                for (int i = studentAnnouncements.size() - 1; i >= 0; i--){
                    String announcementText = studentAnnouncements.get(i).toString();
            
                    if (!m.getItems().contains(announcementText)) {
                        m.getItems().add(announcementText);
                    }
                    
                }
            }
                
        } else {
            m.getItems().addAll("Add an announcement");
        }
        
        return m;
    }
    public ComboBox<String> configureHelpComboBox(){
        ComboBox<String> m = new ComboBox<>();
        m.setValue("Help");
        m.getItems().addAll("Ask for help");
        return m;
    }
    public ComboBox<String> configureLogoutComboBox(){
        ComboBox<String> m = new ComboBox<>();
        m.setValue("Logout");
        m.getItems().addAll("Logout");
        return m;
    }
    
    public ComboBox<String> configureSettingsComboBox(){
        ComboBox<String> m = new ComboBox<>();
        m.setValue("Settings");
        
        m.getItems().addAll("Change password", "Delete account");
        
        return m;
    }
    
    public User getSelectedModule(User user, String module){
        
        ArrayList<Module> modules = moduleManager.getModules();
        Module userModule;
        for (int i = 0; i < modules.size(); i++){
            if (module.equals(modules.get(i).getModuleName())){
                userModule = modules.get(i);
                ((Lecture)user).setTeachingModule(userModule);
                modules.get(i).setInstructor(user.getFirstName() + user.getLastName());
                break;
            }
        }
        
        return user;
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
            
            
            
        //u = setStudentAnnouncements(u);
            
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
        
    public void saveUser(User u){
        ArrayList<User> users = userManager.getUsers();
        
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getUserId().equals(u.getUserId())){
                users.set(i, u); 
                userManager.saveUsers();
                
                return;
            } 
        }
        
        // if not found
        userManager.addUser(u);
        userManager.saveUsers();
    }
    
    public boolean validatePassword(String password){
        if (password.length() < 12){
            return false;
        }
        String[] specialCharacters = {"@", "$", "#", "&"};
        boolean found = false;
        for (int i = 0; i < specialCharacters.length; i++){
            if (password.contains(specialCharacters[i])){
                found = true;
                break;
            }
        }
        return false;
    }
    
    public boolean validateEmail(){
        return true;
    }
    
    public ArrayList<User> searchUser(String search){
        String[] searchKeyWords = search.split(" ");
        ArrayList<User> users = new ArrayList<>();
        
        for (int i = 0; i < searchKeyWords.length; i++){
            for (int j = 0; j < userManager.getUsers().size(); j++){
                if (userManager.getUsers().get(j).toString().contains(searchKeyWords[i])){
                    users.add(userManager.getUsers().get(j));
                }
            }
        }
        return users;
    }
    
    
}