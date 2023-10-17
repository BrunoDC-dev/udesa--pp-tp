package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public class Up extends Message {
    public Up() {
        super('u'); // Set the name in the superclass constructor
    }
    public void Execute(Nemo nemo) {
         nemo.moveUp();
    }
}
