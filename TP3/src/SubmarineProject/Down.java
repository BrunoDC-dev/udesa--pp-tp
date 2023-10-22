package SubMarineProject;

public class Down extends Message {
    public Down() {
        super('d'); 
    }
    public void Execute(Nemo nemo) {
         nemo.moveDown();
    }
}
