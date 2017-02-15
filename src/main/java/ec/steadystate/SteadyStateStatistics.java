/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package ec.steadystate;

import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.simple.SimpleStatistics;

@SuppressWarnings("serial")
public class SteadyStateStatistics extends SimpleStatistics {
	
	@Override
	public void postEvaluationStatistics(EvolutionState state) {
        Individual[] best_i = new Individual[state.population.subpops.length];  // quiets compiler complaints
        for(int x=0;x<state.population.subpops.length;x++)
            {
            best_i[x] = state.population.subpops[x].individuals[0];
            for(int y=1;y<state.population.subpops[x].individuals.length;y++)
                {
                if (state.population.subpops[x].individuals[y] == null)
                    {
                    }
                else if (best_i[x] == null || state.population.subpops[x].individuals[y].fitness.betterThan(best_i[x].fitness))
                    best_i[x] = state.population.subpops[x].individuals[y];
                }
        
            // now test to see if it's the new best_of_run
            if (best_of_run[x]==null || best_i[x].fitness.betterThan(best_of_run[x].fitness))
                best_of_run[x] = (Individual)(best_i[x].clone());
            }
	}

	@Override
	public void generationBoundaryStatistics(EvolutionState state) {	
		
		for(int subpop_index=0;subpop_index<state.population.subpops.length;subpop_index++){
			float size_acumulator = 0;
			float fitness_acumulator = 0;
			GPIndividual best_individual = (GPIndividual) state.population.subpops[0].individuals[0];
			
			for(int indiv_index=1;indiv_index<state.population.subpops[subpop_index].individuals.length;indiv_index++){
				GPIndividual indiv = (GPIndividual) state.population.subpops[subpop_index].individuals[indiv_index];

				size_acumulator += indiv.size();
				fitness_acumulator += indiv.fitness.fitness();
				if(indiv.fitness.fitness() > best_individual.fitness.fitness()){
					best_individual = indiv;
				}
			}
			
			float size_avg = size_acumulator / (float) state.population.subpops[subpop_index].individuals.length;
			state.output.message("Average size: " + size_avg);
			
			float fitness_avg = fitness_acumulator / (float) state.population.subpops[subpop_index].individuals.length;
			state.output.message("Average fitness: " + fitness_avg);
			
			state.output.message("Best fitness: " + best_individual.fitness.fitness());
			state.output.message("Best individual size: " + best_individual.size());
			
			state.output.message("");
			
			state.output.println(state.generation + " " 
					 		   + size_avg + " "
					 		   + fitness_avg + " "
					 		   + best_individual.fitness.fitness() + " "
							   + best_individual.size(), 
							   statisticslog);
		}
	}
	
}
