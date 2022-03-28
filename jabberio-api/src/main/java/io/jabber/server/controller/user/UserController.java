package io.jabber.server.controller.user;

import io.jabber.server.controller.user.dto.JabberUserCreationDto;
import io.jabber.server.service.user.UserService;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing user entities.
 */
@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) throws NullPointerException {
    this.userService = Objects.requireNonNull(userService, "User service is null");
  }

  /**
   * Creates a new user.
   * @param user The new user to create.
   */
  @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void create(@Valid @RequestBody JabberUserCreationDto user) {
    userService.createUser(user);
  }

}
