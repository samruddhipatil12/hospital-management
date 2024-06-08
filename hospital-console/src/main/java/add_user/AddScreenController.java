package add_user;

import appointment_management.AppointmentManagement;
import cases_management.CaseManagement;
import common.RestUtil;
import dashboard.DashboardScreen;
import dto.DirectoryRequest;
import dto.DirectoryResponse;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

  @FXML private TextField confirm;

  @FXML private TextField email;

  @FXML private TextField mobileNumber;

  @FXML private TextField password;

  @FXML private TextField role;

  @FXML private TextField userName_English;

  @FXML
  void CancelButton(ActionEvent event) {
    UserManagement.showUserScreen();
  }

  @FXML
  void SaveButton(ActionEvent event) {
    DirectoryRequest addUser = new DirectoryRequest();

    addUser.setUsername(userName_English.getText());
    addUser.setEmail(email.getText());
    addUser.setMobile_number(mobileNumber.getText());
    ;
    addUser.setRole(role.getText());
    addUser.setMobile_number(mobileNumber.getText());
    addUser.setPassword(password.getText());
    addUser.setConfirm(confirm.getText());

    try {
      DirectoryResponse response =
          RestUtil.sendPostRequest(
              "http://localhost:8081/api/v1/directory/add", DirectoryResponse.class, addUser);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("User Added ");
    alert.setContentText("User added successfully!");
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
    UserManagement.showUserScreen();
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
