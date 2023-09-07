package queue;
class EmptyQueue implements QueueState {
    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public String head() {
        throw new Error("Queue is empty");
    }

    @Override
    public QueueState take() {
        throw new Error("Queue is empty");
    }

    @Override
    public QueueState add(String element) {
        return new NonEmptyQueue(element, this);
    }

    @Override
    public int size() {
        return 0;
    }
}
