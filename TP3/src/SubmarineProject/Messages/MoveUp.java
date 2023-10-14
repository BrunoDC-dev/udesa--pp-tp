package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public class MoveUp extends Message {
    public MoveUp() {
        super('u'); // Set the name in the superclass constructor
    }
    public void Execute(Nemo nemo) {
         nemo.moveUp();
    }
    public String getMessage(){
        return "Move Up";
    }
}
