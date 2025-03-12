package org.babinkuk.multidatasource.service;

import java.util.List;
import org.babinkuk.multidatasource.repository.jobportal.custom.JobPortalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * implementation class for CourseService interface 
 */
@Service
public class UserServiceImpl implements UserService {

	private final JobPortalRepository jobPortalRepository;
		
	@Autowired
	public UserServiceImpl(JobPortalRepository jobPortalRepository) {
		this.jobPortalRepository = jobPortalRepository;
}

	@Override
	public List<String> getAllUserEmails() {
		return jobPortalRepository.getAllUserEmails();
	}
}
