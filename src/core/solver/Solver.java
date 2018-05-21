package core.solver;

import core.problem.State;

public interface Solver {

	public State solve(State initState);
}
