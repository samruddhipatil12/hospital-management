package edit_patient;

import appointment_management.AppointmentManagement;
import cases_management.CaseManagement;
import common.RestUtil;
import dashboard.DashboardScreen;
import dto.PatientRequest;
import dto.PatientResponse;
import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class EditScreenController {

  @FXML private Button Appointments;

  @FXML private Button Cancle;

  @FXML private Button Cases;

  @FXML private Button DashBoard;

  @FXML private Button Logout;

  @FXML private TextField PatientId_search;

  @FXML private Button Patients;

  @FXML private Button Save;

  @FXML private Button Search;

  @FXML private Button User;

  @FXML private TextField address;

  @FXML private DatePicker birth_date;

  @FXML private DatePicker first_examination_date;

  @FXML private TextField gender;

  @FXML private TextField mobile_number;

  @FXML private TextField patient_name_english;

  @FXML private TextField patient_name_english_search;

  @FXML private TextField patient_name_marathi;

  @FXML
  void AppointmentsButton(ActionEvent event) {
    AppointmentManagement.showAppointmentManagementScreen();
  }

  @FXML
  void CancleButton(ActionEvent event) {
    PatientManagement.showPatientScreen();
  }

  @FXML
  void CaseButton(ActionEvent event) {
    CaseManagement.showCaseScreen();
  }

  @FXML
  void DashBoardButton(ActionEvent event) {
    DashboardScreen.showDashboardScreen();
  }

  @FXML
  void LogoutButton(ActionEvent event) {
    PatientManagement.showPatientScreen();
  }

  @FXML
  void PatientButton(ActionEvent event) {
    PatientManagement.showPatientScreen();
  }

  public void SaveButton(ActionEvent event) throws IOException {
    PatientRequest updatePatient = new PatientRequest();
    updatePatient.setAddress(address.getText());
    updatePatient.setMobile_number(mobile_number.getText());
    updatePatient.setPatient_name_marathi(patient_name_marathi.getText());
    updatePatient.setPatient_name_english(patient_name_english.getText());
    updatePatient.setBirth_date(birth_date.getValue().toString());
    updatePatient.setFirst_examination_date(first_examination_date.getValue().toString());
    updatePatient.setGender(gender.getText());

    String patientId = PatientId_search.getText();

    try {
      PatientResponse response =
          RestUtil.sendPostRequest(
              "http://localhost:8082/api/v1/patient/" + patientId,
              PatientResponse.class,
              updatePatient);

      if (response != null && response.getStatus().equals("Success")) {
        showAlert(
            "Success",
            "Patient updated successfully!!",
            "Patient details have been updated successfully.");
      } else {
        showAlert(
            "Error",
            "Failed to update patient",
            "An error occurred while updating the patient details.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert(
          "Error",
          "Failed to update patient",
          "An error occurred while updating the patient details.");
    }
  }

  public void SearchButton(ActionEvent event) throws IOException {
    String patientId = PatientId_search.getText().trim();
    String patientNameEnglish = patient_name_english.getText().trim();

    PatientResponse response = null;

    try {
      if (!patientId.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8082/api/v1/patient/" + patientId, PatientResponse.class);
      } else if (!patientNameEnglish.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8082/api/v1/patient/name/" + patientNameEnglish,
                PatientResponse.class);
      }

      if (response != null && response.getStatus().equals("Success")) {
        patient_name_english.setText(response.getPatient_name_english());
        patient_name_marathi.setText(response.getPatient_name_marathi());
        mobile_number.setText(response.getMobile_number());
        gender.setText(response.getGender());
        birth_date.setValue(LocalDate.parse(response.getBirth_date()));
        first_examination_date.setValue(LocalDate.parse(response.getFirst_examination_date()));
        address.setText(response.getAddress());
      } else {
        showAlert("Error", "Patient not found", "Please enter a valid patient ID or Name.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert(
          "Error",
          "Failed to fetch patient details",
          "An error occurred while fetching the patient details.");
    }
  }

  @FXML
  void UserButton(ActionEvent event) {
    UserManagement.showUserScreen();
  }

  private void showAlert(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.show();
  }
}
