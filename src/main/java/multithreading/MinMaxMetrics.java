package multithreading;

public class MinMaxMetrics {

    // Add all necessary member variables
    private long minValue;
    private long maxValue;

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics(long min, long max) {
        // Add code here
        this.minValue = Long.MIN_VALUE;
        this.maxValue = Long.MAX_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public void addSample(long newSample) {
        // Add code here
        synchronized (this) {
            this.minValue = Math.min(newSample, this.minValue);
            this.maxValue = Math.min(newSample, this.maxValue);
        }
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        // Add code here
        return minValue;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        // Add code here
        return maxValue;
    }

}
