package solver;

import problem.Action;
import problem.State;

public class HillClimbing implements Solver {

	@Override
	public State solve(State initState) {
		State currentState = initState;
		int bestCost = Integer.MAX_VALUE;
		boolean existingBetterNeighbour = true;

		while (existingBetterNeighbour) {
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
		}
		return currentState;
	}

}
