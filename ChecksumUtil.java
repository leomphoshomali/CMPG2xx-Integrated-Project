import java.io.FileInputStream;
import java.security.MessageDigest;

public class ChecksumUtil 
{

    public static String generateChecksum(String filePath)
    {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            FileInputStream fis = new FileInputStream(filePath);

            byte[] byteArray = new byte[1024];
            int bytesCount;

            while ((bytesCount = fis.read(byteArray)) != -1) 
            {
                digest.update(byteArray, 0, bytesCount);
            }

            fis.close();

            byte[] bytes = digest.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes)
            {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } 
        catch (Exception e)
        {
            System.out.println("Checksum error: " + e.getMessage());
            return null;
        }
    }
}