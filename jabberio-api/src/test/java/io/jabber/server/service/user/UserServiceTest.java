package io.jabber.server.service.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.jabber.server.controller.user.dto.JabberUserCreationDto;
import io.jabber.server.data.dao.user.JabberUserDao;
import io.jabber.server.data.models.user.JabberUser;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceTest {

  private final JabberUserDao userDao = mock(JabberUserDao.class);
  private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
  private final StringKeyGenerator saltGenerator = mock(StringKeyGenerator.class);
  private final UserService userService = new UserService(userDao, passwordEncoder, saltGenerator);

  @Test
  public void constructor_UserDaoIsNull_ExceptionThrown() {
    assertThrows(
        NullPointerException.class,
        () -> new UserService(null, passwordEncoder, saltGenerator)
    );
  }

  @Test
  public void constructor_PasswordEncoderIsNull_ExceptionThrown() {
    assertThrows(
        NullPointerException.class,
        () -> new UserService(userDao, null, saltGenerator)
    );
  }

  @Test
  public void constructor_SaltGeneratorIsNull_ExceptionThrown() {
    assertThrows(
        NullPointerException.class,
        () -> new UserService(userDao, passwordEncoder, null)
    );
  }

  @Test
  public void createUser_UserAlreadyExists_ExceptionThrown() {
    JabberUserCreationDto rawUser = mock(JabberUserCreationDto.class);
    when(rawUser.getEmail()).thenReturn("user@test.com");
    when(userDao.findByEmail(rawUser.getEmail())).thenReturn(Optional.of(mock(JabberUser.class)));

    assertThrows(
        UserAlreadyExistsException.class, () -> userService.createUser(rawUser)
    );
  }

  @Test
  public void createUser_UserDoesNotAlreadyExist_UserCreated() {
    JabberUserCreationDto rawUser = mock(JabberUserCreationDto.class);
    when(rawUser.getEmail()).thenReturn("user@test.com");
    when(rawUser.getFirstName()).thenReturn("John");
    when(rawUser.getSurname()).thenReturn("Edwards");
    when(rawUser.getDateOfBirth()).thenReturn(LocalDate.of(1996, 4, 12));
    when(rawUser.getPassword()).thenReturn("testPassword");

    when(userDao.findByEmail(rawUser.getEmail())).thenReturn(Optional.empty());

    String salt = "Test salt";
    String encryptedPassword = "Test encrypted password";
    when(saltGenerator.generateKey()).thenReturn(salt);
    when(passwordEncoder.encode(rawUser.getPassword() + salt))
        .thenReturn(encryptedPassword);

    ArgumentCaptor<JabberUser> userCaptor = ArgumentCaptor.forClass(JabberUser.class);

    userService.createUser(rawUser);

    verify(userDao, times(1)).save(userCaptor.capture());

    JabberUser savedUser = userCaptor.getValue();
    assertNotNull(savedUser, "No user saved");
    assertEquals(rawUser.getEmail(), savedUser.getEmail());
    assertEquals(rawUser.getFirstName(), savedUser.getFirstName());
    assertEquals(rawUser.getSurname(), savedUser.getSurname());
    assertEquals(rawUser.getDateOfBirth(), savedUser.getDateOfBirth());
    assertNotNull(savedUser.getAccountCreatedTime());
    assertEquals(salt, savedUser.getPasswordSalt());
    assertEquals(encryptedPassword, savedUser.getPassword());
  }

}