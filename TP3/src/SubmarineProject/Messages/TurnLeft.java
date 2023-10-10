package SubMarineProject.Messages;
import SubMarineProject.Nemo;
public class  TurnLeft extends Message {
    public String name = "TurnLeft";
    public String getMessage(){
        return "Turn Left";
    }
    public Nemo Execute(Nemo nemo) {
        return nemo.turnLeft();
    }
}
