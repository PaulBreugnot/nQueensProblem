package core.solver;

import java.util.ArrayList;
import java.util.Random;

import core.problem.Action;
import core.problem.State;

public class SimulatedAnnealing implements Solver {

	private int MaxIteration;
	
	public SimulatedAnnealing() {
		this(defaultMaxIteration);
	}
	
	public SimulatedAnnealing(int MaxIteration) {
		this.MaxIteration = MaxIteration;
	}
	
	public void setMaxIteration(int maxIteration) {
		MaxIteration = maxIteration;
	}

	@Override
	public State solve(State initState) {
		State currentState = initState;
		int t = 0;
		while(t < MaxIteration) {
			Random rd = new Random();
			ArrayList<Action> actions = Action.availableActions(currentState);
			int randomIndex = rd.nextInt(actions.size());
			Action action = actions.get(randomIndex);
			State randomNeighbour = action.result(currentState)
;
			int delta = randomNeighbour.cost() - currentState.cost();
			if(delta < 0) {
				currentState = randomNeighbour;
			}
			else {
				if(rd.nextFloat() < Math.exp(- delta * t)) {
					currentState = randomNeighbour;
				}
			}
			t++;
		}
		return currentState;
	}

	@Override
	public String toString() {
		return "Simulated Annealing";
	}
}
