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
import model.Course;
import utils.DbUtils;
import utils.InputValidator;

public class CourseDaoImpl implements CourseDaoInt {

    Connection con = null;

    @Override
    public List<Course> fetchAllCourses() {
        LinkedList<Course> allCourses = new LinkedList();
        String sql = "SELECT COURSEID, TITLE, STREAM, TYPE, START_DATE, END_DATE FROM COURSE;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Course c = new Course(rs.getInt("courseID"), rs.getString("TITLE"), rs.getString("STREAM"), rs.getString("TYPE"),
                        rs.getDate("START_DATE").toLocalDate(), rs.getDate("END_DATE").toLocalDate());
                allCourses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDaoImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return allCourses;
        }
    }

    @Override
    public void insertCourse(Course c) {
        String sql = "INSERT INTO COURSE (COURSEID, TITLE, STREAM, TYPE, START_DATE, END_DATE) VALUES (null,?, ?, ?, ?, ?);";
        PreparedStatement ps = null;

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getTitle());
            ps.setString(2, c.getStream());
            ps.setString(3, c.getType());
            ps.setDate(4, Date.valueOf(c.getStart_date()));
            ps.setDate(5, Date.valueOf(c.getStart_date()));
            ps.executeUpdate();
            System.out.println("done!!");
        } catch (SQLException ex) {
            Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Course> fetchCoursePerStudentId(int sid) {
        LinkedList<Course> CoursList = new LinkedList();
        String sql = "SELECT COURSE.COURSEID, COURSE.TITLE, COURSE.STREAM, COURSE.TYPE, COURSE.START_DATE,"
                + " COURSE.END_DATE FROM  ENROLL,COURSE WHERE STUDENTID =? AND ENROLL.COURSEID= COURSE.COURSEID;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course(rs.getInt("courseID"), rs.getString("TITLE"), rs.getString("STREAM"), rs.getString("TYPE"),
                        rs.getDate("START_DATE").toLocalDate(), rs.getDate("END_DATE").toLocalDate());
                CoursList.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDaoImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return CoursList;
        }
    }

    @Override
    public Course createNewCourse(Scanner sc) {
        System.out.println("Please type course's data in the format \"Title, Stream, Type\"");
        String[] oneCourse = InputValidator.checkUsersInputForCoursesAndTrainers(sc);
        String title = oneCourse[0].trim();
        String stream = oneCourse[1].trim();
        String type = oneCourse[2].trim();
        System.out.println("When does this course start?");
        LocalDate startDate = InputValidator.dateCheckAndFormat(sc);
        System.out.println("When does this course end?");
        LocalDate endDate = InputValidator.dateCheckAndFormat(sc);
        Course course = new Course(title, stream, type, startDate, endDate);
        return course;
    }


}
