package add_patient;

import appointment_management.AppointmentManagement;
import cases_management.CaseManagement;
import common.RestUtil;
import dashboard.DashboardScreen;
import dto.PatientData;
import dto.PatientResponse;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class AddScreenController {

  @FXML private Button appointment_button;
  @FXML private Button cancel_button;
  @FXML private Button case_button;
  @FXML private Button dashboard_button;
  @FXML private Button logout_button;
  @FXML private Button patient_button;
  @FXML private Button save_button;
  @FXML private Button user_button;
  @FXML private TextField address;
  @FXML private DatePicker birth_date;
  @FXML private DatePicker first_examination_date;
  @FXML private TextField gender;
  @FXML private TextField mobile_number;
  @FXML private TextField patient_name_english;
  @FXML private TextField patient_name_marathi;

  @FXML
  void CancelButton(ActionEvent event) {
    PatientManagement.showPatientScreen();
  }

  @FXML
  void SaveButton(ActionEvent event) {
    PatientData newPatient = new PatientData();

    newPatient.setAddress(address.getText());
    newPatient.setBirth_date(birth_date.getValue().toString());
    newPatient.setFirst_examination_date(first_examination_date.getValue().toString());
    newPatient.setGender(gender.getText());
    newPatient.setMobile_number(mobile_number.getText());
    newPatient.setPatient_name_english(patient_name_english.getText());
    newPatient.setPatient_name_marathi(patient_name_marathi.getText());

    try {
      PatientResponse response =
          RestUtil.sendPostRequest(
              "http://localhost:8082/api/v1/patient/add", PatientResponse.class, newPatient);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Patient Added ");
    alert.setContentText("Patient added successfully!");
    alert.setHeaderText("Success!!");
    alert.show();
  }

  @FXML
  void showAppointmentScreen(ActionEvent event) {
    AppointmentManagement.showAppointmentManagementScreen();
  }

  @FXML
  void showCaseScreen(ActionEvent event) {
    CaseManagement.showCaseScreen();
  }

  @FXML
  void showDashboardScreen(ActionEvent event) {
    DashboardScreen.showDashboardScreen();
  }

  @FXML
  void showLogoutScreen(ActionEvent event) {
    PatientManagement.showPatientScreen();
  }

  @FXML
  void showPatientScreen(ActionEvent event) {
    PatientManagement.showPatientScreen();
  }

  @FXML
  void showUserScreen(ActionEvent event) {
    UserManagement.showUserScreen();
  }
}
