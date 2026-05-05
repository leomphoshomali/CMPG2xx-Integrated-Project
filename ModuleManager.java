import java.util.ArrayList;

public class ModuleManager
{
    private static ArrayList<Module> totalModules;
    public ModuleManager(){
        
    }
    
    public void addModule(Module m){
        totalModules.add(m);
    }
    
    public boolean loadModules(){
        return false;
    }
    
    public ArrayList<Module> getModules(){
        return totalModules;
    }
}