package SubMarineProject;

public class Right extends Message {
    public Right() {
        super('r');
    }
    public void Execute(Nemo nemo) {
        nemo.turnRight();
    }
}
