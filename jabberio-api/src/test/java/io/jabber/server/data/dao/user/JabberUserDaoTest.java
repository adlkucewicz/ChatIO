package io.jabber.server.data.dao.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.jabber.server.data.models.user.JabberUser;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

class JabberUserDaoTest {

  private final JabberUserRepository userRepository = mock(JabberUserRepository.class);
  private final JabberUserDao userDao = new JabberUserDao(userRepository);

  @Test
  public void constructor_ValidInput_FieldsSet() {
    assertEquals(JabberUser.class, userDao.getType());
  }

  @Test
  public void constructor_UserRepositoryIsNull_ExceptionThrown() {
    assertThrows(
        NullPointerException.class,
        () -> new JabberUserDao(null)
    );
  }

  @Test
  @SuppressWarnings("unchecked")
  public void findByEmail_UserFound_UserReturned() {
    JabberUser user = mock(JabberUser.class);
    when(userRepository.findOne(any(Specification.class))).thenReturn(Optional.of(user));
    assertEquals(Optional.of(user), userDao.findByEmail("any@email.com"));
  }



}