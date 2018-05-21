package view.analizer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class AnalizerPane extends VBox {
	
	public AnalizerPane() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/analizer/AnalizerPane.fxml"));
		fxmlLoader.setRoot(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
