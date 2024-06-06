package org.dnyanyog.services;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.AppointmentRequest;
import org.dnyanyog.dto.AppointmentResponse;
import org.dnyanyog.entity.Appointments;
import org.dnyanyog.repo.AppointmentRepository;
import org.dnyanyog.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentManagementServiceImp {

  @Autowired private AppointmentRepository appointmentRepo;

  public List<Appointments> getAppointmentsByPatientId(String patientId) {
    return appointmentRepo.findByPatientId(patientId);
  }

  public AppointmentResponse addAppointment(@Valid AppointmentRequest request) throws Exception {
    AppointmentResponse appointmentResponse = AppointmentResponse.getInstance();
    Appointments newAppointment = new Appointments();
    String appointmentId = IdGenerator.generateAppointmentId();
    newAppointment.setAppointmentId(appointmentId); // Set the generated appointment ID

    newAppointment.setPatientId(IdGenerator.generatePatientId()); // Generate and set the patient ID
    newAppointment.setPatientNameEnglish(request.getPatient_name_english());
    newAppointment.setExaminationDate(request.getExamination_date());

    try {
      newAppointment = appointmentRepo.save(newAppointment);
      populateAppointmentResponse(appointmentResponse, newAppointment);
      appointmentResponse.setStatus(ResponseCode.ADD_APPOINTMENT.getStatus());
      appointmentResponse.setMessage(ResponseCode.ADD_APPOINTMENT.getMessage());
    } catch (Exception e) {
      appointmentResponse.setStatus(ResponseCode.ADD_APPOINTMENT_FAILED.getStatus());
      appointmentResponse.setMessage(ResponseCode.ADD_APPOINTMENT_FAILED.getMessage());
    }

    return appointmentResponse;
  }

  private void populateAppointmentResponse(AppointmentResponse response, Appointments appointment) {
    response.setAppointmentId(
        appointment.getAppointmentId()); // No need to convert as it is already a String
    response.setPatient_name_english(appointment.getPatientNameEnglish());
    response.setPatient_id(appointment.getPatientId());
    response.setExamination_date(appointment.getExaminationDate());
  }

  public AppointmentResponse updateAppointment(
      String patientId, @Valid AppointmentRequest request) {
    List<Appointments> appointmentOptional = appointmentRepo.findByPatientId(patientId);
    AppointmentResponse appointmentResponse = AppointmentResponse.getInstance();

    if (appointmentOptional.isEmpty()) {
      appointmentResponse.setStatus(ResponseCode.UPDATE_APPOINTMENT_FAILED.getStatus());
      appointmentResponse.setMessage(ResponseCode.UPDATE_APPOINTMENT_FAILED.getMessage());
    } else {
      Appointments appointment = appointmentOptional.get(0);

      appointment.setPatientNameEnglish(request.getPatient_name_english());
      appointment.setExaminationDate(request.getExamination_date());

      try {
        appointmentRepo.save(appointment);
        populateAppointmentResponse(appointmentResponse, appointment);
        appointmentResponse.setMessage(ResponseCode.UPDATE_APPOINTMENT.getMessage());
        appointmentResponse.setStatus(ResponseCode.UPDATE_APPOINTMENT.getStatus());
      } catch (Exception e) {
        appointmentResponse.setStatus(ResponseCode.UPDATE_APPOINTMENT_FAILED.getStatus());
        appointmentResponse.setMessage(ResponseCode.UPDATE_APPOINTMENT_FAILED.getMessage());
      }
    }

    return appointmentResponse;
  }

  public AppointmentResponse searchAppointment(String appointmentId) {
    Optional<Appointments> appointmentOptional = appointmentRepo.findByAppointmentId(appointmentId);
    AppointmentResponse appointmentResponse = AppointmentResponse.getInstance();

    if (appointmentOptional.isEmpty()) {
      appointmentResponse.setMessage(ResponseCode.SEARCH_APPOINTMENT_FAILED.getMessage());
      appointmentResponse.setStatus(ResponseCode.SEARCH_APPOINTMENT_FAILED.getStatus());
    } else {
      Appointments appointment = appointmentOptional.get();
      populateAppointmentResponse(appointmentResponse, appointment);
      appointmentResponse.setMessage(ResponseCode.SEARCH_APPOINTMENT.getMessage());
      appointmentResponse.setStatus(ResponseCode.SEARCH_APPOINTMENT.getStatus());
    }

    return appointmentResponse;
  }

  public AppointmentResponse deleteAppointment(String appointmentId) {
    Optional<Appointments> appointmentOptional = appointmentRepo.findByAppointmentId(appointmentId);
    AppointmentResponse appointmentResponse = AppointmentResponse.getInstance();

    if (appointmentOptional.isEmpty()) {
      appointmentResponse.setMessage(ResponseCode.DELETE_APPOINTMENT_FAILED.getMessage());
      appointmentResponse.setStatus(ResponseCode.DELETE_APPOINTMENT_FAILED.getStatus());
    } else {
      Appointments appointment = appointmentOptional.get();
      appointmentRepo.delete(appointment);

      appointmentResponse.setMessage(ResponseCode.DELETE_APPOINTMENT.getMessage());
      appointmentResponse.setStatus(ResponseCode.DELETE_APPOINTMENT.getStatus());
    }

    return appointmentResponse;
  }
}
