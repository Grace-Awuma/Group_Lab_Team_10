
package Business.Profiles;

import Business.Business;
import Business.Person.Person;
import java.util.ArrayList;
import java.util.Iterator;

public class StudentDirectory {
   Business business;
   ArrayList<StudentProfile> studentlist;

   public StudentDirectory(Business b) {
      this.business = b;
      this.studentlist = new ArrayList();
   }

   public StudentProfile newStudentProfile(Person p) {
      StudentProfile sp = new StudentProfile(p);
      this.studentlist.add(sp);
      return sp;
   }

   public StudentProfile findStudent(String id) {
      Iterator var2 = this.studentlist.iterator();

      StudentProfile sp;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         sp = (StudentProfile)var2.next();
      } while(!sp.isMatch(id));

      return sp;
   }

   public ArrayList<StudentProfile> getStudentList() {
      return this.studentlist;
   }

   public ArrayList<StudentProfile> searchByName(String name) {
      ArrayList<StudentProfile> results = new ArrayList();
      String searchTerm = name.toLowerCase();
      Iterator var4 = this.studentlist.iterator();

      while(var4.hasNext()) {
         StudentProfile sp = (StudentProfile)var4.next();
         if (sp.getPerson().getFullName().toLowerCase().contains(searchTerm)) {
            results.add(sp);
         }
      }

      return results;
   }

   public ArrayList<StudentProfile> searchByDepartment(String dept) {
      ArrayList<StudentProfile> results = new ArrayList();
      Iterator var3 = this.studentlist.iterator();

      while(var3.hasNext()) {
         StudentProfile sp = (StudentProfile)var3.next();
         if (sp.getDepartment().equalsIgnoreCase(dept)) {
            results.add(sp);
         }
      }

      return results;
   }
}
