package view.analizer.settings;

import core.solver.GeneticAlgorithm;
import core.solver.Solver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import view.analizer.pane.AnalizerPaneController;

public class AnalizerSettingsController {
	
	@FXML
	private Spinner<Integer> queenNumberSpinner;
	@FXML
	private Spinner<Integer> sampleSizeSpinner;
	@FXML
	private Spinner<Integer> maxIterationSpinner;
	@FXML
	private Spinner<Integer> historicSizeSpinner;
	@FXML
	private Spinner<Integer> populationSizeSpinner;
	@FXML
	private Spinner<Double> muSpinner;
	
	private AnalizerPaneController analizerPaneController;
	private Stage stage;
	
	@FXML
	private void initialize() {
		setQueenNumberSpinner();
		setSampleSizeSpinner();
		setMaxIterationSpinner();
		setHistoricSizeSpinner();
		setPopulationSizeSpinner();
		setMuSpinner();
	}
	
	@FXML
	private void handleOk() {
		stage.close();
	}
	
	public void setAnalizerPaneController(AnalizerPaneController analizerPaneController) {
		this.analizerPaneController = analizerPaneController;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	private void setQueenNumberSpinner() {
		queenNumberSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100, 10));
		queenNumberSpinner.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> v, Number oldVal, Number newVal) {
				analizerPaneController.setQueenNumber((int) newVal);
			}
		});
	}
	
	private void setSampleSizeSpinner() {
		sampleSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(100, 10000, 100));
		sampleSizeSpinner.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> v, Number oldVal, Number newVal) {
				analizerPaneController.setSampleSize((int) newVal);
			}
		});
	}
	
	private void setMaxIterationSpinner() {
		maxIterationSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(100, 10000, Solver.defaultMaxIteration));
		maxIterationSpinner.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> v, Number oldVal, Number newVal) {
				analizerPaneController.getHillClimbing().setMaxIteration((int) newVal);
				analizerPaneController.getTabooHillClimbing().setMaxIteration((int) newVal);
				analizerPaneController.getSimulatedAnnealing().setMaxIteration((int) newVal);
				analizerPaneController.getTabooSimulatedAnnealing().setMaxIteration((int) newVal);
				analizerPaneController.getGeneticAlgorithm().setMaxIteration((int) newVal);
			}
		});
	}
	
	private void setHistoricSizeSpinner() {
		historicSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 10000, Solver.defaultHistoricSize));
		historicSizeSpinner.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> v, Number oldVal, Number newVal) {
				analizerPaneController.getTabooHillClimbing().setHistoricSize((int) newVal);
				analizerPaneController.getTabooSimulatedAnnealing().setHistoricSize((int) newVal);
			}
		});
	}
	
	private void setPopulationSizeSpinner() {
		populationSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 1000, GeneticAlgorithm.defaultPopulationSize));
		populationSizeSpinner.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> v, Number oldVal, Number newVal) {
				analizerPaneController.getGeneticAlgorithm().setPopulationSize((int) newVal);
			}
		});
	}
	
	private void setMuSpinner() {
		muSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1, GeneticAlgorithm.defaultMu, 0.01));
		muSpinner.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> v, Number oldVal, Number newVal) {
				analizerPaneController.getGeneticAlgorithm().setMu((double) newVal);
			}
		});
	}
}
