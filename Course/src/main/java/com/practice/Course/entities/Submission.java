package com.practice.Course.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Submission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "submission_id")
	private Long submissionId;

	@Column(name = "submission_date")
	private LocalDate submissionDate;

	@Column(name = "status")
	private boolean status;

	@ManyToOne
	@JoinColumn(name = "assignment_id")
	@JsonBackReference(value = "assignment_submission")
	private Assignment assignment;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "user_submission")
	private User user;

	public Submission() {
		super();
	}

	public Submission(Long submissionId, Assignment assignment, User user, LocalDate submissionDate, boolean status) {
		super();
		this.submissionId = submissionId;
		this.assignment = assignment;
		this.user = user;
		this.submissionDate = submissionDate;
		this.status = status;
	}

	public Long getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(LocalDate submissionDate) {
		this.submissionDate = submissionDate;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Submission [submissionId=" + submissionId + ", assignment=" + assignment + ", user=" + user
				+ ", submissionDate=" + submissionDate + ", status=" + status + "]";
	}

}
