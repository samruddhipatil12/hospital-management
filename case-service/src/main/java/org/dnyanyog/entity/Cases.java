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
@Table(name = "Cases")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cases {

  @Column(name = "patient_name_english", nullable = false, length = 50)
  private String patientNameEnglish;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "patient_id", nullable = false, unique = true)
  private Long patientId;

  @Column(name = "case_number", nullable = false, length = 50)
  private String caseNumber;

  @Column(name = "examination_date", nullable = false, length = 50)
  private String examinationDate;

  @Column(name = "symptoms", nullable = false)
  private String symptoms;

  @Column(name = "prescription")
  private String prescription;

  // Getters and Setters
  public String getPatientNameEnglish() {
    return patientNameEnglish;
  }

  public void setPatientNameEnglish(String patientNameEnglish) {
    this.patientNameEnglish = patientNameEnglish;
  }

  public Long getPatientId() {
    return patientId;
  }

  public void setPatientId(Long patientId) {
    this.patientId = patientId;
  }

  public String getCaseNumber() {
    return caseNumber;
  }

  public void setCaseNumber(String caseNumber) {
    this.caseNumber = caseNumber;
  }

  public String getExaminationDate() {
    return examinationDate;
  }

  public void setExaminationDate(String examinationDate) {
    this.examinationDate = examinationDate;
  }

  public String getSymptoms() {
    return symptoms;
  }

  public void setSymptoms(String symptoms) {
    this.symptoms = symptoms;
  }

  public String getPrescription() {
    return prescription;
  }

  public void setPrescription(String prescription) {
    this.prescription = prescription;
  }
}
