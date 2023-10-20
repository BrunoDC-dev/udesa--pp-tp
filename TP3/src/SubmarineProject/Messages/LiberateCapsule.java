package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public class LiberateCapsule  extends Message{
    public LiberateCapsule() {
        super('m'); 
    }
    public void Execute(Nemo nemo) {
        nemo.liberateCapsule();
    }
}