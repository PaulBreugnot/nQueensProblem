package main;

import problem.Action;
import problem.State;

public class Main {
	
	public static void main(String[] args) {
		int[] positions = {0, 1, 1, 3};
		State state = new State(4, positions);
		System.out.println(state.cost());
		Action action = new Action(0, 3);
		State neighbour = action.result(state);
		System.out.println(neighbour);
	}
}
