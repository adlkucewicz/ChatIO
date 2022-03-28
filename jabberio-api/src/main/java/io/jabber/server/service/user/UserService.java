package io.jabber.server.service.user;

import io.jabber.server.controller.user.dto.JabberUserCreationDto;
import io.jabber.server.data.dao.user.JabberUserDao;
import io.jabber.server.data.models.user.JabberUser;
import java.time.LocalDate;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final JabberUserDao userDao;
  private final PasswordEncoder passwordEncoder;
  private final StringKeyGenerator saltGenerator;

  public UserService(
      JabberUserDao userDao,
      PasswordEncoder passwordEncoder,
      StringKeyGenerator saltGenerator) {
    this.userDao = Objects.requireNonNull(userDao, "User DAO is null");
    this.passwordEncoder = Objects.requireNonNull(passwordEncoder, "Password encoder is null");
    this.saltGenerator = Objects.requireNonNull(saltGenerator, "Salt generator is null");
  }

  @Transactional
  public JabberUser createUser(JabberUserCreationDto rawUser) {
    if (userDao.findByEmail(rawUser.getEmail()).isPresent()) {
      throw UserAlreadyExistsException.forEmail(rawUser.getEmail());
    }

    JabberUser user = new JabberUser();
    user.setEmail(rawUser.getEmail());
    user.setFirstName(rawUser.getFirstName());
    user.setSurname(rawUser.getSurname());
    user.setDateOfBirth(rawUser.getDateOfBirth());
    user.setAccountCreatedTime(LocalDate.now());
    user.setPasswordSalt(saltGenerator.generateKey());
    user.setPassword(
        passwordEncoder.encode(rawUser.getPassword() + user.getPasswordSalt())
    );

    userDao.save(user);

    return user;
  }

}
