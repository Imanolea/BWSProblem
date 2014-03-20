package es.deusto.engineering.is.blwhsquares;

import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;

/**
 * Defines the move one position operator
 */
public class MoveOneOp extends Operator{
	
	protected State effect(State env) {
		if (!(env instanceof Environment))
			return null;
		Environment state = (Environment) ((Environment) env).clone();
		state.setCurrentPos(state.getCurrentPos() + 1);
		return state;
	}

	protected boolean isApplicable(State env) {
		if (!(env instanceof Environment))
			return false;
		return true;
	}

}
