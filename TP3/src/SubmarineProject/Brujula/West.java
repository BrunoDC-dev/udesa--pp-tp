package SubMarineProject.Brujula;

/**
 * West
 */
public class West extends Brujula{
    public String name = "West";
    public Brujula turnRight(){
       return new North();
    }
    public Brujula turnLeft(){
        return new South();
    }
    public int[] move(){
        int[]  coordenadas = {-1,0};
        return coordenadas;
    }
}