package eu.balev.demo.swagger.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.balev.demo.swagger.domain.Course;
import eu.balev.demo.swagger.repository.CourseRepository;

@Api(tags="courses")
@RestController
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@ApiOperation("Retrieves all courses.")
	@RequestMapping(
			method = RequestMethod.GET, 
			value = "/api/courses", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Course> getCourses() {
		//todo: paging
		List<Course> courses = new ArrayList<>();
		courseRepository.findAll().forEach(courses::add);
		return courses;
	}

	
}
