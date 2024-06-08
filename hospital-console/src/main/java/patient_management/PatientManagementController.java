package patient_management;

import add_patient.AddPatientScreen;
import appointment_management.AppointmentManagement;
import cases_management.CaseManagement;
import dashboard.DashboardScreen;
import edit_patient.EditPatientScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import user_management.UserManagement;

public class PatientManagementController {

  @FXML private Button add_button;

  @FXML private Button appointment_button;

  @FXML private Button case_button;

  @FXML private Button dashboard_button;

  @FXML private Button delete_button;

  @FXML private Button edit_button;

  @FXML private Button logout_button;

  @FXML private Button patient_button;

  @FXML private Button search_button;

  @FXML private Button user_button;

  @FXML
  void showAddScreen(ActionEvent event) {
    AddPatientScreen.showAddPatientScreen();
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
  void showDeleteScreen(ActionEvent event) {}

  @FXML
  void showEditScreen(ActionEvent event) {
    EditPatientScreen.showEditPatientScreen();
  }

  @FXML
  void showLogoutScreen(ActionEvent event) {
    DashboardScreen.showDashboardScreen();
  }

  @FXML
  void showPatientScreen(ActionEvent event) {
    PatientManagement.showPatientScreen();
  }

  @FXML
  void showSearchScreen(ActionEvent event) {}

  @FXML
  void showUserScreen(ActionEvent event) {
    UserManagement.showUserScreen();
  }
}
