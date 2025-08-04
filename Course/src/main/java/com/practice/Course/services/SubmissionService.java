package com.practice.Course.services;

import java.util.List;

import com.practice.Course.entities.Submission;

public interface SubmissionService {

	List<Submission> findAllsubmissions();

	Submission findSubmissionById(Long submissionId);

	Submission createSubmission(Submission submission);
}
