package SubMarineProject;
import SubMarineProject.Brujula.Brujula;
import SubMarineProject.Brujula.East;
import SubMarineProject.Coordenadas.Coordenadas;
import SubMarineProject.Height.Height;
import SubMarineProject.Messages.LiberateCapsule;
import SubMarineProject.Messages.Message;
import SubMarineProject.Messages.Move;
import SubMarineProject.Messages.MoveDown;
import SubMarineProject.Messages.MoveUp;
import SubMarineProject.Messages.TurnLeft;
import SubMarineProject.Messages.TurnRight;

import java.util.ArrayList;
import java.util.List;

public class Nemo {
    private Brujula direction;
    private Coordenadas coordenadas;
    private Height height;
    private List<Message> possibleMessages = new ArrayList<>();
    

    public Nemo(){
        this.direction = new East();
        this.coordenadas = new Coordenadas(); 
        this.height = new Height();
        this.possibleMessages.add(new Move());
        this.possibleMessages.add(new MoveDown());
        this.possibleMessages.add(new MoveUp());
        this.possibleMessages.add(new TurnLeft());
        this.possibleMessages.add(new TurnRight());
        this.possibleMessages.add(new LiberateCapsule());
    }
    public void recieveMessage(String string) {
        string.chars()
              .mapToObj(letter -> (char) letter)
              .flatMap(letter -> possibleMessages.stream().filter(message -> message.applies(letter)))
              .forEach(message-> message.Execute(this));
    }
    public  void execute (Message message){
       message.Execute(this);
    }
    public void move(){
         this.direction.move(this);
    }
    public Nemo turnRight() {
        this.direction = this.direction.turnRight();
        return this;
    }
    
    public Nemo turnLeft(){
        this.direction = this.direction.turnLeft();
        return this;
    }
    public void moveDown(){        
        this.height.Submerged();
    }
    public void moveUp(){
        this.height.Emerged();
    }
    public  void liberateCapsule(){
        height.LiberateCapsule();
    }
    public boolean isInSurface(){
        return height.isInSurface();
    }

    public int getHeight(){
        return this.height.getHeight();
    }
    public Brujula getDirection(){
        return this.direction;
    }
    public int getAmountOfCapsules(){
        return this.height.getAmountOfCapsules();
    }
    public int [] getCoordenadas(){
        return this.coordenadas.getCoordenates();
    }
    public void fowardInX (){
        this.coordenadas.addXcoord();
    }
    public void backInX (){
        this.coordenadas.minusXcordo();
    }
    public void fowardInY (){
        this.coordenadas.addYcoord();
    }
    public void backIny (){
        this.coordenadas.minusYcoord();
    }
    public int getXcoord (){
        return this.coordenadas.getXcoord();
    }
    public int getYcoord (){
        return this.coordenadas.getYcoord();
    }
}
