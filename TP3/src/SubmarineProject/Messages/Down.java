package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public class Down extends Message {
    public Down() {
        super('d'); // Set the name in the superclass constructor
    }
    public void Execute(Nemo nemo) {
         nemo.moveDown();
    }
}
