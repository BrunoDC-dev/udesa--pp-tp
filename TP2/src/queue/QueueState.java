package queue;

public interface QueueState {
    boolean isEmpty();
    String head();
    QueueState take();
    int size();
}
