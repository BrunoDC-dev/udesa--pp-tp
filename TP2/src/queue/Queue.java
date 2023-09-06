
    package queue;


public class Queue {
    private QueueState state;

    public Queue() {
        this.state = new EmptyQueue();
    }

    public Queue add(String element) {
        return new Queue(new NonEmptyQueue(element, state));
    }

    private Queue(QueueState state) {
        this.state = state;
    }

    public boolean isEmpty() {
        return state.isEmpty();
    }

    public String head() {
        return state.head();
    }

    public Queue take() {
        return new Queue(state.take());
    }

    public int size() {
        return state.size();
    }
}
