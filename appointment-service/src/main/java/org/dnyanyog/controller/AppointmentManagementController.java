package org.dnyanyog.controller;

import java.util.List;
import org.dnyanyog.dto.AppointmentRequest;
import org.dnyanyog.dto.AppointmentResponse;
import org.dnyanyog.entity.Appointments;
import org.dnyanyog.services.AppointmentManagementServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppointmentManagementController {

  @Autowired private AppointmentManagementServiceImp appointmentService;

  @PostMapping(
      path = "/api/v1/appointment/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public AppointmentResponse addAppointment(@RequestBody AppointmentRequest appointmentRequest)
      throws Exception {
    return appointmentService.addAppointment(appointmentRequest);
  }

  @PostMapping("/api/v1/appointment/{patient_id}")
  public AppointmentResponse updateAppointment(
      @PathVariable String patient_id, @RequestBody AppointmentRequest request) {
    return appointmentService.updateAppointment(patient_id, request);
  }

  @GetMapping(path = "/api/v1/appointment/{appointment Id}")
  public AppointmentResponse searchAppointment(@PathVariable String patient_id) {
    return appointmentService.searchAppointment(patient_id);
  }

  @GetMapping("/api/v1/patient/{patient_id}/appointments")
  public List<Appointments> getAppointmentsByPatientId(@PathVariable String patient_id) {
    return appointmentService.getAppointmentsByPatientId(patient_id);
  }

  @DeleteMapping(path = "/api/v1/appointment/{appointment Id}")
  public AppointmentResponse deleteAppointment(@PathVariable String patient_id) {
    return appointmentService.deleteAppointment(patient_id);
  }
}
