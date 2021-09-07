/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class StudentAssignment {

    private int studentId;
    private int assignmentId;
    private int oralMark;       //ΕΙΝΑΙ Η ΒΑΘΜΟΛΟΓΙΑ ΤΟΥ ΚΑΘΕ ΜΑΘΗΤΗ ΓΙΑ ΤΟ ASSIGNMENT
    private int totalMark;

    public StudentAssignment(int studentId, int assignmentId, int oralMark, int totalMark) {
        this.studentId = studentId;
        this.assignmentId = assignmentId;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public StudentAssignment(int studentId, int assignmentId) {
        this.studentId = studentId;
        this.assignmentId = assignmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getOralMark() {
        return oralMark;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

}
