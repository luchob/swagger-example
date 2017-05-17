package eu.balev.demo.swagger.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Student {

	private int id;
	private String name;
	private Collection<Course> courses;

	public Student() {

	}

	public Student(String name) {
		this.name = name;
	}

	public Student(String name, Collection<Course> courses) {
		this.name = name;
		this.courses = courses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Collection<Course> getCourses() {
		return courses;
	}

	@ApiModelProperty(hidden=true)
	public void setCourses(Collection<Course> courses) {
		this.courses = courses;

	}
}
