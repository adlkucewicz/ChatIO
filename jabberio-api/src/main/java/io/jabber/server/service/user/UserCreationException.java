package io.jabber.server.service.user;

/**
 * Specifies that a user cannot be created.
 */
public class UserCreationException extends RuntimeException {

  /**
   * Creates a new exception to specify that a user cannot be created.
   * @param message The exception message.
   */
  public UserCreationException(String message) {
    super(message);
  }

}
