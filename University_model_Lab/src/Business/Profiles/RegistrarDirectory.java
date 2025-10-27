/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Profiles;

import Business.Business;
import Business.Person.Person;
import java.util.ArrayList;

/**
 *
 * @author Yaksha
 */
public class RegistrarDirectory {
    Business business;
    ArrayList<RegistrarProfile> registrarlist;

    public RegistrarDirectory(Business b) {
        business = b;
        registrarlist = new ArrayList<>();
    }

    public RegistrarProfile newRegistrarProfile(Person p) {
        RegistrarProfile rp = new RegistrarProfile(p);
        registrarlist.add(rp);
        return rp;
    }

    public RegistrarProfile findRegistrar(String id) {
        for (RegistrarProfile rp : registrarlist) {
            if (rp.isMatch(id)) {
                return rp;
            }
        }
        return null;
    }

    public ArrayList<RegistrarProfile> getRegistrarList() {
        return registrarlist;
    }
}
