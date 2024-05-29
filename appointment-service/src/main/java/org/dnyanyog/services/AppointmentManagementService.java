package org.dnyanyog.services;

import jakarta.validation.Valid;
import org.dnyanyog.dto.AppointmentRequest;
import org.dnyanyog.dto.AppointmentResponse;

public interface AppointmentManagementService {

  public AppointmentResponse addAppointment(@Valid AppointmentRequest request) throws Exception;

  public AppointmentResponse updateAppointment(long patient_id, AppointmentRequest request);

  public AppointmentResponse searchAppointment(long patient_id);

  public AppointmentResponse deleteAppointment(long patient_id);
}
