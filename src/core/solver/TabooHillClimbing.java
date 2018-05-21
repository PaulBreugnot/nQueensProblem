package core.solver;

import java.util.concurrent.ArrayBlockingQueue;

import core.problem.Action;
import core.problem.State;

public class TabooHillClimbing implements Solver {
	
	private int historicSize;
	private int MaxIteration;

	private ArrayBlockingQueue<State> historic;
	
	public TabooHillClimbing() {
		this(defaultMaxIteration, defaultHistoricSize);
	}

	public TabooHillClimbing(int MaxIteration, int historicSize) {
		this.MaxIteration = MaxIteration;
		this.historicSize = historicSize;
	}
	
	public void setHistoricSize(int historicSize) {
		this.historicSize = historicSize;
	}
	
	public int getHistoricSize() {
		return historicSize;
	}

	public void setMaxIteration(int MaxIteration) {
		this.MaxIteration = MaxIteration;
	}

	@Override
	public State solve(State initState) {
		historic = new ArrayBlockingQueue<State>(historicSize);
		State currentState = initState;
		int bestCost = Integer.MAX_VALUE;
		boolean existingBetterNeighbour = true;

		int t = 0;
		while (existingBetterNeighbour && t < MaxIteration) {
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
