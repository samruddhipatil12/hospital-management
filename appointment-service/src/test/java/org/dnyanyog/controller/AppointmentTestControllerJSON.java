package org.dnyanyog.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.AppointmentRequest;
import org.dnyanyog.entity.Appointments;
import org.dnyanyog.repo.AppointmentRepository;
import org.dnyanyog.services.AppointmentManagementServiceImp;
import org.dnyanyog.utils.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class AppointmentTestControllerJSON {

  @Mock private AppointmentRepository appointmentRepo;

  @InjectMocks private AppointmentManagementServiceImp appointmentManagementService;

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(appointmentManagementService).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void testAddAppointment() throws Exception {
    AppointmentRequest request = new AppointmentRequest();
    request.setPatient_name_english("Samruddhi Patil");
    request.setExamination_date("2023-06-01");

    Appointments newAppointment = new Appointments();
    newAppointment.setAppointmentId(IdGenerator.generateAppointmentId());
    newAppointment.setPatientId(IdGenerator.generatePatientId());
    newAppointment.setPatientNameEnglish(request.getPatient_name_english());
    newAppointment.setExaminationDate(request.getExamination_date());

    when(appointmentRepo.save(any(Appointments.class))).thenReturn(newAppointment);

    mockMvc
        .perform(
            post("/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(ResponseCode.ADD_APPOINTMENT.getStatus()))
        .andExpect(jsonPath("$.message").value(ResponseCode.ADD_APPOINTMENT.getMessage()))
        .andExpect(jsonPath("$.appointmentId").value(newAppointment.getAppointmentId()))
        .andExpect(jsonPath("$.patientId").value(newAppointment.getPatientId()));
  }

  @Test
  public void testUpdateAppointment() throws Exception {
    String patientId = "testPatientId";
    AppointmentRequest request = new AppointmentRequest();
    request.setPatient_name_english("Samruddhi Patil");
    request.setExamination_date("2023-06-01");

    Appointments existingAppointment = new Appointments();
    existingAppointment.setAppointmentId("testAppointmentId");
    existingAppointment.setPatientId(patientId);
    existingAppointment.setPatientNameEnglish("Samruddhi Patil");
    existingAppointment.setExaminationDate("2023-06-01");

    when(appointmentRepo.findByPatientId(patientId)).thenReturn(List.of(existingAppointment));
    when(appointmentRepo.save(any(Appointments.class))).thenReturn(existingAppointment);

    mockMvc
        .perform(
            put("/appointments/" + patientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(ResponseCode.UPDATE_APPOINTMENT.getStatus()))
        .andExpect(jsonPath("$.message").value(ResponseCode.UPDATE_APPOINTMENT.getMessage()))
        .andExpect(jsonPath("$.patientId").value(patientId));
  }

  @Test
  public void testSearchAppointment() throws Exception {
    String appointmentId = "testAppointmentId";

    Appointments existingAppointment = new Appointments();
    existingAppointment.setAppointmentId(appointmentId);
    existingAppointment.setPatientId("testPatientId");
    existingAppointment.setPatientNameEnglish("Samruddhi Patil");
    existingAppointment.setExaminationDate("2023-06-01");

    when(appointmentRepo.findByAppointmentId(appointmentId))
        .thenReturn(Optional.of(existingAppointment));

    mockMvc
        .perform(get("/appointments/" + appointmentId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(ResponseCode.SEARCH_APPOINTMENT.getStatus()))
        .andExpect(jsonPath("$.message").value(ResponseCode.SEARCH_APPOINTMENT.getMessage()))
        .andExpect(jsonPath("$.appointmentId").value(appointmentId));
  }

  @Test
  public void testDeleteAppointment() throws Exception {
    String appointmentId = "testAppointmentId";

    Appointments existingAppointment = new Appointments();
    existingAppointment.setAppointmentId(appointmentId);
    existingAppointment.setPatientId("testPatientId");

    when(appointmentRepo.findByAppointmentId(appointmentId))
        .thenReturn(Optional.of(existingAppointment));

    mockMvc
        .perform(delete("/appointments/" + appointmentId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(ResponseCode.DELETE_APPOINTMENT.getStatus()))
        .andExpect(jsonPath("$.message").value(ResponseCode.DELETE_APPOINTMENT.getMessage()));
  }
}
