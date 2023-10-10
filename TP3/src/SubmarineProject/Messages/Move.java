package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public class Move extends Message {
    public String name = "Move";
    public String getMessage(){
        return "Move";
    }
    public Nemo Execute(Nemo nemo) {
        return nemo.move();
    }
}
