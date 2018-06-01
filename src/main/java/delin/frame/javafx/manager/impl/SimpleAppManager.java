package delin.frame.javafx.manager.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import delin.frame.javafx.manager.AppManager;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Stage;

public class SimpleAppManager implements AppManager{
	private static HashMap<String,Stage> windows;
	static {
		windows=new HashMap<>();
	}
	@Override
	public Object loadFXML(String fxml) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		Object node;
		try (InputStream in = ClassLoader.getSystemResource(fxml).openStream()) {
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			loader.setLocation(ClassLoader.getSystemResource(fxml));
			node = loader.load(in);
		}
		return node;
		
	}

	@Override
	public void addStage(String id, Stage stage) {
		windows.put(id, stage);
	}

	@Override
	public Stage getStage(String id) {
		return windows.get(id);
	}

}
