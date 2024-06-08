package edit_case;

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
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class EditScreenController {

  @FXML private Button Appointment;

  @FXML private Button Cancle;

  @FXML private Rectangle Case_Id_search;

  @FXML private TextField Case_id_search;

  @FXML private Button Cases;

  @FXML private Button DashBoard;

  @FXML private Button Logout;

  @FXML private TextField Patient_id_search;

  @FXML private Button Patients;

  @FXML private Button Save;

  @FXML private Button Search;

  @FXML private Button Users;

  @FXML private TextField caseNumber;

  @FXML private TextField examinationDate;

  @FXML private TextField patientId;

  @FXML private TextField patientName;

  @FXML private TextField prescription;

  @FXML private TextField symptoms;

  @FXML
  void AppointmentButton(ActionEvent event) {
    AppointmentManagement.showAppointmentManagementScreen();
  }

  @FXML
  void CancleButton(ActionEvent event) {
    CaseManagement.showCaseScreen();
  }

  @FXML
  void CasesButton(ActionEvent event) {
    CaseManagement.showCaseScreen();
  }

  @FXML
  void DashBoardButton(ActionEvent event) {
    DashboardScreen.showDashboardScreen();
  }

  @FXML
  void LogoutButton(ActionEvent event) {
    CaseManagement.showCaseScreen();
  }

  @FXML
  void PatientsButton(ActionEvent event) {
    PatientManagement.showPatientScreen();
  }

  public void SaveButton(ActionEvent event) throws IOException {
    CaseRequest updateCase = new CaseRequest();
    updateCase.setCaseNumber(caseNumber.getText());
    updateCase.setExaminationDate(examinationDate.getText());
    updateCase.setPatientId(patientId.getText());
    updateCase.setPatientNameEnglish(patientName.getText());
    updateCase.setPrescription(prescription.getText());
    updateCase.setSymptoms(symptoms.getText());

    String caseId = Case_id_search.getText();

    try {
      CaseResponse response =
          RestUtil.sendPostRequest(
              "http://localhost:8083/api/v1/case/" + caseId, CaseResponse.class, updateCase);

      if (response != null && response.getStatus().equals("Success")) {
        showAlert(
            "Success",
            "Case updated successfully!!",
            "Case details have been updated successfully.");
      } else {
        showAlert(
            "Fail", "Unable to update case.", "An error occurred while updating the case details.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert(
          "Fail", "Unable to update case.", "An error occurred while updating the case details.");
    }
  }

  public void SearchButton(ActionEvent event) throws IOException {
    String patientId = Patient_id_search.getText().trim();
    String caseId = Case_id_search.getText().trim();

    CaseResponse response = null;
    try {
      if (!patientId.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8083/api/v1/case/patient/" + patientId, CaseResponse.class);
      } else if (!caseId.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8083/api/v1/case/" + caseId, CaseResponse.class);
      }
      if (response != null && response.getStatus().equals("Success")) {
        patientName.setText(response.getPatientNameEnglish());
        Patient_id_search.setText(response.getPatientId());
        caseNumber.setText(response.getCaseNumber());
        examinationDate.setText(response.getExaminationDate());
        symptoms.setText(response.getSymptoms());
        prescription.setText(response.getPrescription());
      } else {
        showAlert("Fail", "No case found.", "Please enter a valid patient Id or case Id");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert("Fail", "No case found.", "An error occurred while fetching the case details.");
    }
  }

  @FXML
  void UsersButton(ActionEvent event) {
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
