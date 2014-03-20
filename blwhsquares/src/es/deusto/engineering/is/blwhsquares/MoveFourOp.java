package es.deusto.engineering.is.blwhsquares;

import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;
/**
 * Defines the move four positions operator
 */
public class MoveFourOp extends Operator {

	protected State effect(State env) {
		if (!(env instanceof Environment))
			return null;
		Environment state = (Environment) ((Environment) env).clone();
		state.setCurrentPos(state.getCurrentPos() + 4);
		return state;
	}

	protected boolean isApplicable(State env) {
		if (!(env instanceof Environment))
			return false;
		
		if (((Environment) env).actualPosition("BLACK"))
			return true;
		else
			return false;
	}

}