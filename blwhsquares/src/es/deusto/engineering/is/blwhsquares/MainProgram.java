package es.deusto.engineering.is.blwhsquares;

import es.deusto.ingenieria.is.search.algorithms.blind.BreadthFSwithLog;
import es.deusto.ingenieria.is.search.algorithms.blind.DepthFSwithLog;
import es.deusto.ingenieria.is.search.algorithms.heuristic.BestFS;
import es.deusto.ingenieria.is.search.algorithms.heuristic.BestFSwithLog;

public class MainProgram {

	public MainProgram() {
	}

	public static void main(String[] args) {
		
		BWSProblem theProblem = new BWSProblem();
		
		// Corrected here Homework 3/4 [1.35/1.5]BlWhProblem solve()
		Environment environment = new Environment(((Environment)theProblem.gatherInitialPercepts()).getLine(),0);
		
		//instantiate environment reader
		System.out.println(theProblem.gatherInitialPercepts());

		// adds to the problem the operators
		
		theProblem.createOperators();

		// tries to solve the problem with BFS
		
		theProblem.solve(BreadthFSwithLog.getInstance());
		System.out.println();

		// tries to solve the problem with DFS

		theProblem.solve(DepthFSwithLog.getInstance());
		System.out.println();
		
		// tries to solve the problem with BestFS
		
		theProblem.solve(new BestFSwithLog(new BWSPEvaluationFunction()));
		System.out.println();
		
		// tries to solve the problem with AStar
		
		theProblem.solve(new AStarwithLog(new BWSPEvaluationFunction()));
		System.out.println();
		
		
	}

}
