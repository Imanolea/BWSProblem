package es.deusto.engineering.is.blwhsquares;

import es.deusto.ingenieria.is.search.algorithms.blind.BreadthFSwithLog;
import es.deusto.ingenieria.is.search.algorithms.blind.DepthFSwithLog;

public class MainProgram {

	public MainProgram() {
	}

	public static void main(String[] args) {
		
		BWSProblem theProblem = new BWSProblem();
		Environment environment = new Environment(((Environment)new EnvironmentReader("percepts/blackwhitesquares1.xml").getState()).getLine(),0);
		
		//instantiate environment reader
		System.out.println(theProblem.gatherInitialPercepts());
		
		//check final state
		
// This was the test according to the 1/4 homework		
		
//		if (theProblem.isFinalState(theProblem.getInitialStates().get(0)))
//			System.out.println("The initial state is the final state");
//		else
//			System.out.println("The initial state is not the final state");
//		
//		environment.setCurrentPos(environment.getLine().size() - 1);
//		
//		if (theProblem.isFinalState((State) environment))
//			System.out.println("A final state is a final state");
//		else
//			System.out.println("A final state is not a final state");
		
		//check create operators, isApplicable and effect
		
		environment.setCurrentPos(0);
		System.out.println("The current position is " + environment.getCurrentPos());
		
		theProblem.createOperators();
		
		theProblem.solve(BreadthFSwithLog.getInstance());
		System.out.println();
		theProblem.solve(DepthFSwithLog.getInstance());
		
// This was the test according to the 1/4 homework
		
//		System.out.println("We try to move one square to the right");
//		if (((MoveOneOp)theProblem.getOperators().get(0)).isApplicable(environment)) {
//			environment = (Environment) ((MoveOneOp) theProblem.getOperators().get(0)).effect(environment);
//			System.out.println("We move one square to the right");
//		} else {
//			System.out.println("I'm afraid we can't do that given the color of the square in which we are");
//		}
//		System.out.println("The current position is " + environment.getCurrentPos());
//		
//		System.out.println("We try to move two squares to the right");
//		if (((MoveTwoOp)theProblem.getOperators().get(1)).isApplicable(environment)) {
//			environment = (Environment) ((MoveTwoOp) theProblem.getOperators().get(1)).effect(environment);
//			System.out.println("We move two squares to the right");
//		} else {
//			System.out.println("I'm afraid we can't do that given the color of the square in which we are");
//		}
//		System.out.println("The current position is " + environment.getCurrentPos());
//		
//		System.out.println("We try to move four squares to the right");
//		if (((MoveFourOp)theProblem.getOperators().get(2)).isApplicable(environment)) {
//			environment = (Environment) ((MoveFourOp) theProblem.getOperators().get(2)).effect(environment);
//			System.out.println("We move four squares to the right");
//		} else {
//			System.out.println("I'm afraid we can't do that given the color of the square in which we are");
//		}
//		System.out.println("The current position is " + environment.getCurrentPos());
		
	}

}
