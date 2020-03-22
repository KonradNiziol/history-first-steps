import java.util.Set;

public class Body {
    private BodyColor color;
    private BodyType type;
    private Set<Equipment> listOfEq;

    public BodyColor getColor() {
        return color;
    }

    public void setColor(BodyColor color) {
        this.color = color;
    }

    public BodyType getType() {
        return type;
    }

    public void setType(BodyType type) {
        this.type = type;
    }

    public Set<Equipment> getListOfEq() {
        return listOfEq;
    }

    public void setListOfEq(Set<Equipment> listOfEq) {
        this.listOfEq = listOfEq;
    }

    public Body() {
    }

    public Body(BodyColor color, BodyType type, Set<Equipment> listOfEq) {
        setType(type);
        setColor(color);
        setListOfEq(listOfEq);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Body body = (Body) o;

        if (color != body.color) return false;
        if (type != body.type) return false;
        return listOfEq == body.listOfEq;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (listOfEq != null ? listOfEq.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Body{" +
                "color=" + color +
                ", type=" + type +
                ", listOfEq=" + listOfEq +
                '}';
    }
}
