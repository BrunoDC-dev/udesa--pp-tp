package SubMarineProject.Messages;

import SubMarineProject.Nemo;
public class TurnRight extends Message {
    public TurnRight() {
        super('r'); // Set the name in the superclass constructor
    }
    public String getMessage(){
        return "Turn Right";
    }
    public void Execute(Nemo nemo) {
        nemo.turnRight();
    }
}
