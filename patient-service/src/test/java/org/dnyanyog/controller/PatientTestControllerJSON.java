package org.dnyanyog.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.PatientRequest;
import org.dnyanyog.entity.Patients;
import org.dnyanyog.repo.PatientsRepository;
import org.dnyanyog.services.PatientManagementServiceImp;
import org.dnyanyog.utils.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class PatientTestControllerJSON {

  @Mock private PatientsRepository patientRepo;

  @InjectMocks private PatientManagementServiceImp patientService;

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(patientService).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void testAddPatientSuccess() throws Exception {
    PatientRequest request = new PatientRequest();
    request.setAddress("Wagholi");
    request.setBirth_date("2003-01-01");
    request.setFirst_examination_date("2023-06-01");
    request.setGender("Female");
    request.setMobile_number("1234567890");
    request.setPatient_name_english("Samruddhi Patil");
    request.setPatient_name_marathi("समृद्धी पाटील");

    Patients patient = new Patients();
    patient.setpatientId(IdGenerator.generatePatientId());
    patient.setAddress("Wagholi");
    patient.setBirth_date("2003-01-01");
    patient.setFirst_examination_date("2023-06-01");
    patient.setGender("Female");
    patient.setMobile_number("1234567890");
    patient.setPatient_name_english("Samruddhi Patil");
    patient.setPatient_name_marathi("समृद्धी पाटील");

    when(patientRepo.save(any(Patients.class))).thenReturn(patient);

    mockMvc
        .perform(
            post("/patients/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(ResponseCode.ADD_PATIENT.getStatus()))
        .andExpect(jsonPath("$.message").value(ResponseCode.ADD_PATIENT.getMessage()))
        .andExpect(jsonPath("$.address").value("Wagholi"))
        .andExpect(jsonPath("$.birth_date").value("2003-01-01"))
        .andExpect(jsonPath("$.first_examination_date").value("2023-06-01"))
        .andExpect(jsonPath("$.gender").value("Female"))
        .andExpect(jsonPath("$.mobile_number").value("1234567890"))
        .andExpect(jsonPath("$.patient_name_english").value("Samruddhi Patil"))
        .andExpect(jsonPath("$.patient_name_marathi").value("समृद्धी पाटील"));
  }

  @Test
  public void testAddPatientFailure() throws Exception {
    PatientRequest request = new PatientRequest();
    request.setAddress("Wagholi");
    request.setBirth_date("2003-01-01");
    request.setFirst_examination_date("2023-06-01");
    request.setGender("Female");
    request.setMobile_number("1234567890");
    request.setPatient_name_english("Samruddhi Patil");
    request.setPatient_name_marathi("समृद्धी पाटील");

    when(patientRepo.save(any(Patients.class))).thenThrow(DataIntegrityViolationException.class);

    mockMvc
        .perform(
            post("/patients/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(ResponseCode.ADD_PATIENT_FAILED.getStatus()))
        .andExpect(jsonPath("$.message").value(ResponseCode.ADD_PATIENT_FAILED.getMessage()));
  }

  @Test
  public void testUpdatePatientSuccess() throws Exception {
    String patientId = "123456";
    PatientRequest request = new PatientRequest();
    request.setAddress("Wagholi");
    request.setBirth_date("2003-01-01");
    request.setFirst_examination_date("2023-06-01");
    request.setGender("Female");
    request.setMobile_number("1234567890");
    request.setPatient_name_english("Samruddhi Patil");
    request.setPatient_name_marathi("समृद्धी पाटील");

    Patients patient = new Patients();
    patient.setpatientId(patientId);
    patient.setAddress("Wagholi");
    patient.setBirth_date("2003-01-01");
    patient.setFirst_examination_date("2023-06-01");
    patient.setGender("Female");
    patient.setMobile_number("1234567890");
    patient.setPatient_name_english("Samruddhi Patil");
    patient.setPatient_name_marathi("समृद्धी पाटील");

    when(patientRepo.findByPatientId(patientId)).thenReturn(Collections.singletonList(patient));
    when(patientRepo.save(any(Patients.class))).thenReturn(patient);

    mockMvc
        .perform(
            post("/patients/update/" + patientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(ResponseCode.UPDATE_PATIENT.getStatus()))
        .andExpect(jsonPath("$.message").value(ResponseCode.UPDATE_PATIENT.getMessage()))
        .andExpect(jsonPath("$.address").value("Wagholi"))
        .andExpect(jsonPath("$.birth_date").value("2003-01-01"))
        .andExpect(jsonPath("$.first_examination_date").value("2023-06-01"))
        .andExpect(jsonPath("$.gender").value("Female"))
        .andExpect(jsonPath("$.mobile_number").value("1234567890"))
        .andExpect(jsonPath("$.patient_name_english").value("Samruddhi Patil"))
        .andExpect(jsonPath("$.patient_name_marathi").value("समृद्धी पाटील"));
  }

  @Test
  public void testUpdatePatientFailure() throws Exception {
    String patientId = "123456";
    PatientRequest request = new PatientRequest();
    request.setAddress("Wagholi");
    request.setBirth_date("2003-01-01");
    request.setFirst_examination_date("2023-06-01");
    request.setGender("Female");
    request.setMobile_number("1234567890");
    request.setPatient_name_english("Samruddhi Patil");
    request.setPatient_name_marathi("समृद्धी पाटील");

    when(patientRepo.findByPatientId(patientId)).thenReturn(Collections.emptyList());

    mockMvc
        .perform(
            post("/patients/update/" + patientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(ResponseCode.UPDATE_PATIENT_FAILED.getStatus()))
        .andExpect(jsonPath("$.message").value(ResponseCode.UPDATE_PATIENT_FAILED.getMessage()));
  }

  @Test
  public void testSearchPatientSuccess() throws Exception {
    String patientId = "123456";
    Patients patient = new Patients();
    patient.setpatientId(patientId);
    patient.setAddress("Wagholi");
    patient.setBirth_date("2003-01-01");
    patient.setFirst_examination_date("2023-06-01");
    patient.setGender("Female");
    patient.setMobile_number("1234567890");
    patient.setPatient_name_english("Samruddhi Patil");
    patient.setPatient_name_marathi("समृद्धी पाटील");

    when(patientRepo.findByPatientId(patientId)).thenReturn(Collections.singletonList(patient));

    mockMvc
        .perform(post("/patients/search/" + patientId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(ResponseCode.SEARCH_PATIENT.getStatus()))
        .andExpect(jsonPath("$.message").value(ResponseCode.SEARCH_PATIENT.getMessage()))
        .andExpect(jsonPath("$.address").value("123 Main St"))
        .andExpect(jsonPath("$.birth_date").value("1990-01-01"))
        .andExpect(jsonPath("$.first_examination_date").value("2023-06-01"))
        .andExpect(jsonPath("$.gender").value("Female"))
        .andExpect(jsonPath("$.mobile_number").value("1234567890"))
        .andExpect(jsonPath("$.patient_name_english").value("Samruddhi Patil"))
        .andExpect(jsonPath("$.patient_name_marathi").value("समृद्धी पाटील"));
  }

  @Test
  public void testSearchPatientFailure() throws Exception {
    String patientId = "123456";

    when(patientRepo.findByPatientId(patientId)).thenReturn(Collections.emptyList());

    mockMvc
        .perform(post("/patients/search/" + patientId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(ResponseCode.SEARCH_PATIENT_FAILED.getStatus()))
        .andExpect(jsonPath("$.message").value(ResponseCode.SEARCH_PATIENT_FAILED.getMessage()));
  }

  @Test
  public void testDeletePatientSuccess() throws Exception {
    String patientId = "123456";
    Patients patient = new Patients();
    patient.setpatientId(patientId);
    patient.setAddress("Wagholi");
    patient.setBirth_date("2003-01-01");
    patient.setFirst_examination_date("2023-06-01");
    patient.setGender("Female");
    patient.setMobile_number("1234567890");
    patient.setPatient_name_english("Samruddhi Patil");
    patient.setPatient_name_marathi("समृद्धी पाटील");

    when(patientRepo.findByPatientId(patientId)).thenReturn(Collections.singletonList(patient));

    mockMvc
        .perform(post("/patients/delete/" + patientId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(ResponseCode.DELETE_PATIENT.getStatus()))
        .andExpect(jsonPath("$.message").value(ResponseCode.DELETE_PATIENT.getMessage()))
        .andExpect(jsonPath("$.address").value("Wagholi"))
        .andExpect(jsonPath("$.birth_date").value("2003-01-01"))
        .andExpect(jsonPath("$.first_examination_date").value("2023-06-01"))
        .andExpect(jsonPath("$.gender").value("Female"))
        .andExpect(jsonPath("$.mobile_number").value("1234567890"))
        .andExpect(jsonPath("$.patient_name_english").value("Samruddhi Patil"))
        .andExpect(jsonPath("$.patient_name_marathi").value("समृद्धी पाटील"));
  }

  @Test
  public void testDeletePatientFailure() throws Exception {
    String patientId = "123456";

    when(patientRepo.findByPatientId(patientId)).thenReturn(Collections.emptyList());

    mockMvc
        .perform(post("/patients/delete/" + patientId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(ResponseCode.DELETE_PATIENT_FAILED.getStatus()))
        .andExpect(jsonPath("$.message").value(ResponseCode.DELETE_PATIENT_FAILED.getMessage()));
  }
}
