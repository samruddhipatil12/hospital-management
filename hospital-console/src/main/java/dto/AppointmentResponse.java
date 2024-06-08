package dto;

public class AppointmentResponse {

  private String status;
  private String message;
  private String patient_name_english;
  private String patientId;
  private String examination_date;
  private String appointmentId;
  private String appointmentTime;

  public String getAppointmentTime() {
    return appointmentTime;
  }

  public void setAppointmentTime(String appointmentTime) {
    this.appointmentTime = appointmentTime;
  }

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

  public String getPatientId() {
    return patientId;
  }

  public void setPatient_id(String patientId) {
    this.patientId = patientId;
  }

  public String getExamination_date() {
    return examination_date;
  }

  public void setExamination_date(String examination_date) {
    this.examination_date = examination_date;
  }

  public String getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(String appointmentId) {
    this.appointmentId = appointmentId;
  }

  public static AppointmentResponse getInstance() { // TODO Auto-generated method stub
    return new AppointmentResponse();
  }
}
