/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschoolsql;

import dto.AssignmentDto;
import java.util.List;
import model.Course;
import model.Student;
import model.Trainer;


public class PrintMethods {
    

    public static void printListOfStudents(List<Student> studentList) {
        for (Student s : studentList) {
            System.out.println(s.toString());
        }
    }

    public static void printListOfCourses(List<Course> courseList) {
        for (Course c : courseList) {
            System.out.println(c.toString());
        }
    }

    public static void printListOfTrainers(List<Trainer> trainerList) {
        for (Trainer t : trainerList) {
            System.out.println(t.toString());
        }
    }

    public static void printListOfAssignments(List<AssignmentDto> assignmentList) {
        for (AssignmentDto a : assignmentList) {
            System.out.println(a.toString());
        }
    }
    

}
