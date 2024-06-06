package org.dnyanyog.services;

import org.dnyanyog.dto.PatientRequest;
import org.dnyanyog.dto.PatientResponse;

public interface PatientManagementService {

  PatientResponse addPatient(PatientRequest request) throws Exception;

  PatientResponse updatePatient(String patientId, PatientRequest request);

  PatientResponse searchPatient(String patientId);

  PatientResponse deletePatient(String patientId);
}
