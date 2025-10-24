/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona.Faculty;

import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.Persona.*;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.SeatAssignment;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class FacultyProfile {

    Person person;
    ArrayList <FacultyAssignment> facultyassignments; 
    
    public FacultyProfile(Person p) {

        person = p;
        facultyassignments = new ArrayList();
    }
    public  double getProfAverageOverallRating(){
        
        double sum = 0.0;
        //for each facultyassignment extract class rating
        //add them up and divide by the number of teaching assignmnet;
        for(FacultyAssignment fa: facultyassignments){
            
            sum = sum + fa.getRating();
            
        }
        //divide by the total number of faculty assignments
        
        return sum/(facultyassignments.size()*1.0); //this ensure we have double/double
        
    }

    public FacultyAssignment AssignAsTeacher(CourseOffer co){
        
        FacultyAssignment fa = new FacultyAssignment(this, co);
        facultyassignments.add(fa);
        
        return fa;
    }
    
    public FacultyProfile getCourseOffer(String courseid){
        return null; //complete it later
    }

    public boolean isMatch(String id) {
        if (person.getPersonId().equals(id)) {
            return true;
        }
        return false;
    }
    
    public ArrayList<SeatAssignment> getEnrolledStudents(CourseOffer courseOffer) {
    return courseOffer.getEnrolledStudents(); // Calls CourseOffer method
}
    /**
 * Access a student's transcript summary
 */
    public String viewStudentTranscript(SeatAssignment seatAssignment) {
    // Get the student's course load
    CourseLoad studentCourseLoad = seatAssignment.getCourseload();
    // Access their transcript through the course load
    // Return formatted transcript info
    return "Student transcript summary...";
}

/**
 * Get performance reports for a specific semester only
 */
    public ArrayList<String> getPerformanceReportsBySemester(String semester) {
    ArrayList<String> reports = new ArrayList<>();
    // Filter faculty assignments by semester
    // Generate reports only for that semester
    return reports;
}

}
