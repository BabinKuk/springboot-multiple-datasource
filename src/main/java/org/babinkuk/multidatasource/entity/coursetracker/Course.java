package org.babinkuk.multidatasource.entity.coursetracker;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "instructor_id")
	private int instructor;
	
	public Course(String title, int instructor) {
		this.title = title;
		this.instructor = instructor;
	}
	
	public Course(String title) {
		this.title = title;
	}
	
	public Course() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getInstructor() {
		return instructor;
	}

	public void setInstructor(int instructor) {
		this.instructor = instructor;
	}
}