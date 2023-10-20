package SubMarineProject.Depths;

public class Surface extends SubmergedLevel{
    public boolean isInSurface(){
        return true;
    }
    public SubmergedLevel emerge(){
        return this;
    }
    public SubmergedLevel submerge(){
        return new Underwater();
    }    
    public int getDepth(){
        return 0;
    }
}
