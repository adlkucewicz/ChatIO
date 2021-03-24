package io.jabber.server.data.dao.user;

import io.jabber.server.data.dao.common.EntityDao;
import io.jabber.server.data.models.user.JabberUser;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class JabberUserDao extends EntityDao<JabberUser> {

  private final JabberUserRepository userRepository;

  public JabberUserDao(JabberUserRepository userRepository) throws NullPointerException {
    super(JabberUser.class);
    this.userRepository = Objects.requireNonNull(userRepository, "User repository is null");
  }

  /**
   * Attempts to find the user with the specified email address.
   * @param email The email of the user.
   * @return The found user, or an empty optional if no user exists with that email address.
   */
  public Optional<JabberUser> findByEmail(String email) {
    return userRepository.findOne(hasEmail(email));
  }

  private static Specification<JabberUser> hasEmail(String email) {
    return (user, cq, cb) -> cb.equal(user.get("email"), email);
  }

}
