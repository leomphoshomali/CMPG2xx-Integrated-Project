import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Logger
{

    private static final String LOG_FILE = "files\\audit_log.txt";

    public static void log(String userId, String action)
    {
        try
        {
            PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true));

            out.println(LocalDateTime.now() + " | USER: " + userId + " | ACTION: " + action);

            out.close();

        }
        catch (Exception e) 
        {
            System.out.println("Logging failed: " + e.getMessage());
        }
    }
}
