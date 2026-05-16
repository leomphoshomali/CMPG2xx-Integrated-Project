import java.util.ArrayList;
import java.io.Serializable;

public class StudentAssignment implements Serializable
{
    private String title;
    private Module module;
    private boolean completed;
    private double maxMarks;
    private double marksObtained;
    private String dueDate;
    private String dateOpened;
    private ArrayList<String> questions;
    private ArrayList<String> answers;
    private ArrayList<Double> markPerQuestion;
    private ArrayList<String> feedback;
    
    public StudentAssignment(){
        
    }
    public StudentAssignment(String title, Module module, double maxMarks, String dueDate, String dateOpened){
        this.title = title;
        this.module = module;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
        this.dateOpened = dateOpened;
        this.completed = false;
        this.marksObtained = 0.0;
        questions = new ArrayList<>();
        markPerQuestion = new ArrayList<>();
        answers = new ArrayList<>();
        feedback = new ArrayList<>();
        
    }
    
    public void setCompleted(boolean completed){
        this.completed = completed;
    }
    
    public boolean getCompleted(){
        return completed;
    }
    
    @Override
    public String toString(){
        String output = String.format("Task: %s\nModule: %s\tAvailable since: %s\tDue at: %s", title, module.getModuleCode(), dateOpened, dueDate);
        return output;
    }
    
    public ArrayList<Double> getMarks(){
        return markPerQuestion;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void addQuestionAnswer(String question, String answer, double mark){
        questions.add(question);
        answers.add(answer);
        markPerQuestion.add(mark);
    }
    
    public void submitAnswer(int questionIndex, String answer){
        if (answer.equalsIgnoreCase(answers.get(questionIndex))){
            marksObtained += markPerQuestion.get(questionIndex);
            
            String out = String.format("Question: %s\nYour answer: %s - CORRECT = %1.1f Marks", questions.get(questionIndex), answer, markPerQuestion.get(questionIndex));
            
            feedback.add(out);
            
            
        } else {
            String out = String.format("Question: %s\nYour answer: %s - INCORRECT\nActual answer: %s", questions.get(questionIndex), answer, answers.get(questionIndex));
            feedback.add(out);
        }
        
        if (questionIndex == questions.size()-1){
            completed = true;
            marksObtained = getMarksObtained();
        }
    }
    
    public ArrayList<String> getFeedback(){
        return feedback;
    }
    
    public ArrayList<String> getQuestions(){
        return questions;
    }
    
    public ArrayList<String> getAnswers(){
        return answers;
    }
    
    public String getModuleCode(){
        return module.getModuleCode();
    }
    
    public double getMarksObtained(){
        double total = (marksObtained/maxMarks) * 100;
        return total;
    }
}