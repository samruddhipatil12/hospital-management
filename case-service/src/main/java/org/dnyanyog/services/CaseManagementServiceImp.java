package org.dnyanyog.services;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.CaseRequest;
import org.dnyanyog.dto.CaseResponse;
import org.dnyanyog.entity.Cases;
import org.dnyanyog.repo.CaseRepository;
import org.dnyanyog.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseManagementServiceImp {

  @Autowired private CaseRepository caseRepo;

  public List<Cases> getCasesByPatientId(String patientId) {
    return caseRepo.findByPatientId(patientId);
  }

  public CaseResponse addCase(@Valid CaseRequest request) throws Exception {
    CaseResponse response = CaseResponse.getInstance();
    Cases newCase = new Cases();

    String patientId = IdGenerator.generatePatientId();
    String caseId = IdGenerator.generateCaseId();

    newCase.setPatientId(patientId);
    newCase.setCaseId(caseId);
    newCase.setPatientNameEnglish(request.getPatientNameEnglish());
    newCase.setCaseNumber(request.getCaseNumber());
    newCase.setSymptoms(request.getSymptoms());
    newCase.setPrescription(request.getPrescription());
    newCase.setExaminationDate(request.getExaminationDate());

    try {
      newCase = caseRepo.save(newCase);
      populateCaseResponse(response, newCase);
      response.setStatus(ResponseCode.ADD_CASE.getStatus());
      response.setMessage(ResponseCode.ADD_CASE.getMessage());
    } catch (Exception e) {
      response.setStatus(ResponseCode.ADD_CASE_FAILED.getStatus());
      response.setMessage(ResponseCode.ADD_CASE_FAILED.getMessage());
    }

    return response;
  }

  public CaseResponse updateCase(String caseId, @Valid CaseRequest request) {
    CaseResponse response = CaseResponse.getInstance();

    Optional<Cases> existingCaseOptional = caseRepo.findById(caseId);

    if (existingCaseOptional.isEmpty()) {
      response.setStatus(ResponseCode.UPDATE_CASE_FAILED.getStatus());
      response.setMessage(ResponseCode.UPDATE_CASE_FAILED.getMessage());
    } else {
      Cases existingCase = existingCaseOptional.get();

      existingCase.setPatientNameEnglish(request.getPatientNameEnglish());
      existingCase.setCaseNumber(request.getCaseNumber());
      existingCase.setSymptoms(request.getSymptoms());
      existingCase.setPrescription(request.getPrescription());
      existingCase.setExaminationDate(request.getExaminationDate());

      try {
        caseRepo.save(existingCase);
        populateCaseResponse(response, existingCase);
        response.setMessage(ResponseCode.UPDATE_CASE.getMessage());
        response.setStatus(ResponseCode.UPDATE_CASE.getStatus());
      } catch (Exception e) {
        response.setStatus(ResponseCode.UPDATE_CASE_FAILED.getStatus());
        response.setMessage(ResponseCode.UPDATE_CASE_FAILED.getMessage());
      }
    }

    return response;
  }

  public CaseResponse searchCase(String caseId) {
    Optional<Cases> foundCaseOpt = caseRepo.findByCaseId(caseId);
    CaseResponse response = CaseResponse.getInstance();

    if (foundCaseOpt.isEmpty()) {
      response.setMessage(ResponseCode.SEARCH_CASE_FAILED.getMessage());
      response.setStatus(ResponseCode.SEARCH_CASE_FAILED.getStatus());
    } else {
      Cases foundCase = foundCaseOpt.get();
      populateCaseResponse(response, foundCase);
      response.setMessage(ResponseCode.SEARCH_CASE.getMessage());
      response.setStatus(ResponseCode.SEARCH_CASE.getStatus());
    }

    return response;
  }

  public CaseResponse deleteCase(String patientId) {
    List<Cases> foundCases = caseRepo.findByPatientId(patientId);
    CaseResponse response = CaseResponse.getInstance();

    if (foundCases.isEmpty()) {
      response.setMessage(ResponseCode.DELETE_CASE_FAILED.getMessage());
      response.setStatus(ResponseCode.DELETE_CASE_FAILED.getStatus());
    } else {
      Cases foundCase = foundCases.get(0);
      caseRepo.delete(foundCase);
      response.setMessage(ResponseCode.DELETE_CASE.getMessage());
      response.setStatus(ResponseCode.DELETE_CASE.getStatus());
    }

    return response;
  }

  private void populateCaseResponse(CaseResponse response, Cases caseEntity) {
    response.setPatientId(caseEntity.getPatientId());
    response.setCaseId(caseEntity.getCaseId());
    response.setPatientNameEnglish(caseEntity.getPatientNameEnglish());
    response.setCaseNumber(caseEntity.getCaseNumber());
    response.setSymptoms(caseEntity.getSymptoms());
    response.setPrescription(caseEntity.getPrescription());
    response.setExaminationDate(caseEntity.getExaminationDate());
  }
}
