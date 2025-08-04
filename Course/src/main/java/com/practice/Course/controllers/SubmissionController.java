package com.practice.Course.controllers;

import java.util.List;

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

import com.practice.Course.entities.Instructor;
import com.practice.Course.entities.Submission;

import com.practice.Course.services.SubmissionService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/submissions")
@Slf4j
public class SubmissionController {

	SubmissionService submissionService;

	@Autowired
	public SubmissionController(SubmissionService submissionService) {
		this.submissionService = submissionService;
	}

	@GetMapping
	public ResponseEntity<List<Submission>> getAllSubmissions() {
		List<Submission> submissionList = submissionService.findAllsubmissions();
		return ResponseEntity.status(HttpStatus.OK).body(submissionList);

	}

	@GetMapping("/{submissionId}")
	public ResponseEntity<Submission> getSubmission(@PathVariable Long submissionId) {
		Submission submission = submissionService.findSubmissionById(submissionId);

		return ResponseEntity.status(HttpStatus.OK).body(submission);

	}
	
	@PostMapping
	public ResponseEntity<Submission> createSubmission(@Valid @RequestBody Submission submission,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		Submission save = submissionService.createSubmission(submission);
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}

}
