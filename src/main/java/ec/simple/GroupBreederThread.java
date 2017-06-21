package ec.simple;

import ec.EvolutionState;
import ec.Individual;
import ec.Population;

public class GroupBreederThread extends Thread{

    GroupBreeder groupBreeder;
    
    Population newpop;
    EvolutionState state;
    int[] numinds;
    int[] from;

    int subpop = 0;
    int threadnum = 0;
    
    SimpleProblemForm prob;
    
    public GroupBreederThread(GroupBreeder groupBreeder, int threadnum, Population newpop, EvolutionState state, int[] numinds, int[] from) {
        this.groupBreeder = groupBreeder;
        this.threadnum = threadnum;
        this.newpop = newpop;
        this.state = state;
        this.numinds = numinds;
        this.from = from;
        
        prob = (SimpleProblemForm) state.evaluator.p_problem.clone();
    }
    
    public void run() {
        // Breeding
        groupBreeder.breedPopChunk(newpop, state, numinds, from, threadnum);
        
        //Evaluation
        int i = from[subpop];
        int upperbound = from[subpop] + numinds[subpop];
        while (i < upperbound){
            Individual indiv = newpop.subpops[subpop].individuals[i];
            
            prob.evaluate(state, indiv, subpop, threadnum);
            
            i++;
        }
    }
    
}
