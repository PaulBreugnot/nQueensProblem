package main;

import problem.Action;
import problem.State;
import solver.GeneticAlgorithm;
import solver.HillClimbing;
import solver.SimulatedAnnealing;
import solver.Solver;
import solver.TabooHillClimbing;
import solver.TabooSimulatedAnnealing;

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
		solver = new TabooHillClimbing(30);
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
	}
}
