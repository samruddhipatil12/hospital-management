package org.dnyanyog.controller;

import org.dnyanyog.dto.PatientRequest;
import org.dnyanyog.dto.PatientResponse;
import org.dnyanyog.services.PatientManagementServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientManagementController {

  @Autowired private PatientManagementServiceImp patientService;

  @PostMapping("/api/v1/patient/add")
  public PatientResponse addPatient(@RequestBody PatientRequest request) {
    return patientService.addPatient(request);
  }

  @PutMapping("/api/v1/patient/{patientId}")
  public PatientResponse updatePatient(
      @PathVariable("patientId") String patientId, @RequestBody PatientRequest request) {
    return patientService.updatePatient(patientId, request);
  }

  @GetMapping("/api/v1/patient/{patientId}")
  public PatientResponse searchPatient(@PathVariable("patientId") String patientId) {
    return patientService.searchPatient(patientId);
  }

  @DeleteMapping("/api/v1/patient/{patientId}")
  public PatientResponse deletePatient(@PathVariable("patientId") String patientId) {
    return patientService.deletePatient(patientId);
  }
}
