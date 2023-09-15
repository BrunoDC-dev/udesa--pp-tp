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
    assertFalse( newQueue().add( "Something" ).isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( "Something", newQueue().add( "Something" ).head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = newQueue().add( "Something" );

    queue.take();
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    Queue queue = newQueue();
    String addedObject = "Something";
    queue.add( addedObject );
    
    assertEquals( addedObject, queue.take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = newQueue();
    String firstAddedObject = "First";
    String secondAddedObject = "Second";
    
    queue.add( firstAddedObject );
    queue.add( secondAddedObject );

    assertEquals( queue.take(), firstAddedObject );
    assertEquals( queue.take(), secondAddedObject );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    Queue queue = newQueue();
    String firstAddedObject = "First";

    queue.add( firstAddedObject );
    queue.add( "Second" );

    assertEquals( queue.head(), firstAddedObject );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = newQueue();
    queue.add( "Something" );
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, newQueue().add( "First" ).add( "Second" ).size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    Queue queue = newQueue();
    /*
    // try {
    //   queue.take();
    //   fail( "Expected Error was not thrown." );
    // } catch (Error e) {
    //   assertTrue( e.getMessage().equals( "Queue is empty" ) );
    // }
    */

    /*
    Throwable exception = assertThrows(Error.class, () -> {
        queue.take();
    });
    assertEquals("Queue is empty", exception.getMessage());
    */

    assertThrows(Error.class, () -> queue.take(), "Queue is empty");
  }

  @Test public void test11CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = newQueue();
    queue.add( "Something" );
    queue.take();
    /*
    // try {
    //   queue.take();
    //   fail( "Expected Error was not thrown." );
    // } catch (Error e) {
    //   assertTrue( e.getMessage().equals( "Queue is empty" ) );
    // }
    */
    
    /*
    Throwable exception = assertThrows(Error.class, () -> {
        queue.take();
    });
    assertEquals("Queue is empty", exception.getMessage());
    */

    assertThrows(Error.class, () -> queue.take(), "Queue is empty");
  }

  @Test public void test12CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    Queue queue = newQueue();

    /*
    // try {
    //   queue.head();
    //   fail( "Expected Error was not thrown." );
    // } catch (Error e) {
    //   assertTrue( e.getMessage().equals( "Queue is empty" ) );
    // }
    */

    /*
    Throwable exception = assertThrows(Error.class, () -> {
        queue.head();
    });
    assertEquals("Queue is empty", exception.getMessage());
    */

    assertThrows(Error.class, () -> queue.head(), "Queue is empty");
  }

  public Queue newQueue() {
        return new Queue();
  }
}