package solver;

import java.util.ArrayList;
import java.util.Random;
import java.util.PriorityQueue;

import problem.State;
import util.Pair;

public class GeneticAlgorithm implements Solver {
	
	private int populationSize;
	private double mu;
	private PriorityQueue<State> population;
	private int stepMax;

	public GeneticAlgorithm(int populationSize, double mu, PriorityQueue<State> population, int stepMax) {
		this.populationSize = populationSize;
		this.mu = mu;
		this.population = population;
		this.stepMax = stepMax;
	}
	
	public GeneticAlgorithm(int queenNumber, int populationSize, double mu, int stepMax) {
		this(populationSize, mu, initRandomPopulation(queenNumber, populationSize), stepMax);
	}

	@Override
	public State solve(State initState) {
		int step = 0;
		while(step < stepMax) {
			ArrayList<Pair<State, State>> pairs = generatePairs();
			for (Pair<State, State> pair : pairs) {
				Pair<State, State> children = crossover(pair);
				Random rd = new Random();
				if(rd.nextDouble() < mu) {
					population.add(mutate(children.getFirst()));
					population.add(mutate(children.getSecond()));
				}
				population.add(children.getFirst());
				population.add(children.getSecond());
			}
			selection();
			step++;
		}
		return population.peek();
	}
	
	private Pair<State, State> crossover (Pair<State, State> pair) {
		State state1 = pair.getFirst();
		State state2 = pair.getSecond();
		int queenNumber = state1.getQueensNumber();
		int[] child1 = new int[queenNumber];
		int[] child2 = new int[queenNumber];
		Random rd = new Random();
		int k = rd.nextInt(queenNumber - 1) + 1;
		for(int i = 0; i < k; i ++) {
			child1[i] = state1.getQueenPosition(i);
			child2[i] = state2.getQueenPosition(i);
		}
		for(int i = k; i < queenNumber; i++) {
			child1[i] = state2.getQueenPosition(i);
			child2[i] = state1.getQueenPosition(i);
		}
		return new Pair<State, State>(new State(queenNumber, child1), new State(queenNumber, child2));
	}
	
	private State mutate(State state) {
		State mutateState = new State(state.getQueensNumber());
		Random rd = new Random();
		int selectedQueen = rd.nextInt(state.getQueensNumber());
		int position = rd.nextInt(state.getQueensNumber());
		for (int queen = 0; queen < state.getQueensNumber(); queen ++) {
			if(queen == selectedQueen) {
				mutateState.setQueenPosition(selectedQueen, position);
			}
			else {
				mutateState.setQueenPosition(queen, state.getQueenPosition(queen));
			}
		}
		return mutateState;
	}
	
	private ArrayList<Pair<State, State>> generatePairs(){
		ArrayList<Pair<State, State>> pairs = new ArrayList<>();
		ArrayList<State> populationCopy = new ArrayList<>();
		populationCopy.addAll(population);
		Random rd = new Random();
		for(int i = 0; i < populationSize/2 ; i ++) {
			State first = populationCopy.get(rd.nextInt(populationCopy.size()));
			populationCopy.remove(first);
			State second = populationCopy.get(rd.nextInt(populationCopy.size()));
			populationCopy.remove(second);
			pairs.add(new Pair<State, State>(first, second));
		}
		return pairs;
	}
	
	private void selection() {
		//Minimum cost selection
		PriorityQueue<State> newPopulation = new PriorityQueue<>();
		for(int i = 0; i < populationSize; i++) {
			//Adding the State with least costs
			newPopulation.add(population.poll());
		}
		population.clear();
		population.addAll(newPopulation);
	}
	
	private static PriorityQueue<State> initRandomPopulation(int queenNumber, int populationSize) {
		PriorityQueue<State> population = new PriorityQueue<>();
		for (int i = 0; i < populationSize; i++) {
			State state = new State(queenNumber);
			state.randomInit();
			population.add(state);
		}
		return population;
	}

}
