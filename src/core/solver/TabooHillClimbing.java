package core.solver;

import java.util.concurrent.ArrayBlockingQueue;

import core.problem.Action;
import core.problem.State;

public class TabooHillClimbing implements Solver {
	
	private static final int defaultHistoricSize = 30;
	private static final int defaultTlimite = 100;
	
	private int Tlimite;

	private ArrayBlockingQueue<State> historic;
	
	public TabooHillClimbing() {
		this(defaultTlimite, defaultHistoricSize);
	}

	public TabooHillClimbing(int Tlimite, int historicSize) {
		this.Tlimite = Tlimite;
		historic = new ArrayBlockingQueue<State>(historicSize);
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
			t++;
		}
		return currentState;
	}
	
	@Override
	public String toString() {
		return "Taboo Hill Climbing";
	}

}
