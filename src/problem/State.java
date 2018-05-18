package problem;

import java.util.Arrays;

public class State {

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
				if (getQueenPosition(queen) - getQueenPosition(queenToCheck) == queen - queenToCheck) {
					conflicts++;
				}
			}
		}
		return conflicts;
	}

	@Override
	public String toString() {
		return Arrays.toString(queensPositions);
	}
	
	
}
