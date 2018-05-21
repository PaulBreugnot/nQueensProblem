package view.solver;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class SolverPane extends BorderPane {

	public SolverPane() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/solver/SolverPane.fxml"));
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
