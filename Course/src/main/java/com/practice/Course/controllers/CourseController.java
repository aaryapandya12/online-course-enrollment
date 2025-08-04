package com.practice.Course.controllers;

import com.practice.Course.dto.AvailableCourseDto;
import com.practice.Course.dto.CourseDto;
import com.practice.Course.dto.CourseEnrollmentDto;
import com.practice.Course.dto.EnrolledCourseDto;
import com.practice.Course.dto.Top5CoursesDto;
import com.practice.Course.entities.Assignment;
import com.practice.Course.entities.Course;
import com.practice.Course.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/courses")
@Slf4j
public class CourseController {

	private CourseService courseService;

	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@PostMapping
	public ResponseEntity<Course> addCourse(@Valid @RequestBody Course course, BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		Course savedCourse = courseService.addCourse(course);
		return ResponseEntity.ok(savedCourse);
	}

	@GetMapping
	public ResponseEntity<List<CourseDto>> getAllCourses() {
		List<CourseDto> courses = courseService.getAllCourses();
		return ResponseEntity.ok(courses);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
		Optional<Course> course = courseService.getCourseById(id);
		return course.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable Long id, @Valid @RequestBody Course updatedCourse,
			BindingResult result) {
		if (result.hasErrors()) {

			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		Course course = courseService.updateCourse(id, updatedCourse);
		if (course != null) {
			return ResponseEntity.ok(course);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
		courseService.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}/assignments")
	public ResponseEntity<List<Assignment>> getAssignmentsByCourseId(@PathVariable Long id) {
		List<Assignment> assignments = courseService.findByCourseCourseId(id);
		if (assignments.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(assignments);
	}

	@GetMapping("/coursereport")
	public ResponseEntity<List<CourseEnrollmentDto>> getCourseEnrollmentReport() {
		List<CourseEnrollmentDto> report = courseService.getCourseEnrollmentReport();
		return ResponseEntity.status(HttpStatus.OK).body(report);
	}

	@GetMapping("/instructor/{instructorId}")
	public List<String> getCourseTitlesByInstructor(@PathVariable Long instructorId) {
		List<String> titles = courseService.getCourseTitlesByInstructorId(instructorId);
		return titles;
	}

	@GetMapping("/available/{userid}")
	public ResponseEntity<List<AvailableCourseDto>> findCoursesWithIsEnrollment(@PathVariable Long userid) {
		List<AvailableCourseDto> availableCourses = courseService.findCoursesWithIsEnrollment(userid);
		return ResponseEntity.status(HttpStatus.OK).body(availableCourses);
	}

	@GetMapping("/enrolled/{userid}")
	public ResponseEntity<List<EnrolledCourseDto>> enrolledCoursesByStudent(@PathVariable Long userid) {
		List<EnrolledCourseDto> enrolledCourses = courseService.enrolledCoursesByStudent(userid);
		return ResponseEntity.status(HttpStatus.OK).body(enrolledCourses);
	}
	
	@GetMapping("/findTop5Courses")
	public ResponseEntity<List<Top5CoursesDto>> findTop5Courses(){
		List<Top5CoursesDto> top5Courses =courseService.findTop5Courses();
		return ResponseEntity.status(HttpStatus.OK).body(top5Courses);
	}

}
