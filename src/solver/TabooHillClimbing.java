package solver;

import java.util.concurrent.ArrayBlockingQueue;

import problem.Action;
import problem.State;

public class TabooHillClimbing implements Solver {

	private ArrayBlockingQueue<State> historic;

	public TabooHillClimbing(int historicSize) {
		historic = new ArrayBlockingQueue<State>(historicSize);
	}

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
				if (!historic.contains(neighbour)) {
					int neighbourCost = neighbour.cost();
					if (neighbourCost < bestCost) {
						existingBetterNeighbour = true;
						bestCost = neighbourCost;
						bestNeighbour = neighbour;
					}
					if(!historic.offer(neighbour)) {
						historic.poll();
						historic.add(neighbour);
					}
				}
			}
			if (bestNeighbour != null) {
				currentState = bestNeighbour;
			}
		}
		return currentState;
	}

}
