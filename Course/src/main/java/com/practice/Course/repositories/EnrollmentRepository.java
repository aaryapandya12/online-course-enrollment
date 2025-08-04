package com.practice.Course.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.practice.Course.dto.CourseInfoDto;
import com.practice.Course.entities.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	@Query("SELECT new com.practice.Course.dto.CourseInfoDto(" + "e.course.title, e.course.description) "
			+ "FROM Enrollment e " + "WHERE e.user.userId = :studentId")
	List<CourseInfoDto> findCoursesByStudentId(@Param("studentId") Long studentId);

	
	long count();
}
