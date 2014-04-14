package es.deusto.engineering.is.blwhsquares;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.is.search.formulation.State;
/**
 * defines the problem environment state
 */
public class Environment extends State implements Cloneable {

	public enum Square {
		WHITE,
		BLACK,
		UNKNOWN;
	}

	private List<Square> line;
	private int currentPos;
	
	
	/**
	 * @param line: the list of squares forming the environment
	 * @param currentPos: current position in the list of squares
	 */
	public Environment(List<Square> line, int currentPos) {
		super();
		this.line = line;
		this.currentPos = currentPos;
	}
	
	
	/**
	 * @param length: lenght of the list of squares forming the environment
	 */
	public Environment(int length){
		this.line = new ArrayList<Square>(length);
		//default initial position is zero
		this.currentPos = 0;
	}
	
	/**
	 * Add a new square to the line
	 * @param sq: square to be added
	 */
	public void addSquare(Square sq){
		this.line.add(sq);
	}

	/**
	 * @return list of squares
	 */
	public List<Square> getLine() {
		return line;
	}

	/**
	 * @param line: list of squares
	 */
	public void setLine(List<Square> line) {
		this.line = line;
	}

	/**
	 * @return current position in the list
	 */
	public int getCurrentPos() {
		return currentPos;
	}

	/**
	 * 
	 * @param currentPos: current position in the list
	 */
	public void setCurrentPos(int currentPos) {
		this.currentPos = currentPos;
	}
	
	/**
	 * Check the color of the actual position
	 * @param color: name of the color to compare
	 * @return Returns true if the square of the actual position has the same color as the passed as the atribute
	 * Returns false if not.
	 */
	public boolean actualPosition(String color) {
		if (this.getLine().get(this.getCurrentPos()).name().equals(color))
			return true;
		else
			return false;
	}
	
	public String toString() {
		return "Environment currentPos " + this.currentPos + "\n\t" + line;
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof Environment) || obj == null)
			return false;
		return this.line.equals(((Environment) obj).line) && this.currentPos == ((Environment) obj).currentPos;
	}
	
	public Object clone() {
		Object clon = null;
		
		try {
			clon = super.clone();		
		} catch (CloneNotSupportedException e) {
			System.err.println("% [ERROR] Environment.clone(): " + e.getMessage());
		}
		
		return clon;		
	}
	
	
	

	
	
	
}
