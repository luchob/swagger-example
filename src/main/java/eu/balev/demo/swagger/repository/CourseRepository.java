package eu.balev.demo.swagger.repository;

import org.springframework.data.repository.CrudRepository;

import eu.balev.demo.swagger.domain.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {
	
}
