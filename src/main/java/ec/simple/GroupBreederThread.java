package ec.simple;

import ec.EvolutionState;
import ec.Population;

public class GroupBreederThread extends Thread{

    GroupBreeder groupBreeder;
    
    Population newpop;
    EvolutionState state;
    int[] numinds;
    int[] from;
    
    public GroupBreederThread(GroupBreeder groupBreeder, Population newpop, EvolutionState state, int[] numinds, int[] from) {
        this.groupBreeder = groupBreeder;
        this.newpop = newpop;
        this.state = state;
        this.numinds = numinds;
        this.from = from;
    }
    
    public void run() {
        groupBreeder.breedPopChunk(newpop, state, numinds, from);
    }
    
}
