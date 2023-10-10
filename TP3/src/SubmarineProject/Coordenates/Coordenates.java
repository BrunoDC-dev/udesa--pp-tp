package SubMarineProject.Coordenates;

import java.util.Objects;

public abstract class Coordenates {
    public String name;
    public abstract Coordenates turnRight();
    public abstract Coordenates turnLeft();
    public abstract int[] move();
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Coordenates)) {
            return false;
        }
        Coordenates other = (Coordenates) obj;
        return Objects.equals(name, other.name);
    }
}
