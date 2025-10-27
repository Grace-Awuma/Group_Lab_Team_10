/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Course;

/**
 *
 * @author Yaksha
 */
public class Assignment {
    String assignmentId;
    String title;
    String description;
    double weight;
    Double score;
    String submissionStatus;

    public Assignment(String assignmentId, String title, String description, double weight) {
        this.assignmentId = assignmentId;
        this.title = title;
        this.description = description;
        this.weight = weight;
        this.submissionStatus = "Not Submitted";
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(String submissionStatus) {
        this.submissionStatus = submissionStatus;
    }
}
