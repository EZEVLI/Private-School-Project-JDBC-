/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AssignmentDto;
import java.util.List;
import java.util.Scanner;

public interface AssignmentDaoInt {
    
    public List<AssignmentDto> fetchAllAssignments();
    public void insertAssignment (AssignmentDto a);
    public List<AssignmentDto> fetchAssignmentsPerCourse(int i);
    public List<AssignmentDto> fetchAssignmentsPerCoursePerStudent(int cid, int sid);
    public void insertCourseIdInAssignment(int aid,int cid);
    public AssignmentDto createNewAssignment(Scanner sc);
    
}
