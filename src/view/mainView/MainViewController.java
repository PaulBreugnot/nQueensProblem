package view.mainView;

import javafx.fxml.FXML;

public class MainViewController {
	
	private SolverTabController solverTabController;
	
	@FXML
    public void initialize() {
		solverTabController = new SolverTabController();
    }

}
