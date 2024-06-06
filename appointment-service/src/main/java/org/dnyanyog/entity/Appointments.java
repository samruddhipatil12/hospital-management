package org.dnyanyog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "appointments")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Appointments {

  @Id
  @Column(name = "appointment_id", nullable = false, updatable = false)
  private String appointmentId;

  @Column(name = "patient_name_english", nullable = false, length = 50)
  private String patientNameEnglish;

  @Column(name = "patient_id", nullable = false, length = 50)
  private String patientId;

  @Column(name = "examination_date", nullable = false, length = 50)
  private String examinationDate;

  public String getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(String appointmentId) {
    this.appointmentId = appointmentId;
  }

  public String getPatientNameEnglish() {
    return patientNameEnglish;
  }

  public void setPatientNameEnglish(String patientNameEnglish) {
    this.patientNameEnglish = patientNameEnglish;
  }

  public String getPatientId() {
    return patientId;
  }

  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }

  public String getExaminationDate() {
    return examinationDate;
  }

  public void setExaminationDate(String examinationDate) {
    this.examinationDate = examinationDate;
  }
}
