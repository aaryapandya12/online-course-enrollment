package com.practice.Course.services;

import com.practice.Course.dto.AvailableCourseDto;
import com.practice.Course.dto.CourseDto;
import com.practice.Course.dto.CourseEnrollmentDto;
import com.practice.Course.dto.EnrolledCourseDto;
import com.practice.Course.dto.Top5CoursesDto;
import com.practice.Course.entities.Assignment;
import com.practice.Course.entities.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {

	List<CourseDto> getAllCourses();

	Course addCourse(Course course);

	Course updateCourse(Long courseId, Course updatedCourse);

	void deleteCourse(Long courseId);

	Optional<Course> getCourseById(Long courseId);

	List<Assignment> findByCourseCourseId(Long courseId);

	List<CourseEnrollmentDto> getCourseEnrollmentReport();

	List<String> getCourseTitlesByInstructorId(Long instructorId);

	public List<AvailableCourseDto> findCoursesWithIsEnrollment(Long userId);

	public List<EnrolledCourseDto> enrolledCoursesByStudent(Long studentId);
	
	List<Top5CoursesDto> findTop5Courses(); 

}
