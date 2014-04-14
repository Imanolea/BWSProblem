package es.deusto.engineering.is.blwhsquares;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
	
	public boolean isFullyObserved() {
		Field dataField;
		int memoryLength = 0;
		ArrayList <Square> initialSq = (ArrayList<Square>) ((Environment) this.gatherInitialPercepts()).getLine();
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
		
		return initialSq.size() == memoryLength;
	}

}
