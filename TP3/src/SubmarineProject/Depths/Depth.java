package SubMarineProject.Depths;

public class Depth {
    private SubmergedLevel level;

    public Depth(){
        level = new Surface();
    }
    public void Emerged(){
        level = level.emerged();
    }
    public void Submerged(){
        level = level.submerged();
    }
    public int getDepthLevel(){
        return level.depth();
    }
    public void LiberateCapsule(){
         level.liberateCapsule();
    }
    public boolean isInSurface (){
        return level.isInSurface();
    }

}
