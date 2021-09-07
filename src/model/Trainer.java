/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Trainer {

    private int trainerID;
    private String firstName;
    private String lastName;
    private String subject;

    public Trainer(int trainerID, String firstName, String lastName, String subject) {
        this.trainerID = trainerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public Trainer(String firstName, String lastName, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public Trainer() {
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Trainer{" + "trainerID=" + trainerID + ", firstName=" + firstName + ", lastName=" + lastName + ", subject=" + subject + '}';
    }

}
