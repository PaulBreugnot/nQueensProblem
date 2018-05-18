package solver;

import java.util.ArrayList;
import java.util.Random;

import problem.Action;
import problem.State;

public class SimulatedAnnealing implements Solver {

	private int Tlimite;
	
	public SimulatedAnnealing(int Tlimite) {
		this.Tlimite = Tlimite;
	}
	
	@Override
	public State solve(State initState) {
		State currentState = initState;
		int t = 0;
		while(t < Tlimite) {
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

}
