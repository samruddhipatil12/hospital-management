package dashboard;

import appointment_management.AppointmentManagement;
import cases_management.CaseManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import login.LoginScreen;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class DashboardScreenController {

  @FXML private Button appointment_button;

  @FXML private Button case_button;

  @FXML private Button dashboard_button;

  @FXML private Button logout_button;

  @FXML private Button patient_button;

  @FXML private Button user_button;

  @FXML
  void LogoutButton(ActionEvent event) {
    LoginScreen.showLoginScreen();
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
  void showPatientScreen(ActionEvent event) {
    PatientManagement.showPatientScreen();
  }

  @FXML
  void showUserScreen(ActionEvent event) {
    UserManagement.showUserScreen();
  }
}
