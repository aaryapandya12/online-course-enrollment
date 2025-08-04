package com.practice.Course;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.practice.Course.controllers.EnrollmentController;
import com.practice.Course.entities.Course;
import com.practice.Course.entities.Enrollment;
import com.practice.Course.entities.User;
import com.practice.Course.services.EnrollmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 class EnrollmentControllerTest {

    @Mock
    private EnrollmentService enrollmentService;

    @InjectMocks
    private EnrollmentController enrollmentController;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
     void testCreateEnrollment() {
        
        User user = new User();
        Course course = new Course();
        Enrollment enrollment = new Enrollment(1L, course, user, LocalDate.now());

        when(bindingResult.hasErrors()).thenReturn(false);
        when(enrollmentService.addEnrollment(enrollment)).thenReturn(enrollment);

        ResponseEntity<Enrollment> response = enrollmentController.createEnrollment(enrollment, bindingResult);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).isEqualTo(enrollment);
    }


    @Test
     void testGetAllEnrollments() {
    
        Enrollment e1 = new Enrollment(1L, new Course(), new User(), LocalDate.now());
        Enrollment e2 = new Enrollment(2L, new Course(), new User(), LocalDate.now().minusDays(1));

        when(enrollmentService.getAllEnrollments()).thenReturn(Arrays.asList(e1, e2));

        ResponseEntity<List<Enrollment>> response = enrollmentController.getAllEnrollments();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody().get(0).getEnrollmentId()).isEqualTo(1L);
    }

    @Test
     void testGetEnrollmentById() {

        Enrollment e = new Enrollment(1L, new Course(), new User(), LocalDate.now());
        when(enrollmentService.getEnrollmentById(1L)).thenReturn(Optional.of(e));

        ResponseEntity<Enrollment> response = enrollmentController.getEnrollmentById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getEnrollmentId()).isEqualTo(1L);
    }

}

