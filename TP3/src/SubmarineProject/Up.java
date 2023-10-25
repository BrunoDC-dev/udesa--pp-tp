package SubMarineProject;

public class Up extends Message {
    public Up() {
        super('u'); 
    }
    public void Execute(Nemo nemo) {
         nemo.moveUp();
    }
}
