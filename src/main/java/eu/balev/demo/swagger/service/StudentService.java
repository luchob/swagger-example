package eu.balev.demo.swagger.service;

import java.util.Collection;

import eu.balev.demo.swagger.domain.Course;
import eu.balev.demo.swagger.domain.Student;

/** 
 * Various processing logic related to the student entities.
 * 
 * @author LBalev
 */
public interface StudentService {

	/**
	 * Creates a student and eventually enrolls her in any 
	 * existing or new courses.
	 * 
	 * @param student the student.
	 * 
	 * @return the newly created student.
	 */
	public Student create(Student student);
	
	/** 
	 * Updates the student 
	 * 
	 * @param student the student. Required.
	 * 
	 * @return the updated student.
	 */
	public Student udpateStudent(Student student);
	
	
	/**
	 * Patches the courses in which the student is enrolled.
	 * 
	 * @param student the student
	 * @param courses the courses in which the student should be enrolled
	 * 
	 * @return the updated student. 
	 */
	public Student updateStudentCourses(Student student, Collection<Course> courses);
	
}
