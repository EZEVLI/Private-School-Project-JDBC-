/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class InputValidator {
        public static LocalDate dateCheckAndFormat(Scanner sc) {
        System.out.println("Please type the date in format \"yyyy-MM-dd\" ");
        String date = sc.nextLine().trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate ld = LocalDate.parse(date, formatter);
            return ld;
        } catch (Exception e) {
            System.out.println("wrong format");
            return dateCheckAndFormat(sc);
        }
    }

    public static LocalDateTime dateTimeCheckAndFormat(Scanner sc) {
        System.out.println("Please type the date and time in format \"yyyy-MM-dd HH:mm\" ");
        String date = sc.nextLine().trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime ldt = LocalDateTime.parse(date, formatter);
            return ldt;
        } catch (Exception e) {
            System.out.println("wrong format");
            return dateTimeCheckAndFormat(sc);
        }
    }
        public static int getIntFromScanner(Scanner sc) {
        String i = sc.nextLine().trim();
        try {
            int integ = Integer.parseInt(i);         //ΘΑ ΠΡΕΠΕΙ Ο ΧΡΗΣΤΗΣ NA KATAXΩΡΗΣΕΙ INTEGER
            return integ;
        } catch (NumberFormatException ex) {
            System.out.println("Wrong, please type an integer");
            return getIntFromScanner(sc);
        }
    }
            public static String isYesOrNo(Scanner sc) {
        String a = sc.nextLine().trim();
        if (a.equalsIgnoreCase("y") || a.equalsIgnoreCase("n")) {
            return a.trim();
        } else {
            System.out.println("Wrong, please type \"y\" for yes or \"n\" for no");
            return isYesOrNo(sc);
        }
    }
                public static String[] checkUsersInputForCoursesAndTrainers(Scanner sc) {
        String c = sc.nextLine();
        String[] arr = c.split(",");
        try {
            String thirdElement = arr[2];          //ΘΑ ΠΡΕΠΕΙ Ο ΧΡΗΣΤΗΣ ΝΑ ΜΟΥ ΕΧΕΙ ΔΩΣΕΙ 3 ΣΤΟΙΧΕΙΑ ΧΩΡΙΣΜΕΝΑ ΜΕ "," (ΙΣΧΥΕΙ ΚΑΙ ΓΙΑ COURSES ΚΑΙ ΓΙΑ TRAINERS)
            return arr;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Wrong format, try again");
            return checkUsersInputForCoursesAndTrainers(sc);
        }
    }

    public static String[] checkUsersInputForStudentsAndAssignments(Scanner sc) {
        String c = sc.nextLine();
        String[] arr = c.split(",");
        try {
            String secondElement = arr[1];   //ΘΑ ΠΡΕΠΕΙ Ο ΧΡΗΣΤΗΣ ΝΑ ΜΟΥ ΕΧΕΙ ΔΩΣΕΙ 2 ΣΤΟΙΧΕΙΑ ΧΩΡΙΣΜΕΝΑ ΜΕ "," (ΙΣΧΥΕΙ ΚΑΙ ΓΙΑ STUDENTS ΚΑΙ ΓΙΑ ASSIGNMENTS)
            return arr;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Wrong format, try again");
            return checkUsersInputForStudentsAndAssignments(sc);
        }
    }
    
}
