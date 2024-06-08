package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class CaseData {

  @NotNull(message = "Patient name is mandatory")
  @NotBlank(message = "Patient name should not be blank")
  @Size(max = 50, message = "Patient name length should be at most 50 characters")
  private String patientNameEnglish;

  @NotNull(message = "Case number is mandatory")
  @NotBlank(message = "Case number should not be blank")
  private String caseNumber;

  @NotNull(message = "Examination date is mandatory")
  @PastOrPresent(message = "Examination date cannot be in the future")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private String examinationDate;

  @NotBlank(message = "Symptoms should not be blank")
  private String symptoms;

  private String prescription;

  private String PatientId;

  public String getPatientNameEnglish() {
    return patientNameEnglish;
  }

  public void setPatientNameEnglish(String patientNameEnglish) {
    this.patientNameEnglish = patientNameEnglish;
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

  public String getPatientId() {
    return PatientId;
  }

  public void setPatientId(String patientId) {
    PatientId = patientId;
  }
}
