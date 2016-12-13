package ec.steadystate.bloatcontrol;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ec.EvolutionState;
import ec.Individual;
import ec.simple.SimpleProblemForm;
import ec.steadystate.QueueIndividual;
import ec.steadystate.SteadyStateEvaluator;

public class BloatControlSteadyStateEvaluator extends SteadyStateEvaluator {

	private static final long serialVersionUID = 1L;
    
	private LinkedList<QueueIndividual> toBeEvaluatedIndividuals;
	
	private LinkedList<QueueIndividual> evaluatedIndividuals;

	public BloatControlSteadyStateEvaluator() {
    	toBeEvaluatedIndividuals = new LinkedList<QueueIndividual>();
    	evaluatedIndividuals = new LinkedList<QueueIndividual>();
	}

	@Override
	public void prepareToEvaluate(EvolutionState state, int thread) {		
		int num_threads = 4;
		
		ExecutorService executor = Executors.newFixedThreadPool(num_threads);
		for (int thread_num = 0; thread_num < num_threads; thread_num++) {
			Runnable worker = new BloatControlSteadyStateEvaluatorThread(
					thread_num,
					state,
					(SimpleProblemForm) p_problem.clone(),
					toBeEvaluatedIndividuals,
					evaluatedIndividuals);
			executor.execute(worker);
		}
	}
	
	@Override
	public void evaluateIndividual(EvolutionState state, Individual ind, int subpop) {
		synchronized (toBeEvaluatedIndividuals) {
			toBeEvaluatedIndividuals.addLast(new QueueIndividual(ind, subpop));
			toBeEvaluatedIndividuals.notify();
		}
	}
	
	@Override
	public boolean canEvaluate() {
		return true;
	}
	
	@Override
	public Individual getNextEvaluatedIndividual() {
		synchronized (evaluatedIndividuals) {
			try{
				return evaluatedIndividuals.removeFirst().ind;
			}catch(NoSuchElementException e){
				return null;
			}
		}
	}
	
	@Override
	public int getSubpopulationOfEvaluatedIndividual() {
		return 0;
	}
	
	@Override
	public boolean runComplete(EvolutionState state, Individual ind) {
	    if (ind != null && ind.fitness.isIdealFitness())
            return true;
        return false;
	}
	
}
