package com.practice.Course.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.Course.dto.AssignmentDto;
import com.practice.Course.entities.Assignment;
import com.practice.Course.services.AssignmentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/assignments")
@Slf4j
public class AssignmentController {

	private final AssignmentService assignmentService;


	@Autowired
	public AssignmentController(AssignmentService assignmentService) {
		
		this.assignmentService = assignmentService;
	
	}

	@GetMapping
	public ResponseEntity<List<AssignmentDto>> getAllAssignments() {
		List<AssignmentDto> assignmentList = assignmentService.getAllAssignments();
		return ResponseEntity.status(HttpStatus.OK).body(assignmentList);
	}

	

	@GetMapping("/{id}")
	public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
		Optional<Assignment> assignment = assignmentService.getAssignmentById(id);
		return assignment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/display")
	public ResponseEntity<Assignment> addAssignment(@RequestBody Assignment assignment,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException(bindingResult.getFieldErrors().toString());
		}
		Assignment createdAssignment = assignmentService.addAssignment(assignment);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAssignment);
	}
	
	@PostMapping
	public ResponseEntity<AssignmentDto> addAssignmentInTable(@RequestBody AssignmentDto assignmentDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException(bindingResult.getFieldErrors().toString());
		}
		AssignmentDto createdAssignment = assignmentService.addAssignmentInTable(assignmentDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAssignment);
	}
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @Valid @RequestBody Assignment updatedAssignment,
			BindingResult result) {
		if (result.hasErrors()) {

			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		Assignment assignment = assignmentService.updateAssignment(id, updatedAssignment);
		if (assignment != null) {
			return ResponseEntity.ok(assignment);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	

	@PostMapping("/assignments/{assignmentId}/assign/{courseId}")
	public ResponseEntity<String> assignAssignmentToCourse(
	        @PathVariable Long assignmentId,
	        @PathVariable Long courseId) {

	    assignmentService.assignAssignmentToCourse(assignmentId, courseId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	
}
