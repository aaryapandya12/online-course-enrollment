package com.practice.Course.services;

import java.util.List;

import com.practice.Course.dto.InstructorWiseStudentDto;
import com.practice.Course.entities.Instructor;

public interface InstructorService {

	List<Instructor> findAllInstructor();

	Instructor findInstructorById(Long instructorId);

	Instructor createInstructor(Instructor instructor);

	Instructor updateInstructor(Instructor instructor, Long instructorId);

	boolean deleteInstructor(Long instructorId);

	List<InstructorWiseStudentDto> getInstructorWiseStudentCount();
	
	void assignInstructorToCourse(Long instructorId, Long courseId);
}
