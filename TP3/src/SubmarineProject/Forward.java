package SubMarineProject;

public class Forward extends Message {
    public Forward() {
        super('f'); 
    }
    public void Execute(Nemo nemo) {
        nemo.forward();
    }
}
