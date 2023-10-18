package SubMarineProject.Height;

public class Height {
    private SubmergedLevel level;

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
         level.LiberateCapsule();
    }
    public boolean isInSurface (){
        return level.isInSurface();
    }

}
