package ec.gp.koza;

import ec.FitnessWithTime;

public class KozaFitnessWithTime extends KozaFitness implements FitnessWithTime {

    private static final long serialVersionUID = -1324141958798858247L;
    
    private long evaluationTime;

    public void setEvaluationTime(long time_ms) {
        this.evaluationTime = time_ms;
    }

    public long getEvaluationTime() {
        return evaluationTime;
    }

}
