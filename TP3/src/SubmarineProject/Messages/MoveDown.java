package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public class MoveDown extends Message {
    public String name = "d";
    public String getMessage(){
        return "Move Down";
    }
    public Nemo Execute(Nemo nemo) {
        return nemo.moveDown();
    }
}
