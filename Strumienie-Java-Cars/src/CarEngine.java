public class CarEngine {

    private EngineType engineType;
    private double capacity;
    private double combustion;

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getCombustion() {
        return combustion;
    }

    public void setCombustion(double combustion) {
        this.combustion = combustion;
    }

    public CarEngine() {
    }

    public CarEngine(EngineType engineType, double capacity, double combustion) {
        setCapacity(capacity);
        setCombustion(combustion);
        setEngineType(engineType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEngine carEngine = (CarEngine) o;

        if (Double.compare(carEngine.capacity, capacity) != 0) return false;
        if (Double.compare(carEngine.combustion, combustion) != 0) return false;
        return engineType == carEngine.engineType;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = engineType != null ? engineType.hashCode() : 0;
        temp = Double.doubleToLongBits(capacity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(combustion);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "CarEngine{" +
                "engineType=" + engineType +
                ", capacity=" + capacity +
                ", combustion=" + combustion +
                '}';
    }
}
