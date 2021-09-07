/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschoolsql;

import dao.AssignmentDaoImpl;
import dao.AssignmentDaoInt;
import dao.CourseDaoImpl;
import dao.CourseDaoInt;
import dao.EnrollDaoImpl;
import dao.EnrollDaoInt;
import dao.StudentAssignmentImpl;
import dao.StudentAssignmentInt;
import dao.StudentDaoImpl;
import dao.StudentDaoInt;
import dao.TeachDaoImpl;
import dao.TeachDaoInt;
import dao.TrainerDaoImpl;
import dao.TrainerDaoInt;
import dto.AssignmentDto;
import java.util.Scanner;
import model.Course;
import model.Enroll;
import model.Student;
import model.StudentAssignment;
import model.Teach;
import model.Trainer;
import static privateschoolsql.PrintMethods.printListOfAssignments;
import static privateschoolsql.PrintMethods.printListOfCourses;
import static privateschoolsql.PrintMethods.printListOfStudents;
import static privateschoolsql.PrintMethods.printListOfTrainers;
import utils.InputValidator;
import static utils.InputValidator.getIntFromScanner;

public class PrivateSchoolSql {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String answer;

        CourseDaoInt cdao = new CourseDaoImpl();
        StudentDaoInt sdao = new StudentDaoImpl();
        TrainerDaoInt tdao = new TrainerDaoImpl();
        AssignmentDaoInt adao = new AssignmentDaoImpl();
        EnrollDaoInt edao = new EnrollDaoImpl();
        TeachDaoInt tedao = new TeachDaoImpl();
        StudentAssignmentInt sadao = new StudentAssignmentImpl();

        String menu = " press  1 if you want to see a list of all the students \n press  2 if you want to see a list of all the trainers \n " + ""
                + "press  3 if you want to see a list of all the assignments \n press  4 if you want to see a list of all the courses \n " + ""
                + "press  5 if you want to see all the students per course \n press  6 if you want to see all the trainers per course \n " + ""
                + "press  7 if you want to see all the assignments per course \n press  8 if you want to see all the assignments per course per student \n " + ""
                + "press  9 if you want to see a list of students that belong to more than one courses \n press 10 if you want to add a course \n "
                + "press 11 if you want to add a trainer \n press 12 if you want to add a student \n press 13 if you want to add an assignment \n "
                + "press 14 if you want to add students per course  \n press 15 if you want to add trainers per course\n "
                + "press 16 if you want to add assignments per student per course ";

        do {
            System.out.println(menu);
            int key = getIntFromScanner(sc);
            if (key == 1) {
                printListOfStudents(sdao.fetchAllStudents());
            }
            if (key == 2) {
                printListOfTrainers(tdao.fetchAllTrainers());
            }
            if (key == 3) {
                printListOfAssignments(adao.fetchAllAssignments());
            }
            if (key == 4) {
                printListOfCourses(cdao.fetchAllCourses());
            }
            if (key == 5) {
                printListOfCourses(cdao.fetchAllCourses());
                System.out.println("Please type the course's id ");
                int i = getIntFromScanner(sc);
                printListOfStudents(sdao.fetchStudentsPerCourse(i));
            }
            if (key == 6) {
                printListOfCourses(cdao.fetchAllCourses());
                System.out.println("Please type the course's id ");
                int i = getIntFromScanner(sc);
                printListOfTrainers(tdao.fetchTrainersPerCourse(i));
            }
            if (key == 7) {
                printListOfCourses(cdao.fetchAllCourses());
                System.out.println("Please type the course's id ");
                int i = getIntFromScanner(sc);
                printListOfAssignments(adao.fetchAssignmentsPerCourse(i));
            }
            if (key == 8) {
                printListOfCourses(cdao.fetchAllCourses());
                System.out.println("Please type the course's id ");
                int cid = getIntFromScanner(sc);
                printListOfStudents(sdao.fetchStudentsPerCourse(cid));
                System.out.println("Please type the student's id ");
                int sid = getIntFromScanner(sc);
                printListOfAssignments(adao.fetchAssignmentsPerCoursePerStudent(cid, sid));
            }
            if (key == 9) {
                printListOfStudents(sdao.studentsInManyCourses());
            }
            if (key == 10) {
                Course cou = cdao.createNewCourse(sc);
                cdao.insertCourse(cou);
            }
            if (key == 11) {
                Trainer t = tdao.createNewTrainer(sc);
                tdao.insertTrainer(t);
            }
            if (key == 12) {
                Student s = sdao.createNewStudent(sc);
                sdao.insertStudent(s);
            }
            if (key == 13) {
                AssignmentDto a = adao.createNewAssignment(sc);
                adao.insertAssignment(a);
            }
            if (key == 14) {
                printListOfCourses(cdao.fetchAllCourses());
                System.out.println("Please type the course's id ");
                int cid = getIntFromScanner(sc);
                printListOfStudents(sdao.fetchAllStudents());
                System.out.println("Please type the student's id ");
                int sid = getIntFromScanner(sc);
                Enroll e = new Enroll(cid, sid);
                edao.insertStudentPerCourse(e);
            }
            if (key == 15) {
                printListOfCourses(cdao.fetchAllCourses());
                System.out.println("Please type the course's id ");
                int cid = getIntFromScanner(sc);
                printListOfTrainers(tdao.fetchAllTrainers());
                System.out.println("Please type the trainer's id ");
                int tid = getIntFromScanner(sc);
                Teach t = new Teach(cid, tid);
                tedao.insertTrainerPerCourse(t);
            }
            if (key == 16) {
                printListOfStudents(sdao.fetchAllStudents());
                System.out.println("Please type the student's id ");
                int sid = getIntFromScanner(sc);
                printListOfAssignments(adao.fetchAllAssignments());
                System.out.println("Please type the assignment's id ");
                int aid = getIntFromScanner(sc);
                int cid;
                if (cdao.fetchCoursePerStudentId(sid).size() > 1) {
                    printListOfCourses(cdao.fetchCoursePerStudentId(sid));
                    cid = getIntFromScanner(sc);
                } else {
                    cid = cdao.fetchCoursePerStudentId(sid).get(0).getCourseID();
                }
                StudentAssignment sa = new StudentAssignment(sid, aid);
                sadao.insertAssignmentsPerStudentPerCourse(sa);
                adao.insertCourseIdInAssignment(aid, cid);  //aid,cid
            }
            System.out.println("do you want anything else? y/n");
            answer = InputValidator.isYesOrNo(sc);
        } while (answer.equalsIgnoreCase("y"));
    }
}
