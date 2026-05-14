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
}
