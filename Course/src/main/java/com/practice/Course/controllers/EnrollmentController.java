package com.practice.Course.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.practice.Course.dto.CourseInfoDto;

import com.practice.Course.dto.EnrollmentReportDTO;

import com.practice.Course.entities.Enrollment;
import com.practice.Course.services.EnrollmentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/enrollments")

public class EnrollmentController {

	private final EnrollmentService enrollmentService;

	@Autowired
	public EnrollmentController(EnrollmentService enrollmentService) {

		this.enrollmentService = enrollmentService;
	}

	@PostMapping
	public ResponseEntity<Enrollment> createEnrollment(@Valid @RequestBody Enrollment enrollment,
			BindingResult result) {

		if (result.hasErrors()) {

			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}


		Enrollment newEnroll = enrollmentService.addEnrollment(enrollment);

		return ResponseEntity.ok(newEnroll);

	}

	@GetMapping
	public ResponseEntity<List<Enrollment>> getAllEnrollments() {

		List<Enrollment> allEnrollments = enrollmentService.getAllEnrollments();

		return ResponseEntity.ok(allEnrollments);

	}

	@GetMapping("/course/{id}")
	public ResponseEntity<List<CourseInfoDto>> findCoursesByStudentId(@PathVariable Long id) {
		List<CourseInfoDto> studentCourse = enrollmentService.findCoursesByStudentId(id);
		return ResponseEntity.status(HttpStatus.OK).body(studentCourse);
	}	

	@GetMapping("/{enrollmentId}")
	public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long enrollmentId) {

		Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(enrollmentId);

		return enrollment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}
	
	@GetMapping("/enrollment-report")
	public ResponseEntity<List<EnrollmentReportDTO>> getEnrollmentReport() {
		
	    List<EnrollmentReportDTO> report = enrollmentService.getEnrollmentReport();
	    
	    return ResponseEntity.ok(report);  
	}


}
