/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Scanner;
import model.Trainer;

public interface TrainerDaoInt {

    public List<Trainer> fetchAllTrainers();

    public void insertTrainer(Trainer t);

    public List<Trainer> fetchTrainersPerCourse(int i);

    public Trainer createNewTrainer(Scanner sc);
}
