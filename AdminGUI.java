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
    private UserManager userManager = new UserManager();
    
    private User user;
    
    private DatePicker dateOpened = new DatePicker();
    private DatePicker dueDate = new DatePicker();
    
    //private Label l = new Label("test");
    private LocalDate taskDueDate;
    private GridPane taskPane = new GridPane();
    private ScrollPane taskScrollPane = new ScrollPane(taskPane);
    private Label lblQuestion = new Label();
    private Label lblAnswer = new Label();
    private ArrayList<User> allUsers;
    private ArrayList<User> allStudents;
    //private ArrayList<ComboBox> cbxAssignment = new ArrayList<>();
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        showHomePage();
    }
    
    public AdminGUI(User user){
        this.user = user;
        allUsers = userManager.getUsers();
        allStudents = ((Admin)user).getAllStudents();
        
    }
    
    private void showHomePage(){
        GridPane mainGridPane = new GridPane();
        ScrollPane mainScrollPane = new ScrollPane(mainGridPane);
        VBox vboxUsers = new VBox();
        ScrollPane usersScrollPane = new ScrollPane(vboxUsers);
        VBox vboxStudents = new VBox();
        ScrollPane studentsScrollPane = new ScrollPane(vboxStudents);
        
        Scene scene = new Scene(mainScrollPane);
        
        String padding = """
            -fx-padding: 20px;
        """;
        mainGridPane.setStyle(padding);
        mainScrollPane.setStyle(padding);
        vboxUsers.setStyle("-fx-padding: 7px");
        vboxStudents.setStyle("-fx-padding: 7px");
        
        int rowUsers = 3;
        int colUsers = 0;
        int rowStudents = 3;
        int colStudents = 4;
        Button btnSortUsers = new Button("Sort users");
        btnSortUsers.setOnAction(this::handleSortUsers);
        
        Button btnSortStudents = new Button("Sort students");
        btnSortStudents.setOnAction(this::handleSortStudents);
        
        Button btnManagerUsers = new Button("Manage users");
        Button btnLogout = new Button("Logout");
        Button btnExit = new Button("Exit");
        
        btnExit.setOnAction(this::handleExit);
        btnLogout.setOnAction(this::handleLogoutPage);
        btnManagerUsers.setOnAction(this::handleManageUsers);
        
        mainGridPane.add(btnManagerUsers, 0, 3);
        mainGridPane.add(btnLogout, 1, 3);
        mainGridPane.add(btnExit, 2, 3);
        
        Image imgSadFace = new Image(getClass().getResourceAsStream("images\\no_task_sad_face.png"));
        ImageView imgView = new ImageView(imgSadFace);

        
        if (allUsers != null){
            for (int i = 0; i < allUsers.size(); i++){
                String displayUser = allUsers.get(i).getFirstName() + allUsers.get(i).getLastName() + " - " + allUsers.get(i).getUserId();
                Label lblDisplayUser = new Label(displayUser);
                vboxUsers.getChildren().add(lblDisplayUser);
            }
        } else {
            vboxUsers.getChildren().add(imgView);
        }
        
        if (allStudents != null){
            for (int i = 0; i < allStudents.size() && allStudents != null; i++){
                String displayUser = allUsers.get(i).getFirstName() + allUsers.get(i).getLastName() + " - " + allUsers.get(i).getUserId();
                Label lblDisplayUser = new Label(displayUser);
                vboxStudents.getChildren().add(lblDisplayUser);
            }
        } else {
            vboxStudents.getChildren().add(imgView);
        }
        
        mainGridPane.add(btnSortUsers, 0, 3);
        mainGridPane.add(btnSortStudents, 4, 3);
        mainGridPane.add(usersScrollPane, 0, 4);
        mainGridPane.add(studentsScrollPane, 4, 4);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleExit(ActionEvent event){
        guiManager.saveUser(user);
        System.exit(0);
    }
    
    private void handleManageUsers(ActionEvent event){
        
    }
    
    private void handleLogoutPage(ActionEvent event){
        guiManager.saveUser(user);
        MainGUI mainGui = new MainGUI();
        mainGui.start(primaryStage);
        
        
    }
    
    private void handleSortUsers(ActionEvent event){
        
    }
    
    private void handleSortStudents(ActionEvent event){
        
    }
    
    private void displayAllUsers(){
        
    }
}