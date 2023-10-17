package SubMarineProject.Messages;

import SubMarineProject.Nemo;
public class Right extends Message {
    public Right() {
        super('r'); // Set the name in the superclass constructor
    }
    public void Execute(Nemo nemo) {
        nemo.turnRight();
    }
}
