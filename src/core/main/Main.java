package core.main;

import java.util.TreeMap;

import core.analizer.HistoCostAnalizer;
import core.problem.Action;
import core.problem.State;
import core.solver.GeneticAlgorithm;
import core.solver.HillClimbing;
import core.solver.SimulatedAnnealing;
import core.solver.Solver;
import core.solver.TabooHillClimbing;
import core.solver.TabooSimulatedAnnealing;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main {

	public static void main(String[] args) {

		State state = new State(10);
		state.randomInit();
		System.out.println(state.cost());

		// Hill Climbing
		System.out.println("-----Hill Climbing-----");
		Solver solver = new HillClimbing();
		State solution = solver.solve(state);
		System.out.println("Cost : " + solution.cost());
		System.out.println("Solution : " + solution);

		// Taboo Hill Climbing
		System.out.println("--Taboo Hill Climbing--");
		solver = new TabooHillClimbing();
		solution = solver.solve(state);
		System.out.println("Cost : " + solution.cost());
		System.out.println("Solution : " + solution);

		// SimulatedAnnealing
		System.out.println("--Simulated Annealing--");
		solver = new SimulatedAnnealing(10000);
		solution = solver.solve(state);
		System.out.println("Cost : " + solution.cost());
		System.out.println("Solution : " + solution);

		// TabooSimulatedAnnealing
		System.out.println("-- Taboo Simulated Annealing--");
		solver = new TabooSimulatedAnnealing(10000, 30);
		solution = solver.solve(state);
		System.out.println("Cost : " + solution.cost());
		System.out.println("Solution : " + solution);

		// Genetic algorithm
		System.out.println("--Genetic Algorithm--");
		solver = new GeneticAlgorithm(4, 10, 0.01, 100);
		solution = solver.solve(state);
		System.out.println("Cost : " + solution.cost());
		System.out.println("Solution : " + solution);
		
		//Analizer
		HistoCostAnalizer analizer = new HistoCostAnalizer(new SimulatedAnnealing(), 5, 1000);
		//System.out.println(analizer.analize());
		
		TreeMap<Integer, Integer> results = analizer.analize();
		Stage stage = new Stage();
		stage.setTitle("Bar Chart Sample");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<Number,Number> bc = 
            new BarChart<>(xAxis,yAxis);
        bc.setTitle("Costs Counts");
        xAxis.setLabel("Value");       
        yAxis.setLabel("Occurences");
        
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("2003");
        for(Integer value : results.keySet()) {
        	series.getData().add(new XYChart.Data<Number, Number>(value, results.get(value)));
        }
        
        Scene scene  = new Scene(bc);
        bc.getData().add(series);
        stage.setScene(scene);
        stage.show();
	}
}
