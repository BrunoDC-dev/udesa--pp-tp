package SubMarineProject.Brujula;

/**
 * East
 */
public class East extends Brujula{
    public String name = "East";    
    public Brujula turnRight(){
    return new South();
    }
    public Brujula turnLeft(){
       return new North();
    }
    public int[] move(){
        int[]  coordenadas = {1,0};
        return coordenadas;
    }
    
}