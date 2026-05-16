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
import javafx.scene.shape.Line;
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
    private TextField txtfSearch;
    //private ArrayList<ComboBox> cbxAssignment = new ArrayList<>();
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        allUsers = userManager.getUsers();
        allStudents = ((Admin)user).getAllStudents();
        showHomePage();
    }
    
    public AdminGUI(User user){
        this.user = user;
        
        
    }
    
    private void handleShowHomePage(ActionEvent event){
        showHomePage();
    }
    
    private void showHomePage(){
        
        
        
        GridPane mainGridPane = new GridPane();
        VBox mainPane = new VBox();
        Image headerImage = new Image(getClass().getResourceAsStream("images\\StudyEazzy - Admin.png"));
        ImageView imgViewHeader = new ImageView(headerImage);
        mainPane.getChildren().add(imgViewHeader);
        mainPane.getChildren().add(mainGridPane);
        ScrollPane mainScrollPane = new ScrollPane(mainPane);
        VBox vboxUsers = new VBox();
        ScrollPane usersScrollPane = new ScrollPane(vboxUsers);
        VBox vboxStudents = new VBox();
        ScrollPane studentsScrollPane = new ScrollPane(vboxStudents);
        
        Scene scene = new Scene(mainScrollPane, 700, 800);
        
        Label lblSearch = new Label("Search for user: ");
        Button btnSearch = new Button("Search");
        txtfSearch = new TextField();
        
        HBox hboxSearch = new HBox();
        hboxSearch.getChildren().addAll(lblSearch, txtfSearch, btnSearch);
        mainGridPane.add(hboxSearch, 0, 4);
        btnSearch.setOnAction(this::handleSearch);
        
        String padding = """
            -fx-padding: 20px;
        """;
        mainPane.setStyle(padding);
        mainGridPane.setStyle(padding);
        //mainScrollPane.setStyle(padding);
        vboxUsers.setStyle("-fx-padding: 7px");
        vboxStudents.setStyle("-fx-padding: 7px");
        mainGridPane.setHgap(15);
        mainGridPane.setVgap(15);
        
        
        
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
        HBox hboxButtons = new HBox();
        hboxButtons.setSpacing(10);
        hboxButtons.getChildren().addAll(btnManagerUsers, btnLogout, btnExit);
        String buttonStyle = """
        -fx-background-color: red;
            -fx-text-fill: white;
            -fx-padding: 5px 10px;
            -fx-background-radius: 12px;
            -fx-border-radius: 12px;
            -fx-border-color: black;
            -fx-border-width: 1px;
        """;
        
        btnExit.setStyle(buttonStyle);
        btnLogout.setStyle(buttonStyle);
        btnManagerUsers.setStyle(buttonStyle);
        
        
        mainGridPane.add(hboxButtons, 0, 3);
        
        mainGridPane.setStyle("-fx-background-color:gray");
        
        Image imgSadFace = new Image(getClass().getResourceAsStream("images\\no_task_sad_face.png"));
        ImageView imgView = new ImageView(imgSadFace);

        
        if (allUsers != null){
            for (int i = 0; i < allUsers.size(); i++){
                String displayUser = allUsers.get(i).getFirstName() + " " + allUsers.get(i).getLastName() + " - " + allUsers.get(i).getUserId();
                Label lblDisplayUser = new Label(displayUser);
                
                lblDisplayUser.setFont(javafx.scene.text.Font.font("Verdana", 15));
                vboxUsers.getChildren().add(lblDisplayUser);
            }
        } else {
            vboxUsers.getChildren().add(imgView);
        }
        
        if (allStudents != null){
            for (int i = 0; i < allStudents.size() && allStudents != null; i++){
                String displayUser = allStudents.get(i).getFirstName() + " " + allStudents.get(i).getLastName() + " - " + allStudents.get(i).getUserId();
                Label lblDisplayUser = new Label(displayUser);
                lblDisplayUser.setFont(javafx.scene.text.Font.font("Verdana", 15));
                vboxStudents.getChildren().add(lblDisplayUser);
            }
        } else {
            vboxStudents.getChildren().add(imgView);
        }
        
        mainGridPane.add(btnSortUsers, 0, 6);
        mainGridPane.add(btnSortStudents, 4, 6);
        mainGridPane.add(usersScrollPane, 0, 7);
        mainGridPane.add(studentsScrollPane, 4, 7);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleSearch(ActionEvent event){
        String search = txtfSearch.getText();
        if (search == null || search == ""){
            return;
        }
        ArrayList<User> users = guiManager.searchUser(search);
        VBox pane = new VBox();
        ScrollPane scrollPane = new ScrollPane(pane);
        Scene scene = new Scene(scrollPane);
        
        Image headerImage = new Image(getClass().getResourceAsStream("images\\StudyEazzy - Admin.png"));
        ImageView imgViewHeader = new ImageView(headerImage);
        
        pane.getChildren().add(imgViewHeader);
        
        Button btnReturn = new Button("Return");
        btnReturn.setOnAction(this::handleShowHomePage);
        pane.getChildren().add(btnReturn);
        
        String padding = """
            -fx-padding: 20px;
        """;
        
        String buttonStyle = """
        -fx-background-color: red;
            -fx-text-fill: white;
            -fx-padding: 5px 10px;
            -fx-background-radius: 12px;
            -fx-border-radius: 12px;
            -fx-border-color: black;
            -fx-border-width: 1px;
        """;
        
        Image imgSadFace = new Image(getClass().getResourceAsStream("images\\no_task_sad_face.png"));
        ImageView imgView = new ImageView(imgSadFace);
        
        if (users.isEmpty()){
            pane.getChildren().add(imgView);
        } else {
            for (int i = 0; i < users.size(); i++){
                HBox hboxProfile = new HBox();
                Image imgProfile = new Image(getClass().getResourceAsStream("male_pfp.jpg"));
                ImageView imgViewPfp = new ImageView(imgProfile);
                Label lblBio = new Label(user.getBio());
                Label lblBioTitle = new Label("Bio: ");
                Label lblFaculty = new Label(users.get(i).getFaculty());
                Label lblFacultyTitle = new Label("Faculty: ");
                Label lblUserID = new Label(users.get(i).getUserId());
                Label lblUserIDTitle = new Label("UserID: "); 
                Label lblGender = new Label(users.get(i).getGender());
                Label lblGenderTitle = new Label("Gender: ");
                Label lblEmail = new Label(users.get(i).getEmail());
                Label lblEmailTitle = new Label("Email: ");
                
                lblBio.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
                lblFaculty.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
                lblUserID.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
                lblEmail.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
                lblGender.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
            }
        }
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
        
        for (int i = 0; i < allUsers.size()-1 && allUsers != null; i++){
            for (int k = 0; k < allUsers.size()-i-1; k++){
                if (allUsers.get(k).compareTo(allUsers.get(k+1)) > 0){
                    User temp = allUsers.get(k);
                    allUsers.set(k, allUsers.get(k+1));
                    allUsers.set(k+1, temp);
                }
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("List was sorted");
        alert.showAndWait();
        showHomePage();
    }
    
    private void handleSortStudents(ActionEvent event){
        for (int i = 0; i < allStudents.size()-1 && allStudents != null; i++){
            for (int k = 0; k < allStudents.size()-i-1; k++){
                if (allStudents.get(k).compareTo(allStudents.get(k+1)) > 0){
                    User temp = allStudents.get(k);
                    allStudents.set(k, allStudents.get(k+1));
                    allStudents.set(k+1, temp);
                }
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("List was sorted");
        alert.showAndWait();
        showHomePage();
    }
    
    private void displayAllUsers(){
        
    }
}