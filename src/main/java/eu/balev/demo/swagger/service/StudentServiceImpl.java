package eu.balev.demo.swagger.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.balev.demo.swagger.domain.Course;
import eu.balev.demo.swagger.domain.Student;
import eu.balev.demo.swagger.exceptions.NotFoundException;
import eu.balev.demo.swagger.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Student create(Student studentDTO) {
		
		//possibly ignore the courses comming in the DTO, or signal an error.
		Student newStudent = new Student(studentDTO.getName());

		return  studentRepository.save(newStudent);
	}

	@Override
	public Student udpateStudent(Student studentDTO) {
		
		Student existingStudent = requireNotNull(studentRepository.findOne(studentDTO.getId()), studentDTO.getId());
		
		existingStudent.setName(studentDTO.getName());
		
		return  studentRepository.save(existingStudent);
		
	}

	private static Student requireNotNull(Student s, Integer studentId)
	{
		if (s == null)
			throw new NotFoundException("Student with Id " + studentId + " not found!");
		else
			return s;
	}

	@Override
	public Student updateStudentCourses(Student student,
			Collection<Course> courses) {

		//maybe some error hanling, etc?
		student.setCourses(courses);
		
		return studentRepository.save(student);
	}
	
}
