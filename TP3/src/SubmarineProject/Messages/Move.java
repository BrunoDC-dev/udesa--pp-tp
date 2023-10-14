package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public class Move extends Message {
    public Move() {
        super('f'); // Set the name in the superclass constructor
    }
    public String getMessage(){
        return "Move";
    }
    public void Execute(Nemo nemo) {
        nemo.move();
    }
}
