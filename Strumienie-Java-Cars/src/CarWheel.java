public class CarWheel {
    private String name;
    private int size;
    private TireType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public TireType getType() {
        return type;
    }

    public void setType(TireType type) {
        this.type = type;
    }

    public CarWheel() {
    }

    public CarWheel(String name, int size, TireType type) {
        setSize(size);
        setName(name);
        setType(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarWheel carWheel = (CarWheel) o;

        if (size != carWheel.size) return false;
        if (name != null ? !name.equals(carWheel.name) : carWheel.name != null) return false;
        return type == carWheel.type;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + size;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarWheel{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", type=" + type +
                '}';
    }
}
