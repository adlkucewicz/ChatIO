package io.jabber.server.data.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

class PersistenceExceptionTest {

  @Test
  public void messageConstructor_ValidInput_MessageSet() {
    String message = "Test message";
    assertEquals(message, new PersistenceException(message).getMessage());
  }

  @Test
  public void causeConstructor_ValidInput_CauseSet() {
    Throwable cause = mock(Throwable.class);
    assertEquals(cause, new PersistenceException(cause).getCause());
  }

  @Test
  public void messageAndCauseConstructor_ValidInputs_MessageAndCauseSet() {
    String message = "Test message";
    Throwable cause = mock(Throwable.class);
    Throwable exception = new PersistenceException(message, cause);
    assertEquals(message, exception.getMessage());
    assertEquals(cause, exception.getCause());
  }

}