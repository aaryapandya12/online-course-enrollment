package com.practice.Course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.practice.Course.entities.Submission;
import com.practice.Course.exceptions.SubmissionNotFoundException;
import com.practice.Course.repositories.SubmissionRepository;

@Service
@Slf4j
public class SubmissionServiceImpl implements SubmissionService {

	private final SubmissionRepository repository;

	@Autowired
	public SubmissionServiceImpl(SubmissionRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Submission> findAllsubmissions() {
		return repository.findAll();
	}

	@Override
	public Submission findSubmissionById(Long submissionId) {
		return repository.findById(submissionId).orElseThrow(() -> {
			return new SubmissionNotFoundException("Submission not found with ID: " + submissionId);
		});
	}

	@Override
	public Submission createSubmission(Submission submission) {
		return repository.save(submission);
	}

}
