package add_case;

import appointment_management.AppointmentManagement;
import cases_management.CaseManagement;
import common.RestUtil;
import dashboard.DashboardScreen;
import dto.CaseRequest;
import dto.CaseResponse;
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

  @FXML private Button save_Button;

  @FXML private Button user_Button;

  @FXML private TextField caseNumber;

  @FXML private DatePicker examinationDate;

  @FXML private TextField patientId;

  @FXML private TextField patientName;

  @FXML private TextField prescription;

  @FXML private TextField symptoms;

  @FXML
  void CancelButton(ActionEvent event) {
    CaseManagement.showCaseScreen();
  }

  @FXML
  void SaveButton(ActionEvent event) {

    CaseRequest addCase = new CaseRequest();

    addCase.setPatientNameEnglish(patientName.getText());
    addCase.setPatientId(patientId.getText());
    addCase.setCaseNumber(caseNumber.getText());
    addCase.setExaminationDate(examinationDate.getValue().toString());
    addCase.setSymptoms(symptoms.getText());
    addCase.setPrescription(prescription.getText());

    try {
      CaseResponse response =
          RestUtil.sendPostRequest(
              "http://localhost:8083/api/v1/case/add", CaseResponse.class, addCase);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Case Added ");
    alert.setContentText("Case added successfully!");
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
  void showDashBoardScreen(ActionEvent event) {
    DashboardScreen.showDashboardScreen();
  }

  @FXML
  void showLogoutScreen(ActionEvent event) {
    CaseManagement.showCaseScreen();
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
