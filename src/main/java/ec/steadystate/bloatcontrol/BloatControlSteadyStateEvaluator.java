package ec.steadystate.bloatcontrol;

import java.util.LinkedList;

import ec.EvolutionState;
import ec.Individual;
import ec.simple.SimpleProblemForm;
import ec.steadystate.QueueIndividual;
import ec.steadystate.SteadyStateEvaluator;

public class BloatControlSteadyStateEvaluator extends SteadyStateEvaluator {

	private static final long serialVersionUID = 1L;
	
    LinkedList<QueueIndividual> queue = new LinkedList<QueueIndividual>();
    
    /** Holds the subpopulation currently being evaluated.  */ 
    int subpopulationBeingEvaluated = -1;

    /** Our problem. */
    SimpleProblemForm problem; 

	@Override
	public void prepareToEvaluate(EvolutionState state, int thread) {
		// Init threads
		problem = (SimpleProblemForm)p_problem.clone();
	}
	
	@Override
	public void evaluateIndividual(EvolutionState state, Individual ind, int subpop) {
		problem.evaluate(state, ind, subpop, 0);
        queue.addLast(new QueueIndividual(ind, subpop));
	}
	
	@Override
	public boolean canEvaluate() {
		return true;
	}
	
	@Override
	public Individual getNextEvaluatedIndividual() {
        QueueIndividual qind = queue.removeFirst();
        
        if (qind == null) 
        	return null;
        
        subpopulationBeingEvaluated = qind.subpop;
        
        return qind.ind;
	}
	
	@Override
	public int getSubpopulationOfEvaluatedIndividual() {
		return subpopulationBeingEvaluated;
	}
	
}
