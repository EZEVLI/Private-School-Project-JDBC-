/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Scanner;
import model.Course;

public interface CourseDaoInt {

    public List<Course> fetchAllCourses();

    public void insertCourse(Course c);

    public List<Course> fetchCoursePerStudentId(int sid);

    public Course createNewCourse(Scanner sc);
    

}
