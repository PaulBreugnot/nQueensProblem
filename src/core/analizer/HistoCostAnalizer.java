package core.analizer;

import java.util.TreeMap;

import core.problem.State;
import core.solver.Solver;

public class HistoCostAnalizer {
	
	private Solver solver;
	private int queenNumber;
	private int sampleSize;
	
	public HistoCostAnalizer(Solver solver, int queenNumber, int sampleSize) {
		this.solver = solver;
		this.queenNumber = queenNumber;
		this.sampleSize = sampleSize;
	}
	
	public TreeMap<Integer, Integer> analize() {
		TreeMap<Integer, Integer> Histogram = new TreeMap<>();
		State initialState = new State(queenNumber);
		for(int i = 0; i < sampleSize; i ++) {
			initialState.randomInit();
			int cost = solver.solve(initialState).cost();
			if(Histogram.containsKey(cost)) {
				int oldValue = Histogram.get(cost);
				Histogram.put(cost, oldValue + 1);
			}
			else {
				Histogram.put(cost, 1);
			}
		}
		int lastCost = Histogram.lastKey();
		for(int i = 0; i < lastCost; i ++) {
			if(i%2 == 0 && !Histogram.containsKey(i)) {
				Histogram.put(i, 0);
			}
		}
		return Histogram;
	}

}
