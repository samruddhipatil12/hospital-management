package org.dnyanyog.controller;

import org.dnyanyog.dto.AppointmentRequest;
import org.dnyanyog.dto.AppointmentResponse;
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

  @PutMapping("/api/v1/appointment/{patientId}")
  public AppointmentResponse updateAppointment(
      @PathVariable String patientId, @RequestBody AppointmentRequest request) {
    return appointmentService.updateAppointment(patientId, request);
  }

  @GetMapping(path = "/api/v1/appointment/{appointmentId}")
  public AppointmentResponse searchAppointment(@PathVariable String appointmentId) {
    return appointmentService.searchAppointment(appointmentId);
  }

  @DeleteMapping(path = "/api/v1/appointment/{appointmentId}")
  public AppointmentResponse deleteAppointment(@PathVariable String appointmentId) {
    return appointmentService.deleteAppointment(appointmentId);
  }
}
