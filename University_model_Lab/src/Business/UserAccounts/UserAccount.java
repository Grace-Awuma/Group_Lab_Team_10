/*
 * 
 */
package Business.UserAccounts;

import Business.Profiles.Profile;

/**
 * @author Yaksha
 */
public class UserAccount {
    
    private Profile role;
    private String username;
    private String password;
    
    public UserAccount(Profile role, String username, String password) {
        this.role = role;
        this.username = username;
        this.password = password;
    }
    
    public String getPersonId() {
        return role.getPerson().getPersonId();
    }
    
    public String getUserLoginName() {
        return username;
    }
    
    public boolean isMatch(String id) {
        return getPersonId().equals(id);
    }
    
    public boolean isValidUser(String un, String pw) {
        return username.equalsIgnoreCase(un) && password.equals(pw);
    }
    
    // Fixed: Changed from getProfile() to getRole()
    public String getRole() {
        return role.getRole();
    }
    
    public Profile getAssociatedPersonRole() {
        return role;
    }
    
    @Override
    public String toString() {
        return getUserLoginName();
    }
}