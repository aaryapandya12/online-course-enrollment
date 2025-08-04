package com.practice.Course.services;

import com.practice.Course.dto.AvailableCourseDto;
import com.practice.Course.dto.CourseDto;
import com.practice.Course.dto.CourseEnrollmentDto;
import com.practice.Course.dto.EnrolledCourseDto;
import com.practice.Course.dto.Top5CoursesDto;
import com.practice.Course.entities.Assignment;
import com.practice.Course.entities.Course;
import com.practice.Course.exceptions.CourseNotFoundException;
import com.practice.Course.repositories.AssignmentRepository;
import com.practice.Course.repositories.CourseRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseServiceImple implements CourseService {

	CourseRepository courseRepository;
	AssignmentRepository assignmentRepository;

	@Autowired
	public CourseServiceImple(CourseRepository courseRepository, AssignmentRepository assignmentRepository) {
		this.courseRepository = courseRepository;
		this.assignmentRepository = assignmentRepository;
	}

	@Override
	public Course addCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Course updateCourse(Long courseId, Course updatedCourse) {
		Course existing = courseRepository.findById(courseId).orElseThrow(() -> {
			return new CourseNotFoundException("Course not found with courseId" + courseId);
		});
		Optional<Course> existingCourseOpt = courseRepository.findById(courseId);
		if (existingCourseOpt.isPresent()) {
			Course existingCourse = existingCourseOpt.get();
			existingCourse.setTitle(updatedCourse.getTitle());
			existingCourse.setDescription(updatedCourse.getDescription());
			existingCourse.setInstructor(updatedCourse.getInstructor());
			existingCourse.setFees(updatedCourse.getFees());

			return courseRepository.save(existingCourse);
		}
		return null;
	}

	@Override
	public void deleteCourse(Long courseId) {
		if (!courseRepository.existsById(courseId)) {
			throw new CourseNotFoundException("Course not found with icourseId" + courseId);

		}
		courseRepository.deleteById(courseId);
	}

	@Override
	public List<CourseDto> getAllCourses() {
		return courseRepository.getAllCourses();
	}

	@Override
	public Optional<Course> getCourseById(Long courseId) {

		if (!courseRepository.existsById(courseId)) {
			throw new CourseNotFoundException("Course not found with icourseId" + courseId);
		}
		return courseRepository.findById(courseId);
	}

	@Override
	public List<Assignment> findByCourseCourseId(Long courseId) {
		List<Assignment> assignments = assignmentRepository.findByCourse_CourseId(courseId);
		return assignments;
	}

	@Override
	public List<CourseEnrollmentDto> getCourseEnrollmentReport() {

		List<CourseEnrollmentDto> report = courseRepository.getCourseEnrollmentReport();
		return report;
	}

	@Override
	public List<String> getCourseTitlesByInstructorId(Long instructorId) {
		List<String> titles = courseRepository.findCourseTitlesByInstructorId(instructorId);
		return titles;
	}

	@Override
	public List<AvailableCourseDto> findCoursesWithIsEnrollment(Long userId) {
		List<AvailableCourseDto> availableCourses = courseRepository.findCoursesWithIsEnrollment(userId);
		return availableCourses;
	}

	@Override
	public List<EnrolledCourseDto> enrolledCoursesByStudent(Long studentId) {

		List<EnrolledCourseDto> enrolledCourses = courseRepository.enrolledCoursesByStudent(studentId);
		return enrolledCourses;
	}

	@Override
	public List<Top5CoursesDto> findTop5Courses() {
		return courseRepository.findTop5Courses();
	} 

}
