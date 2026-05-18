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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import org.mindrot.jbcrypt.BCrypt;

public class StudentGUI
{
    public Stage primaryStage;
    private TextField txtfFirstName = new TextField();
    private TextField txtfLastName = new TextField();
    private TextField txtfEmail = new TextField();
    private PasswordField txtfPassword = new PasswordField();
    private TextField txtfUserId = new TextField();
    private PasswordField txtfNewPassword = new PasswordField();
    private TextField txtfBio = new TextField();
    
    private UserManager userManager = new UserManager();
    private ArrayList<ComboBox> totalSelectableModules;
    private ComboBox<Module> cbxModule1;
    private ComboBox<Module> cbxModule2;
    private ComboBox<Module> cbxModule3;
    private ComboBox<Module> cbxModule4;
    private ComboBox<Module> cbxModule5;
    
    private ComboBox<String> cbxYearLevel;
    private ComboBox<String> cbxFaculty;
    private GridPane pane = new GridPane(); //title it modulePane
    private ArrayList<ComboBox> selectedModules = new ArrayList<>();
    private GUIManager guiManager = new GUIManager();
    private int moduleRows = 4;
    private int moduleCols = 0;
    
    private User user;
    
    private ComboBox<String> cbxTasks = new ComboBox<>();
    private ComboBox<String> cbxProfile = new ComboBox<>();
    private ComboBox<String> cbxModules = new ComboBox<>();
    private ComboBox<String> cbxHelp = new ComboBox<>();
    private ComboBox<String> cbxNotifications = new ComboBox<>();;
    private ComboBox<String> cbxLogout = new ComboBox<>();
    private ComboBox<String> cbxSettings = new ComboBox<>();
    private ComboBox<String> cbxGender = new ComboBox<>();
    
    
    private ArrayList<Button> taskButtons = new ArrayList<>();
    private ArrayList<TextArea> txtaStudentAnswers = new ArrayList<>();
    private int questionIndex = 0;
    private String chosenTask = "";
    public StudentGUI(User user){
        this.user = user;
    }
    
    public void handleLogin(Stage primaryStage){
        this.primaryStage = primaryStage;
        
        showHomePage();
    }
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        showModuleSelectionPage();
    }
    
    
    private void handleRemovableModule(ActionEvent event){
        if (!selectedModules.isEmpty()) {
    
            ComboBox last = selectedModules.remove(selectedModules.size() - 1);
    
            pane.getChildren().remove(last);
    
            moduleRows--;
            if (moduleRows < 4) {
                moduleRows = 4;
                moduleCols = 0;
            }
        }
    }
    
    private void handleSelectableModule(ActionEvent event){
        boolean validYearLevel = guiManager.isInputValid(cbxYearLevel.getValue());
        boolean validFaculty = guiManager.isInputValid(cbxFaculty.getValue());
        if (!validYearLevel){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please select your year level first");
            alert.showAndWait();
        } else {
            if (!validFaculty){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Please select your faculty first");
                alert.showAndWait();
            } else {
                ComboBox<String> m = new ComboBox<>();
            
                m = guiManager.populateModuleComboBox();
                selectedModules.add(m);
                
                if (moduleRows >= 8 && moduleCols >= 3){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("You can only select 8 modules");
                    alert.showAndWait();
                } else {
                    if (moduleRows >= 8){
                        moduleRows = 4;
                        moduleCols = 3;
                        pane.add(m, moduleCols, moduleRows);
                    } else {
                        pane.add(m, moduleCols, moduleRows);
                    }
                    moduleRows++;
                }
            }
        }
            
            
    }
    
    private void showModuleSelectionPage(){
        
        
        cbxYearLevel = guiManager.configureYearLevelComboBox();
        
        cbxYearLevel.setOnAction(this::handleYearLevelSelection);
        
        
        cbxFaculty = guiManager.configureFacultyComboBox();
        cbxFaculty.setOnAction(this::handleFacultySelection);
        
        Button submit = new Button("Submit");
        submit.setOnAction(this::handleModuleSubmission);
        Button btnAdd = new Button("Add a module");
        Button btnRemove = new Button("Remove a module");
        Button btnSubmit = new Button("Submit");
        
        btnAdd.setOnAction(this::handleSelectableModule);
        btnRemove.setOnAction(this::handleRemovableModule);
        btnSubmit.setOnAction(this::handleModuleSubmission);
        
        pane.add(cbxYearLevel, 0, 1);
        pane.add(cbxFaculty, 0, 2);
        pane.add(btnAdd, 0, 3);
        pane.add(btnRemove, 2, 3);
        pane.add(btnSubmit, 4, 9);
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
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private void handleYearLevelSelection(ActionEvent event){
        int yearLevel = Integer.parseInt(cbxYearLevel.getValue());
        ((Student)user).setYearLevel(yearLevel);
        
    }
    
    private void handleFacultySelection(ActionEvent event){
        String faculty = cbxFaculty.getValue();
        ((Student)user).setFaculty(faculty);
        
    }
    
    private void handleModuleSubmission(ActionEvent event){
        try{
            ArrayList<String> userModules = new ArrayList<>();
            
            for (int i = 0; i < selectedModules.size(); i++){
                ComboBox<String> m = selectedModules.get(i);
                if (!guiManager.isInputValid(m.getValue())){
                    throw new InvalidModuleSelectionException("Please select your module first");
                } else {
                    userModules.add(m.getValue());
                }
                
            }
            
            try{
                user = guiManager.registerUserModules(user, userModules);
                guiManager.saveUser(user);
                showHomePage();
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } catch(InvalidModuleSelectionException imse){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(imse.getMessage());
            alert.showAndWait();
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
        cbxNotifications.setOnAction(this::handleShowNotification);
        cbxSettings.setOnAction(this::handleSettingsPage);
        cbxTasks.setOnAction(this::handleTasks);
        cbxModules.setOnAction(this::handleModulePage);
        cbxHelp.setOnAction(this::handleAdminHelp);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
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
    
    private void handleModulePage(ActionEvent event){
        String value = cbxModules.getValue();
        if (value.equals("View modules")){
            viewModules();
        } 
        
    }
    
    private void viewModules(){
        GridPane pane = new GridPane();
        
        Scene scene = new Scene(pane);
        
        Button btnReturn = new Button("Return");
        
        btnReturn.setOnAction(this::handleShowHomePage);
        pane.add(btnReturn, 0, 0);
        ArrayList<Module> modules = ((Student)user).getEnrolledModules();
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
        for (int i = 0; i < modules.size(); i++){
            Module module = modules.get(i);
            Label name = new Label(module.getModuleName());
            Label code = new Label(module.getModuleCode());
            Label instructor = new Label(module.getInstructor());
            name.setFont(javafx.scene.text.Font.font("Verdana", 15));
            code.setFont(javafx.scene.text.Font.font("Verdana", 15));
            instructor.setFont(javafx.scene.text.Font.font("Verdana", 15));
            pane.add(name, 0, (i+5));
            pane.add(code, 1, (i+5));
            pane.add(instructor, 2, (i+5));
            
            
        }
        
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
            userManager.removeUser(findUser.getUserId());
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
    
    private void handleTasks(ActionEvent event){
        if (cbxTasks.getValue().equals("Pending tasks")){
            showTasks();
        }
    }
    
    private void showTasks(){
        ArrayList<StudentAssignment> assignments = ((Student)user).getAssignments();
        
        GridPane pane = new GridPane();
        ScrollPane scrollPane = new ScrollPane(pane);
        Scene scene = new Scene(scrollPane, 500, 500);
        
        Label displayContent = new Label("");
        pane.setVgap(15);
        pane.setHgap(15);
        
        Button btnReturn = new Button("Return");
        
        scrollPane.setStyle("-fx-padding:15px");
        pane.setStyle("-fx-padding:15px;");
        
        taskButtons.clear(); 
    
    if (assignments.isEmpty()){
        Image imgSadFace = new Image(getClass().getResourceAsStream("images\\no_task_sad_face.png"));
        ImageView imgView = new ImageView(imgSadFace);
        
        displayContent.setText("No pending tasks");
        
        pane.add(btnReturn, 0, 0);
        pane.add(displayContent, 3, 3);
        pane.add(imgView, 3, 5);
        
    } else {
        pane.add(btnReturn, 0, 0);
        
        for (int i = 0; i < assignments.size(); i++){
            
            StudentAssignment assignment = assignments.get(i);
            
            Label lblTitle = new Label(assignment.toString());
            lblTitle.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
            
            Button btn = new Button("Start: " + assignment.getTitle());
            
            
            btn.setUserData(assignment);
            
            btn.setOnAction(this::handleStartTask);
            
            taskButtons.add(btn);
            
            pane.add(lblTitle, 0, i + 3);
            pane.add(btn, 1, i + 3);
        }
    }
    
    btnReturn.setOnAction(this::handleShowHomePage);
    
    primaryStage.setScene(scene);
    primaryStage.show();
    }
    
    private void handleStartTask(ActionEvent event){
        txtaStudentAnswers.clear();
        GridPane pane = new GridPane();
        ScrollPane scrollPane = new ScrollPane(pane);
        Scene scene = new Scene(pane);
        pane.setHgap(15);
        pane.setVgap(15);
        pane.setStyle("-fx-padding:20px;");
        
        Button btnReturn = new Button("Return");
        Button btnSubmit = new Button("Submit");
        
        pane.add(btnReturn, 0, 0);
        pane.add(btnSubmit, 3, 0);
        
        
        Button clickedButton = (Button) event.getSource();
        
        
        StudentAssignment assignment = (StudentAssignment) clickedButton.getUserData();
    
    if (!assignment.getCompleted()){
        for (int i = 0; i < assignment.getQuestions().size(); i++){
            String question = assignment.getQuestions().get(i);
            double mark = assignment.getMarks().get(i);
            
            TextArea txtaAnswer = new TextArea();
            int row = i + 3;
            
            Label lblQuestion = new Label(question + "\t\t[" + mark + "]");
            
            pane.add(lblQuestion, 0, row);
            
            txtaStudentAnswers.add(txtaAnswer);
            questionIndex = 0;
            
            pane.add(txtaAnswer, 0, row + 1);
            }
        } else {
            for (int i = 0; i < assignment.getFeedback().size(); i++){
                int row = i + 3;
                
                Label lblFeedback = new Label(assignment.getFeedback().get(i));
                lblFeedback.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 15));
                
                if (assignment.getFeedback().get(i).contains("CORRECT")){
                    lblFeedback.setStyle("-fx-text-fill:green");
                } else {
                    lblFeedback.setStyle("-fx-text-fill:red");
                }
                
                pane.add(lblFeedback, 0, row);
            }
        }
    
        btnSubmit.setOnAction(this::handleAnswerSubmission);
        btnReturn.setOnAction(this::handleShowHomePage);
    
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleAnswerSubmission(ActionEvent event){
    ArrayList<StudentAssignment> assignments = ((Student)user).getAssignments();
    
    StudentAssignment target = null;

    for (int i = 0; i < assignments.size(); i++){
        if (assignments.get(i).getCompleted() == false){
            target = assignments.get(i);
            break;
        }
    }

    if (target != null){
        target.setCompleted(true);

        for (int k = 0; k < txtaStudentAnswers.size(); k++){
            String answer = txtaStudentAnswers.get(k).getText();
            target.submitAnswer(k, answer);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("The assignment was submitted");
        alert.showAndWait();

        target.getMarksObtained();
        }
    }
    
    public void editProfile(){
        GridPane pane = new GridPane();
        ScrollPane scrollPane = new ScrollPane(pane);
        Scene scene = new Scene(scrollPane, 600, 600);
        pane.setVgap(5);
        pane.setHgap(3);
        ArrayList<Module> studentModules = ((Student)user).getEnrolledModules();
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
    
    private void showProfile(){
        GridPane pane = new GridPane();
        ScrollPane scrollPane = new ScrollPane(pane);
        Scene scene = new Scene(scrollPane, 600, 600);
        pane.setVgap(5);
        pane.setHgap(3);
        ArrayList<Module> studentModules = ((Student)user).getEnrolledModules();
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
    
    public void handleShowProfile(ActionEvent event){
        if (cbxProfile.getValue().equals("View profile")){
            showProfile();
        } else {
            editProfile();
        }
        
    }
    
    
    
    public void handleShowNotification(ActionEvent event){
        
        GridPane pane = new GridPane();
        
        Scene scene = new Scene(pane, 900, 600);
        Button btnReturn = new Button("Return");
        pane.setHgap(15);
        pane.setVgap(15);
        pane.setStyle("-fx-padding:20px;");
        
        String content = "";
        String announcementPreview = cbxNotifications.getValue();
        
        ArrayList<Announcement> announcements = ((Student)user).getAnnouncements();
        
        for (int i = 0; i < announcements.size(); i++){
            if (announcements.get(i).toString().contains(announcementPreview)){
                
                content = announcements.get(i).showAnnouncement();
                break;
            } else {
                content = "Something went wrong. Please return back to the main page";
            }
        }
        Label display = new Label(content);
        display.setStyle("-fx-font-size:15px");
        pane.add(btnReturn, 0, 0);
        pane.add(display, 0, 2);
        
        btnReturn.setOnAction(this::handleShowHomePage);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void handleShowHomePage(ActionEvent event){
        showHomePage();
        
        
    }
    
    private void handleShowLogout(ActionEvent event){
        ModuleManager moduleManager = new ModuleManager();
        guiManager.saveUser(user);
        moduleManager.saveModules();
        MainGUI mainGUI = new MainGUI();
        mainGUI.start(primaryStage);
        
        
    }
}