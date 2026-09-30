/** Pulse range assigned to a reading. */
public enum PulseCategory {
    LOW("Low", 0, 59),
    NORMAL("Normal", 60, 100),
    ELEVATED("Elevated", 101, 120),
    HIGH("High", 121, Integer.MAX_VALUE);

    private final String label;
    private final int minimumBpm;
    private final int maximumBpm;

    PulseCategory(String label, int minimumBpm, int maximumBpm) {
        this.label = label;
        this.minimumBpm = minimumBpm;
        this.maximumBpm = maximumBpm;
    }

    public static PulseCategory fromBpm(int bpm) {
        for (PulseCategory category : values()) {
            if (bpm >= category.minimumBpm && bpm <= category.maximumBpm) {
                return category;
            }
        }
        throw new IllegalArgumentException("BPM must be zero or greater.");
    }

    @Override
    public String toString() {
        return label;
    }
}
