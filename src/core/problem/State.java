package core.problem;

import java.util.Arrays;
import java.util.Random;

public class State implements Comparable<State> {

	private int[] queensPositions;
	private int queensNumber;

	public State(int n) {
		queensNumber = n;
		queensPositions = new int[queensNumber];
	}
	
	public State(int n, int[] positions) {
		queensNumber = n;
		queensPositions = positions;
	}
	
	public int[] getQueensPositions() {
		return queensPositions;
	}
	
	public int getQueensNumber() {
		return queensNumber;
	}

	public int getQueenPosition(int queen) {
		return queensPositions[queen];
	}

	public void setQueenPosition(int queen, int position) {
		//A position represents the line of the queen
		queensPositions[queen] = position;
	}

	public int cost() {
		int cost = 0;
		for (int queen = 0; queen < queensNumber; queen++) {
			cost += checkLineConstraint(queen);
			cost += checkDiagConstraint(queen);
		}
		return cost;
	}

	private int checkLineConstraint(int queen) {
		int conflicts = 0;
		for (int queenToCheck = 0; queenToCheck < queensNumber; queenToCheck++) {
			if (queenToCheck != queen) {
				if (getQueenPosition(queen) == getQueenPosition(queenToCheck)) {
					conflicts++;
				}
			}
		}
		return conflicts;
	}
	
	private int checkDiagConstraint(int queen) {
		int conflicts = 0;
		for (int queenToCheck = 0; queenToCheck < queensNumber; queenToCheck++) {
			if (queenToCheck != queen) {
				if (Math.abs(getQueenPosition(queen) - getQueenPosition(queenToCheck)) == Math.abs(queen - queenToCheck)) {
					conflicts++;
				}
			}
		}
		return conflicts;
	}
	
	public void randomInit() {
		Random rd = new Random();
		for (int queen = 0; queen < queensNumber; queen ++) {
			setQueenPosition(queen, rd.nextInt(queensNumber));
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(queensPositions);
	}

	@Override
	public int compareTo(State o) {
		if(cost() < o.cost()) {
			return -1;
		}
		if (cost() > o.cost()) {
			return 1;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + queensNumber;
		result = prime * result + Arrays.hashCode(queensPositions);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (queensNumber != other.queensNumber)
			return false;
		if (!Arrays.equals(queensPositions, other.queensPositions))
			return false;
		return true;
	}
	
	
	
	
}
