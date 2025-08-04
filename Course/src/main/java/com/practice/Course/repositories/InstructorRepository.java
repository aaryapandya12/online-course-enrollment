package com.practice.Course.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practice.Course.dto.InstructorWiseStudentDto;
import com.practice.Course.entities.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

	long count();
	
	@Query("SELECT new com.practice.Course.dto.InstructorWiseStudentDto(COUNT(e.user.userI"
			+ "d), c.instructor.name) " +
		       "FROM Enrollment e " +
		       "JOIN e.course c " +
		       "GROUP BY c.instructor.name")
		List<InstructorWiseStudentDto> fetchInstructorWiseStudentCount();

}
