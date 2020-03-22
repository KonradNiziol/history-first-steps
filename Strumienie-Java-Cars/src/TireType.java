import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum TireType {
    WINTER,SUMMER;

    private static final List<TireType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static TireType randomTireType() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
