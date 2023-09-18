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
    assertFalse( newQueueWith( "something" ).isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( "something", newQueueWith( "something" ).head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = newQueueWith( "something" );
    queue.take();
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    assertEquals( "something", newQueueWith( "something" ).take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = newQueueWith( "first" ).add( "second" );
    assertEquals( queue.take(), "first" );
    assertEquals( queue.take(), "second" );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    Queue queue = newQueueWith( "first" ).add( "second" );
    assertEquals( queue.head(), "first" );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = newQueueWith( "something" );
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, newQueueWith( "first" ).add( "second" ).size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    Queue queue = newEmptyQueue();
    assertThrows(Error.class, () -> queue.take(),"Queue is empty");
  }

  @Test public void test11CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = newQueueWith( "something" );
    queue.take();
    assertThrows(Error.class, () -> queue.take(),"Queue is empty");
  }

  @Test public void test12CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    Queue queue = newEmptyQueue();
    assertThrows(Error.class, () -> queue.head(),"Queue is empty");
  }

  @Test public void test13CanNotHeadWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = newQueueWith( "something" );
    queue.take();
    assertThrows(Error.class, () -> queue.head(),"Queue is empty");
  }
  
  private Queue newEmptyQueue() {
    return new Queue();
  }

  private Queue newQueueWith(String element) {
    return new Queue().add( element );
  }

}