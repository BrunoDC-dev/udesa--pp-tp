package SubMarineProject.Coordenates;

/**
 * East
 */
public class East extends Coordenates{
    public String name = "East";    
    public Coordenates turnRight(){
    return new South();
    }
    public Coordenates turnLeft(){
       return new North();
    }
    public int[] move(){
        int[]  coordenadas = {1,0};
        return coordenadas;
    }
    
}