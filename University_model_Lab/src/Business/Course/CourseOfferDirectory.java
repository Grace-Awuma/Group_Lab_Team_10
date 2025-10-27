/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Course;

import java.util.ArrayList;

/**
 *
 * @author Yaksha
 */
public class CourseOfferDirectory {
    ArrayList<CourseOffer> courseofferlist;

    public CourseOfferDirectory() {
        courseofferlist = new ArrayList<>();
    }

    public CourseOffer newCourseOffer(String offerId, Course course, String semester, int capacity) {
        CourseOffer offer = new CourseOffer(offerId, course, semester, capacity);
        courseofferlist.add(offer);
        return offer;
    }

    public CourseOffer findCourseOffer(String offerId) {
        for (CourseOffer offer : courseofferlist) {
            if (offer.getOfferId().equals(offerId)) {
                return offer;
            }
        }
        return null;
    }

    public ArrayList<CourseOffer> getCourseOffersBySemester(String semester) {
        ArrayList<CourseOffer> results = new ArrayList<>();
        for (CourseOffer offer : courseofferlist) {
            if (offer.getSemester().equals(semester)) {
                results.add(offer);
            }
        }
        return results;
    }

    public ArrayList<CourseOffer> searchByCourseId(String courseId, String semester) {
        ArrayList<CourseOffer> results = new ArrayList<>();
        for (CourseOffer offer : courseofferlist) {
            if (offer.getSemester().equals(semester) && 
                offer.getCourse().getCourseId().toLowerCase().contains(courseId.toLowerCase())) {
                results.add(offer);
            }
        }
        return results;
    }

    public ArrayList<CourseOffer> searchByTeacher(String teacherName, String semester) {
        ArrayList<CourseOffer> results = new ArrayList<>();
        for (CourseOffer offer : courseofferlist) {
            if (offer.getSemester().equals(semester) && offer.getAssignedFaculty() != null &&
                offer.getAssignedFaculty().getPerson().getFullName().toLowerCase().contains(teacherName.toLowerCase())) {
                results.add(offer);
            }
        }
        return results;
    }

    public ArrayList<CourseOffer> searchByCourseName(String courseName, String semester) {
        ArrayList<CourseOffer> results = new ArrayList<>();
        for (CourseOffer offer : courseofferlist) {
            if (offer.getSemester().equals(semester) && 
                offer.getCourse().getCourseName().toLowerCase().contains(courseName.toLowerCase())) {
                results.add(offer);
            }
        }
        return results;
    }

    public ArrayList<CourseOffer> getCourseOfferList() {
        return courseofferlist;
    }
}
