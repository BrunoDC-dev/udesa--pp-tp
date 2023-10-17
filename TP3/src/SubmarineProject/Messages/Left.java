package SubMarineProject.Messages;
import SubMarineProject.Nemo;
public class  Left extends Message {
    public Left() {
        super('l'); 
    }
    public void Execute(Nemo nemo) {
        nemo.turnLeft();
    }
}
