package dashboard;

import common.StageFactory;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class DashboardScreen {

  public static void showDashboardScreen() {

    try {
      Parent actorGroup =
          FXMLLoader.load(new URL("file:///C|//hospital-console-screen//dashboard.fxml"));
      StageFactory.stage.setTitle(" Dashboard Screen ");
      Scene scene = new Scene(actorGroup, 600, 400);
      StageFactory.stage.setScene(scene);
      StageFactory.stage.setFullScreen(true);
      StageFactory.stage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
