package SubMarineProject;

public class LiberateCapsule  extends Message{
    public LiberateCapsule() {
        super('m'); 
    }
    public void Execute(Nemo nemo) {
        nemo.liberateCapsule();
    }
}