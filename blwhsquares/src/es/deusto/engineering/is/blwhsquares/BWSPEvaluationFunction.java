package es.deusto.engineering.is.blwhsquares;

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;

public class BWSPEvaluationFunction extends EvaluationFunction {

	
	public double calculateG(Node n) {
		return n.getDepth();
	}

	
	
	public double calculateH(Node n) {
		return Math.ceil((((Environment) n.getState()).getLine().size() - ((Environment) n.getState()).getCurrentPos() + 1) / 4);
	}
	
}
