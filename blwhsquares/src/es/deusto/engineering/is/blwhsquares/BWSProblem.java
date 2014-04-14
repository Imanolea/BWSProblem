package es.deusto.engineering.is.blwhsquares;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import es.deusto.engineering.is.blwhsquares.Environment.Square;
import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.SearchMethod;
import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;
/**
 * Defines the problem of the colored squares list 
 */
public class BWSProblem extends Problem {
	
	public BWSProblem() {
		super();
		this.addInitialState(this.gatherInitialPercepts());
	}
	
	public boolean isFinalState(State state) {
		
		if (((Environment) state).getCurrentPos() > ((Environment) state).getLine().size() - 1)
			return true;
		else
			return false;
	}
	
	/**
	 * Gather the initial percepts of the problem
	 * @return the initial state
	 */
	public State gatherInitialPercepts() {
		//EnvironmentReader reader = new EnvironmentReader("percepts/blackwhitesquares1.xml");
		EnvironmentReader reader = new EnvironmentReader("percepts/blackwhitesquaresPartialpercepts1.xml");
		State initialSt = reader.getState();
		
		if (!this.isFullyObserved(reader.getState()))
			for (int i = ((Environment) reader.getState()).getLine().size(); i < getProblemLength(reader.getState()); i++)
				((Environment) reader.getState()).getLine().add(Square.UNKNOWN);
		
		return initialSt;
		
	}
	
	/**
	 * This mey
	 */
	
	public void createOperators() {
		Operator moveOne = new MoveOneOp();
		Operator moveTwo = new MoveTwoOp();
		Operator moveFour = new MoveFourOp();
		moveOne.setName("Move one");
		moveTwo.setName("Move two");
		moveFour.setName("Move four");
		
		this.addOperator(moveTwo);
		this.addOperator(moveFour);
		this.addOperator(moveOne);
	}
	
	/**
	 * 
	 * @param searchMethod
	 */
	
	public void solve(SearchMethod searchMethod) {		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.S");
		Date beginDate = GregorianCalendar.getInstance().getTime();
		System.out.println("\n* Start '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(beginDate) + ")");				
		Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
		Date endDate = GregorianCalendar.getInstance().getTime();		
		System.out.println("* End   '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(endDate) + ")");
		
		long miliseconds = (int) Math.abs(beginDate.getTime() - endDate.getTime());
		long seconds = miliseconds / 1000;
		miliseconds %= 1000;		
		long minutes = seconds / 60;
		seconds %= 60;
		long hours = minutes / 60;
		minutes %= 60;
		
		String time = "* Search lasts: ";
		time += (hours > 0) ? hours + " h " : " ";
		time += (minutes > 0) ? minutes + " m " : " ";
		time += (seconds > 0) ? seconds + "s " : " ";
		time += (miliseconds > 0) ? miliseconds + "ms " : " ";
		
		System.out.println(time);
		
		if (finalNode != null) {
			System.out.println("\n- Solution found!     :)");
			List<String> operators = new ArrayList<String>();
			searchMethod.solutionPath(finalNode, operators);
			searchMethod.createSolutionLog(operators);			
			System.out.println("- Final state:" + finalNode.getState());
		} else {
			System.out.println("\n- Unable to find the solution!     :(");
		}
	}
	
	public int getProblemLength(State state) {
		Field dataField;
		int memoryLength = 0;
		ArrayList <Square> initialSq = (ArrayList<Square>) ((Environment) state).getLine();
		
		try {
			dataField = ArrayList.class.getDeclaredField("elementData");
			dataField.setAccessible(true);
			memoryLength = ((Object[]) dataField.get(initialSq)).length;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return memoryLength;
	}
	
	public boolean isFullyObserved(State state) {
		
		boolean observable = true;
		
		for (int i = 0; i < ((Environment) state).getLine().size(); i++)
			if (((Environment) state).getLine().get(i).name().equals("UNKNOWN"))
				observable = false;
		
		if (((Environment) state).getLine().size() < getProblemLength(state))
			observable = false;
		
		return observable;
	}
	
	public State gatherPercepts(State state) {
		State returnState = (State) ((Environment)state).clone();
		int currentPos = ((Environment) returnState).getCurrentPos();
		
		boolean answered = false;
		Square sq = null;
		Scanner s = new Scanner(System.in);
		
		do {
			System.out.print("Color of the actual Square? (b/w): ");
			
			String choice = s.nextLine();

			switch(choice) {
			case "w": case "W":
				sq = Square.WHITE;
				answered = true;
				break;
			case "b": case "B":
				sq = Square.BLACK;
				answered = true;
				break;
			}
		} while (!answered);

		if (currentPos < getProblemLength(returnState))
			((Environment) returnState).getLine().set(currentPos, sq);
		
		return returnState;
	}

}
