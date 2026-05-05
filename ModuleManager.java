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
    
    public boolean removeModule(Module m){
        boolean isRemoved = false;
        for (int i = 0; i < totalModules.size(); i++){
            Module obj = totalModules.get(i);
            if (obj.getModuleCode().equals(m.getModuleCode())){
                totalModules.remove(i);
                isRemoved = true;
                break;
            }
            
        }
        
        return isRemoved;
    }
}