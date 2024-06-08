package add_patient;

import common.StageFactory;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AddPatientScreen {

  public static void showAddPatientScreen() {

    try {
      Parent actorGroup =
          FXMLLoader.load(new URL("file:///C|//hospital-console-screen//add_patient.fxml"));
      StageFactory.stage.setTitle(" Add Patient Screen ");
      Scene scene = new Scene(actorGroup, 600, 400);
      StageFactory.stage.setScene(scene);
      StageFactory.stage.setFullScreen(true);
      StageFactory.stage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
