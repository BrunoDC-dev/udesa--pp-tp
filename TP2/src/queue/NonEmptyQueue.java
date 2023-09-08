package queue;

class NonEmptyQueue implements QueueState {
    private final String head;
    private final QueueState tail;

    public NonEmptyQueue(String head, QueueState tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String head() {
        return head;
    }
//pruebaa
    @Override
    public QueueState take() {
        return tail;
    }

    @Override
    public QueueState add(String element) {
        
        return new NonEmptyQueue(head, tail.add(element));
    }  

    @Override
    public int size() {
        return 1 + tail.size();
    }
}