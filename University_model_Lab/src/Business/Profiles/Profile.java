 
package Business.Profiles;

import Business.Person.Person;

public abstract class Profile {
   Person person;

   public Profile(Person p) {
      this.person = p;
   }

   public abstract String getRole();

   public Person getPerson() {
      return this.person;
   }

   public boolean isMatch(String id) {
      return this.person.getPersonId().equals(id);
   }

   public String toString() {
      String var10000 = this.person.toString();
      return var10000 + " (" + this.getRole() + ")";
   }
}
