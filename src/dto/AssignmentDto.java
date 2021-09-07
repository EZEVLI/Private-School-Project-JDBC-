/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.time.LocalDateTime;

public class AssignmentDto {

    private int assignmentID;
    private String title;
    private String description;
    private LocalDateTime subDateTime;
    private int totalMark;
    private int oralMark;

    public AssignmentDto(int assignmentID, String title, String description, LocalDateTime subDateTime, int totalMark, int oralMark) {
        this.assignmentID = assignmentID;
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.totalMark = totalMark;
        this.oralMark = oralMark;
    }

    public AssignmentDto(String title, String description, LocalDateTime subDateTime, int totalMark, int oralMark) {
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.totalMark = totalMark;
        this.oralMark = oralMark;
    }

    public AssignmentDto() {
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
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

    public LocalDateTime getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(LocalDateTime subDateTime) {
        this.subDateTime = subDateTime;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    public int getOralMark() {
        return oralMark;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    @Override
    public String toString() {
        return "Assignment{" + "assignmentID=" + assignmentID + ", title=" + title + ", description=" + description + ", subDateTime=" + subDateTime + ", totalMark=" + totalMark + ", oralMark=" + oralMark + '}';
    }

}
