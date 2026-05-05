
public class Module
{
    private String moduleName;
    private String moduleCode;
    private String instructor;
    private int yearLevel;
    private String faculty;
    
    public Module(String moduleName, String moduleCode, String instructor, int yearLevel, String faculty){
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.instructor = instructor;
        this.yearLevel = yearLevel;
        this.faculty = faculty;
    }
    
    public String getModuleName(){
        return moduleName;
    }
    
    public String getModuleCode(){
        return moduleCode;
    }
    
    public int getYearLevel(){
        return yearLevel;
    }
    
    public String getInstructor(){
        return instructor;
    }
    
    public String getFaculty(){
        return faculty;
    }
    
    
    
    
}