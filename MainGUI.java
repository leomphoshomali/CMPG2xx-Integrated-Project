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
}