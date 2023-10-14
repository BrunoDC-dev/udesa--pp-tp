package SubMarineProject.Height;

public class Height {
    private SubmergedLevel level;
    private int amountOfCapsules = 0 ;
    public Height(){
        level = new Surface();
    }
    public void Emerged(){
        level = level.Emerged();
    }
    public void Submerged(){
        level = level.Submerged();
    }
    public int getHeight(){
        return level.Height();
    }
    public void LiberateCapsule(){
        amountOfCapsules += level.LiberateCapsule();
    }
    public int getAmountOfCapsules(){
        return amountOfCapsules;
    }
    public boolean isInSurface (){
        return level.isInSurface();
    }

}
