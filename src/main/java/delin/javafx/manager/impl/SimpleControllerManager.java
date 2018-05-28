package delin.javafx.manager.impl;

import java.util.HashMap;

import delin.javafx.manager.ControllerManager;
import javafx.fxml.Initializable;

public class SimpleControllerManager implements ControllerManager {
	private static HashMap<String, Initializable> controllers= new HashMap<>();
	@Override
	public void add(String id, Initializable controller) {
		controllers.put(id, controller);
	}

	@Override
	public Initializable get(String id) {
		return controllers.get(id);
	}

}
