package SubMarineProject.Depths;

public abstract  class SubmergedLevel {
    public abstract boolean isInSurface();
    public abstract SubmergedLevel emerged();
    public abstract SubmergedLevel submerged();
    public abstract int depth();
    public int liberateCapsule(){
        return 1;
    }
}
