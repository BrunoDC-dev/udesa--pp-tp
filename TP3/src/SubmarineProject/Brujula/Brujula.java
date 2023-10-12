package SubMarineProject.Brujula;

import java.util.Objects;

public abstract class Brujula {
    public String name;
    public abstract Brujula turnRight();
    public abstract Brujula turnLeft();
    public abstract int[] move();
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Brujula)) {
            return false;
        }
        Brujula other = (Brujula) obj;
        return Objects.equals(name, other.name);
    }
}
