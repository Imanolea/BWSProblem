package es.deusto.engineering.is.blwhsquares;

import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;

/**
 * Defines the move two positions operator
 */
public class MoveTwoOp extends Operator {

	protected State effect(State env) {
		if (!(env instanceof Environment))
			return null;
		Environment state = (Environment) ((Environment) env).clone();
		state.setCurrentPos(state.getCurrentPos() + 2);
		return state;
	}

	protected boolean isApplicable(State env) {
		if (!(env instanceof Environment))
			return false;
		
		if (((Environment) env).actualPosition("WHITE"))
			return true;
		else
			return false;
	}

}
