package SubMarineProject;
import java.util.Arrays;

import SubMarineProject.Coordenates.Coordenates;
import SubMarineProject.Coordenates.East;
import SubMarineProject.Coordenates.North;
import SubMarineProject.Coordenates.South;
import SubMarineProject.Coordenates.West;
import SubMarineProject.Messages.Message;


public class Nemo {
    private boolean isInSurface;
    private int height;
    private Coordenates direction;
    private int amountOfCapsules;
    private int[] coordenates;
    private int Xcoord;
    private int Ycoord;

    public Nemo(){
        this.isInSurface = true;
        this.height = 0;
        this.direction = new East();
        this.amountOfCapsules = 0;
        this.Xcoord = 0;
        this.Ycoord = 0;
        this.coordenates = new int[]{Xcoord, Ycoord};
    }
    public void recieveMessage(Message... messages){
       // Arrays.stream(messages).forEach(this::execute);
       Nemo updatedNemo = this; // Start with the current Nemo object
        updatedNemo = Arrays.stream(messages)
            .reduce(updatedNemo, (nemo, message) -> nemo.execute(message), (nemo1, nemo2) -> nemo1);
     // Update the current Nemo object with the updated Nemo object
        this.isInSurface = updatedNemo.isInSurface;
        this.height = updatedNemo.height;
        this.direction = updatedNemo.direction;
        this.amountOfCapsules = updatedNemo.amountOfCapsules;
        this.Xcoord = updatedNemo.Xcoord;
        this.Ycoord = updatedNemo.Ycoord;
        this.coordenates = updatedNemo.coordenates;
    }
    public  Nemo execute (Message message){
       return message.Execute(this);
    }
    public Nemo move(){
        int[] coordenates = this.direction.move();
        this.Xcoord += coordenates[0];
        this.Ycoord += coordenates[1];
        this.coordenates = new int[]{Xcoord, Ycoord};
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
    public Coordenates getDirection(){
        return this.direction;
    }
    public int getAmountOfCapsules(){
        return this.amountOfCapsules;
    }
    public int getXcoord(){
        return this.Xcoord;
    }
    public int getYcoord(){
        return this.Ycoord;
    }
    public int[] getCoordenates(){
        return this.coordenates;
    }

}
