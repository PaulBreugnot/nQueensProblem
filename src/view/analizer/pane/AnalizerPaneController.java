package view.analizer.pane;

import java.io.IOException;
import java.util.TreeMap;

import core.analizer.HistoCostAnalizer;
import core.solver.GeneticAlgorithm;
import core.solver.HillClimbing;
import core.solver.SimulatedAnnealing;
import core.solver.TabooHillClimbing;
import core.solver.TabooSimulatedAnnealing;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.analizer.settings.AnalizerSettingsController;

public class AnalizerPaneController {

	@FXML
	private BarChart<String, Number> HillClimbingChart;
	@FXML
	private BarChart<String, Number> TabooHillClimbingChart;
	@FXML
	private BarChart<String, Number> SimulatedAnnealingChart;
	@FXML
	private BarChart<String, Number> TabooSimulatedAnnealingChart;
	@FXML
	private BarChart<String, Number> GeneticAlgorithmChart;

	private int sampleSize = 100;
	private int queenNumber = 10;

	private HillClimbing hillClimbing = new HillClimbing();
	private TabooHillClimbing tabooHillClimbing = new TabooHillClimbing();
	private SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing();
	private TabooSimulatedAnnealing tabooSimulatedAnnealing = new TabooSimulatedAnnealing();
	private GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

	public void setSampleSize(int sampleSize) {
		this.sampleSize = sampleSize;
	}
	
	public int getSampleSize() {
		return sampleSize;
	}

	public void setQueenNumber(int queenNumber) {
		this.queenNumber = queenNumber;
	}
	
	public int getQueenNumber() {
		return queenNumber;
	}

	public HillClimbing getHillClimbing() {
		return hillClimbing;
	}

	public TabooHillClimbing getTabooHillClimbing() {
		return tabooHillClimbing;
	}

	public SimulatedAnnealing getSimulatedAnnealing() {
		return simulatedAnnealing;
	}

	public TabooSimulatedAnnealing getTabooSimulatedAnnealing() {
		return tabooSimulatedAnnealing;
	}

	public GeneticAlgorithm getGeneticAlgorithm() {
		return geneticAlgorithm;
	}

	@FXML
	private void handleRun() {
		computeHillClimbingChart();
		computeTabooHillClimbingChart();
		computeSimulatedAnnealingChart();
		computeTabooSimulatedAnnealingChart();
		computeGeneticAlgorithmChart();

	}
	
	@FXML
	private void handleClearCharts() {
		HillClimbingChart.getData().clear();
		TabooHillClimbingChart.getData().clear();
		SimulatedAnnealingChart.getData().clear();
		TabooSimulatedAnnealingChart.getData().clear();
		GeneticAlgorithmChart.getData().clear();
	}

	@FXML
	private void handleSettings() {
		Stage settingsStage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/analizer/settings/AnalizerSettings.fxml"));
		try {
			VBox root = (VBox) fxmlLoader.load();
			settingsStage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
		AnalizerSettingsController controller = (AnalizerSettingsController) fxmlLoader.getController();
		controller.setAnalizerPaneController(this);
		controller.setStage(settingsStage);
		settingsStage.show();
	}

	private void computeHillClimbingChart() {
		HistoCostAnalizer analizer = new HistoCostAnalizer(hillClimbing, queenNumber, sampleSize);

		TreeMap<Integer, Integer> results = analizer.analize();

		XYChart.Series<String, Number> series = new XYChart.Series<>();
		for (Integer value : results.keySet()) {
			series.getData().add(new XYChart.Data<String, Number>(value.toString(), results.get(value)));
		}

		HillClimbingChart.getData().add(series);
	}

	private void computeTabooHillClimbingChart() {
		HistoCostAnalizer analizer = new HistoCostAnalizer(tabooHillClimbing, queenNumber, sampleSize);

		TreeMap<Integer, Integer> results = analizer.analize();

		XYChart.Series<String, Number> series = new XYChart.Series<>();
		for (Integer value : results.keySet()) {
			series.getData().add(new XYChart.Data<String, Number>(value.toString(), results.get(value)));
		}

		TabooHillClimbingChart.getData().add(series);
	}

	private void computeSimulatedAnnealingChart() {
		HistoCostAnalizer analizer = new HistoCostAnalizer(simulatedAnnealing, queenNumber, sampleSize);

		TreeMap<Integer, Integer> results = analizer.analize();

		XYChart.Series<String, Number> series = new XYChart.Series<>();
		for (Integer value : results.keySet()) {
			series.getData().add(new XYChart.Data<String, Number>(value.toString(), results.get(value)));
		}

		SimulatedAnnealingChart.getData().add(series);
	}

	private void computeTabooSimulatedAnnealingChart() {
		HistoCostAnalizer analizer = new HistoCostAnalizer(tabooSimulatedAnnealing, queenNumber, sampleSize);

		TreeMap<Integer, Integer> results = analizer.analize();

		XYChart.Series<String, Number> series = new XYChart.Series<>();
		for (Integer value : results.keySet()) {
			series.getData().add(new XYChart.Data<String, Number>(value.toString(), results.get(value)));
		}

		TabooSimulatedAnnealingChart.getData().add(series);
	}

	private void computeGeneticAlgorithmChart() {
		HistoCostAnalizer analizer = new HistoCostAnalizer(geneticAlgorithm, queenNumber, sampleSize);

		TreeMap<Integer, Integer> results = analizer.analize();

		XYChart.Series<String, Number> series = new XYChart.Series<>();
		for (Integer value : results.keySet()) {
			series.getData().add(new XYChart.Data<String, Number>(value.toString(), results.get(value)));
		}

		GeneticAlgorithmChart.getData().add(series);
	}

}
