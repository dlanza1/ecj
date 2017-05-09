package ec;

public interface FitnessWithTIme {

    /**
     * Establish the time the evaluation took
     * 
     * @param time_ms Evaluation time in milliseconds
     */
    public void setEvaluationTime(long time_ms);
    
    /**
     * Get evaluation time in milliseconds
     * 
     * @return Evaluation time in milliseconds
     */
    public long getEvaluationTime();
    
}
