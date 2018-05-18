package problem;

public class Action {
	
	//This class provides methods to generate neighbours from a State
	private int queenToMove;
	private int newPosition;
	
	public Action(int queenToMove, int newPosition) {
		this.queenToMove = queenToMove;
		this.newPosition = newPosition;
	}
	
	public State result(State state) {
		State neighbour = new State(state.getQueensNumber());
		for(int queen = 0; queen < neighbour.getQueensNumber(); queen ++) {
			if(queen == queenToMove) {
				neighbour.setQueenPosition(queenToMove, newPosition);
			}
			else {
				neighbour.setQueenPosition(queen, state.getQueenPosition(queen));
			}
		}
		return neighbour;
	}
	

}
