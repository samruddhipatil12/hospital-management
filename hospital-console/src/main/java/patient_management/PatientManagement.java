package patient_management;

import common.StageFactory;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class PatientManagement {

  public static void showPatientScreen() {
    try {
      Parent actorGroup =
          FXMLLoader.load(new URL("file:///C|//hospital-console-screen//patient_management.fxml"));
      StageFactory.stage.setTitle("Patient Management Screen");
      Scene scene = new Scene(actorGroup, 600, 400);
      StageFactory.stage.setScene(scene);
      StageFactory.stage.setFullScreen(true);
      StageFactory.stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
