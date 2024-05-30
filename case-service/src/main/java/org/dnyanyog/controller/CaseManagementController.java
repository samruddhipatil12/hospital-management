package org.dnyanyog.controller;

import org.dnyanyog.dto.CaseRequest;
import org.dnyanyog.dto.CaseResponse;
import org.dnyanyog.services.CaseManagementServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CaseManagementController {

  @Autowired private CaseManagementServiceImp caseService;

  @PostMapping(
      path = "/api/v1/case/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public CaseResponse addCase(@RequestBody CaseRequest caseRequest) throws Exception {
    return caseService.addCase(caseRequest);
  }

  @PostMapping("/api/v1/case/{patient_id}")
  public CaseResponse updateCase(
      @PathVariable String patient_id, @RequestBody CaseRequest request) {
    Long patientId = Long.parseLong(patient_id); // we use this to convert String to Long
    return caseService.updateCase(patientId, request);
  }

  @GetMapping(path = "/api/v1/case/{patient_id}")
  public CaseResponse searchCase(@PathVariable String patient_id) {
    Long patientId = Long.parseLong(patient_id);
    return caseService.searchCase(patientId);
  }

  @DeleteMapping(path = "/api/v1/case/{patient_id}")
  public CaseResponse deleteCase(@PathVariable String patient_id) {
    Long patientId = Long.parseLong(patient_id); // Convert String to Long
    return caseService.deleteCase(patientId);
  }
}
