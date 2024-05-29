package org.dnyanyog.common;

public enum ResponseCode {
  ADD_APPOINTMENT("Success", "Appointment added successfully!!"),
  ADD_APPOINTMENT_FAILED("Fail", "Unable to add appointment."),
  UPDATE_APPOINTMENT("Success", "Appointment updated successfully!!"),
  UPDATE_APPOINTMENT_FAILED("Fail", "Unable to update appointment."),
  SEARCH_APPOINTMENT("Success", "Appointment are found!!"),
  SEARCH_APPOINTMENT_FAILED("Fail", "No appointments found."),
  DELETE_APPOINTMENT("Success", "Appointment deleted successfully!!"),
  DELETE_APPOINTMENT_FAILED("Fail", "Failed to delete the appointment.");

  private final String status;
  private final String message;

  ResponseCode(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
