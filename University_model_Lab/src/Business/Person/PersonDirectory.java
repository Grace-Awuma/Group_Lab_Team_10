/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
package Business.Person;

import java.util.ArrayList;
import java.util.Iterator;

public class PersonDirectory {
   ArrayList<Person> personlist = new ArrayList();
   private int personCounter = 1001;

   public Person newPerson(String firstName, String lastName) {
      Object[] var10001 = new Object[]{this.personCounter++};
      String id = "PER" + String.format("%06d", var10001);
      Person p = new Person(id, firstName, lastName);
      this.personlist.add(p);
      return p;
   }

   public Person newPersonWithId(String id, String firstName, String lastName) {
      Person p = new Person(id, firstName, lastName);
      this.personlist.add(p);
      return p;
   }

   public Person findPerson(String id) {
      Iterator var2 = this.personlist.iterator();

      Person p;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         p = (Person)var2.next();
      } while(!p.isMatch(id));

      return p;
   }

   public boolean emailExists(String email) {
      Iterator var2 = this.personlist.iterator();

      Person p;
      do {
         if (!var2.hasNext()) {
            return false;
         }

         p = (Person)var2.next();
      } while(p.getEmail() == null || !p.getEmail().equalsIgnoreCase(email));

      return true;
   }

   public ArrayList<Person> getPersonList() {
      return this.personlist;
   }

   public String generateId(String prefix) {
      return prefix + String.format("%06d", this.personCounter++);
   }
}
