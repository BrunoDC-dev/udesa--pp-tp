package SubMarineProject.Messages;

import SubMarineProject.Nemo;
/**
 * LiberateCapsule
 */
public class LiberateCapsule  extends Message{
    public LiberateCapsule() {
        super('m'); // Set the name in the superclass constructor
    }
    public void Execute(Nemo nemo) {
        nemo.liberateCapsule();
    }
}