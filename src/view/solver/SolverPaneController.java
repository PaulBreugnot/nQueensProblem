package view.solver;

import java.util.HashSet;

import core.problem.State;
import core.solver.GeneticAlgorithm;
import core.solver.HillClimbing;
import core.solver.SimulatedAnnealing;
import core.solver.Solver;
import core.solver.TabooHillClimbing;
import core.solver.TabooSimulatedAnnealing;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.MainApp;

public class SolverPaneController {

	@FXML
	private AnchorPane ChessboardPane;
	@FXML
	private Spinner<Integer> sizeSpinner;
	@FXML
	private ComboBox<Solver> solverComboBox;
	@FXML
	private Label costLabel;

	private double tileSize;
	private double xOffset;
	private double yOffset;

	private HashSet<ImageView> queens = new HashSet<>();
	private Image queenImg = new Image("file:ressources/queen.png");

	@FXML
	private void initialize() {
		setSpinner();
		setChessboardListeners();
		setSolverComboBox();
		costLabel.setText(" ");
	}

	private void setSpinner() {
		sizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100, 10));
		sizeSpinner.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> v, Number oldVal, Number newVal) {
				setSolverComboBox();
				displayChessBoard((int) newVal, ChessboardPane.getWidth(), ChessboardPane.getHeight());
			}
		});
	}

	private void setChessboardListeners() {
		ChessboardPane.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> v, Number oldVal, Number newVal) {
				displayChessBoard(sizeSpinner.getValue(), ChessboardPane.getWidth(), (double) newVal);
			}
		});
		ChessboardPane.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> v, Number oldVal, Number newVal) {
				displayChessBoard(sizeSpinner.getValue(), (double) newVal, ChessboardPane.getHeight());
			}
		});

	}

	public void displayChessBoard(int n, double chessboardWidth, double chessboardHeight) {
		ChessboardPane.getChildren().clear();
		if (chessboardHeight <= chessboardWidth) {
			tileSize = chessboardHeight / n;
			xOffset = chessboardWidth / 2 - tileSize * n / 2;
			yOffset = 0;
		} else {
			tileSize = chessboardWidth / n;
			xOffset = 0;
			yOffset = chessboardHeight / 2 - tileSize * n / 2;
		}

		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				Rectangle tile = new Rectangle(tileSize, tileSize);
				if ((x + y) % 2 == 0) {
					tile.setFill(Color.BISQUE);
				} else {
					tile.setFill(Color.BROWN);
				}
				AnchorPane.setTopAnchor(tile, yOffset + y * tileSize);
				AnchorPane.setLeftAnchor(tile, xOffset + x * tileSize);
				ChessboardPane.getChildren().add(tile);
			}
		}
		if (MainApp.getState() != null) {
			render(MainApp.getState());
		}
	}

	private void setSolverComboBox() {
		solverComboBox.getItems().clear();
		solverComboBox.getItems().addAll(new HillClimbing(), new TabooHillClimbing(), new SimulatedAnnealing(),
				new TabooSimulatedAnnealing(), new GeneticAlgorithm(sizeSpinner.getValue()));

		solverComboBox.valueProperty().addListener(new ChangeListener<Solver>() {
			@Override
			public void changed(ObservableValue<? extends Solver> ov, Solver oldVal, Solver newVal) {
				MainApp.setSolver(newVal);
			}
		});
	}

	public void render(State state) {
		ChessboardPane.getChildren().removeAll(queens);
		for (int queen = 0; queen < state.getQueensNumber(); queen++) {
			ImageView img = new ImageView(queenImg);
			img.setFitWidth(tileSize);
			img.setFitHeight(tileSize);
			queens.add(img);
			AnchorPane.setTopAnchor(img, yOffset + state.getQueenPosition(queen) * tileSize);
			AnchorPane.setLeftAnchor(img, xOffset + queen * tileSize);
			ChessboardPane.getChildren().add(img);
		}
		costLabel.setText(Integer.toString(state.cost()));
	}

	@FXML
	public void handleSolve() {
		if (MainApp.getSolver() != null && MainApp.getState() != null) {
			MainApp.solve();
			render(MainApp.getState());
		}
	}

	@FXML
	public void handleRandomInit() {
		State state = new State(sizeSpinner.getValue());
		state.randomInit();
		MainApp.setState(state);
		render(state);
	}
}
