import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Equipment {
    ELECTRIC_MIRRORS, ELECTRIC_WINDOWS,
    DOOR, ALARM, FOG_LIGHTS, AIR_CONDITIONING, AUDIO,
    SKIN;

    private static final List<Equipment> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Equipment randomEquipment() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
