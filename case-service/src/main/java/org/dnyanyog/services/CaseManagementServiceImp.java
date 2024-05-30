package org.dnyanyog.services;

import jakarta.validation.Valid;
import java.util.List;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.CaseRequest;
import org.dnyanyog.dto.CaseResponse;
import org.dnyanyog.entity.Cases;
import org.dnyanyog.repo.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseManagementServiceImp {

  @Autowired private CaseRepository caseRepo;

  public List<Cases> getCasesByPatientId(Long patientId) {
    return caseRepo.findByPatientId(patientId);
  }

  public CaseResponse addCase(@Valid CaseRequest request) throws Exception {
    CaseResponse response = CaseResponse.getInstance();
    Cases newCase = new Cases();

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

  private void populateCaseResponse(CaseResponse response, Cases newCase) {
    response.setPatientNameEnglish(newCase.getPatientNameEnglish());
    response.setPatientId(String.valueOf(newCase.getPatientId())); // Convert Long to String
    response.setCaseNumber(newCase.getCaseNumber());
    response.setSymptoms(newCase.getSymptoms());
    response.setPrescription(newCase.getPrescription());
    response.setExaminationDate(newCase.getExaminationDate());
  }

  public CaseResponse updateCase(Long patientId, @Valid CaseRequest request) {
    Cases existingCase = caseRepo.findById(patientId).orElse(null);
    CaseResponse response = CaseResponse.getInstance();

    if (existingCase == null) {
      response.setStatus(ResponseCode.UPDATE_CASE_FAILED.getStatus());
      response.setMessage(ResponseCode.UPDATE_CASE_FAILED.getMessage());
    } else {
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

  public CaseResponse searchCase(Long patientId) {
    Cases foundCase = caseRepo.findById(patientId).orElse(null);
    CaseResponse response = CaseResponse.getInstance();

    if (foundCase == null) {
      response.setMessage(ResponseCode.SEARCH_CASE_FAILED.getMessage());
      response.setStatus(ResponseCode.SEARCH_CASE_FAILED.getStatus());
    } else {
      populateCaseResponse(response, foundCase);
      response.setMessage(ResponseCode.SEARCH_CASE.getMessage());
      response.setStatus(ResponseCode.SEARCH_CASE.getStatus());
    }

    return response;
  }

  public CaseResponse deleteCase(Long patientId) {
    Cases foundCase = caseRepo.findById(patientId).orElse(null);
    CaseResponse response = CaseResponse.getInstance();

    if (foundCase == null) {
      response.setMessage(ResponseCode.DELETE_CASE_FAILED.getMessage());
      response.setStatus(ResponseCode.DELETE_CASE_FAILED.getStatus());
    } else {
      caseRepo.delete(foundCase);
      response.setMessage(ResponseCode.DELETE_CASE.getMessage());
      response.setStatus(ResponseCode.DELETE_CASE.getStatus());
    }

    return response;
  }
}
