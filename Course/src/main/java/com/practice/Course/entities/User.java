package com.practice.Course.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@NotBlank(message = "Username is mandatory")
	@Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
	@Column(name = "user_name")
	private String userName;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Invalid email format")
	@Column(name = "email", unique = true)
	private String email;

	@NotBlank(message = "Password is mandatory")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	@Column(name = "password")
	private String password;

	@NotBlank(message = "Phone number is mandatory")
	@Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
	@Column(name = "phone")
	private String phone;

	@NotBlank(message = "User type is mandatory")
	@Column(name = "user_type")
	private String userType;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "user_enroll")
	private List<Enrollment> enrollments = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "user_submission")
	private List<Submission> submissions = new ArrayList<>();

	public User() {
		super();
	}

	public User(Long userId, String userName, String email, String password, String phone, String userType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.userType = userType;
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public List<Submission> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + ", userType=" + userType + ", enrollments=" + enrollments + ", submissions="
				+ submissions + "]";
	}

}
