import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum EngineType {
    DIESEL,GASOLINE;

    private static final List<EngineType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static EngineType randomTEngineType() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
