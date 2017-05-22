package eu.balev.demo.swagger.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import eu.balev.demo.swagger.domain.Course;
import eu.balev.demo.swagger.domain.Student;
import eu.balev.demo.swagger.exceptions.NotFoundException;
import eu.balev.demo.swagger.repository.StudentRepository;
import eu.balev.demo.swagger.service.StudentService;

/**
 * Responsible for REST operations involving students. Reading, creating,
 * assigning to courses, etc.
 * 
 * @author LBalev
 */
@Api(tags = "students")
@RestController
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentService studentService;

	/**
	 * Get a student by ID.
	 * 
	 * @param studentId
	 * @return
	 */
	@ApiOperation("Retrieves a student by the given ID.")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Student not found."),
			@ApiResponse(code = 200, message = "OK") })
	@RequestMapping(method = RequestMethod.GET, value = "/api/students/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudent(
			@ApiParam(name="studentId", value = "The ID of the student.", required = true) 
			@PathVariable Integer studentId) {
		return requireNotNull(studentRepository.findOne(studentId), studentId);

	}

	/**
	 * Get student's courses.
	 * 
	 * @param studentId
	 *            the ID of the student
	 * @return
	 */
	@ApiOperation("Retrieves the courses in which is enrolled a student.")
	@RequestMapping(method = RequestMethod.GET, value = "/api/students/{studentId}/courses", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Course> getStudentCourses(
			@ApiParam(name="studentId", value = "The ID of the student.", required = true)
			@PathVariable Integer studentId) {
		return requireNotNull(studentRepository.findOne(studentId), studentId)
				.getCourses();
	}

	/**
	 * Creates a new student.
	 * 
	 * @param student
	 *            the new student.
	 * 
	 * @return
	 */
	@ApiOperation("Creates a new student.")
	@RequestMapping(method = RequestMethod.POST, value = "/api/students", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> createStudent(
			@ApiParam(name="student", value = "The student.", required = true)
			@RequestBody Student student) {

		Student newStudent = studentService.create(student);

		return ResponseEntity.created(studentURI(newStudent.getId())).body(
				newStudent);
	}

	/**
	 * Update a student.
	 * 
	 * @param studentId
	 * @param student
	 * @return
	 */
	@ApiOperation("Updates an existing student.")
	@RequestMapping(method = RequestMethod.PUT, value = "/api/students/{studentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> udpateStudent(
			@ApiParam(name="studentId", value = "The ID of the student.", required = true)
			@PathVariable Integer studentId, 
			@ApiParam(name="student", value = "The student.", required = true)
			@RequestBody Student student) {

		student.setId(studentId);

		Student updatedStudent = studentService.udpateStudent(student);

		return ResponseEntity.created(studentURI(updatedStudent.getId())).body(
				updatedStudent);
	}

	/**
	 * Patches the courses in which the student is enrolled.
	 * 
	 * @param studentId
	 * @param courses
	 * @return
	 */
	@ApiOperation("Patches the courses in which is enrolled a student.")
	@RequestMapping(method = RequestMethod.PATCH, value = "/api/students/{studentId}/courses", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateStudentCourses(
			@ApiParam(name="studentId", value = "The ID of the student.", required = true)
			@PathVariable Integer studentId,
			@ApiParam(name="courses", value = "A collection of courses in which the student is assigned.", required = true)
			@RequestBody Collection<Course> courses) {

		Student student = requireNotNull(studentRepository.findOne(studentId),
				studentId);

		studentService.updateStudentCourses(student, courses);

		return ResponseEntity.noContent().location(coursesURI(studentId))
				.build();
	}

	private static URI studentURI(Integer studentId) {
		return toUri("/api/students/{id}", studentId);
	}

	private static URI coursesURI(Integer studentId) {
		return toUri("/api/students/{id}/courses", studentId);
	}

	private static URI toUri(String path, Integer studentId) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(path)
				.buildAndExpand(studentId).toUri();
	}

	private static Student requireNotNull(Student s, Integer studentId) {
		if (s == null)
			throw new NotFoundException("Student with Id " + studentId
					+ " not found!");
		else
			return s;
	}

}