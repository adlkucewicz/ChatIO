package io.jabber.server.data.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {

  @Test
  public void forEntityTypeWithId_EntityTypeSet_ExceptionConstructed() {
    Class<String> type = String.class;
    long id = 5L;
    assertEquals(
        "Could not find an entity of type " + type.getSimpleName() + " with ID " + id,
        NotFoundException.forEntityTypeWithId(type, id).getMessage()
    );
  }

  @Test
  public void forEntityTypeWithId_EntityTypeIsNull_ExceptionThrown() {
    assertThrows(
        NullPointerException.class,
        () -> NotFoundException.forEntityTypeWithId(null, 1L)
    );
  }

}