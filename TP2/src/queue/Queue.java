package queue;

public class Queue {
    private QueueState state;

    public Queue() {
        this.state = new EmptyQueue();
    }

    public Queue add(String element) {
        this.state = this.state.add(element);
        return this;
    }

    public boolean isEmpty() {
        return state.isEmpty();
    }

    public String head() {
        return state.head();
    }

    public String take() {
        /* Takes the first element of the queue */

        String head = state.head();
        this.state = state.take();
        return head;
    }

    public int size() {
        return state.size();
    }
}
