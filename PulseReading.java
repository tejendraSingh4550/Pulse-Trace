import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** One pulse measurement stored by PulseTrace. */
public class PulseReading {
    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final int id;
    private String personName;
    private int bpm;
    private LocalDateTime recordedAt;

    public PulseReading(int id, String personName, int bpm) {
        this.id = id;
        this.personName = personName;
        this.bpm = bpm;
        this.recordedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void update(String personName, int bpm) {
        this.personName = personName;
        this.bpm = bpm;
        this.recordedAt = LocalDateTime.now();
    }

    public String toDisplayString() {
        return String.format("%-6d | %-20s | %3d bpm | %-9s | %s",
                id, personName, bpm, PulseCategory.fromBpm(bpm),
                recordedAt.format(TIME_FORMAT));
    }
}
