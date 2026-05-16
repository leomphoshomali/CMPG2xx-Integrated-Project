import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class MainGUI extends Application
{
    private Stage primaryStage;
    private TextField txtfFirstName;
    private TextField txtfLastName;
    private TextField txtfEmail;
    private TextField txtfPassword;
    private TextArea txtaBio;
    private ComboBox<String> cbxGender;
    private ComboBox<String> cbxRole;
    
    private User user;
    private GUIManager guiManager = new GUIManager();
    private UserManager userManager = new UserManager();
    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        
        initializeTextFields();
        showLoginPage();
    }
    
    private void initializeTextFields(){
        txtfFirstName = new TextField();
        txtfLastName = new TextField();
        txtfEmail = new TextField();
        txtfPassword = new TextField();
        txtaBio = new TextArea();
        txtaBio.setText("Hello there! I am using StudyEazzy!");
    }
    
    private void showLoginPage(){
        GridPane pane = new GridPane();
        initializeTextFields();
        Scene scene = new Scene(pane);
        
        Label lblTitle = new Label("Login Page");
        Label lblEmail = new Label("Email");
        Label lblPassword = new Label("Password");
        Label lblForgotPassword = new Label("Forgot password?");
        
        Button btnLogin = new Button("Login");
        Button btnRegister = new Button("Register instead");
        Button btnResetPassword = new Button("Reset password");
        
        Button btnAdmin = new Button("Admin login");
        btnAdmin.setOnAction(this::handleAdminLogin);
        pane.setHgap(15);
        pane.setVgap(15);
        btnRegister.setOnAction(this::handleShowSignUp);
        
        btnLogin.setOnAction(this::handleLoginSubmission);
         //***
        pane.setStyle("-fx-padding: 20px;");//
        pane.add(lblTitle, 1, 0);
        pane.add(lblEmail, 0, 1);
        pane.add(txtfEmail, 2, 1);
        pane.add(lblPassword, 0, 2);
        pane.add(txtfPassword, 2, 2);
        pane.add(btnLogin, 3, 3);
        pane.add(lblForgotPassword, 3, 4);
        pane.add(btnResetPassword, 4, 4);
        pane.add(btnRegister, 3, 5);
        pane.add(btnAdmin, 4, 5);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleShowLogin(ActionEvent event){
        showLoginPage();
    }
    
    private void handleAdminLogin(ActionEvent event){
        showAdminLoginPage();
    }
    private void showAdminLoginPage(){
        
    }
    private void handleShowSignUp(ActionEvent e){
        showRegistrationPage();
    }
    //private void handleAdminLogin
    
    private void handleLoginSubmission(ActionEvent event){
        String email = txtfEmail.getText();
        String password = txtfPassword.getText();
        
        boolean validateEmail = guiManager.isInputValid(email);
        boolean validatePass = guiManager.isInputValid(password);
        
        if (!validateEmail || !validatePass){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in the missing inputs");
            alert.showAndWait();
        }
        
        User submit = userManager.validateUserSubmission(email, password);
        
        if (submit == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Email or password doesn’t match any account.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Login successful! You will now be redirected to the home page.");
            alert.showAndWait();
            
            user = submit;
            
            handlePageRole();
        }
    }
    
    private void initializeComboBoxes(){
        cbxRole = guiManager.configureRoleComboBox();
        cbxGender = guiManager.configureGenderComboBox();
    }
    
    private void handleUserSubmission(ActionEvent e){
        try{
            String name = txtfFirstName.getText();
            String surname = txtfLastName.getText();
            String role = cbxRole.getValue();
            String gender = cbxGender.getValue();
            String email = txtfEmail.getText();
            String password = txtfPassword.getText();
            String bio = txtaBio.getText();
            
            //first make sure all inputs have been entered
            ArrayList<String> data = new ArrayList<>();
            data.add(name);
            data.add(surname);
            data.add(role);
            data.add(gender);
            data.add(email);
            data.add(password);
            
            
            boolean validate = false;
            
            for (int i = 0; i < data.size(); i++){
                validate = guiManager.isInputValid(data.get(i));
                if (!validate){
                    throw new InvalidModuleSelectionException("Please fill in the missing inputs");
                }
            }
            
            if (txtfEmail.getText().contains("@") && txtfEmail.getText().contains(".") || txtfEmail.getText().length() < 5){
                throw new UserNotFoundException("Invalid email");
            }
            //if sucessful
            
            
            user = guiManager.createUser(name, surname, role, email, password, gender);
            user.setBio(bio);
            //user.setPasswordHash(password);
            
            
            showModuleSubmissionPage();
        }catch (InvalidModuleSelectionException cie){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(cie.getMessage());
            alert.showAndWait();
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    
    private void showModuleSubmissionPage(){
        if (user instanceof Student){
            StudentGUI studentGUI = new StudentGUI(user);
            studentGUI.start(primaryStage);
        } else {
            LectureGUI lectureGUI = new LectureGUI(user);
            lectureGUI.start(primaryStage);
        }
    }
    
    private void handlePageRole(){
        if (user instanceof Student){
            StudentGUI studentGUI = new StudentGUI(user);
            studentGUI.handleLogin(primaryStage);
        } else {
            LectureGUI lectureGUI = new LectureGUI(user);
            lectureGUI.handleLogin(primaryStage);
        }
    }
    
    private void handleShowSignUp(ActionEvent e){
        showRegistrationPage();
    }
    
    private void showPasswordResetPage(){
        //
    }
    
    private void handlePasswordReset(ActionEvent e){
        
    }
    
    private void showRegistrationPage(){
        
        
        initializeTextFields();
        GridPane mainLayout = new GridPane();
        
        initializeComboBoxes();
        //labels
        Label lblPageTitle = new Label("Register Page");
        Label lblName = new Label("Name");
        
        Label lblSurname = new Label("Surname");
        
        Label lblEmail = new Label("Email");
        
        Label lblPassword = new Label("Password");
        
        Label lblBio = new Label("Add a bio (optional)");
        
        //buttons
        Button btnLogin = new Button("Log in instead");
        
        Button btnSubmit = new Button("Submit");
        
        btnLogin.setOnAction(this::handleShowLogin);
        btnSubmit.setOnAction(this::handleUserSubmission);
        
        
        mainLayout.add(lblPageTitle, 1, 0);
        mainLayout.add(lblName, 0, 1);
        mainLayout.add(txtfFirstName, 0, 2);
        mainLayout.add(lblSurname, 0, 3);
        mainLayout.add(txtfLastName, 0, 4);
        
        mainLayout.add(cbxGender, 0, 5);
        mainLayout.add(lblEmail, 0, 6);
        mainLayout.add(txtfEmail, 0, 7);
        mainLayout.add(lblPassword, 0, 8);
        mainLayout.add(txtfPassword, 0, 9);
        mainLayout.add(cbxRole, 0, 10);
        mainLayout.add(lblBio, 0, 11);
        mainLayout.add(txtaBio, 0, 12, 3, 3);
        mainLayout.add(btnLogin, 5, 13);
        mainLayout.add(btnSubmit, 6, 13);
        
        mainLayout.setVgap(15);
        mainLayout.setHgap(15);
        mainLayout.setStyle("-fx-padding:20px;");
        
        Scene scene = new Scene(mainLayout);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}