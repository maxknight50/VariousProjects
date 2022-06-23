package userLogin;

import java.security.NoSuchAlgorithmException;

/**
 * @author Maxine Knight 
 * Superclass User
 */

class User {

    private String firstName;
    private String lastName;
    private String userID;
    private String password;
    
    protected static int totUsers = 0; // Static member variable
    protected static final int MAXUSERS = 4; // Maximum number of users that can be stored
    protected static User users[] = new User[MAXUSERS]; // Static member variable users
    protected static boolean isHashed = false; // Variable to determine if pass is hashed or not
    
    public User() { // Sets defaults
        firstName = "FIRST";
        lastName = "LAST";
        userID = "Unknown";
        password = "Unknown";
    }

    public User(String first, String last, String ID, String pass) throws NoSuchAlgorithmException {
        // Call mutators to set the corresponding member variables
        setFirstName(first);
        setLastName(last);
        setUserId(ID);
        setPassword(pass); 
    }
    
    public String userInfo(boolean fullData) {
        String returnString = ""; 
        if(fullData == true) {
            // Concatenate, with appropriate labels, the first and last names, ID, and password
            returnString = "First Name: " + firstName + "\nLast Name: " + lastName + "\nUser ID: " + userID + "\nPassword: " + password; 
            return returnString;
        }
        else {
            // Only the first and last names should be concatenated together for return string with no labels
            returnString = firstName + " " + lastName; 
            return returnString; 
        }
    }
    
    public boolean equals(String st) {
        // String contains first name, space, then last name
        // Tests if parameter is equal to user name, even if different cases
        if(st.toLowerCase().equals(firstName.toLowerCase() + " " + lastName.toLowerCase()))
            return true; 
        else
            return false;
    }
    
    public boolean isValidPassword(String pass) {
        // Check if less than 8 characters or if password contains any spaces
        if ((pass.length() < 8) || (pass.contains(" "))) 
            return false;
        
        // Loop from 0 to 9 and check if password contains any digits
        int count = 0;
        for (int i = 0; i <= 9; i++) {
            String integer = Integer.toString(i); // Convert i into a String to compare
            if (pass.contains(integer)) {
                count = 1;
                break; // Digit has been located, so exit loop
            }
        } 
        if (count == 0) // If no digits are found, not valid
            return false;
        
        // Loop through each character and check if password contains at least 1 special character
        count = 0;
        for (int i = 0; i < pass.length(); i++) {
            if (!Character.isLetterOrDigit(pass.charAt(i))) { // If the character is not a letter or a digit, it is a special character 
                count = 1;
                break; // Special has been found, so exit loop
            }
        }
        if (count == 0) // If no specials are found, not valid
            return false;

        // Loop through each character and check if password contains at least 1 upper and 1 lower case
        int lowerCount = 0;
        int upperCount = 0;
        for(int i = 0; i < pass.length(); i++) {
            if(Character.isLowerCase(pass.charAt(i)) == true) 
                lowerCount = 1; // If at least 1 character is lower case, count is 1
            if(Character.isUpperCase(pass.charAt(i)) == true)
                upperCount = 1; // If at least 1 character is upper case, count is 1
        }
        if ((lowerCount == 0 || upperCount == 0)) { // If either the lower or upper count is 0, password is not valid 
            return false; 
        }
        
        // After all tests pass, return true as password is valid
        else { 
            return true;
        }
    }

    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String first) {
        //Ensure storage with correct case. First char caps, all else lower
        first = first.toLowerCase(); // Converts whole string to lower case
        char firstLetter = Character.toUpperCase(first.charAt(0)); // Converts first letter to uppercase
        this.firstName = firstLetter + first.substring(1); // Set firstName to the concatenated firstLetter and lowercase rest of the String
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String last) {
        //Ensure storage with correct case. First char caps, all else lower. See setFirstName for additional comments.
        last = last.toLowerCase();
        char firstLetter = Character.toUpperCase(last.charAt(0));
        this.lastName = firstLetter + last.substring(1);
    }
    
    public String getUserId() {
        return userID;
    }
    
    public void setUserId(String id) {
        // Ensure ID has no spaces and begins with an alphabetic letter
        char c = id.charAt(0);
        if((id.contains(" ")) || (!Character.isAlphabetic(c))) 
            id = "Unknown"; // Not valid if ID contains a space or first character is not alphabetic
        
        // Loop through each character and ensure ID contains only alphabetic letters or numeric digits
        for (int i = 1; i < id.length(); i++) { // Already tested first character, so start at 1
            if ((!Character.isAlphabetic(id.charAt(i))) && (!Character.isDigit(id.charAt(i)))) {
                id = "Unknown"; // If a character is not alphabetic or a digit, not valid
            }
        }
        userID = id; // If the tests pass, set the userID         
    }
    
    public String getPassword() {
        return password; 
    }
    
    public void setPassword(String pass) throws NoSuchAlgorithmException {
        if (isHashed == true) { // If the password is already hashed, set it directly
            password = pass;
        }
        else if (isValidPassword(pass) == true) { // If it is a valid password, use cryptographic hashing to store
                // getSHA takes string, performs hash algorithm, and produces byte array
                // toHexString converts the byte array to string with hexadecimal notation
                password = GFG.toHexString(GFG.getSHA(pass));
            }
        else { // Password is not valid, so set password to unknown
            password = "Unknown";
        }
        isHashed = false; // Reset isHashed to false
    }
    
    public static String printUserInfo() { // Returns full info about each user on a new line separated by tabs
        String userInfo = "";
        for (User user : users) { // Loop through every user in array
            if (user != null) { // Makes sure entry is not null
                userInfo += user.getFirstName() + "\t";
                userInfo += user.getLastName() + "\t";
                userInfo += user.getUserId() + "\t";
                userInfo += user.getPassword() + "\t\n";
            }
        }
        return userInfo;
    }
    
    public static String listUsers() {
        // Loop through array listing users, return name and ID of all users in the array on separate lines
        String userList = ""; 
        for (User user : users) { // Loop through every user in array
            if (user != null) // List only if not null
                userList += user.toString() + " | " + user.firstName + " " + user.lastName + " (" + user.userID + ")\n";
        }
        return userList;
    }
    
    public static int findUser(String firstAndLast) {
        // Takes first and last name of user separated by space, searches array and looks for match
        for(int i = 0; i < users.length; i++) {
            if(users[i] == null)
                break;
            if(users[i].equals(firstAndLast)) // Call equals method to test
                return i; // Return index value
        }
        return -1; // Returns -1 if user is not found
    }
    
    public static boolean addUser(String first, String last, String id, String pass, boolean hash) throws NoSuchAlgorithmException {
         // Return false if array of users is full
         if (totUsers >= MAXUSERS)
            return false;
        
        User userOb = new User();
        // Creating a User object and adding it to the array of users
            for (int i = 0; i < users.length; i++) {
            if (users[i] == null) { // Only add to a position that is empty
                if (hash == false) { // Hash boolean determines whether pass needs to be hashed, if false, assign pass directly
                    isHashed = true; // If this is true, in setPassword method pass will be set directly instead of hashed
                }
                userOb = new User(first, last, id, pass);
                users[i] = userOb; // Sets the user object into the array position
                totUsers++;
                break;
            }
        }
        return true; // Indicates whether attempt to add user was successful
    }
    
    public static User getUser(int index) {
        // Method returns user in the array element index
        return users[index];
    }
    
    public static String userLogin(String id, String pass) throws NoSuchAlgorithmException {
        // Return result of the login attempt based on searching the users array and validating the password
        int found = 0;
        String hashPass = GFG.toHexString(GFG.getSHA(pass)); // Convert pass into hashed value for comparison 
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (id.equals(users[i].userID)) {
                    found = 1; // User is found
                    if (hashPass.equals(users[i].password)) { // If the hashed passwords are equal, success
                        return "Login successful. Welcome, " + users[i].getFirstName() + " " + users[i].getLastName() + "!";
                    } else if (users[i].password.equals("Unknown")) { // If the password is "Unknown", this means it was invalid when created
                        return pass + " is an invalid password. Please recreate user.";
                    } else { // Entered into the if statement so user was found, but no password match
                        return "Error: User is found but password does not match.";
                    }
                }
            }
        }
        if (found == 0) // Never enters the first if statement so user was not found
            return "Error: UserID does not exist.";
        
        return "Unknown error.";
    }
    
    @Override
    public String toString() {
        // If you return an instance ref of the User class, the toString method will be automatically be invoked
        return "User";
    }
        
}
        
    
