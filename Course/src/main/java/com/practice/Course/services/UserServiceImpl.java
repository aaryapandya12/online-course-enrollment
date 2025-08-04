package com.practice.Course.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.Course.dto.StudentDto;
import com.practice.Course.entities.User;
import com.practice.Course.exceptions.EmailAlreadyExistsException;
import com.practice.Course.exceptions.UserAlreadyExistsException;
import com.practice.Course.exceptions.UserNotFoundException;
import com.practice.Course.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User createUser(User user) {

		if (userRepository.existsByEmail(user.getEmail())) {
			throw new EmailAlreadyExistsException(user.getEmail());
		}

		if (userRepository.existsByUserName(user.getUserName())) {
			throw new UserAlreadyExistsException(user.getUserName());
		}

		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> {
			return new UserNotFoundException("User not found with ID: " + id);
		});
	}

	@Override
	public User updateUser(Long id, User updated) {

		User existing = userRepository.findById(id).orElseThrow(() -> {
			return new UserNotFoundException("User not found with ID: " + id);
		});

		existing.setUserName(updated.getUserName());
		existing.setEmail(updated.getEmail());
		existing.setPassword(updated.getPassword());
		existing.setUserType(updated.getUserType());
		existing.setPhone(updated.getPhone());

		User savedUser = userRepository.save(existing);
		return savedUser;
	}

	@Override
	public boolean deleteUser(Long id) {

		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException("User not found with ID: " + id);
		}

		userRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User not found with this email"));
	}

	@Override
	public List<StudentDto> fetchAllStudentsNames() {
		return userRepository.fetchAllStudentsNames();
	}

	@Override
	public List<StudentDto> fetchStudentOfCourses(Long id) {

		return userRepository.fetchStudentOfCourses(id);
	}
	

	@Override
    public List<User> findAllStudents() {
        return userRepository.findAllStudents();
    }
}
