/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Trainer;
import utils.DbUtils;
import utils.InputValidator;

public class TrainerDaoImpl implements TrainerDaoInt {

    Connection con = null;

    @Override
    public List<Trainer> fetchAllTrainers() {
        LinkedList<Trainer> allTrainers = new LinkedList();
        String sql = "SELECT TRAINERID, FIRSTNAME, LASTNAME, SUBJECT FROM TRAINER;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Trainer t = new Trainer(rs.getInt("TRAINERID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("SUBJECT"));
                allTrainers.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return allTrainers;
        }
    }

    @Override
    public void insertTrainer(Trainer t) {
        String sql = "INSERT INTO TRAINER (TRAINERID, FIRSTNAME, LASTNAME,SUBJECT) VALUES (NULL,?,?,?);";
        PreparedStatement ps = null;

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getFirstName());
            ps.setString(2, t.getLastName());
            ps.setString(3, t.getSubject());
            ps.executeUpdate();
            System.out.println("done!!");
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public List<Trainer> fetchTrainersPerCourse(int i) {
        LinkedList<Trainer> trainersPerCourse = new LinkedList();
        String sql = "SELECT  TRAINER.TRAINERID,TRAINER.FIRSTNAME, TRAINER.LASTNAME,"
                + " TRAINER.SUBJECT FROM TEACH,TRAINER WHERE TEACH.TRAINERID=TRAINER.TRAINERID AND TEACH.COURSEID=?";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Trainer t = new Trainer(rs.getInt("TRAINERID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("SUBJECT"));
                trainersPerCourse.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return trainersPerCourse;
        }
    }

    @Override
    public Trainer createNewTrainer(Scanner sc) {
        System.out.println("Please type trainer's data in the format \"First Name, Last Name, Subject\"");
        String[] oneTrainer = InputValidator.checkUsersInputForCoursesAndTrainers(sc);
        String firstName = oneTrainer[0].trim();
        String lastName = oneTrainer[1].trim();
        String subject = oneTrainer[2].trim();
        Trainer trainer = new Trainer(firstName, lastName, subject);
        return trainer;
    }

}
