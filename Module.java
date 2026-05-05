
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
    
    public void setModuleName(String moduleName){
        this.moduleName = moduleName;
    }
    
    public void setModuleCode(String moduleCode){
        this.moduleCode = moduleCode;
    }
    public void setYearLevel(int yearLevel){
        this.yearLevel = yearLevel;
    }
    public void setInstructor(String instructor){
        this.instructor = instructor;
    }
    public void setFaculty(String faculty){
        this.faculty = faculty;
    }
    
    @Override
    public String toString(){
        String info = String.format("Module name: %s\nModule Code: %s\nInstructor: %s\nYear Level: %d\nFaculty: %s", moduleName, moduleCode, instructor, yearLevel, faculty);
        return info;
    }
    
    
}