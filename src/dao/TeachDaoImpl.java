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
import model.Teach;
import utils.DbUtils;

public class TeachDaoImpl implements TeachDaoInt {

    Connection con = null;

    @Override
    public void insertTrainerPerCourse(Teach t) {
        String sql = "INSERT INTO TEACH (COURSEID, TRAINERID) "
                + "VALUES ((SELECT COURSEID FROM COURSE WHERE COURSEID =?), "
                + "(SELECT TRAINERID FROM TRAINER WHERE TRAINERID =?));";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, t.getCourseid());
            ps.setInt(2, t.getTrainerid());
            ps.executeUpdate();
            System.out.println("done!!");
        } catch (SQLException ex) {
            Logger.getLogger(TeachDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeachDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
