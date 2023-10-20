package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public class Forward extends Message {
    public Forward() {
        super('f'); // Set the name in the superclass constructor
    }
    public void Execute(Nemo nemo) {
        nemo.forward();
    }
}
