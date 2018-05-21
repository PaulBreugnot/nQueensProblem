package core.solver;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import core.problem.Action;
import core.problem.State;

public class TabooSimulatedAnnealing implements Solver {
	
	private int historicSize;
	private int MaxIteration;
	
	private ArrayBlockingQueue<State> historic;
	
	public TabooSimulatedAnnealing () {
		this(defaultMaxIteration, defaultHistoricSize);
	}

	public TabooSimulatedAnnealing(int MaxIteration, int historicSize) {
		this.MaxIteration = MaxIteration;
		this.historicSize = historicSize;
	}
	

	public void setHistoricSize(int historicSize) {
		this.historicSize = historicSize;
	}

	public void setMaxIteration(int MaxIteration) {
		this.MaxIteration = MaxIteration;
	}

	@Override
	public State solve(State initState) {
		historic = new ArrayBlockingQueue<State>(historicSize);
		State currentState = initState;
		int t = 0;
		while (t < MaxIteration) {
			Random rd = new Random();
			State randomNeighbour;
			ArrayList<Action> actions = Action.availableActions(currentState);
			do {
				int randomIndex = rd.nextInt(actions.size());
				Action action = actions.get(randomIndex);
				actions.remove(action);
				randomNeighbour = action.result(currentState);

			} while (actions.size() > 1 && historic.contains(randomNeighbour));
			if (!historic.offer(randomNeighbour)) {
				historic.poll();
				historic.add(randomNeighbour);
			}
			int delta = randomNeighbour.cost() - currentState.cost();
			if (delta < 0) {
				currentState = randomNeighbour;
			} else {
				if (rd.nextFloat() < Math.exp(-delta * t)) {
					currentState = randomNeighbour;
				}
			}
			t++;
		}
		return currentState;
	}
	
	@Override
	public String toString() {
		return "Taboo Simulated Annealing";
	}

}
