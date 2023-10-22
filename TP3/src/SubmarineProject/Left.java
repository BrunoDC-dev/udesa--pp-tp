package SubMarineProject;

public class  Left extends Message {
    public Left() {
        super('l'); 
    }
    public void Execute(Nemo nemo) {
        nemo.turnLeft();
    }
}
