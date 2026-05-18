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
import javafx.scene.control.PasswordField;
import org.mindrot.jbcrypt.BCrypt;


public class LectureGUI 
{
    private Stage primaryStage;
    private TextField txtfFirstName = new TextField();
    private TextField txtfLastName = new TextField();
    private TextField txtfEmail = new TextField();
    private PasswordField txtfPassword = new PasswordField();
    private TextField txtfUserId = new TextField();
    private PasswordField txtfNewPassword = new PasswordField();
    private TextField txtfBio = new TextField();
    
    private ComboBox<String> cbxGender = new ComboBox<>();
    private ComboBox<String> cbxFaculty;
    private GUIManager guiManager = new GUIManager();
    private int taskRows = 4;
    private int taskCols = 0;
    private int questionIndex = 0;
    private UserManager userManager = new UserManager();
    
    private User user;
    
    private ComboBox<String> cbxTasks = new ComboBox<>();
    private ComboBox<String> cbxProfile = new ComboBox<>();
    private ComboBox<String> cbxModules = new ComboBox<>();
    private ComboBox<String> cbxHelp = new ComboBox<>();
    private ComboBox<String> cbxNotifications = new ComboBox<>();
    private ComboBox<String> cbxLogout = new ComboBox<>();
    private ComboBox<String> cbxSettings = new ComboBox<>();
    private ComboBox<String> cbxTitles = new ComboBox<>();
    private ComboBox<String> cbxModule = new ComboBox<>();
    
    
    private TextField txtfTitle = new TextField();
    private TextArea txtaContent = new TextArea();
    private TextArea txtaQuestion = new TextArea();
    private TextField txtaAnswer = new TextField();
    
    //private GridPane pane = new GridPane(); //title it modulePane
    private ArrayList<TextArea> txtaQuestions = new ArrayList<>();
    private ArrayList<RadioButton> rbnAnswers = new ArrayList<>();
    private ArrayList<ToggleGroup> group = new ArrayList<>();
    private ArrayList<TextArea> txtaAnswers = new ArrayList<>();
    private ArrayList<Label> lblQuestions = new ArrayList<>();
    private ArrayList<Label> lblAnswers = new ArrayList<>();
    private ArrayList<TextField> txtfMarks = new ArrayList<>();
    
    private DatePicker dateOpened = new DatePicker();
    private DatePicker dueDate = new DatePicker();
    
    //private Label l = new Label("test");
    private LocalDate taskDueDate;
    private GridPane taskPane = new GridPane();
    private ScrollPane taskScrollPane = new ScrollPane(taskPane);
    private Label lblQuestion = new Label();
    private Label lblAnswer = new Label();
    //private ArrayList<ComboBox> cbxAssignment = new ArrayList<>();
    public LectureGUI(User user){
        this.user = user;
    }
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        showModuleSelectionPage();
    }
    
    private void handleAdminHelp(ActionEvent event){
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        Button btnReturn = new Button("Return");
        Button btnSubmit = new Button("Submit");
        
        btnReturn.setOnAction(this::handleShowHomePage);
        
        Label lblDisplay = new Label("Write to admin support");
        pane.setHgap(20);
        pane.setVgap(20);
        TextArea txtaAdminSupport = new TextArea();
        pane.setStyle("-fx-padding:20px");
        pane.add(btnReturn, 0, 0);
        pane.add(lblDisplay, 0, 2);
        pane.add(txtaAdminSupport, 0, 3, 5, 5);
        pane.add(btnSubmit, 5, 8);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    // public void showHomePage(){
        // GridPane pane = new GridPane();
        // Scene scene = new Scene(pane, 1000, 600);
        // initializeComboBoxes();
        // Label title = new Label("STUDYEAZZY");
        // pane.setStyle("-fx-padding:15px;");
        
        // Button btnLogout = new Button("Logout");
        // pane.setHgap(3);
        // pane.setVgap(10);
        // pane.add(title, 0, 0);
        // pane.add(cbxTasks, 0, 4);
        // pane.add(cbxProfile, 1, 4);
        // pane.add(cbxModules, 2, 4);
        // pane.add(cbxNotifications, 3, 4);
        // pane.add(cbxSettings, 4, 4);
        // pane.add(cbxHelp, 5, 4);
        // pane.add(btnLogout, 6, 4);
        
        
        // event handlers
        // cbxProfile.setOnAction(this::handleShowProfile);
        // btnLogout.setOnAction(this::handleShowLogout);
        // cbxNotifications.setOnAction(this::handleNotificationPage);
        // cbxTasks.setOnAction(this::handleTaskPage);
        // primaryStage.setResizable(false);
        // primaryStage.setScene(scene);
        // primaryStage.show();
    // }
    
    private void handleTaskPage(ActionEvent event){
        if (cbxTasks.getValue().equals("Add new task")){
            createTask();
        }
       
        
    }
    
    public void editProfile(){
        GridPane pane = new GridPane();
        ScrollPane scrollPane = new ScrollPane(pane);
        Scene scene = new Scene(scrollPane, 600, 600);
        pane.setVgap(5);
        pane.setHgap(3);
        //ArrayList<Module> studentModules = ((Lecture)user).getEnrolledModules();
        String total = ""; //C:\Users\mphos\OneDrive\Desktop\CMPG2XX Integrated Project\Final Project\CMPG2xx-Integrated-Project\images
        Image profilePic = new Image(getClass().getResourceAsStream("images\\male_pfp.jpg"));
        
        
        if (user.getGender().equals("Female")){
            profilePic = new Image(getClass().getResourceAsStream("images\\female_pfp.jpg"));
        } else {
            profilePic = new Image(getClass().getResourceAsStream("images\\male_pfp.jpg"));
        }
        ImageView imgViewer = new ImageView(profilePic);
        imgViewer.setFitWidth(200);
        imgViewer.setFitHeight(200);
        imgViewer.setPreserveRatio(true);
        Label lblBio = new Label(user.getBio());
        Label lblBioTitle = new Label("Bio: ");
        Label lblFaculty = new Label(user.getFaculty());
        Label lblFacultyTitle = new Label("Faculty: ");
        Label lblUserID = new Label(user.getUserId());
        Label lblUserIDTitle = new Label("UserID: "); 
        Label lblGender = new Label(user.getGender());
        Label lblGenderTitle = new Label("Gender: ");
        Label lblEmail = new Label(user.getEmail());
        Label lblEmailTitle = new Label("Email: ");
        
        Button btnEdit = new Button("Edit");
        
        btnEdit.setOnAction(this::handleEditProfile);
        
        lblBio.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
        lblFaculty.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
        lblUserID.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
        lblEmail.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
        lblGender.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
        pane.add(imgViewer, 2, 3);
        pane.add(lblBioTitle, 2, 4);
        pane.add(lblBio, 3, 4);
        pane.add(lblFacultyTitle, 2, 5);
        pane.add(lblFaculty, 3, 5);
        pane.add(lblUserIDTitle, 2, 6);
        pane.add(lblUserID, 3, 6);
        pane.add(lblGenderTitle, 2, 7);
        pane.add(lblGender, 3, 7);
        pane.add(lblEmailTitle, 2, 8);
        pane.add(lblEmail, 3, 8);
        pane.setStyle("-fx-padding: 20px;");
        pane.add(btnEdit, 5, 9);
        
        
        
        Button btnReturn = new Button("Return");
        btnReturn.setOnAction(this::handleShowHomePage);
        pane.add(btnReturn, 0, 0);
        
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private void handleEditProfile(ActionEvent event){
        GridPane pane = new GridPane();
        
        Scene scene = new Scene(pane);
        Button btnReturn = new Button("Return");
        btnReturn.setOnAction(this::handleShowHomePage);
        
        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(this::handleEditProfileSubmission);
        cbxGender = guiManager.configureGenderComboBox();
        txtfEmail.clear();
        txtfBio.clear();
        cbxGender.setValue(user.getGender());
        txtfEmail.setText(user.getEmail());
        txtfBio.setText(user.getBio());
        
        pane.setHgap(20);
        pane.setVgap(20);
        pane.add(btnReturn, 0, 0);
        
        Label gender = new Label("Edit gender: ");
        Label bio = new Label("Edit bio");
        Label lblEmail = new Label("Edit email: ");
        
        pane.add(gender, 0, 3);
        pane.add(cbxGender, 2, 3);
        pane.add(bio, 0, 4);
        pane.add(txtfBio, 2, 4);
        pane.add(lblEmail, 0, 5);
        pane.add(txtfEmail, 2, 5);
        pane.add(btnSubmit, 3, 6);
        
        pane.setStyle("-fx-padding:20px");
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private void handleExit(ActionEvent event){
        guiManager.saveUser(user);
        System.exit(0);
    }
    
    private void handleSettingsPage(ActionEvent event){
        if (cbxSettings.getValue().equals("Change password")){
            changePassword();
        } else {
            deleteAccount();
        }
    }
    
    private void deleteAccount(){
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Warning! You are about to permanently delete your account. This action cannot be undone");
        alert.showAndWait();
        GridPane pane = new GridPane();
        txtfPassword.clear();
        txtfUserId.clear();
        
        Label lblPassword = new Label("Enter your password: ");
        Label lblUserId = new Label("Enter your userID: ");
        
        
        Button btnReturn = new Button("Return");
        pane.add(btnReturn, 0, 0);
        pane.add(lblPassword, 0, 2);
        pane.add(lblUserId, 0, 3);
        pane.add(txtfPassword, 1, 2);
        pane.add(txtfUserId, 1, 3);
        
        Button btnDelete = new Button("Delete Account");
        pane.add(btnDelete, 2, 4);
        
        Scene scene = new Scene(pane);
        pane.setStyle("-fx-padding:20px");
        pane.setVgap(20);
        pane.setHgap(20);
        
        btnReturn.setOnAction(this::handleShowHomePage);
        btnDelete.setOnAction(this::handleDeleteAccount);
        
        //btnProceed.setOnAction(this::handleDeleteAccount);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleDeleteAccount(ActionEvent event){
        String id = txtfUserId.getText();
        String pw = txtfPassword.getText();
        boolean validateId = guiManager.isInputValid(id);
        boolean validatePw = guiManager.isInputValid(pw);
        
        if (!validateId || !validatePw){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter valid input");
            alert.showAndWait();
            return;
        }
        User findUser = userManager.validateUserSubmission(id, pw);
        if (findUser != null){
            userManager.removeUser(user.getUserId());
            userManager.saveUsers();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Your account has been deleted.");
            alert.showAndWait();
            MainGUI mainGUI = new MainGUI();
            mainGUI.start(primaryStage);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("UserId or password doesn’t match any account");
            alert.showAndWait();
        }
            
        
    }
    
    private void changePassword(){
        GridPane pane = new GridPane();
        ScrollPane scrollPane = new ScrollPane(pane);
        txtfPassword.clear();
        txtfNewPassword.clear();
        txtfUserId.clear();
        Scene scene = new Scene(scrollPane);
        
        Button btnReturn = new Button("Return");
        btnReturn.setOnAction(this::handleShowHomePage);
        
        pane.add(btnReturn, 0, 0);
        
        txtfPassword.clear();
        txtfUserId.clear();
        txtfNewPassword.clear();
        Label lblPasswordRequirements = new Label(
        "Password Requirements:\n" +
        "Must be at least 12 characters long\n" +
        "Must contain at least one uppercase letter (A–Z)\n" +
        "Must contain at least one lowercase letter (a–z)\n" +
        "Must contain at least one special character (@, $, #, &)");
        
        Label lblCurrentPassword = new Label("Enter your current password:");
        Label lblNewPassword = new Label("Enter your new password:");
        Label lblUserId = new Label("Enter your userID: ");
        
        pane.setHgap(15);
        pane.setVgap(15);
        pane.setStyle("-fx-padding:20px;");
        
        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(this::handlePasswordChangeSubmit);
        
        pane.add(lblPasswordRequirements, 0, 2, 5, 5);
        pane.add(lblUserId, 0, 8);
        pane.add(txtfUserId, 0, 9);
        pane.add(lblCurrentPassword, 0, 11);
        pane.add(txtfPassword, 0, 12);
        pane.add(lblNewPassword, 0, 14);
        pane.add(txtfNewPassword, 0, 15);
        
        pane.add(btnSubmit, 2, 17);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private void handlePasswordChangeSubmit(ActionEvent event){
        try{
            String oldPW = txtfPassword.getText();
            String newPW = txtfNewPassword.getText();
            String id = txtfUserId.getText();
            
            boolean validOldPW = guiManager.isInputValid(oldPW);
            boolean validNewPW = guiManager.isInputValid(newPW);
            boolean validId = guiManager.isInputValid(id);
        
            if (!validOldPW || !validNewPW || !validId){
                throw new UserNotFoundException("Please fill in the missing inputs");
            } 
            
            if (oldPW.equalsIgnoreCase(newPW)){
                throw new UserNotFoundException("Old password cannot be the same as the new password.");
            }
            
            if (!guiManager.validatePassword(newPW)){
                throw new UserNotFoundException("Please enter a valid password");
            }
            User checkUser = userManager.validateUserSubmission(id, oldPW);
            if (checkUser == null){
                throw new UserNotFoundException("UserId or password doesn’t match any account.");
            } else {
                String hashedPassword = BCrypt.hashpw(newPW, BCrypt.gensalt());
                user.setPasswordHash(hashedPassword);
                guiManager.saveUser(user);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("The password has been set.");
                alert.showAndWait();
                showHomePage();
            }
            
        } catch(UserNotFoundException unfe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(unfe.getMessage());
            alert.showAndWait();
        }
    }
    
    private void createTask(){
        GridPane pane = new GridPane();
        dateOpened = new DatePicker();
        dueDate = new DatePicker();
        Scene scene = new Scene(pane, 1000, 900);
        //txtfTitle.clear();
        Button btnReturn = new Button("Return");
        pane.setStyle("-fx-padding: 20px;");
        pane.setHgap(15);
        pane.setVgap(15);
        
        //clear
        txtfTitle.clear();
        
        Label lblTitle = new Label("Enter title of the assignment");
        Label lblDateOpened = new Label("Enter the date the assignment will be opened");
        Label lblDueDate = new Label("Enter the date the assignment will be due");
        Button btnNext = new Button("Next");
        pane.add(btnReturn, 0, 0);
        pane.add(lblTitle, 0, 2);
        pane.add(txtfTitle, 0, 3);
        pane.add(lblDateOpened, 0, 4);
        pane.add(dateOpened, 0, 5);
        pane.add(lblDueDate, 0, 6);
        pane.add(dueDate, 0, 7);
        pane.add(btnNext, 2, 8);
        btnReturn.setOnAction(this::handleShowHomePage);
        btnNext.setOnAction(this::handleShowNextTaskPage);
        //datePicker.setOnAction(this::getTaskDueDate);
        //btnNext.setOnAction(this::handleCreateTaskSubmission);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
        
    }
    
    private void handleShowNextTaskPage(ActionEvent event){
        
        boolean validTitle = guiManager.isInputValid(txtfTitle.getText());
        boolean validDateOpened = guiManager.isInputValid(dateOpened.getValue() + "");
        boolean validDueDate = guiManager.isInputValid(dueDate.getValue() + "");
        if (!validTitle || !validDateOpened || !validDueDate){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in the missing inputs");
            alert.showAndWait();
        } else if(String.valueOf(dateOpened.getValue()).compareTo(dueDate.getValue() + "") > 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The due date cannot exceed the opening date.");
            alert.showAndWait();
        }
        
        else {
            
            Scene scene = new Scene(taskScrollPane, 1000, 900);
            
            Button btnAdd = new Button("Add question");
            
            Button btnReturn = new Button("Return");
            Button btnSubmit = new Button("Submit");
            Button btnRemove = new Button("Remove");
            btnAdd.setOnAction(this::handleTaskAddition);
            btnRemove.setOnAction(this::handleTaskRemoval);
            btnReturn.setOnAction(this::handleShowHomePage);
            
            taskPane.add(btnReturn, 0, 0);
            taskPane.add(btnAdd, 0, 2);
            taskPane.add(btnRemove, 0, 3);
            taskPane.add(btnSubmit, 2, 3);
            
            btnSubmit.setOnAction(this::handleTaskSubmission);
            taskPane.setVgap(15);
            taskPane.setHgap(15);
            taskPane.setStyle("-fx-padding:20px;");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        
    }
    
    private void handleTaskRemoval(ActionEvent event){
        int index = txtaAnswers.size()-1;
        TextArea txtaAnswer = txtaAnswers.get(index);
        TextArea txtaQuestion = txtaQuestions.get(index);
        Label lblAnswer = lblAnswers.get(index);
        Label lblQuestion = lblQuestions.get(index);
        TextField txtfMark = txtfMarks.get(index);
        if (questionIndex != 0){
            taskPane.getChildren().remove(txtaAnswer);
            taskPane.getChildren().remove(lblAnswer);
            taskPane.getChildren().remove(txtfMark);
            taskPane.getChildren().remove(txtaQuestion);
            taskPane.getChildren().remove(lblQuestion);
            
            txtaAnswers.remove(index);
            txtaQuestions.remove(index);
            lblAnswers.remove(index);
            lblQuestions.remove(index);
            txtfMarks.remove(index);
            taskRows--;
            taskRows--;
            taskRows--;
            taskRows--;
            questionIndex--;
        }
    }
    
    private void handleTaskAddition(ActionEvent event){
        //Scene scene = new Scene(taskScrollPane, 1000, 900);
        //taskPane.setVgap(15);
        //taskPane.setHgap(15);
        //taskPane.setStyle("-fx-padding:20px;");
        
        Label lblQuestion = new Label((questionIndex+1) + ". Add a question and a mark allocation in the next empty field to the right");
        Label lblAnswer = new Label("Add an answer");
        TextArea txtaQuestion = new TextArea();
        TextArea txtaAnswer = new TextArea();
        TextField txtfMark = new TextField();
        taskPane.add(lblQuestion, taskCols, taskRows);
        taskPane.add(txtfMark, taskCols+2, taskRows);
        taskRows++;
        taskPane.add(txtaQuestion, taskCols, taskRows);
        taskRows++;
        taskPane.add(lblAnswer, taskCols, taskRows);
        taskRows++;
        taskPane.add(txtaAnswer, taskCols, taskRows);
        taskRows++;
        txtaQuestions.add(txtaQuestion);
        txtaAnswers.add(txtaAnswer);
        lblQuestions.add(lblQuestion);
        lblAnswers.add(lblAnswer);
        txtfMarks.add(txtfMark);
        questionIndex++;
        //primaryStage.setScene(scene);
        //primaryStage.show();
    }
    
    private void handleTaskSubmission(ActionEvent event){
        try{
            boolean validQuestion = false;
            boolean validAnswer = false;
            boolean validMark = false;
            ArrayList<String> questions = new ArrayList<>();
            ArrayList<String> answers = new ArrayList<>();
            ArrayList<Double> marks = new ArrayList<>();
            
            String title = txtfTitle.getText();
            String openDate = dateOpened.getValue() + "";
            String closeDate = dueDate.getValue() + "";
            
            
            double maxMarks = 0.0;
            for (int i = 0; i < txtaQuestions.size(); i++){
                String question = txtaQuestions.get(i).getText();
                String answer = txtaAnswers.get(i).getText();
                String markString = txtfMarks.get(i).getText();
                double mark = Double.parseDouble(markString);
                validQuestion = guiManager.isInputValid(question);
                validAnswer = guiManager.isInputValid(answer);
                validMark = guiManager.isInputValid(markString);
                
                if (!validQuestion || !validAnswer || !validMark){
                    
                    throw new Exception("Please fill in the missing inputs");
                    
                } else {
                    questions.add(question);
                    answers.add(answer);
                    marks.add(mark);
                    
                    maxMarks += mark;
                }
            }
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The assignment has been posted.");
            alert.showAndWait();
            
            guiManager.assignStudentAssignment(questions, answers, marks, user, maxMarks, title, closeDate, openDate);
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error assigning task: " + e.getMessage());
            alert.showAndWait();
        }
        
    }
        
    private void handleCreateTaskSubmission(ActionEvent event){
        
        txtaQuestion = new TextArea();
        
        //RadioButton radio = new RadioButton();
        Label lblQuestion = new Label("Add a question");
        Label lblAnswer = new Label("Add an answer");
        
        
        
        
    }
    
    public void handleLogin(Stage primaryStage){
        this.primaryStage = primaryStage;
        
        showHomePage();
    }
    
    private void handleSelectableModule(ActionEvent event){
        
        boolean validFaculty = guiManager.isInputValid(cbxFaculty.getValue());

            if (!validFaculty){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Please select your faculty first");
                alert.showAndWait();
            }
                
    }
    
    private void showModuleSelectionPage(){
        
        GridPane pane = new GridPane();
        cbxFaculty = guiManager.configureFacultyComboBox();
        cbxFaculty.setOnAction(this::handleFacultySelection);
        cbxTitles = guiManager.configureTitleComboBox();
        cbxModules = guiManager.populateModuleComboBox();
        
        Button submit = new Button("Submit");
        submit.setOnAction(this::handleModuleSubmission);
        Button btnSubmit = new Button("Submit");
        
        cbxModules.setOnAction(this::handleSelectableModule);
        btnSubmit.setOnAction(this::handleModuleSubmission);
        
        pane.add(cbxTitles, 0, 1);
        pane.add(cbxFaculty, 0, 2);
        pane.add(cbxModules, 0, 3);
        pane.add(btnSubmit, 4, 7);
        pane.setHgap(15);
        
    
        // pane.add(cbxModule1, 0, 2);
        // pane.add(cbxModule2, 0, 3);
        // pane.add(cbxModule3, 0, 4);
        // pane.add(cbxModule4, 0, 5);
        // pane.add(cbxModule5, 0, 6);
        // pane.add(cbxFaculty, 0, 6);
        
        
        
        //pane.add(submit, 2, 7);
        
            
        
        pane.setStyle("-fx-padding: 25px;");
        pane.setVgap(16);
        
        Scene scene = new Scene(pane, 600, 600);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleTitleSelection(ActionEvent event){
        String title = cbxTitles.getValue();
        ((Lecture)user).setTitle(title);
    }
    
    private void handleFacultySelection(ActionEvent event){
        String faculty = cbxFaculty.getValue();
        ((Lecture)user).setFaculty(faculty);
        
    }
    
    private void handleModuleSubmission(ActionEvent event){
        String module = cbxModules.getValue();
        ModuleManager moduleManager = new ModuleManager();
        ArrayList<Module> modules = moduleManager.getModules();
        
        if (!guiManager.isInputValid(module)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select your module first");
            alert.showAndWait();
            return;
        }
        for (int i = 0; i < modules.size() && modules!= null; i++){
            if (modules.get(i).getModuleName().equals(module)){
                if (modules.get(i).getInstructor() == null){
                    user = guiManager.getSelectedModule(user, module);
                    guiManager.saveUser(user);
                    showHomePage();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("This module has already been taken");
                    alert.showAndWait();
                }
            }
        }
        
        
    }
    
    public void initializeComboBoxes(){
        cbxTasks = guiManager.configureTasksComboBox(user);
        cbxProfile = guiManager.configureProfileComboBox();
        cbxModules = guiManager.configureModulesComboBox();
        cbxHelp = guiManager.configureHelpComboBox();
        cbxNotifications = guiManager.configureNotificationsComboBox(user);
        cbxLogout = guiManager.configureLogoutComboBox();;
        cbxSettings = guiManager.configureSettingsComboBox();
        
    }
        
    public void showHomePage(){
        
        //GridPane mainGridPane = new GridPane();
        VBox mainPane = new VBox();
        ScrollPane scrollPane = new ScrollPane(mainPane);
        //VBox vbox = new VBox();
        //HBox hbox = new HBox();
        Scene scene = new Scene(scrollPane, 1500, 1500);
        scene.getRoot().setStyle("-fx-background-color: white;");
        initializeComboBoxes();
        
        
        Image headerImage = new Image(getClass().getResourceAsStream("images\\StudyEazzy.png"));
        ImageView imgvHeader = new ImageView(headerImage);
        Image homePageImg = new Image(getClass().getResourceAsStream("images\\welcomeImg.png"));
        ImageView imgVHomePage = new ImageView(homePageImg);
        
        mainPane.setSpacing(35);
        
        mainPane.getChildren().add(imgvHeader);
        
        HBox hboxComboBoxes = new HBox();
        mainPane.getChildren().add(hboxComboBoxes);
        
        mainPane.getChildren().add(imgVHomePage);
        
        mainPane.setStyle("-fx-padding:15px;-fx-background-color:white");
        scrollPane.setStyle("-fx-background-color:white");
        //vbox.setStyle("-fx-padding:15px;-fx-background-color:white");
        //hbox.setStyle("-fx-padding:15px;-fx-background-color:white");
        //pane.setHgap(3);
        //pane.setVgap(10);
        
        String style = """
            -fx-background-color: skyblue;
            -fx-text-fill: black;
            -fx-padding: 5px 10px;
            -fx-background-radius: 12px;
            -fx-border-radius: 12px;
            -fx-border-color: black;
            -fx-border-width: 1px;
        """; 
        Button btnLogout = new Button("Logout");
        cbxProfile.setStyle(style);
        cbxModules.setStyle(style);
        cbxTasks.setStyle(style);
        cbxNotifications.setStyle(style);
        cbxSettings.setStyle(style);
        cbxHelp.setStyle(style);
        btnLogout.setStyle(style);
        btnLogout.setPrefHeight(40);
        btnLogout.setPrefWidth(100);
        
        //button
        
        Button btnExit = new Button("Exit");
        btnExit.setStyle(style);
        btnExit.setPrefHeight(40);
        btnExit.setPrefWidth(100);
        btnExit.setOnAction(this::handleExit);
        hboxComboBoxes.setSpacing(40);
        hboxComboBoxes.setStyle("-fx-background-color:gray");
        hboxComboBoxes.getChildren().addAll(cbxTasks, cbxProfile, cbxModules, cbxNotifications, cbxSettings, cbxHelp, btnLogout, btnExit);
        //vbox.getChildren().addAll(hbox, imgvWelcome);
        //pane.add(imgvHeader, 0, 0);
        //pane.add(vbox, 0, 4);
        //pane.add(imgvQuote, 0, 5);
        // pane.add(cbxTasks, 1, 4);
        // pane.add(cbxProfile, 2, 4);
        // pane.add(cbxModules, 3, 4);
        // pane.add(cbxNotifications, 4, 4);
        // pane.add(cbxSettings, 5, 4);
        // pane.add(cbxHelp, 6, 4);
        // pane.add(btnLogout, 7, 4);
        
        
        
        
        //event handlers
        cbxProfile.setOnAction(this::handleShowProfile);
        btnLogout.setOnAction(this::handleShowLogout);
        cbxNotifications.setOnAction(this::handleNotificationPage);
        cbxSettings.setOnAction(this::handleSettingsPage);
        cbxTasks.setOnAction(this::handleTaskPage);
        cbxModules.setOnAction(this::handleModulePage);
        cbxHelp.setOnAction(this::handleAdminHelp);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleModulePage(ActionEvent event){
        
        GridPane pane = new GridPane();
        
        Scene scene = new Scene(pane);
        
        Button btnReturn = new Button("Return");
        
        btnReturn.setOnAction(this::handleShowHomePage);
        pane.add(btnReturn, 0, 0);
        Module module = ((Lecture)user).getTeachingModule();
        Label lblName = new Label("Module name");
        Label lblCode = new Label("Code");
        Label lblInstructor = new Label("Instructor");
        pane.add(lblName, 0, 3);
        pane.add(lblCode, 1, 3);
        pane.add(lblInstructor, 2, 3);
        pane.setHgap(20);
        pane.setVgap(20);
        pane.setStyle("-fx-padding:20px");
        lblName.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 20));
        lblCode.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 20));
        lblInstructor.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 20));
        
        Label name = new Label(module.getModuleName());
        Label code = new Label(module.getModuleCode());
        Label instructor = new Label(module.getInstructor());
        name.setFont(javafx.scene.text.Font.font("Verdana", 15));
        code.setFont(javafx.scene.text.Font.font("Verdana", 15));
        instructor.setFont(javafx.scene.text.Font.font("Verdana", 15));
        pane.add(name, 0, 5);
        pane.add(code, 1, 5);
        pane.add(instructor, 2, 5);
               
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void showProfile(){
        GridPane pane = new GridPane();
        ScrollPane scrollPane = new ScrollPane(pane);
        Scene scene = new Scene(scrollPane, 600, 600);
        pane.setVgap(5);
        pane.setHgap(3);
        Module lectureModules = ((Lecture)user).getTeachingModule();
        String total = ""; //C:\Users\mphos\OneDrive\Desktop\CMPG2XX Integrated Project\Final Project\CMPG2xx-Integrated-Project\images
        Image profilePic = new Image(getClass().getResourceAsStream("images\\male_pfp.jpg"));
        
        
        if (user.getGender().equals("Female")){
            profilePic = new Image(getClass().getResourceAsStream("images\\female_pfp.jpg"));
        } else {
            profilePic = new Image(getClass().getResourceAsStream("images\\male_pfp.jpg"));
        }
        ImageView imgViewer = new ImageView(profilePic);
        imgViewer.setFitWidth(200);
        imgViewer.setFitHeight(200);
        imgViewer.setPreserveRatio(true);
        Label lblBio = new Label(user.getBio());
        Label lblBioTitle = new Label("Bio: ");
        Label lblFaculty = new Label(user.getFaculty());
        Label lblFacultyTitle = new Label("Faculty: ");
        Label lblUserID = new Label(user.getUserId());
        Label lblUserIDTitle = new Label("UserID: "); 
        Label lblGender = new Label(user.getGender());
        Label lblGenderTitle = new Label("Gender: ");
        Label lblEmail = new Label(user.getEmail());
        Label lblEmailTitle = new Label("Email: ");
        
        lblBio.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
        lblFaculty.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
        lblUserID.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
        lblEmail.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
        lblGender.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
        pane.add(imgViewer, 2, 3);
        pane.add(lblBioTitle, 2, 4);
        pane.add(lblBio, 3, 4);
        pane.add(lblFacultyTitle, 2, 5);
        pane.add(lblFaculty, 3, 5);
        pane.add(lblUserIDTitle, 2, 6);
        pane.add(lblUserID, 3, 6);
        pane.add(lblGenderTitle, 2, 7);
        pane.add(lblGender, 3, 7);
        pane.add(lblEmailTitle, 2, 8);
        pane.add(lblEmail, 3, 8);
        pane.setStyle("-fx-padding: 20px;");
        
        
        
        Button btnReturn = new Button("Return");
        btnReturn.setOnAction(this::handleShowHomePage);
        pane.add(btnReturn, 0, 0);
        
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private void handleShowLogout(ActionEvent event){
        ModuleManager moduleManager = new ModuleManager();
        guiManager.saveUser(user);
        moduleManager.saveModules();
        
        MainGUI mainGUI = new MainGUI();
        mainGUI.start(primaryStage);
    }
    
    private void handleNotificationPage(ActionEvent e){
        GridPane pane = new GridPane();
        dateOpened = new DatePicker();
        Button btnReturn = new Button("Return");
        Button btnPost = new Button("Post");
        Label lblTitle = new Label("Enter title:");
        Label lblDate = new Label("Enter date:");
        Label lblContent = new Label("Write the announcement:");
        
        pane.add(btnReturn, 0, 0);
        pane.add(lblTitle, 0, 2);
        pane.add(txtfTitle, 0, 3);
        pane.add(lblDate, 0, 4);
        pane.add(dateOpened, 0, 5);
        pane.add(lblContent, 0, 6);
        pane.add(txtaContent, 0, 7);
        pane.add(btnPost, 2, 8);
        
        
        btnPost.setOnAction(this::handleNotificationPosting);
        btnReturn.setOnAction(this::handleShowHomePage);
        
        pane.setHgap(15);
        pane.setVgap(15);
        pane.setStyle("-fx-padding:20px;");
        Scene scene = new Scene(pane, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
    
    private void handleNotificationPosting(ActionEvent event){
        boolean validateTitle = false;
        boolean validateContent = false;
        boolean validateDate = false;
        String title = txtfTitle.getText();
        String content = txtaContent.getText();
        String date = dateOpened.getValue() + "";
        validateTitle = guiManager.isInputValid(title);
        validateContent = guiManager.isInputValid(content);
        validateDate = guiManager.isInputValid(date);
        if (!validateContent || !validateTitle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in the missing inputs.");
            alert.showAndWait();
        } else {
            String instructor = user.getFirstName() + " " + user.getLastName();
            Announcement announcement = new Announcement(title, content, ((Lecture)user).getTeachingModule().getModuleCode(), instructor, date);
            ((Lecture)user).sendAnnouncement(announcement);
            //guiManager.addAnnouncement(announcement);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The announcement has been posted.");
            alert.showAndWait();
        }
        
        
    }
    
    private void handleShowProfile(ActionEvent event){
        if (cbxProfile.getValue().equals("View profile")){
            showProfile();
        } else {
            editProfile();
        }
        
    }
    
    public void handleShowHomePage(ActionEvent event){
        showHomePage();
    }
    
    private void handleEditProfileSubmission(ActionEvent event){
        String bio = txtfBio.getText();
        String email = txtfEmail.getText();
        String gender = cbxGender.getValue();
        
        if (!guiManager.isInputValid(gender) || !guiManager.isInputValid(email) || !guiManager.isInputValid(bio)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Plese fill in missing inputs");
            alert.showAndWait();
        } else {
            if (!guiManager.validateEmail(email)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Plese enter a valid email.");
                alert.showAndWait();
            } else {
                user.setBio(bio);
                user.setEmail(email);
                user.setGender(gender);
                guiManager.saveUser(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("The Profile has been edited");
                alert.showAndWait();
                showProfile();
            }
        }
    }
    
    
}