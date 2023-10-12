package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public class MoveUp extends Message {
    public String name = "u";
    public Nemo Execute(Nemo nemo) {
        return nemo.moveUp();
    }
    public String getMessage(){
        return "Move Up";
    }
}
