package core.solver;

import core.problem.State;

public interface Solver {
	
	public static final int defaultMaxIteration = 100;
	public static final int defaultHistoricSize = 30;

	public State solve(State initState);
}
