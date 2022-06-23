package userLogin;

import java.security.NoSuchAlgorithmException;

/**
 * @author Maxine Knight 
 * Admin User subclass of user, adds in permissions
 */

public class AdminUser extends User {
    private String permissions = ""; // Contains any combo of database, cloud, or security
    
    public AdminUser() {
       super();
       permissions = ""; 
    }
    
    public AdminUser(String first, String last, String id, String pass, String perm) throws NoSuchAlgorithmException {
        super(first, last, id, pass);
        // Sets the permissions variable according to the parameter
        setPermissions(perm); 
    }
    
    public void setPermissions(String perm) {
        String[] permList = perm.split(" ");

        for (int i = 0; i < permList.length; i++) {
            permList[i].toLowerCase();
            if(permList[i].equals("database") || permList[i].equals("cloud") || permList[i].equals("security")) {
                if((i != 0) && (i == (permList.length - 1)))
                        permissions += permList[i];
                else 
                    permissions += permList[i] + " ";
            }
        }
    }
    
    public String getPermissions() {
        return permissions;
    }
    
    @Override
    public String userInfo(boolean fullInfo) {
       if(fullInfo == true) { // If user wants the detailed User info
           String st = super.userInfo(fullInfo); // Invoke User class's userInfo to obtain user info
           return st + "\nAdmin permissions: " + permissions; // Add on the user's permissions
       }
       else
           return super.userInfo(fullInfo); 
    }
    
    @Override
    public String toString() {
        return "Admin User"; // Identifies the instance
    }
    
    public static boolean addAdminUser(String first, String last, String id, String pass, String perm, boolean hash) throws NoSuchAlgorithmException {
        // Ensure space, assign instance to next available array element
        
        // Return false if array of users is full
        if (totUsers >= MAXUSERS)
            return false;
                 
        AdminUser userOb = new AdminUser(); // Same as addUser, except creates AdminUser instance
        // Creating a AdminUser object and adding it to the array of users
            for (int i = 0; i < users.length; i++) {
            if (users[i] == null) { // Only add to a position that is empty
                if (hash == false) { // Hash boolean determines whether pass needs to be hashed, if false, assign pass directly
                    isHashed = true; // If this is true, in setPassword method pass will be set directly instead of hashed
                }
                userOb = new AdminUser(first, last, id, pass, perm);
                users[i] = userOb; // Sets the user object into the array position
                totUsers++;
                break;
            }
        }
        return true; // Indicates whether attempt to add user was successful
    }
    
    public static String listAdminUsers(String perm) {
        // List only users that are admin from users array
        // Parameter for which permission type should be included, either database, cloud, security, or any
        
        String adminUserList = "";
        String[] permList = new String[]{"database", "cloud", "security"}; // Create string with each perm value
        int isFound = 0; 

        for (int i = 0; i < users.length; i++) { // Loop through each user in array
            if ((users[i] instanceof AdminUser) && (users[i] != null)) { // Check that it is an instance of AdminUser and not null
                String userPerm = ((AdminUser) User.users[i]).getPermissions(); // Store the user's permissions string 
                String[] permSplit = userPerm.split(" "); // Split each user permission so they can be compared individually

                if (perm.equalsIgnoreCase("any")) { // If the user wants to list the admin users with any permissions
                    for (int j = 0; j < permList.length; j++) { // Will loop through each permission type in array 
                        if (userPerm.contains(permList[j]) && isFound != 1) { // Loops to check if the user permission string contains any permission type
                            adminUserList += "\t" + users[i].userInfo(false) + " (" + users[i].getUserId() + ") | " + userPerm + "\n";
                            isFound = 1;
                        }
                    }
                } else if (!perm.equalsIgnoreCase("any")) { // If the user entry was not "any"
                    for (int j = 0; j < permSplit.length; j++) { // Will loop through each permission that the user has 
                        if (permSplit[j].equalsIgnoreCase(perm) && isFound != 1) {
                            adminUserList += "\t" + users[i].userInfo(false) + " (" + users[i].getUserId() + ") | " + userPerm + "\n";
                            isFound = 1;
                        }
                    }
                } else {
                    adminUserList = "No matching permissions found.";
                }
                isFound = 0; // Reset isFound for the "any"
            }
        }
        return adminUserList;
    }
}
        
