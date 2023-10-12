package SubMarineProject.Messages;

import SubMarineProject.Nemo;
/**
 * LiberateCapsule
 */
public class LiberateCapsule  extends Message{

    public String name = "m";
    public String getMessage(){
        return "Liberate Capsule";
    }
    public Nemo Execute(Nemo nemo) {
        return nemo.liberateCapsule();
    }
}