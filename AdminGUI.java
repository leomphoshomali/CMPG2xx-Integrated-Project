import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
public class AdminGUI extends Application
{
    private Stage primaryStage;
    private TextField txtfFirstName;
    private TextField txtfLastName;
    private TextField txtfEmail;
    private TextField txtfPassword;
        
    private ComboBox<String> cbxFaculty;
    private GUIManager guiManager = new GUIManager();
    
    
    private User user;
    
    private DatePicker dateOpened = new DatePicker();
    private DatePicker dueDate = new DatePicker();
    
    //private Label l = new Label("test");
    private LocalDate taskDueDate;
    private GridPane taskPane = new GridPane();
    private ScrollPane taskScrollPane = new ScrollPane(taskPane);
    private Label lblQuestion = new Label();
    private Label lblAnswer = new Label();
    //private ArrayList<ComboBox> cbxAssignment = new ArrayList<>();
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
    
    public AdminGUI(User user){
        this.user = user;
    }
    
    private void showHomePage(){
        GridPane pane = new GridPane();
        ScrollPane scrollPane = new ScrollPane(pane);
        
        Scene scene = new Scene(scrollPane);
        
        
    }
}