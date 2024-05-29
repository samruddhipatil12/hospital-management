package org.dnyanyog.dto;

public class AppointmentResponse {

  private String status;
  private String message;
  private String patient_name_english;
  private String patient_id;
  private String appointment_date;
  private String examination_date;
  private String appointment_id;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getPatient_name_english() {
    return patient_name_english;
  }

  public void setPatient_name_english(String patient_name_english) {
    this.patient_name_english = patient_name_english;
  }

  public String getPatient_id() {
    return patient_id;
  }

  public void setPatient_id(String patient_id) {
    this.patient_id = patient_id;
  }

  public String getAppointment_date() {
    return appointment_date;
  }

  public void setAppointment_date(String appointment_date) {
    this.appointment_date = appointment_date;
  }

  public String getExamination_date() {
    return examination_date;
  }

  public void setExamination_date(String examination_date) {
    this.examination_date = examination_date;
  }

  public String getAppointment_id() {
    return appointment_id;
  }

  public void setAppointment_id(String appointment_id) {
    this.appointment_id = appointment_id;
  }

  public static AppointmentResponse getInstance() { // TODO Auto-generated method stub
    return new AppointmentResponse();
  }
}
