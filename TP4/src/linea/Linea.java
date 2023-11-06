package linea;

public class Linea {
    int width;
    int height;
    char gameType;

    public Linea(int width, int height, char gameType) {
        this.width = width;
        this.height = height;
        this.gameType = gameType;
    }

    public String show() {
        return "Lorem ipsum dolor sit amet";
    }

    public boolean finished() {
        return true;
    }

    public void playRedAt(int position) {
        return;
    }

    public void playBlueAt(int position) {
        return;
    }
}
