/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.UserAccounts;

import Business.Profiles.Profile;
import java.util.ArrayList;

/**
 *
 * @author Yaksha
 */
public class UserAccountDirectory {
    
    private ArrayList<UserAccount> useraccountlist;
    
    public UserAccountDirectory() {
        useraccountlist = new ArrayList<>();
    }
    
    public UserAccount newUserAccount(Profile role, String username, String password) {
        UserAccount ua = new UserAccount(role, username, password);
        useraccountlist.add(ua);
        return ua;
    }
    
    public UserAccount authenticateUser(String username, String password) {
        for (UserAccount ua : useraccountlist) {
            if (ua.isValidUser(username, password)) {
                return ua;
            }
        }
        return null;
    }
    
    public UserAccount findUserAccountByPersonId(String personId) {
        for (UserAccount ua : useraccountlist) {
            if (ua.isMatch(personId)) {
                return ua;
            }
        }
        return null;
    }
    
    public UserAccount findUserAccountByUsername(String username) {
        for (UserAccount ua : useraccountlist) {
            if (ua.getUserLoginName().equalsIgnoreCase(username)) {
                return ua;
            }
        }
        return null;
    }
    
    public boolean usernameExists(String username) {
        return findUserAccountByUsername(username) != null;
    }
    
    public ArrayList<UserAccount> getUserAccountList() {
        return useraccountlist;
    }
}