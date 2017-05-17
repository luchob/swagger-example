package eu.balev.demo.swagger.repository;

import org.springframework.data.repository.CrudRepository;

import eu.balev.demo.swagger.domain.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
	
}