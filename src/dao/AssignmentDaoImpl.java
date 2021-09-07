/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AssignmentDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DbUtils;
import utils.InputValidator;

public class AssignmentDaoImpl implements AssignmentDaoInt {

    Connection con = null;

    @Override
    public List<AssignmentDto> fetchAllAssignments() {
        LinkedList<AssignmentDto> allAssignments = new LinkedList();
        String sql = "SELECT ASSIGNMENTID, TITLE, DESCRIPTION, SUBDATETIME, TOTALMARK, ORALMARK FROM ASSIGNMENT;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                AssignmentDto a = new AssignmentDto(rs.getInt("ASSIGNMENTID"), rs.getString("TITLE"),
                        rs.getString("DESCRIPTION"), rs.getTimestamp("SUBDATETIME").toLocalDateTime(),
                        rs.getInt("TOTALMARK"), rs.getInt("ORALMARK"));
                allAssignments.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return allAssignments;
        }
    }

    @Override
    public void insertAssignment(AssignmentDto a) {
        String sql = "INSERT INTO ASSIGNMENT (ASSIGNMENTID, TITLE, DESCRIPTION, SUBDATETIME, TOTALMARK,ORALMARK, COURSEID) VALUES (NULL, ?,?,?,?,?,NULL);";
        PreparedStatement ps = null;

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(a.getSubDateTime()));
            ps.setInt(4, a.getTotalMark());
            ps.setInt(5, a.getOralMark());
            ps.executeUpdate();
            System.out.println("done!!");
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public List<AssignmentDto> fetchAssignmentsPerCourse(int i) {
        LinkedList<AssignmentDto> assignmentsPerCourse = new LinkedList();
        String sql = "SELECT ASSIGNMENTID, TITLE, DESCRIPTION, SUBDATETIME, "
                + "TOTALMARK, ORALMARK FROM ASSIGNMENT WHERE ASSIGNMENT.COURSEID=?;";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AssignmentDto a = new AssignmentDto(rs.getInt("ASSIGNMENTID"), rs.getString("TITLE"),
                        rs.getString("DESCRIPTION"), rs.getTimestamp("SUBDATETIME").toLocalDateTime(),
                        rs.getInt("TOTALMARK"), rs.getInt("ORALMARK"));
                assignmentsPerCourse.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return assignmentsPerCourse;
        }
    }

    @Override
    public List<AssignmentDto> fetchAssignmentsPerCoursePerStudent(int cid, int sid) {
        LinkedList<AssignmentDto> assignmentsPerCoursePerStudent = new LinkedList();
        String sql = "SELECT ASSIGNMENT.ASSIGNMENTID, ASSIGNMENT.TITLE, ASSIGNMENT.DESCRIPTION, "
                + "ASSIGNMENT.SUBDATETIME, ASSIGNMENT.TOTALMARK, ASSIGNMENT.ORALMARK "
                + "FROM ASSIGNMENT,ENROLL WHERE ASSIGNMENT.COURSEID=? "
                + "AND ENROLL.STUDENTID=? GROUP BY  ASSIGNMENT.ASSIGNMENTID;";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.setInt(2, sid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AssignmentDto a = new AssignmentDto(rs.getInt("ASSIGNMENTID"), rs.getString("TITLE"),
                        rs.getString("DESCRIPTION"), rs.getTimestamp("SUBDATETIME").toLocalDateTime(),
                        rs.getInt("TOTALMARK"), rs.getInt("ORALMARK"));
                assignmentsPerCoursePerStudent.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return assignmentsPerCoursePerStudent;
        }
    }

    @Override
    public void insertCourseIdInAssignment(int aid, int cid) {
        String sql = "UPDATE ASSIGNMENT SET COURSEID=(SELECT COURSEID FROM COURSE WHERE COURSEID =?) WHERE ASSIGNMENTID=?;";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.setInt(2, aid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public AssignmentDto createNewAssignment(Scanner sc) {  //ΟΙ ΒΑΘΜΟΙ TOTAL KAI ORAL MARK EINAI H ΜΕΓΙΣΤΗ ΒΑΘΜΟΛΟΓΙΑ ΠΟΥ ΜΠΟΡΕΙ ΝΑ ΠΑΡΕΙ ΕΝΑΣ ΜΑΘΗΤΗΣ ΣΤΟ ASSIGNMENT
        System.out.println("Please type assigments's data in the format  \"title, description\" ");
        String[] oneAssignment = InputValidator.checkUsersInputForStudentsAndAssignments(sc);
        String title = oneAssignment[0].trim();
        String discription = oneAssignment[1].trim();
        System.out.println("Please type the assignment's submition date and time ");
        LocalDateTime subDT = InputValidator.dateTimeCheckAndFormat(sc);
        System.out.println("Please type the assignment's total mark ");
        int totalMark = InputValidator.getIntFromScanner(sc);
        System.out.println("Please type the assignment's oral mark ");
        int oralMark = InputValidator.getIntFromScanner(sc);
        AssignmentDto assignment = new AssignmentDto(title, discription, subDT, totalMark, oralMark);
        return assignment;
    }

}
