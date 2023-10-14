package SubMarineProject.Messages;
import SubMarineProject.Nemo;
public class  TurnLeft extends Message {
    public TurnLeft() {
        super('l'); // Set the name in the superclass constructor
    }
    public String getMessage(){
        return "Turn Left";
    }
    public void Execute(Nemo nemo) {
        nemo.turnLeft();
    }
}
