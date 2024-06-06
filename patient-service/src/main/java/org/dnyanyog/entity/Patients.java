package org.dnyanyog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "patients") // Make sure this matches your actual table name
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patients {

  @GeneratedValue
  @Id
  @Column(nullable = false, insertable = true, updatable = false)
  private long patient_code;

  @Column(name = "patient_id", nullable = false, updatable = false)
  private String patientId;

  @Column(name = "patient_name_english", nullable = false, length = 50)
  private String patient_name_english;

  @Column(name = "patient_name_marathi", nullable = false, length = 50)
  private String patient_name_marathi;

  @Column(name = "mobile_number")
  private String mobile_number;

  @Column(name = "gender")
  private String gender;

  @Column(name = "birth_date")
  private String birth_date;

  @Column(name = "first_examination_date")
  private String first_examination_date;

  @Column(name = "address")
  private String address;

  public long getPatient_code() {
    return patient_code;
  }

  public void setPatient_code(long patient_code) {
    this.patient_code = patient_code;
  }

  public String getpatientId() {
    return patientId;
  }

  public void setpatientId(String patientId) {
    this.patientId = patientId;
  }

  public String getPatient_name_english() {
    return patient_name_english;
  }

  public void setPatient_name_english(String patient_name_english) {
    this.patient_name_english = patient_name_english;
  }

  public String getPatient_name_marathi() {
    return patient_name_marathi;
  }

  public void setPatient_name_marathi(String patient_name_marathi) {
    this.patient_name_marathi = patient_name_marathi;
  }

  public String getMobile_number() {
    return mobile_number;
  }

  public void setMobile_number(String mobile_number) {
    this.mobile_number = mobile_number;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getBirth_date() {
    return birth_date;
  }

  public void setBirth_date(String birth_date) {
    this.birth_date = birth_date;
  }

  public String getFirst_examination_date() {
    return first_examination_date;
  }

  public void setFirst_examination_date(String first_examination_date) {
    this.first_examination_date = first_examination_date;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
