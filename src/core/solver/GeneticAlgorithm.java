package core.solver;

import java.util.ArrayList;
import java.util.Random;

import core.problem.State;
import core.util.Pair;

import java.util.PriorityQueue;

public class GeneticAlgorithm implements Solver {
	
	public static final int defaultPopulationSize = 10;
	public static final double defaultMu = 0.01;
	
	private int populationSize;
	private double mu;
	private PriorityQueue<State> population;
	private int maxIteration;

	public GeneticAlgorithm() {
		this(defaultPopulationSize, defaultMu, defaultMaxIteration);
	}
	
	public GeneticAlgorithm(int populationSize, double mu, int maxIteration) {
		this.populationSize = populationSize;
		this.mu = mu;
		this.maxIteration = maxIteration;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public int getPopulationSize() {
		return populationSize;
	}
	
	public void setMu(double mu) {
		this.mu = mu;
	}
	
	public double getMu() {
		return mu;
	}

	public void setMaxIteration(int maxIteration) {
		this.maxIteration = maxIteration;
	}

	@Override
	public State solve(State initState) {
		initRandomPopulation(initState.getQueensNumber(), populationSize);
		int step = 0;
		while(step < maxIteration) {
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
	
	private void initRandomPopulation(int queenNumber, int populationSize) {
		population = new PriorityQueue<>();
		for (int i = 0; i < populationSize; i++) {
			State state = new State(queenNumber);
			state.randomInit();
			population.add(state);
		}
	}
	
	@Override
	public String toString() {
		return "Genetic Algorithm";
	}

}
