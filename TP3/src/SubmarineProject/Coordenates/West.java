package SubMarineProject.Coordenates;

/**
 * West
 */
public class West extends Coordenates{
    public String name = "West";
    public Coordenates turnRight(){
       return new North();
    }
    public Coordenates turnLeft(){
        return new South();
    }
    public int[] move(){
        int[]  coordenadas = {-1,0};
        return coordenadas;
    }
}