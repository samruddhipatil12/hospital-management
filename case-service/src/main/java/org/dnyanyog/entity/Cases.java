package org.dnyanyog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
  @Column(name = "patient_id", nullable = false, unique = true)
  private String patientId;

  @Column(name = "case_id", nullable = false, unique = true)
  private String caseId;

  @Column(name = "case_number", nullable = false, length = 50)
  private String caseNumber;

  @Column(name = "examination_date", nullable = false, length = 50)
  private String examinationDate;

  @Column(name = "symptoms", nullable = false)
  private String symptoms;

  @Column(name = "prescription")
  private String prescription;

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

  public String getCaseId() {
    return caseId;
  }

  public void setCaseId(String caseId) {
    this.caseId = caseId;
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
