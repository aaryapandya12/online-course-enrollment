package com.practice.Course.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.practice.Course.dto.StudDashboardDTO;
import com.practice.Course.services.StudDashboardService;

@RestController
@RequestMapping("/api/studdashboard")
public class StudDashboardController {
	private final StudDashboardService dashboardService;
	
	@Autowired
	public StudDashboardController(StudDashboardService dashboardService) {
		super();
		this.dashboardService = dashboardService;
	}

	@GetMapping
    public ResponseEntity<StudDashboardDTO> findAllfetchDashboardCount() {
        return ResponseEntity.ok(dashboardService.fetchDashboardCount());
    } 
	
}
