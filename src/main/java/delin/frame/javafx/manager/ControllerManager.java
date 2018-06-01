package delin.frame.javafx.manager;

import javafx.fxml.Initializable;

public interface ControllerManager {
	public void add(String id, Initializable controller);
	public Initializable get(String id);
}
