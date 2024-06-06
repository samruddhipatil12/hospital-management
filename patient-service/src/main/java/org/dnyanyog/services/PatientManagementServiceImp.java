package org.dnyanyog.services;

import jakarta.validation.Valid;
import java.util.List;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.PatientRequest;
import org.dnyanyog.dto.PatientResponse;
import org.dnyanyog.entity.Patients;
import org.dnyanyog.repo.PatientsRepository;
import org.dnyanyog.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PatientManagementServiceImp {

  @Autowired private PatientsRepository patientRepo;

  public PatientResponse addPatient(@Valid PatientRequest request) {
    PatientResponse patientResponse = PatientResponse.getInstance();

    Patients newPatient = new Patients();
    String patientId = IdGenerator.generatePatientId();
    newPatient.setpatientId(patientId);
    newPatient.setAddress(request.getAddress());
    newPatient.setBirth_date(request.getBirth_date());
    newPatient.setFirst_examination_date(request.getFirst_examination_date());
    newPatient.setGender(request.getGender());
    newPatient.setMobile_number(request.getMobile_number());
    newPatient.setPatient_name_english(request.getPatient_name_english());
    newPatient.setPatient_name_marathi(request.getPatient_name_marathi());

    try {
      newPatient = patientRepo.save(newPatient);
      populatePatientResponse(patientResponse, newPatient);
      patientResponse.setStatus(ResponseCode.ADD_PATIENT.getStatus());
      patientResponse.setMessage(ResponseCode.ADD_PATIENT.getMessage());
    } catch (DataIntegrityViolationException e) {
      patientResponse.setStatus(ResponseCode.ADD_PATIENT_FAILED.getStatus());
      patientResponse.setMessage(ResponseCode.ADD_PATIENT_FAILED.getMessage());
    }

    return patientResponse;
  }

  public PatientResponse updatePatient(String patientId, @Valid PatientRequest request) {
    List<Patients> optionalPatient = patientRepo.findByPatientId(patientId);

    PatientResponse patientResponse = PatientResponse.getInstance();
    if (optionalPatient.isEmpty()) {
      patientResponse.setStatus(ResponseCode.UPDATE_PATIENT_FAILED.getStatus());
      patientResponse.setMessage(ResponseCode.UPDATE_PATIENT_FAILED.getMessage());
    } else {
      Patients patients = optionalPatient.get(0);

      patients.setAddress(request.getAddress());
      patients.setMobile_number(request.getMobile_number());
      patients.setBirth_date(request.getBirth_date());
      patients.setFirst_examination_date(request.getFirst_examination_date());
      patients.setGender(request.getGender());
      patients.setPatient_name_english(request.getPatient_name_english());
      patients.setPatient_name_marathi(request.getPatient_name_marathi());

      patientRepo.save(patients);

      populatePatientResponse(patientResponse, patients);
      patientResponse.setMessage(ResponseCode.UPDATE_PATIENT.getMessage());
      patientResponse.setStatus(ResponseCode.UPDATE_PATIENT.getStatus());
    }

    return patientResponse;
  }

  public PatientResponse searchPatient(String patientId) {
    List<Patients> optionalPatient = patientRepo.findByPatientId(patientId);

    PatientResponse patientResponse = PatientResponse.getInstance();
    if (optionalPatient.isEmpty()) {
      patientResponse.setMessage(ResponseCode.SEARCH_PATIENT_FAILED.getMessage());
      patientResponse.setStatus(ResponseCode.SEARCH_PATIENT_FAILED.getStatus());
    } else {
      Patients patients = optionalPatient.get(0);
      populatePatientResponse(patientResponse, patients);
      patientResponse.setMessage(ResponseCode.SEARCH_PATIENT.getMessage());
      patientResponse.setStatus(ResponseCode.SEARCH_PATIENT.getStatus());
    }
    return patientResponse;
  }

  public PatientResponse deletePatient(String patientId) {
    List<Patients> optionalPatient = patientRepo.findByPatientId(patientId);

    PatientResponse patientResponse = PatientResponse.getInstance();
    if (optionalPatient.isEmpty()) {
      patientResponse.setMessage(ResponseCode.DELETE_PATIENT_FAILED.getMessage());
      patientResponse.setStatus(ResponseCode.DELETE_PATIENT_FAILED.getStatus());
    } else {
      Patients patients = optionalPatient.get(0);
      patientRepo.delete(patients);

      patientResponse.setMessage(ResponseCode.DELETE_PATIENT.getMessage());
      patientResponse.setStatus(ResponseCode.DELETE_PATIENT.getStatus());
      populatePatientResponse(patientResponse, patients);
    }
    return patientResponse;
  }

  private void populatePatientResponse(PatientResponse response, Patients patient) {
    response.setAddress(patient.getAddress());
    response.setBirth_date(patient.getBirth_date());
    response.setFirst_examination_date(patient.getFirst_examination_date());
    response.setGender(patient.getGender());
    response.setMobile_number(patient.getMobile_number());
    response.setPatient_name_english(patient.getPatient_name_english());
    response.setPatient_name_marathi(patient.getPatient_name_marathi());
  }
}
