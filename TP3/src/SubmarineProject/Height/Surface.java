package SubMarineProject.Height;
public class Surface extends SubmergedLevel{
    public boolean isInSurface(){
        return true;
    }
    public SubmergedLevel Emerged(){
        return this;
    }
    public SubmergedLevel Submerged(){
        return new UnderWater(this);
        // return new UnderWater();
    }    
    public int Height(){
        return 0;
    }
}
