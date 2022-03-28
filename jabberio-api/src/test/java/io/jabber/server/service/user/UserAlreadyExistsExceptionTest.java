package io.jabber.server.service.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserAlreadyExistsExceptionTest {

  @Test
  public void forEmail_EmailPresent_ExceptionCreated() {
    String email = "user@test.com";
    assertEquals(
        "A user already exists with email " + email,
        UserAlreadyExistsException.forEmail(email).getMessage()
    );
  }

}