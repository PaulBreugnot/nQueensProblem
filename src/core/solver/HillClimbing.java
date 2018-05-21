package core.solver;

import core.problem.Action;
import core.problem.State;

public class HillClimbing implements Solver {
	
	private static final int defaultTlimite = 100;
	
	private int Tlimite;
	
	public HillClimbing() {
		this(defaultTlimite);
	}
	
	public HillClimbing(int Tlimite) {
		this.Tlimite = Tlimite;
	}

	@Override
	public State solve(State initState) {
		State currentState = initState;
		int bestCost = Integer.MAX_VALUE;
		boolean existingBetterNeighbour = true;

		int t = 0;
		while (existingBetterNeighbour && t < Tlimite) {
			existingBetterNeighbour = false;
			State bestNeighbour = null;
			for (Action action : Action.availableActions(currentState)) {
				State neighbour = action.result(currentState);
				int neighbourCost = neighbour.cost();
				if (neighbourCost < bestCost) {
					existingBetterNeighbour = true;
					bestCost = neighbourCost;
					bestNeighbour = neighbour;
				}
			}
			if(bestNeighbour != null) {
				currentState = bestNeighbour;
			}
			t++;
		}
		return currentState;
	}
	
	@Override
	public String toString() {
		return "Hill Climbing";
	}

}
