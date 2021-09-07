/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Enroll;
import utils.DbUtils;

public class EnrollDaoImpl implements dao.EnrollDaoInt {

    Connection con = null;

    @Override
    public void insertStudentPerCourse(Enroll e) {
        String sql = "INSERT INTO ENROLL (COURSEID, STUDENTID) VALUES "
                + "((SELECT COURSEID FROM COURSE WHERE COURSEID =?), "
                + "(SELECT STUDENTID FROM STUDENT WHERE STUDENTID =?));";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, e.getCourseid());
            ps.setInt(2, e.getStudentid());
            ps.executeUpdate();
            System.out.println("done!!");
        } catch (SQLException ex) {
            Logger.getLogger(EnrollDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EnrollDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
