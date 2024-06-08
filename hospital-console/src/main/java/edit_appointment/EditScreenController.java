package edit_appointment;

import appointment_management.AppointmentManagement;
import cases_management.CaseManagement;
import common.RestUtil;
import dashboard.DashboardScreen;
import dto.AppointmentRequest;
import dto.AppointmentResponse;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class EditScreenController {

  @FXML private TextField appointmentTime;

  @FXML private TextField examinationDate;

  @FXML private TextField AppointmentId;

  @FXML private TextField patientNameMarathi;

  @FXML private TextField patientNameEnglish;

  @FXML private TextField AppointmentId_search;

  @FXML private TextField PatientId_search;

  @FXML private Button Search;

  @FXML private Button Save;

  @FXML private Button Cancle;

  @FXML private Button DashBoard;

  @FXML private Button Cases;

  @FXML private Button Appointments;

  @FXML private Button Users;

  @FXML private Button Patients;

  @FXML private Button Logout;

  @FXML
  void CancleButton(ActionEvent event) {
    AppointmentManagement.showAppointmentManagementScreen();
  }

  @FXML
  void DashBoardButton(ActionEvent event) {
    DashboardScreen.showDashboardScreen();
  }

  @FXML
  void PatientsButton(ActionEvent event) {
    PatientManagement.showPatientScreen();
  }

  @FXML
  void CasesButton(ActionEvent event) {
    CaseManagement.showCaseScreen();
  }

  @FXML
  void AppointmentsButton(ActionEvent event) {
    AppointmentManagement.showAppointmentManagementScreen();
  }

  @FXML
  void UsersButton(ActionEvent event) {
    UserManagement.showUserScreen();
  }

  @FXML
  void LogoutButton(ActionEvent event) {
    AppointmentManagement.showAppointmentManagementScreen();
  }

  public void SearchButton(ActionEvent event) throws IOException {
    String appointmentId = AppointmentId_search.getText().trim();
    String patientId = PatientId_search.getText().trim();

    AppointmentResponse response = null;
    try {
      if (!patientId.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8084/api/v1/appointment/patient/" + patientId,
                AppointmentResponse.class);
      } else if (!appointmentId.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8084/api/v1/appointment/" + appointmentId,
                AppointmentResponse.class);
      }
      if (response != null && response.getStatus().equals("Success")) {
        patientNameEnglish.setText(response.getPatient_name_english());
        PatientId_search.setText(response.getPatientId());
        AppointmentId_search.setText(response.getAppointmentId());
        examinationDate.setText(response.getExamination_date());
        appointmentTime.setText(response.getAppointmentTime());

      } else {
        showAlert(
            "Fail", "No appointments found.", "Please enter valid patient Id or appointment Id");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert(
          "Fail",
          "Failed to fetch appointment details",
          "An error occurred while fetching the appointment details.");
    }
  }

  public void SaveButton(ActionEvent event) throws IOException {
    AppointmentRequest updateAppointment = new AppointmentRequest();
    updateAppointment.setAppointment_time(appointmentTime.getText());
    updateAppointment.setExamination_date(examinationDate.getText());
    updateAppointment.setPatientId(PatientId_search.getText());
    updateAppointment.setPatient_name_english(patientNameEnglish.getText());
    updateAppointment.setAppointmentId(AppointmentId_search.getText());

    String appointmentId = AppointmentId_search.getText();

    try {
      AppointmentResponse response =
          RestUtil.sendPostRequest(
              "http://localhost:8084/api/v1/appointment/" + appointmentId,
              AppointmentResponse.class,
              updateAppointment);

      if (response != null && response.getStatus().equals("Success")) {
        showAlert(
            "Success",
            "Appointment updated successfully!!",
            "Appointment details have been updated successfully.");
      } else {
        showAlert(
            "Fail",
            "Unable to update appointment.",
            "An error occurred while updating the Appointment details.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert(
          "Error",
          "Failed to update Appointment",
          "An error occurred while updating the Appointment details.");
    }
  }

  private void showAlert(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.show();
  }
}
