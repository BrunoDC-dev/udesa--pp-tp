package queue;

public class EmptyQueue implements QueueState {
    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public String head() {
        throw new IllegalStateException("Queue is empty");
    }

    @Override
    public QueueState take() {
        throw new IllegalStateException("Queue is empty");
    }

    @Override
    public int size() {
        return 0;
    }
}
