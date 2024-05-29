package org.dnyanyog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "appointments")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Appointments {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "appointment_id", nullable = false, updatable = false)
  private long appointmentId;

  @Column(name = "patient_name_english", nullable = false, length = 50)
  private String patientNameEnglish;

  @Column(name = "patient_id", nullable = false, length = 50)
  private String patientId;

  @Column(name = "appointment_date", nullable = false, length = 50)
  private String appointmentDate;

  @Column(name = "examination_date", nullable = false, length = 50)
  private String examinationDate;

  public long getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(long appointmentId) {
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

  public String getAppointmentDate() {
    return appointmentDate;
  }

  public void setAppointmentDate(String appointmentDate) {
    this.appointmentDate = appointmentDate;
  }

  public String getExaminationDate() {
    return examinationDate;
  }

  public void setExaminationDate(String examinationDate) {
    this.examinationDate = examinationDate;
  }
}
