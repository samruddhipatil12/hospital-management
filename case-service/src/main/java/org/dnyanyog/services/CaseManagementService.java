package org.dnyanyog.services;

import jakarta.validation.Valid;
import org.dnyanyog.dto.CaseRequest;
import org.dnyanyog.dto.CaseResponse;

public interface CaseManagementService {

  public CaseResponse addAppointment(@Valid CaseRequest request) throws Exception;

  public CaseResponse updateAppointment(long patient_id, CaseRequest request);

  public CaseResponse searchAppointment(long patient_id);

  public CaseResponse deleteAppointment(long patient_id);
}
