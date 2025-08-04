package com.practice.Course.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.practice.Course.dto.StudentDto;
import com.practice.Course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	boolean existsByUserName(String userName);

	Optional<User> findByEmail(String email);
	
	@Query("SELECT u.userName FROM User u WHERE u.userId = :userId")
	String findUserNameById(@Param("userId") Long userId);

	@Query("SELECT new com.practice.Course.dto.StudentDto(u.userId, u.userName) " + "FROM User u "
			+ "WHERE u.userType = 'USER'")
	List<StudentDto> fetchAllStudentsNames();

	@Query("SELECT new com.practice.Course.dto.StudentDto(u.userId, u.userName) " + "FROM Enrollment e "
			+ "JOIN e.user u " + "WHERE u.userType = 'USER' AND e.course.courseId = :courseId")
	List<StudentDto> fetchStudentOfCourses(@Param("courseId") Long courseId);

	
	@Query("Select u FROM User u where u.userType='USER'")
	List<User> findAllStudents();
}
