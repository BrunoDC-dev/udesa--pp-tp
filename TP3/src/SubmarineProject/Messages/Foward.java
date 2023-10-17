package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public class Foward extends Message {
    public Foward() {
        super('f'); // Set the name in the superclass constructor
    }
    public void Execute(Nemo nemo) {
        nemo.foward();
    }
}
