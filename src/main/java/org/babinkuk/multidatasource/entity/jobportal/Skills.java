package org.babinkuk.multidatasource.entity.jobportal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "skills")
public class Skills {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "experience_level")
	private String experienceLevel;
	
	@Column(name = "years_of_experience")
	private String yearsOfExperience;
	
	@Column(name = "job_seeker_profile")
	private int jobSeekerProfile;

	public Skills() {
		super();
	}

	public Skills(Integer id, String name, String experienceLevel, String yearsOfExperience,
			int jobSeekerProfile) {
		super();
		this.id = id;
		this.name = name;
		this.experienceLevel = experienceLevel;
		this.yearsOfExperience = yearsOfExperience;
		this.jobSeekerProfile = jobSeekerProfile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public String getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public int getJobSeekerProfile() {
		return jobSeekerProfile;
	}
	
	public void setJobSeekerProfile(int jobSeekerProfile) {
		this.jobSeekerProfile = jobSeekerProfile;
	}
}
