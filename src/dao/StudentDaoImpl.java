/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;
import utils.DbUtils;
import utils.InputValidator;

public class StudentDaoImpl implements StudentDaoInt {

    Connection con = null;

    @Override
    public List<Student> fetchAllStudents() {
        LinkedList<Student> allStudents = new LinkedList();
        String sql = "SELECT STUDENTID, FIRSTNAME, LASTNAME, DATEOFBIRTH, TUITIONFEES FROM STUDENT;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Student s = new Student(rs.getInt("STUDENTID"), rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"), rs.getDate("DATEOFBIRTH").toLocalDate(), rs.getInt("TUITIONFEES"));
                allStudents.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return allStudents;
        }
    }

    @Override
    public void insertStudent(Student s) {
        String sql = "INSERT INTO STUDENT (STUDENTID, FIRSTNAME, LASTNAME,DATEOFBIRTH,TUITIONFEES) VALUES (NULL, ?,?,?,?);";
        PreparedStatement ps = null;

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getFirstName());
            ps.setString(2, s.getLastName());
            ps.setDate(3, Date.valueOf(s.getDateOfBirth()));
            ps.setInt(4, s.getTuitionFees());
            ps.executeUpdate();
            System.out.println("done!!!");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Student> fetchStudentsPerCourse(int i) {
        LinkedList<Student> studentsPerCourse = new LinkedList();
        String sql = "SELECT STUDENT.STUDENTID, STUDENT.FIRSTNAME, STUDENT.LASTNAME,"
                + " STUDENT.DATEOFBIRTH, STUDENT.TUITIONFEES FROM STUDENT,"
                + " ENROLL WHERE STUDENT.STUDENTID=ENROLL.STUDENTID AND ENROLL.COURSEID=?;";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student(rs.getInt("STUDENTID"), rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"), rs.getDate("DATEOFBIRTH").toLocalDate(), rs.getInt("TUITIONFEES"));
                studentsPerCourse.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return studentsPerCourse;
        }
    }

    @Override
    public Student getStudentById(int i) {
        String sql = "SELECT STUDENT.STUDENTID, STUDENT.FIRSTNAME, STUDENT.LASTNAME,"
                + " STUDENT.DATEOFBIRTH, STUDENT.TUITIONFEES FROM STUDENT WHERE STUDENTID=?;";
        PreparedStatement ps = null;
        Student s = new Student();
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s.setStudentID(rs.getInt("STUDENTID"));
                s.setFirstName(rs.getString("FIRSTNAME"));
                s.setLastName(rs.getString("LASTNAME"));
                s.setDateOfBirth(rs.getDate("DATEOFBIRTH").toLocalDate());
                s.setTuitionFees(rs.getInt("TUITIONFEES"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return s;

    }

    @Override
    public List<Student> studentsInManyCourses() {
        LinkedList<Student> studentsInManyCourses = new LinkedList();
        String sql = "SELECT STUDENTID FROM ENROLL GROUP BY STUDENTID HAVING COUNT(ENROLL.COURSEID) > 1;";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = getStudentById(rs.getInt(1));
                studentsInManyCourses.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return studentsInManyCourses;
        }
    }

    @Override
    public Student createNewStudent(Scanner sc) {
        System.out.println("Please type student's data in the format  \"firstName,  lastName\" ");
        String[] oneStudent = InputValidator.checkUsersInputForStudentsAndAssignments(sc);
        String firstName = oneStudent[0].trim();
        String lastName = oneStudent[1].trim();
        System.out.println("Please type student's date of birth");
        LocalDate dateofBirth = InputValidator.dateCheckAndFormat(sc);
        System.out.println("Please type the tuition fees for this student");
        int fees = InputValidator.getIntFromScanner(sc);
        Student student = new Student(firstName, lastName, dateofBirth, fees);
        return student;
    }

}
