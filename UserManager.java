import java.util.ArrayList;
import java.util.Random;

public class UserManager {

    private static ArrayList<User> totalUsers = new ArrayList<>();
    private static Random random = new Random();

    public UserManager() {
    }

    public void addUser(User u) {
        totalUsers.add(u);
    }

    public User getUser(User u) {
        for (User user : totalUsers) {
            if (user.getUserId().equals(u.getUserId())) {
                return user;
            }
        }
        return null;
    }

    public boolean removeUser(User u) {
        for (int i = 0; i < totalUsers.size(); i++) {
            if (u.getUserId().equals(totalUsers.get(i).getUserId())) {
                totalUsers.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean loadUsers() {
        totalUsers = new ArrayList<>();
        return true;
    }

    public String generateUserId(String role) {
        String prefix;

        switch (role.toLowerCase()) {
            case "admin":
                prefix = "A-";
                break;
            case "lecturer":
                prefix = "E-";
                break;
            case "student":
                prefix = "S-";
                break;
            default:
                prefix = "U-";
        }

        String id;
        boolean exists;

        do {
            int number = 1000000 + random.nextInt(9000000);
            id = prefix + number;

            exists = false;

            for (User user : totalUsers) {
                if (user.getUserId().equals(id)) {
                    exists = true;
                    break;
                }
            }

        } while (exists);

        return id;
    }
}
