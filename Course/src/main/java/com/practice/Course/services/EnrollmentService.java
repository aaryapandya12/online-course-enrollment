package com.practice.Course.services;

import java.util.List;
import java.util.Optional;

import com.practice.Course.dto.CourseInfoDto;

import com.practice.Course.dto.EnrollmentReportDTO;

import com.practice.Course.entities.Enrollment;

public interface EnrollmentService {

	Enrollment addEnrollment(Enrollment enrollment);

	List<Enrollment> getAllEnrollments();

	Optional<Enrollment> getEnrollmentById(Long enrollmentId);
    
	List<EnrollmentReportDTO> getEnrollmentReport();

	List<CourseInfoDto> findCoursesByStudentId(Long studentId);

}
