package io.jabber.server.service.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserCreationExceptionTest {

  @Test
  public void messageConstructor_ValidInput_MessageSet() {
    String message = "Test message";
    assertEquals(message, new UserCreationException(message).getMessage());
  }

}