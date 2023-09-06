package queue;


public class NonEmptyQueue implements QueueState {
    private final String element;
    private final QueueState tail;

    public NonEmptyQueue(String element, QueueState tail) {
        this.element = element;
        this.tail = tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String head() {
        return element;
    }

    @Override
    public QueueState take() {
        return tail;
    }

    @Override
    public int size() {
        return 1 + tail.size();
    }
}
