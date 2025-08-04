package com.practice.Course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.Course.dto.InstructorWiseStudentDto;
import com.practice.Course.entities.Course;
import com.practice.Course.entities.Instructor;
import com.practice.Course.exceptions.CourseNotFoundException;
import com.practice.Course.exceptions.InstructorNotFoundException;
import com.practice.Course.repositories.CourseRepository;
import com.practice.Course.repositories.InstructorRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InstructorServiceImpl implements InstructorService {

	private final CourseRepository courseRepository;
	private final InstructorRepository instructorRepo;

	@Autowired
	public InstructorServiceImpl(CourseRepository courseRepository, InstructorRepository instructorRepo) {
		super();
		this.courseRepository = courseRepository;
		this.instructorRepo = instructorRepo;
	}


	@Override
	public List<Instructor> findAllInstructor() {
		return instructorRepo.findAll();
	}


	@Override
	public Instructor findInstructorById(Long instructorId) {
		return instructorRepo.findById(instructorId).orElseThrow(() -> {
			return new InstructorNotFoundException("Instructor not found with instructorId" + instructorId);
		});

	}

	@Override
	public Instructor createInstructor(Instructor instructor) {
		return instructorRepo.save(instructor);
	}

	@Override
	public Instructor updateInstructor(Instructor updated, Long instructorId) {
		Instructor existing = instructorRepo.findById(instructorId).orElseThrow(() -> {
			return new InstructorNotFoundException("Instructor not found with instructorId" + instructorId);
		});
		existing.setName(updated.getName());
		existing.setExpertise(updated.getExpertise());

		Instructor saved = instructorRepo.save(existing);

		return saved;
	}

	@Override
	public boolean deleteInstructor(Long instructorId) {
		if (!instructorRepo.existsById(instructorId)) {
			throw new InstructorNotFoundException("Instructor not found with instructorId" + instructorId);

		}
		instructorRepo.deleteById(instructorId);
		return false;
	}
	
	@Override
	public List<InstructorWiseStudentDto> getInstructorWiseStudentCount() {
        return instructorRepo.fetchInstructorWiseStudentCount();
    }

	@Override
	public void assignInstructorToCourse(Long instructorId, Long courseId) {
	    Course course = courseRepository.findById(courseId)
	            .orElseThrow(() -> new CourseNotFoundException("Course not found with courseId: " + courseId));

	    Instructor instructor = instructorRepo.findById(instructorId)
	            .orElseThrow(() -> new InstructorNotFoundException("Instructor not found with instructorId: " + instructorId));

	    if (course.getInstructor() != null) {
	        Instructor existingInstructor = course.getInstructor();
	        existingInstructor.getCourses().remove(course); 
	    }

	    // Assign the new instructor
	    course.setInstructor(instructor);
	    instructor.getCourses().add(course);

	    courseRepository.save(course);
	    instructorRepo.save(instructor);
	}


}
