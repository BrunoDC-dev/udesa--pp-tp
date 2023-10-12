package SubMarineProject.Height;
import SubMarineProject.Height.SubmergedLevel;
import SubMarineProject.Height.UnderWater;

public class Surface extends SubmergedLevel{
  public boolean isInSurface(){
    return true;
  }
    public SubmergedLevel Emerged(){
        return this;
    }
    public SubmergedLevel Submerged(){
        return new UnderWater(this);
    }    
    public int Height(){
        return 0;
    }
}
