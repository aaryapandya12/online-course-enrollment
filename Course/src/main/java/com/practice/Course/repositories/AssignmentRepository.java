package com.practice.Course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.Course.dto.AssignmentDto;
import com.practice.Course.entities.Assignment;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
	List<Assignment> findByCourse_CourseId(Long courseId);
	
	 @Query("SELECT new com.practice.Course.dto.AssignmentDto(" +
	           "a.assignmentId, a.title, a.description, c.courseId, c.title) " +
	           "FROM Assignment a JOIN a.course c")
	    List<AssignmentDto> findAllAssignmentDtos();
	 
	 

}