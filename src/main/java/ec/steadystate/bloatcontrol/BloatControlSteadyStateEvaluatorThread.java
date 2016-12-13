package ec.steadystate.bloatcontrol;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import ec.EvolutionState;
import ec.simple.SimpleProblemForm;
import ec.steadystate.QueueIndividual;

public class BloatControlSteadyStateEvaluatorThread implements Runnable {
	
	private int thread_num;
	private EvolutionState state;
    private SimpleProblemForm problem;
	private LinkedList<QueueIndividual> toBeEvaluatedIndividuals;
	private LinkedList<QueueIndividual> evaluatedIndividuals;

	private int emptyListCounter;
	private int noEmptyListCounter;
	
	BloatControlSteadyStateEvaluatorThread(
			int thread_num, 
			EvolutionState state,
			SimpleProblemForm problem,
			LinkedList<QueueIndividual> toBeEvaluatedIndividuals,
			LinkedList<QueueIndividual> evaluatedIndividuals){
		this.thread_num = thread_num;
		this.state = state;
		this.problem = problem;
		this.toBeEvaluatedIndividuals = toBeEvaluatedIndividuals;
		this.evaluatedIndividuals = evaluatedIndividuals;
		
		emptyListCounter = 0;
		noEmptyListCounter = 0;
	}
	
	public void run() {
		while(true){
			try{
				QueueIndividual ind = null;
				synchronized (toBeEvaluatedIndividuals) {
					try {
						toBeEvaluatedIndividuals.wait();
					} catch (InterruptedException e) {}
					
					if(toBeEvaluatedIndividuals.size() == 0){
						emptyListCounter++;
						
						continue;
					}else{
						noEmptyListCounter++;
					}
					
					ind = toBeEvaluatedIndividuals.removeFirst();
				}
				
				if(noEmptyListCounter % 10000 == 0)
					System.out.println(thread_num + ": " + noEmptyListCounter + " - " + emptyListCounter);
				
				problem.evaluate(state, ind.ind, 1, 0);
				
				synchronized (evaluatedIndividuals) {
					evaluatedIndividuals.addLast(ind);
				}
			}catch(NoSuchElementException e){}
		}
	}

}
