package main;

import problem.Action;
import problem.State;
import solver.HillClimbing;
import solver.SimulatedAnnealing;
import solver.Solver;

public class Main {
	
	public static void main(String[] args) {
		
		State state = new State(10);
		state.randomInit();
		System.out.println(state.cost());
		
		//Hill Climbing
		System.out.println("-----Hill Climbing-----");
		Solver solver = new HillClimbing();
		State solution = solver.solve(state);
		System.out.println("Cost : " + solution.cost());
		System.out.println("Solution : " + solution);
		
		//SimulatedAnnealing
		System.out.println("--Simulated Annealing--");
		solver = new SimulatedAnnealing(10000);
		solution = solver.solve(state);
		System.out.println("Cost : " + solution.cost());
		System.out.println("Solution : " + solution);
	}
}
