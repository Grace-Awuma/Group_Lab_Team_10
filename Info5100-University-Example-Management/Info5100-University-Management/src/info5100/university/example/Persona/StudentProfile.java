/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona;

import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Persona.EmploymentHistory.EmploymentHistroy;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class StudentProfile {
    Person person;
    Transcript transcript;
    EmploymentHistroy employmenthistory;
    StudentAccount studentAccount;
    
    // Profile fields - ADD THESE
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String dateOfBirth;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String major = "MSIS";
    private String program = "Master of Science in Information Systems";
    
    public StudentProfile(Person p) {
        person = p;
        transcript = new Transcript(this);
        employmenthistory = new EmploymentHistroy();
        studentAccount = new StudentAccount(this);
    }
    
    public boolean isMatch(String id) {
        return person.getPersonId().equals(id);
    }
    
    public Transcript getTranscript() {
        return transcript;
    }
    
    public CourseLoad getCourseLoadBySemester(String semester) {
        return transcript.getCourseLoadBySemester(semester);
    }
    
    public CourseLoad getCurrentCourseLoad() {
        return transcript.getCurrentCourseLoad();
    }
    
    public CourseLoad newCourseLoad(String s) {
        return transcript.newCourseLoad(s);
    }
    
    public ArrayList<SeatAssignment> getCourseList() {
        return transcript.getCourseList();
    }
    
    public StudentAccount getStudentAccount() {
        return studentAccount;
    }
    
    public String getStudentId() {
        return person.getPersonId();
    }
    
    // ========== PROFILE MANAGEMENT METHODS - ADD THESE ==========
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName() {
        if (firstName == null || lastName == null) {
            return "Name not set";
        }
        return firstName + " " + lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getStreetAddress() {
        return streetAddress;
    }
    
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public String getFullAddress() {
        if (streetAddress == null || city == null || state == null || zipCode == null) {
            return "Address not set";
        }
        return streetAddress + ", " + city + ", " + state + " " + zipCode;
    }
    
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getEmergencyContactName() {
        return emergencyContactName;
    }
    
    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }
    
    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }
    
    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }
    
    public String getMajor() {
        return major;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    
    public String getProgram() {
        return program;
    }
    
    public void setProgram(String program) {
        this.program = program;
    }

    
}
