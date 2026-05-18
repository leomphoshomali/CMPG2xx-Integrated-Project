 

public class Announcement 
{
    String title;
    String type;
    String contentText;
    String date;
    String moduleCode;
    int yearLevel;
    String instructor;
    
    public Announcement(){
        
    }
    
    public Announcement(String title, String contentText, String moduleCode, String instructor, String date){
        this.title = title;
        this.contentText = contentText;
        this.moduleCode = moduleCode;
        this.instructor = instructor;
        this.date = date;
    }
    
    
    
    public String getModuleCode(){
        return moduleCode;
    }
    
    public String showAnnouncement(){
        String out = String.format("ANNOUNCEMENT: %s\nAnnouncement by: %s\nPosted at: %s\n\n%s", title, instructor, date, contentText);
        return out;
    }
    
    @Override
    public String toString(){
        String out = String.format("ANNOUNCEMENT: %s\nAnnouncement by: %s\n_____________________________________________", title, instructor);
        return out;
    }
}