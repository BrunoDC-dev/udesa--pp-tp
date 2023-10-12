package SubMarineProject;
import java.util.Arrays;

import SubMarineProject.Brujula.Brujula;
import SubMarineProject.Brujula.East;
import SubMarineProject.Coordenadas.Coordenadas;
import SubMarineProject.Messages.Message;


public class Nemo {
    private boolean isInSurface;
    private int height;
    private Brujula direction;
    private int amountOfCapsules;
    private int[] coordenates;
    private Coordenadas coordenadas;

    public Nemo(){
        this.isInSurface = true;
        this.height = 0;
        this.direction = new East();
        this.amountOfCapsules = 0;
        this.coordenadas = new Coordenadas();
    }
    public void recieveMessage(String string){
       // Arrays.stream(messages).forEach(this::execute);

       for (int i = 0; i < =string.length; i++) {
        
       }
    }
    public  Nemo execute (Message message){
       return message.Execute(this);
    }
    public Nemo move(){
        int[] coordenates = this.direction.move();
        return this;
    }
    public Nemo turnRight() {
        this.direction = this.direction.turnRight();
        return this;
    }
    
    public Nemo turnLeft(){
        this.direction = this.direction.turnLeft();
        return this;
    }
    public Nemo moveDown(){        
        this.height -= 1;
        this.isInSurface = false;
        return this;
    }
    public Nemo moveUp(){
        this.height += 1;
        if (getHeight() == 0){
            this.isInSurface = true;
        } 
        return this;
    }
    public  Nemo liberateCapsule(){
        if(getHeight() >=-1){
            this.amountOfCapsules += 1;
        }else{
            throw new RuntimeException("Nemo exploded");
        }
        return this;
    }
    public boolean isInSurface(){
        return this.isInSurface;
    }

    public int getHeight(){
        return this.height;
    }
    public Brujula getDirection(){
        return this.direction;
    }
    public int getAmountOfCapsules(){
        return this.amountOfCapsules;
    }
    public int [] getCoordenadas(){
        return this.coordenadas.getCoordenates();
    }
    public int[] getCoordenates(){
        return this.coordenates;
    }

}
