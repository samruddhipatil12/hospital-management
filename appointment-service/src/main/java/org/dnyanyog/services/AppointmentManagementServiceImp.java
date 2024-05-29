package org.dnyanyog.services;

import jakarta.validation.Valid;
import java.util.List;
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
    String patientId = IdGenerator.generatePatientId();
    newAppointment.setPatientId(patientId);

    newAppointment.setPatientNameEnglish(request.getPatient_name_english());
    newAppointment.setAppointmentDate(request.getAppointment_date());
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
    response.setAppointment_id(
        String.valueOf(appointment.getAppointmentId())); // Convert long to String
    response.setPatient_name_english(appointment.getPatientNameEnglish());
    response.setPatient_id(appointment.getPatientId());
    response.setAppointment_date(appointment.getAppointmentDate());
    response.setExamination_date(appointment.getExaminationDate());
  }

  public AppointmentResponse updateAppointment(
      String patientId, @Valid AppointmentRequest request) {
    List<Appointments> appointments = appointmentRepo.findByPatientId(patientId);
    AppointmentResponse appointmentResponse = AppointmentResponse.getInstance();

    if (appointments.isEmpty()) {
      appointmentResponse.setStatus(ResponseCode.UPDATE_APPOINTMENT_FAILED.getStatus());
      appointmentResponse.setMessage(ResponseCode.UPDATE_APPOINTMENT_FAILED.getMessage());
    } else {
      Appointments appointment = appointments.get(0);

      appointment.setPatientNameEnglish(request.getPatient_name_english());
      appointment.setAppointmentDate(request.getAppointment_date());
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

  public AppointmentResponse searchAppointment(String patientId) {
    List<Appointments> appointments = appointmentRepo.findByPatientId(patientId);
    AppointmentResponse appointmentResponse = AppointmentResponse.getInstance();

    if (appointments.isEmpty()) {
      appointmentResponse.setMessage(ResponseCode.SEARCH_APPOINTMENT_FAILED.getMessage());
      appointmentResponse.setStatus(ResponseCode.SEARCH_APPOINTMENT_FAILED.getStatus());
    } else {
      Appointments appointment = appointments.get(0);
      populateAppointmentResponse(appointmentResponse, appointment);
      appointmentResponse.setMessage(ResponseCode.SEARCH_APPOINTMENT.getMessage());
      appointmentResponse.setStatus(ResponseCode.SEARCH_APPOINTMENT.getStatus());
    }

    return appointmentResponse;
  }

  public AppointmentResponse deleteAppointment(String patientId) {
    List<Appointments> appointments = appointmentRepo.findByPatientId(patientId);
    AppointmentResponse appointmentResponse = AppointmentResponse.getInstance();

    if (appointments.isEmpty()) {
      appointmentResponse.setMessage(ResponseCode.DELETE_APPOINTMENT_FAILED.getMessage());
      appointmentResponse.setStatus(ResponseCode.DELETE_APPOINTMENT_FAILED.getStatus());
    } else {
      Appointments appointment = appointments.get(0);
      appointmentRepo.delete(appointment);

      appointmentResponse.setMessage(ResponseCode.DELETE_APPOINTMENT.getMessage());
      appointmentResponse.setStatus(ResponseCode.DELETE_APPOINTMENT.getStatus());
    }

    return appointmentResponse;
  }
}
