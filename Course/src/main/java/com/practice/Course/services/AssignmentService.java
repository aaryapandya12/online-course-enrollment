package com.practice.Course.services;

import com.practice.Course.dto.AssignmentDto;
import com.practice.Course.entities.Assignment;
import java.util.List;
import java.util.Optional;

public interface AssignmentService {
	Assignment addAssignment(Assignment assignment);
	
	AssignmentDto addAssignmentInTable(AssignmentDto assignmentDto);

	Optional<Assignment> getAssignmentById(Long assignmentId);

	List<AssignmentDto> getAllAssignments();
	
	Assignment updateAssignment(Long assignmentId, Assignment updatedAssignment);
	
	void assignAssignmentToCourse(Long assignmentId, Long courseId);

}
