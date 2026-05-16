import java.io.Serializable;

/**
 * Base class for module 
 * Implimented Fields 
 * With a default and parametised constructor 
 * with getters and setters 
 * And a toString method to display
 */

public class Module implements Serializable
{
    private String moduleName;
    private String moduleCode;
    private String instructor;
    private int yearLevel;
    private String faculty;
    
    public Module(String moduleName, String moduleCode, String faculty, String instructor, int yearLevel)
    {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.instructor = instructor;
        this.yearLevel = yearLevel;
        this.faculty = faculty;
    }
    
    public Module(String moduleName, String moduleCode, String faculty, int yearLevel)
    {
        setModuleName(moduleName);
        setModuleCode(moduleCode);
        setYearLevel(yearLevel);
        setFaculty(faculty);
    }
    
    public String getModuleName()
    {
        return moduleName;
    }
    
    public String getModuleCode()
    {
        return moduleCode;
    }
    
    public int getYearLevel()
    {
        return yearLevel;
    }
    
    public String getInstructor()
    {
        return instructor;
    }
    
    public String getFaculty()
    {
        return faculty;
    }
    
    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }
    
    public void setModuleCode(String moduleCode)
    {
        this.moduleCode = moduleCode;
    }
    public void setYearLevel(int yearLevel)
    {
        this.yearLevel = yearLevel;
    }
    public void setInstructor(String instructor)
    {
        this.instructor = instructor;
    }
    public void setFaculty(String faculty)
    {
        this.faculty = faculty;
    }
    
    @Override
    public String toString(){
        String info = String.format("Module name: %s\nModule Code: %s\nInstructor: %s\nYear Level: %d\nFaculty: %s", moduleName, moduleCode, instructor, yearLevel, faculty);
        return info;
    }
}