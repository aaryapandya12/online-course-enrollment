package com.practice.Course.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.practice.Course.dto.StudentDto;
import com.practice.Course.entities.User;
import com.practice.Course.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userService.findAllUser();
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userService.findUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<List<StudentDto>> fetchStudentOfCourses(@PathVariable Long id) {
		List<StudentDto> students = userService.fetchStudentOfCourses(id);
		return ResponseEntity.status(HttpStatus.OK).body(students);

	}

	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> fetchAllStudentsNames() {
		List<StudentDto> students = userService.fetchAllStudentsNames();
		return ResponseEntity.status(HttpStatus.OK).body(students);

	}

	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		User savedUser = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User newUser,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		User updatedUser = userService.updateUser(id, newUser);
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/allStudents")
    public ResponseEntity<List<User>> getAllStudents() {
        List<User> students = userService.findAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

}
