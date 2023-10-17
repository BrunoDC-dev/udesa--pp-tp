package SubMarineProject.Messages;
import SubMarineProject.Nemo;
public class  Left extends Message {
    public Left() {
        super('l'); // Set the name in the superclass constructor
    }
    public void Execute(Nemo nemo) {
        nemo.turnLeft();
    }
}
