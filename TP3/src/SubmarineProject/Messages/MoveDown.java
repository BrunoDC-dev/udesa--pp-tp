package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public class MoveDown extends Message {
    public MoveDown() {
        super('d'); // Set the name in the superclass constructor
    }
    public String getMessage(){
        return "Move Down";
    }
    public void Execute(Nemo nemo) {
         nemo.moveDown();
    }
}
