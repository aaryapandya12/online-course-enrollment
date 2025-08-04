package com.practice.Course.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.practice.Course.dto.StudDashboardDTO;
import com.practice.Course.repositories.CourseRepository;
import com.practice.Course.repositories.InstructorRepository;
import com.practice.Course.repositories.UserRepository;

@Service
public class StudDashboardServiceImpl implements StudDashboardService{

	private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
	public StudDashboardServiceImpl(CourseRepository courseRepository, UserRepository userRepository,
		  InstructorRepository instructorRepository) {

		this.courseRepository = courseRepository;
		this.userRepository = userRepository;
		this.instructorRepository = instructorRepository;
	}

    @Override
    public StudDashboardDTO fetchDashboardCount() {
        StudDashboardDTO dto = new StudDashboardDTO();
        dto.setCourseCount((int) courseRepository.count());
        dto.setUserCount( userRepository.findAllStudents().size());
        dto.setInstructorCount((int) instructorRepository.count());
        return dto; 
    }
}
