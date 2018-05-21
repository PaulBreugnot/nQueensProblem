package core.solver;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import core.problem.Action;
import core.problem.State;

public class TabooSimulatedAnnealing implements Solver {
	
	private static final int defaultTlimite = 100;
	private static final int defaultHistoricSize = 30;

	private int Tlimite;
	private ArrayBlockingQueue<State> historic;
	
	public TabooSimulatedAnnealing () {
		this(defaultTlimite, defaultHistoricSize);
	}

	public TabooSimulatedAnnealing(int Tlimite, int historicSize) {
		this.Tlimite = Tlimite;
		historic = new ArrayBlockingQueue<State>(historicSize);
	}

	@Override
	public State solve(State initState) {
		State currentState = initState;
		int t = 0;
		while (t < Tlimite) {
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
