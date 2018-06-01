package delin.frame.javafx.manager;

import java.io.IOException;
import javafx.stage.Stage;

public interface AppManager {
	public Object loadFXML(String fxml)throws IOException;
	public void addStage(String id,Stage stage);
	public Stage getStage(String id);
}
