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

  @PutMapping("/api/v1/case/{patientId}")
  public CaseResponse updateCase(
      @PathVariable("patientId") String patientId, @RequestBody CaseRequest request) {
    return caseService.updateCase(patientId, request);
  }

  @GetMapping("/api/v1/case/{caseId}")
  public CaseResponse SearchCase(@PathVariable("caseId") String caseId) {
    return caseService.searchCase(caseId);
  }

  @DeleteMapping("/api/v1/case/{caseId}")
  public CaseResponse deleteCase(@PathVariable("caseId") String caseId) {
    return caseService.deleteCase(caseId);
  }
}
