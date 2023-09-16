package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class QueueTest {

  @Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( newQueue().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( newQueue().add( getAddedObject() ).isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( getAddedObject(), newQueue().add( getAddedObject() ).head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = newQueue().add( getAddedObject() );

    queue.take();
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    Queue queue = newQueue();
    queue.add( getAddedObject() );
    assertEquals( getAddedObject(), queue.take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = newQueue();
    
    queue.add( getFirstAddedObject() );
    queue.add( getSecondAddedObject() );

    assertEquals( queue.take(), getFirstAddedObject() );
    assertEquals( queue.take(), getSecondAddedObject() );
    assertTrue( queue.isEmpty() );
  }


  @Test public void test07HeadReturnsFirstAddedObject() {
    Queue queue = newQueue();

    queue.add( getFirstAddedObject() );
    queue.add( getSecondAddedObject() );

    assertEquals( queue.head(), getFirstAddedObject() );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = newQueue();
    queue.add( getAddedObject() );
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, newQueue().add(getFirstAddedObject() ).add( getSecondAddedObject()).size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    Queue queue = newQueue();
    assertThrows(Error.class, () -> queue.take(), "Queue is empty");
  }

  @Test public void test11CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = newQueue();
    queue.add( getAddedObject() );
    queue.take();
    assertThrows(Error.class, () -> queue.take(), "Queue is empty");
  }

  @Test public void test12CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    Queue queue = newQueue();
    assertThrows(Error.class, () -> queue.head(), "Queue is empty");
  }

  private Queue newQueue() {
        return new Queue();
  }
  private String getSecondAddedObject() {
    return "Second";
}

  private String getFirstAddedObject() {
    return "First";
  }

  private String getAddedObject() {
    return "Something";
  }
}