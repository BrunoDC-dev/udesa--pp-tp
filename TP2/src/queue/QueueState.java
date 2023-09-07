package queue;

interface QueueState {
    boolean isEmpty();
    String head();
    QueueState take();
    QueueState add(String element);
    int size();
}
