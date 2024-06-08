package edit_case;

import common.StageFactory;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class EditCaseScreen {

  public static void showEditCaseScreen() {

    try {
      Parent actorGroup =
          FXMLLoader.load(new URL("file:///C|//hospital-console-screen//edit_case.fxml"));
      StageFactory.stage.setTitle(" Edit Case Screen ");
      Scene scene = new Scene(actorGroup, 600, 400);
      StageFactory.stage.setScene(scene);
      StageFactory.stage.setFullScreen(true);
      StageFactory.stage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
