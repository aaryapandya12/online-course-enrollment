package com.practice.Course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.practice.Course.entities.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

}
