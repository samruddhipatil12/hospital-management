package add_appointment;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class AddScreenController {

  @FXML private Button appointment_button;

  @FXML private Button case_button;

  @FXML private Button close_button;

  @FXML private Button dashboard_button;

  @FXML private Button logout_button;

  @FXML private Button patient_button;

  @FXML private Button save_button;

  @FXML private Button user_button;

  @FXML private TextField patient_Id;

  @FXML private TextField patient_Name_English;

  @FXML private TextField appointment_Id;

  @FXML private DatePicker examination_date;

  @FXML private TextField appointment_Time;

  @FXML
  void CloseButton(ActionEvent event) {
    AppointmentManagement.showAppointmentManagementScreen();
  }

  @FXML
  void SaveButton(ActionEvent event) {
    AppointmentRequest addAppointment = new AppointmentRequest();

    addAppointment.setPatient_name_english(patient_Name_English.getText());
    addAppointment.setExamination_date(examination_date.getValue().toString());
    addAppointment.setAppointment_time(appointment_Time.getText());
    addAppointment.setPatientId(patient_Id.getText());
    addAppointment.setAppointmentId(appointment_Id.getText());
    try {
      AppointmentResponse response =
          RestUtil.sendPostRequest(
              "http://localhost:8084/api/v1/appointment/add",
              AppointmentResponse.class,
              addAppointment);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Appointment Added ");
    alert.setContentText("Appointment added successfully!");
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
    AppointmentManagement.showAppointmentManagementScreen();
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
