package com.practice.Course.dto;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor 
@RequiredArgsConstructor
public class Top5CoursesDto {

	    private String courseTitle;
	    private Long totalEnrollments;
 
	    
}