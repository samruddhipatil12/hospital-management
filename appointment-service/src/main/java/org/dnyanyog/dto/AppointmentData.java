package org.dnyanyog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class AppointmentData {

  @NotNull(message = "Username is mandatory")
  @NotBlank(message = "Username should not be blank")
  @Size(max = 50, message = "Username length should be at most 50 characters")
  private String patient_name_english;

  @NotNull(message = "Appointment_date is mandatory")
  @PastOrPresent(message = "Appointment_date cannot be in the future")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private String appointment_date;

  @NotNull(message = "Examination_date is mandatory")
  @PastOrPresent(message = "Examination_date cannot be in the future")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private String examination_date;

  public String getPatient_name_english() {
    return patient_name_english;
  }

  public void setPatient_name_english(String patient_name_english) {
    this.patient_name_english = patient_name_english;
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
}
