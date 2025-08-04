package com.practice.Course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.Course.dto.CourseInfoDto;

import com.practice.Course.dto.EnrollmentReportDTO;
import com.practice.Course.entities.Course;

import com.practice.Course.entities.Enrollment;
import com.practice.Course.entities.User;
import com.practice.Course.repositories.CourseRepository;
import com.practice.Course.repositories.EnrollmentRepository;
import com.practice.Course.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {

	EnrollmentRepository enrollmentRepository;

	CourseRepository courseRepository;

	UserRepository userRepository;

	@Autowired
	public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository,
			UserRepository userRepository) {
		super();
		this.enrollmentRepository = enrollmentRepository;
		this.courseRepository = courseRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Enrollment addEnrollment(Enrollment enrollment) {


		return enrollmentRepository.save(enrollment);
	}

	@Override
	public List<Enrollment> getAllEnrollments() {

		return enrollmentRepository.findAll();
	}

	@Override
	public Optional<Enrollment> getEnrollmentById(Long enrollmentId) {

		return enrollmentRepository.findById(enrollmentId);

	}

	@Override
	public List<EnrollmentReportDTO> getEnrollmentReport() {
		List<Enrollment> enrollments = enrollmentRepository.findAll();

		return enrollments.stream().map(enroll -> {
			String courseTitle = enroll.getCourse() != null ? enroll.getCourse().getTitle() : "Unknown Course";

			String studentName = enroll.getUser() != null ? enroll.getUser().getUserName() : "Unknown Student";

			return new EnrollmentReportDTO(enroll.getEnrollmentId(), courseTitle, studentName,
					enroll.getEnrollmentDate());
		}).toList();
	}

	@Override
	public List<CourseInfoDto> findCoursesByStudentId(Long studentId) {

		return enrollmentRepository.findCoursesByStudentId(studentId);
	}

}
