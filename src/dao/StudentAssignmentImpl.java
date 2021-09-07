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
import model.StudentAssignment;
import utils.DbUtils;

public class StudentAssignmentImpl implements StudentAssignmentInt{
    Connection con = null;

    @Override
    public void insertAssignmentsPerStudentPerCourse(StudentAssignment sta) {
        String sql = "INSERT INTO STUDENTASSIGNMENT (STUDENTID, ASSIGNMENTID)"
                + "VALUES ((SELECT STUDENTID FROM STUDENT WHERE STUDENTID =?), "
                + "(SELECT ASSIGNMENTID FROM ASSIGNMENT WHERE ASSIGNMENTID =?));";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sta.getStudentId());
            ps.setInt(2, sta.getAssignmentId());
            ps.executeUpdate();
            System.out.println("done!!");
        } catch (SQLException ex) {
            Logger.getLogger(StudentAssignmentImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentAssignmentImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 
 
}
