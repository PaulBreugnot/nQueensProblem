package view.analizer;

import java.util.TreeMap;

import core.analizer.HistoCostAnalizer;
import core.solver.GeneticAlgorithm;
import core.solver.HillClimbing;
import core.solver.SimulatedAnnealing;
import core.solver.TabooHillClimbing;
import core.solver.TabooSimulatedAnnealing;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

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
	
	@FXML
	private void handleRun() {
		computeHillClimbingChart();
		computeTabooHillClimbingChart();
		computeSimulatedAnnealingChart();
		computeTabooSimulatedAnnealingChart();
		computeGeneticAlgorithmChart();
		
	}
	
	private int sampleSize = 100;
	private int queenNumber= 10;
	
	private void computeHillClimbingChart() {
		HistoCostAnalizer analizer = new HistoCostAnalizer(new HillClimbing(), queenNumber, sampleSize);
		
		TreeMap<Integer, Integer> results = analizer.analize();
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(Integer value : results.keySet()) {
        	series.getData().add(new XYChart.Data<String, Number>(value.toString(), results.get(value)));
        }
        
        HillClimbingChart.getData().add(series);
	}
	
	private void computeTabooHillClimbingChart() {
		HistoCostAnalizer analizer = new HistoCostAnalizer(new TabooHillClimbing(), queenNumber, sampleSize);
		
		TreeMap<Integer, Integer> results = analizer.analize();
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(Integer value : results.keySet()) {
        	series.getData().add(new XYChart.Data<String, Number>(value.toString(), results.get(value)));
        }
        
        TabooHillClimbingChart.getData().add(series);
	}
	
	private void computeSimulatedAnnealingChart() {
		HistoCostAnalizer analizer = new HistoCostAnalizer(new SimulatedAnnealing(), queenNumber, sampleSize);
		
		TreeMap<Integer, Integer> results = analizer.analize();
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(Integer value : results.keySet()) {
        	series.getData().add(new XYChart.Data<String, Number>(value.toString(), results.get(value)));
        }
        
        SimulatedAnnealingChart.getData().add(series);
	}
	
	private void computeTabooSimulatedAnnealingChart() {
		HistoCostAnalizer analizer = new HistoCostAnalizer(new TabooSimulatedAnnealing(), queenNumber, sampleSize);
		
		TreeMap<Integer, Integer> results = analizer.analize();
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(Integer value : results.keySet()) {
        	series.getData().add(new XYChart.Data<String, Number>(value.toString(), results.get(value)));
        }
        
        TabooSimulatedAnnealingChart.getData().add(series);
	}
	
	private void computeGeneticAlgorithmChart() {
		HistoCostAnalizer analizer = new HistoCostAnalizer(new GeneticAlgorithm(queenNumber), queenNumber, sampleSize);
		
		TreeMap<Integer, Integer> results = analizer.analize();
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(Integer value : results.keySet()) {
        	series.getData().add(new XYChart.Data<String, Number>(value.toString(), results.get(value)));
        }
        
        GeneticAlgorithmChart.getData().add(series);
	}

}
