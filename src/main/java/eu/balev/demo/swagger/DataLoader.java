package eu.balev.demo.swagger;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import eu.balev.demo.swagger.domain.Course;
import eu.balev.demo.swagger.domain.Student;
import eu.balev.demo.swagger.repository.CourseRepository;
import eu.balev.demo.swagger.repository.StudentRepository;

@Component
public class DataLoader implements ApplicationRunner {

	private StudentRepository studentRepository;

	@Autowired
	public DataLoader(StudentRepository studentRepository,
			CourseRepository courseRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// student
		Student student = new Student("Lachezar Balev");

		// courses
		Course javaProgramming = new Course("Java Programming");
		Course maths = new Course("Maths");

		student.setCourses(new HashSet<>(Arrays.asList(javaProgramming, maths)));

		studentRepository.save(student);
	}
}
