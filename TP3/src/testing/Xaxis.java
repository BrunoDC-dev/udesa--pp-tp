package testing;

public class Xaxis extends Nemo {
    private String direction = "positive";
    private int value = 0;

    public void moveForward() {
        if (direction == "positive") {
            value++;
        } else {
            value--;
        }
    }
}
