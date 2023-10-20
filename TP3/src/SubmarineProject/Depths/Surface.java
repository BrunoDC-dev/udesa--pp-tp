package SubMarineProject.Depths;
public class Surface extends SubmergedLevel{
    public boolean isInSurface(){
        return true;
    }
    public SubmergedLevel emerged(){
        return this;
    }
    public SubmergedLevel submerged(){
        return new Underwater();
    }    
    public int depth(){
        return 0;
    }
}
