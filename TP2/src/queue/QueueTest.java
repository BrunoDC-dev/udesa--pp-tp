package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QueueTest {

  @Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( newEmptyQueue().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( newQueueWith( addedObject ).isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( addedObject, newQueueWith( addedObject ).head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = newQueueWith( addedObject );
    queue.take();

    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    assertEquals( addedObject, newQueueWith( addedObject ).take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = newQueueWith( firstAddedObject );
    queue.add( secondAddedObject );

    assertEquals( queue.take(), firstAddedObject );
    assertEquals( queue.take(), secondAddedObject );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    Queue queue = newQueueWith( firstAddedObject );
    queue.add( secondAddedObject );

    assertEquals( queue.head(), firstAddedObject );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = newQueueWith( addedObject );
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, newQueueWith( firstAddedObject ).add( secondAddedObject ).size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    Queue queue = newEmptyQueue();
    assertThrows(Error.class, () -> queue.take(), "Queue is empty");
  }

  @Test public void test11CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = newQueueWith( addedObject );
    queue.take();
    assertThrows(Error.class, () -> queue.take(), "Queue is empty");
  }

  @Test public void test12CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    Queue queue = newEmptyQueue();
    assertThrows(Error.class, () -> queue.head(), "Queue is empty");
  }

  @Test public void test13CanNotHeadWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = newQueueWith( addedObject );
    queue.take();
    assertThrows(Error.class, () -> queue.head(), "Queue is empty");
  }

  private String firstAddedObject = "first";
  private String secondAddedObject = "second";
  private String addedObject = "something";
  
  private Queue newEmptyQueue() {
    return new Queue();
  }

  private Queue newQueueWith(String object) {
    return new Queue().add( object );
  }

}