package core.solver;

import core.problem.Action;
import core.problem.State;

public class HillClimbing implements Solver {
	
	private int MaxIteration;
	
	public HillClimbing() {
		this(defaultMaxIteration);
	}
	
	public HillClimbing(int MaxIteration) {
		this.MaxIteration = MaxIteration;
	}

	public void setMaxIteration(int maxIteration) {
		MaxIteration = maxIteration;
	}
	
	public int getMaxIteration() {
		return MaxIteration;
	}

	@Override
	public State solve(State initState) {
		State currentState = initState;
		int bestCost = Integer.MAX_VALUE;
		boolean existingBetterNeighbour = true;

		int t = 0;
		while (existingBetterNeighbour && t < MaxIteration) {
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
