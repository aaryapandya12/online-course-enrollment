package com.practice.Course.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.Course.dto.InstructorWiseStudentDto;
import com.practice.Course.entities.Instructor;
import com.practice.Course.services.InstructorService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/instructors")
@Slf4j
public class InstructorController {

	private final InstructorService instructorService;

	@Autowired
	public InstructorController(InstructorService instructorService) {
		super();
		this.instructorService = instructorService;
	}

	@GetMapping
	public ResponseEntity<List<Instructor>> findAllInnstrctors() {
		List<Instructor> instructorList = instructorService.findAllInstructor();
		return ResponseEntity.status(HttpStatus.OK).body(instructorList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Instructor> findInstructorById(@PathVariable Long id) {
		Instructor instructor = instructorService.findInstructorById(id);
		return ResponseEntity.status(HttpStatus.OK).body(instructor);
	}

	@PostMapping
	public ResponseEntity<Instructor> createInstructor(@Valid @RequestBody Instructor instructor,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		Instructor save = instructorService.createInstructor(instructor);
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @Valid @RequestBody Instructor instructor,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		Instructor newInstructor = instructorService.updateInstructor(instructor, id);
		return ResponseEntity.status(HttpStatus.OK).body(newInstructor);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Instructor> deleteInstructor(@PathVariable Long id) {
		instructorService.deleteInstructor(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/iwisestudent")
    public ResponseEntity<List<InstructorWiseStudentDto>> getInstructorWiseStudentCount() {
        List<InstructorWiseStudentDto> data = instructorService.getInstructorWiseStudentCount();
        return ResponseEntity.ok(data);
    }
	
	@PostMapping("/{instructorId}/assign/{courseId}")
	public ResponseEntity<Void> enrollStudent(@PathVariable Long instructorId, @PathVariable Long courseId) {
		instructorService.assignInstructorToCourse(instructorId, courseId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}