import java.util.ArrayList;
import java.io.Serializable;

public class ModuleManager implements Serializable
{
    private FileService files = new FileService();
    private String fileName = "files\\Modules.ser";
    private static ArrayList<Module> modules = new ArrayList<>();
    public ModuleManager() {
      
    }
    
    public void addModule(Module m){
        modules.add(m);
    }
    
    public void loadModules(){
        modules = files.getModules(fileName);
        
    }
    
    public void loadModulesInitial(){
         
        // this.modules.add(new Module("Calculus I", "MTHS111", "School of Mathematical Sciences and Statistics", null, 1));
        // this.modules.add(new Module("Statistics I", "STAT101", "School of Mathematical Sciences and Statistics", null, 1));
        // this.modules.add(new Module("Algebra Foundations", "MTHS101", "School of Mathematical Sciences and Statistics", null, 1));
        // this.modules.add(new Module("Intro to Mathematics", "MTHS102", "School of Mathematical Sciences and Statistics", null, 1));
        // this.modules.add(new Module("Mathematical Thinking", "MTHS103", "School of Mathematical Sciences and Statistics", null, 1));
        // this.modules.add(new Module("Probability Basics", "STAT102", "School of Mathematical Sciences and Statistics", null, 1));
        
        // this.modules.add(new Module("Linear Algebra", "MTHS212", "School of Mathematical Sciences and Statistics", null, 2));
        // this.modules.add(new Module("Linear Algebra II", "MTHS222", "School of Mathematical Sciences and Statistics", null, 2));
        // this.modules.add(new Module("Calculus II", "MTHS121", "School of Mathematical Sciences and Statistics", null, 2));
        // this.modules.add(new Module("Statistics II", "STAT201", "School of Mathematical Sciences and Statistics", null, 2));
        // this.modules.add(new Module("Discrete Mathematics", "MTHS213", "School of Mathematical Sciences and Statistics", null, 2));
        // this.modules.add(new Module("Mathematical Software", "MTHS214", "School of Mathematical Sciences and Statistics", null, 2));
        
        // this.modules.add(new Module("Probability Theory", "STAT301", "School of Mathematical Sciences and Statistics", null, 3));
        // this.modules.add(new Module("Numerical Methods", "MTHS311", "School of Mathematical Sciences and Statistics", null, 3));
        // this.modules.add(new Module("Real Analysis", "MTHS321", "School of Mathematical Sciences and Statistics", null, 3));
        // this.modules.add(new Module("Abstract Algebra", "MTHS322", "School of Mathematical Sciences and Statistics", null, 3));
        // this.modules.add(new Module("Statistical Inference", "STAT302", "School of Mathematical Sciences and Statistics", null, 3));
        // this.modules.add(new Module("Mathematical Modelling", "MTHS311B", "School of Mathematical Sciences and Statistics", null, 3));
        
        // this.modules.add(new Module("Advanced Modelling", "MTHS411", "School of Mathematical Sciences and Statistics", null, 4));
        // this.modules.add(new Module("Advanced Statistics", "STAT401", "School of Mathematical Sciences and Statistics", null, 4));
        // this.modules.add(new Module("Complex Analysis", "MTHS412", "School of Mathematical Sciences and Statistics", null, 4));
        // this.modules.add(new Module("Operations Research", "MTHS413", "School of Mathematical Sciences and Statistics", null, 4));
        // this.modules.add(new Module("Applied Mathematics", "MTHS414", "School of Mathematical Sciences and Statistics", null, 4));
        // this.modules.add(new Module("Research Project", "MTHS415", "School of Mathematical Sciences and Statistics", null, 4));
        
        // this.modules.add(new Module("Computer Science I", "CS101", "Faculty of Natural and Agricultural Sciences", null, 1));
        // this.modules.add(new Module("Agriculture", "AGR101", "Faculty of Natural and Agricultural Sciences", null, 1));
        // this.modules.add(new Module("Biology I", "BIO101", "Faculty of Natural and Agricultural Sciences", null, 1));
        // this.modules.add(new Module("Chemistry I", "CHE101", "Faculty of Natural and Agricultural Sciences", null, 1));
        // this.modules.add(new Module("Environmental Science", "ENV101", "Faculty of Natural and Agricultural Sciences", null, 1));
        // this.modules.add(new Module("Intro to Programming", "CS102", "Faculty of Natural and Agricultural Sciences", null, 1));
        
        // this.modules.add(new Module("Object-Oriented Programming", "CS201", "Faculty of Natural and Agricultural Sciences", null, 2));
        // this.modules.add(new Module("Data Structures", "CS202", "Faculty of Natural and Agricultural Sciences", null, 2));
        // this.modules.add(new Module("Plant Biology", "BIO201", "Faculty of Natural and Agricultural Sciences", null, 2));
        // this.modules.add(new Module("Organic Chemistry", "CHE201", "Faculty of Natural and Agricultural Sciences", null, 2));
        // this.modules.add(new Module("Soil Science", "AGR201", "Faculty of Natural and Agricultural Sciences", null, 2));
        // this.modules.add(new Module("Ecology", "ENV201", "Faculty of Natural and Agricultural Sciences", null, 2));
        
        // this.modules.add(new Module("Algorithms", "CS301", "Faculty of Natural and Agricultural Sciences", null, 3));
        // this.modules.add(new Module("Operating Systems", "CS302", "Faculty of Natural and Agricultural Sciences", null, 3));
        // this.modules.add(new Module("Databases", "CS303", "Faculty of Natural and Agricultural Sciences", null, 3));
        // this.modules.add(new Module("Computer Networks", "CS304", "Faculty of Natural and Agricultural Sciences", null, 3));
        // this.modules.add(new Module("Animal Science", "AGR301", "Faculty of Natural and Agricultural Sciences", null, 3));
        // this.modules.add(new Module("Genetics", "BIO301", "Faculty of Natural and Agricultural Sciences", null, 3));
        
        // this.modules.add(new Module("Artificial Intelligence", "CS401", "Faculty of Natural and Agricultural Sciences", null, 4));
        // this.modules.add(new Module("Machine Learning", "CS402", "Faculty of Natural and Agricultural Sciences", null, 4));
        // this.modules.add(new Module("Data Science", "CS403", "Faculty of Natural and Agricultural Sciences", null, 4));
        // this.modules.add(new Module("Advanced Agriculture", "AGR401", "Faculty of Natural and Agricultural Sciences", null, 4));
        // this.modules.add(new Module("Research Project", "FNAS401", "Faculty of Natural and Agricultural Sciences", null, 4));
        // this.modules.add(new Module("Bioinformatics", "BIO401", "Faculty of Natural and Agricultural Sciences", null, 4));
        
        // this.modules.add(new Module("English I", "ENG101", "Faculty of Humanities", null, 1));
        // this.modules.add(new Module("Philosophy I", "PHI101", "Faculty of Humanities", null, 1));
        // this.modules.add(new Module("Introduction to Sociology", "SOC101", "Faculty of Humanities", null, 1));
        // this.modules.add(new Module("Zulu Language I", "ZUL101", "Faculty of Humanities", null, 1));
        // this.modules.add(new Module("Media Studies I", "MED101", "Faculty of Humanities", null, 1));
        // this.modules.add(new Module("Cultural Studies I", "CUL101", "Faculty of Humanities", null, 1));
        
        // this.modules.add(new Module("English II", "ENG201", "Faculty of Humanities", null, 2));
        // this.modules.add(new Module("Philosophy II", "PHI201", "Faculty of Humanities", null, 2));
        // this.modules.add(new Module("Anthropology", "ANT201", "Faculty of Humanities", null, 2));
        // this.modules.add(new Module("Zulu Language II", "ZUL201", "Faculty of Humanities", null, 2));
        // this.modules.add(new Module("Sociology II", "SOC201", "Faculty of Humanities", null, 2));
        // this.modules.add(new Module("Ethics", "PHI202", "Faculty of Humanities", null, 2));
        
        // this.modules.add(new Module("Political Science", "POL301", "Faculty of Humanities", null, 3));
        // this.modules.add(new Module("Advanced Sociology", "SOC301", "Faculty of Humanities", null, 3));
        // this.modules.add(new Module("Cultural Studies II", "CUL301", "Faculty of Humanities", null, 3));
        // this.modules.add(new Module("Linguistics", "LIN301", "Faculty of Humanities", null, 3));
        // this.modules.add(new Module("Media Studies II", "MED301", "Faculty of Humanities", null, 3));
        // this.modules.add(new Module("Research Methods", "HUM301", "Faculty of Humanities", null, 3));
        
        // this.modules.add(new Module("Honours Philosophy", "PHI401", "Faculty of Humanities", null, 4));
        // this.modules.add(new Module("Honours Sociology", "SOC401", "Faculty of Humanities", null, 4));
        // this.modules.add(new Module("Advanced Political Theory", "POL401", "Faculty of Humanities", null, 4));
        // this.modules.add(new Module("Advanced Linguistics", "LIN401", "Faculty of Humanities", null, 4));
        // this.modules.add(new Module("Humanities Research Project", "HUM401", "Faculty of Humanities", null, 4));
        // this.modules.add(new Module("Cultural Analysis", "CUL401", "Faculty of Humanities", null, 4));
        
        // this.modules.add(new Module("Economics I", "ECO101", "Faculty of Commerce", null, 1));
        // this.modules.add(new Module("Accounting I", "ACC101", "Faculty of Commerce", null, 1));
        // this.modules.add(new Module("Business Management I", "BUS101", "Faculty of Commerce", null, 1));
        // this.modules.add(new Module("Marketing I", "MKT101", "Faculty of Commerce", null, 1));
        // this.modules.add(new Module("Business Law", "LAW101", "Faculty of Commerce", null, 1));
        // this.modules.add(new Module("Business Mathematics", "MAT101", "Faculty of Commerce", null, 1));
        
        // this.modules.add(new Module("Microeconomics", "ECO201", "Faculty of Commerce", null, 2));
        // this.modules.add(new Module("Accounting II", "ACC201", "Faculty of Commerce", null, 2));
        // this.modules.add(new Module("Marketing II", "MKT201", "Faculty of Commerce", null, 2));
        // this.modules.add(new Module("Business Management II", "BUS201", "Faculty of Commerce", null, 2));
        // this.modules.add(new Module("Financial Management", "FIN201", "Faculty of Commerce", null, 2));
        // this.modules.add(new Module("Statistics for Business", "STA201", "Faculty of Commerce", null, 2));
        
        // this.modules.add(new Module("Macroeconomics", "ECO301", "Faculty of Commerce", null, 3));
        // this.modules.add(new Module("Financial Accounting", "ACC301", "Faculty of Commerce", null, 3));
        // this.modules.add(new Module("Econometrics", "ECO302", "Faculty of Commerce", null, 3));
        // this.modules.add(new Module("Taxation", "TAX301", "Faculty of Commerce", null, 3));
        // this.modules.add(new Module("Corporate Finance", "FIN301", "Faculty of Commerce", null, 3));
        // this.modules.add(new Module("Supply Chain Management", "SCM301", "Faculty of Commerce", null, 3));
        
        // this.modules.add(new Module("Investment Analysis", "FIN401", "Faculty of Commerce", null, 4));
        // this.modules.add(new Module("Advanced Economics", "ECO401", "Faculty of Commerce", null, 4));
        // this.modules.add(new Module("Auditing", "ACC401", "Faculty of Commerce", null, 4));
        // this.modules.add(new Module("Strategic Management", "BUS401", "Faculty of Commerce", null, 4));
        // this.modules.add(new Module("Financial Risk Management", "FIN402", "Faculty of Commerce", null, 4));
        // this.modules.add(new Module("Commerce Research Project", "COM401", "Faculty of Commerce", null, 4));
        
        // this.modules.add(new Module("Human Anatomy", "HLS101", "Faculty of Health Sciences", null, 1));
        // this.modules.add(new Module("Psychology I", "PSY101", "Faculty of Health Sciences", null, 1));
        // this.modules.add(new Module("Biology I", "BIO101", "Faculty of Health Sciences", null, 1));
        // this.modules.add(new Module("Chemistry I", "CHE101", "Faculty of Health Sciences", null, 1));
        // this.modules.add(new Module("Health Systems I", "HLS102", "Faculty of Health Sciences", null, 1));
        // this.modules.add(new Module("Intro to Public Health", "HLS103", "Faculty of Health Sciences", null, 1));
        
        // this.modules.add(new Module("Physiology", "HLS201", "Faculty of Health Sciences", null, 2));
        // this.modules.add(new Module("Biochemistry", "HLS202", "Faculty of Health Sciences", null, 2));
        // this.modules.add(new Module("Psychology II", "PSY201", "Faculty of Health Sciences", null, 2));
        // this.modules.add(new Module("Microbiology", "BIO201", "Faculty of Health Sciences", null, 2));
        // this.modules.add(new Module("Nutrition", "HLS203", "Faculty of Health Sciences", null, 2));
        // this.modules.add(new Module("Epidemiology Basics", "HLS204", "Faculty of Health Sciences", null, 2));
        
        // this.modules.add(new Module("Pharmacology", "HLS301", "Faculty of Health Sciences", null, 3));
        // this.modules.add(new Module("Pathology", "HLS302", "Faculty of Health Sciences", null, 3));
        // this.modules.add(new Module("Public Health", "HLS303", "Faculty of Health Sciences", null, 3));
        // this.modules.add(new Module("Immunology", "HLS304", "Faculty of Health Sciences", null, 3));
        // this.modules.add(new Module("Clinical Skills", "HLS305", "Faculty of Health Sciences", null, 3));
        // this.modules.add(new Module("Medical Ethics", "HLS306", "Faculty of Health Sciences", null, 3));
        
        // this.modules.add(new Module("Advanced Pharmacology", "HLS401", "Faculty of Health Sciences", null, 4));
        // this.modules.add(new Module("Clinical Practice", "HLS402", "Faculty of Health Sciences", null, 4));
        // this.modules.add(new Module("Epidemiology", "HLS403", "Faculty of Health Sciences", null, 4));
        // this.modules.add(new Module("Healthcare Management", "HLS404", "Faculty of Health Sciences", null, 4));
        // this.modules.add(new Module("Research Project", "HLS405", "Faculty of Health Sciences", null, 4));
        // this.modules.add(new Module("Advanced Anatomy", "HLS406", "Faculty of Health Sciences", null, 4));
    }
    
    public ArrayList<Module> getModules(){
        return modules;
    }
    
    public void saveModules(){
        files.saveModulesToFile(modules, fileName);
    }
    
    public boolean removeModule(Module m){
        boolean isRemoved = false;
        for (int i = 0; i < modules.size(); i++){
            Module obj = modules.get(i);
            
            if (obj.getModuleCode().equals(m.getModuleCode())){
                modules.remove(i);
                isRemoved = true;
                break;
            }
            
        }
        
        return isRemoved;
    }
}