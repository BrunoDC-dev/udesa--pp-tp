package SubMarineProject;

public abstract  class SubmergedLevel {
    public abstract boolean isInSurface();
    public abstract SubmergedLevel emerge();
    public abstract SubmergedLevel submerge();
    public abstract int getDepth();
    public int liberateCapsule(){
        return 1;
    }
}
