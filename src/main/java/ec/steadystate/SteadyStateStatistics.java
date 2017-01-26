/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package ec.steadystate;

import ec.EvolutionState;
import ec.gp.GPIndividual;
import ec.simple.SimpleStatistics;

@SuppressWarnings("serial")
public class SteadyStateStatistics extends SimpleStatistics {

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
			state.output.println("Average size: " + size_avg, statisticslog);
			state.output.message("Average size: " + size_avg);
			
			float fitness_avg = fitness_acumulator / (float) state.population.subpops[subpop_index].individuals.length;
			state.output.println("Average fitness: " + fitness_avg, statisticslog);
			state.output.message("Average fitness: " + fitness_avg);
			
			state.output.println("Best fitness: " + best_individual.fitness.fitness(), statisticslog);
			state.output.message("Best fitness: " + best_individual.fitness.fitness());
			
			state.output.println("Best individual size: " + best_individual.size(), statisticslog);
			state.output.message("Best individual size: " + best_individual.size());
			
			state.output.message("");
		}
	}
	
}
