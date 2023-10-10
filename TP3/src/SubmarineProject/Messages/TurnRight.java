package SubMarineProject.Messages;

import SubMarineProject.Nemo;
public class TurnRight extends Message {
    public String name = "TurnRight";
    public String getMessage(){
        return "Turn Right";
    }
    public Nemo Execute(Nemo nemo) {
        return nemo.turnRight();
    }
}
