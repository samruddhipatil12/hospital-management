package cases_management;

import add_case.AddCaseScreen;
import appointment_management.AppointmentManagement;
import dashboard.DashboardScreen;
import edit_case.EditCaseScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class CaseManagementController {

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
    AddCaseScreen.showAddCaseScreen();
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
    EditCaseScreen.showEditCaseScreen();
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
