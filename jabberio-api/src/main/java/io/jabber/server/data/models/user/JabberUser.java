package io.jabber.server.data.models.user;

import io.jabber.server.data.models.user.JabberUser.TableMetadata;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * A record of a Jabber IO user.
 */
@Entity
@Table(
    name = "user",
    uniqueConstraints = {
        @UniqueConstraint(name = "app_user_email", columnNames = TableMetadata.EMAIL_COLUMN_NAME)
    }
)
public class JabberUser {

  @Id
  @Column(name = TableMetadata.ID_COLUMN_NAME, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  @Column(
      name = TableMetadata.EMAIL_COLUMN_NAME,
      nullable = false,
      length = TableMetadata.EMAIL_LENGTH
  )
  private String email;
  
  @Column(
      name = TableMetadata.FIRST_NAME_COLUMN_NAME,
      nullable = false,
      length = TableMetadata.FIRST_NAME_LENGTH
  )
  private String firstName;

  @Column(
      name = TableMetadata.SURNAME_COLUMN_NAME,
      nullable = false,
      length = TableMetadata.SURNAME_LENGTH
  )
  private String surname;

  @Column(name = TableMetadata.DATE_OF_BIRTH_COLUMN_NAME, nullable = false)
  private LocalDate dateOfBirth;

  @Column(
      name = TableMetadata.PASSWORD_COLUMN_NAME,
      nullable = false,
      length = TableMetadata.PASSWORD_LENGTH
  )
  private String password;

  @Column(
      name = TableMetadata.PASSWORD_SALT_COLUMN_NAME,
      nullable = false,
      length = TableMetadata.PASSWORD_SALT_LENGTH
  )
  private String passwordSalt;

  @Column(name = TableMetadata.ACCOUNT_CREATED_TIME_COLUMN_NAME, nullable = false)
  private LocalDate accountCreatedTime;

  /**
   * Gets the user's ID.
   * @return The user's ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the user's ID.
   * @param id The user's ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the user's email address.
   * @return The user's email address.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the user's email address.
   * @param email The user's email address.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the user's first name.
   * @return The user's first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the user's first name.
   * @param firstName The user's first name.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the user's surname.
   * @return The user's surname.
   */
  public String getSurname() {
    return surname;
  }

  /**
   * Sets the user's surname.
   * @param surname The user's surname.
   */
  public void setSurname(String surname) {
    this.surname = surname;
  }

  /**
   * Gets the user's date of birth.
   * @return The user's date of birth.
   */
  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * Sets the user's date of birth.
   * @param dateOfBirth The user's date of birth.
   */
  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  /**
   * Gets the user's hashed password.
   * @return The user's hashed password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the user's hashed password.
   * @param password The user's hashed password.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the user's password salt.
   * @return The user's password salt.
   */
  public String getPasswordSalt() {
    return passwordSalt;
  }

  /**
   * Sets the user's password salt.
   * @param passwordSalt The user's password salt.
   */
  public void setPasswordSalt(String passwordSalt) {
    this.passwordSalt = passwordSalt;
  }

  /**
   * Gets the time that the user's account was created.
   * @return The time that the user's account was created.
   */
  public LocalDate getAccountCreatedTime() {
    return accountCreatedTime;
  }

  /**
   * Sets the time that the user's account was created.
   * @param accountCreatedTime The time that the user's account was created.
   */
  public void setAccountCreatedTime(LocalDate accountCreatedTime) {
    this.accountCreatedTime = accountCreatedTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JabberUser that = (JabberUser) o;
    return Objects.equals(email, that.email)
        && Objects.equals(firstName, that.firstName)
        && Objects.equals(surname, that.surname)
        && Objects.equals(dateOfBirth, that.dateOfBirth)
        && Objects.equals(password, that.password)
        && Objects.equals(passwordSalt, that.passwordSalt)
        && Objects.equals(accountCreatedTime, that.accountCreatedTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        email, firstName, surname, dateOfBirth, password, passwordSalt, accountCreatedTime
    );
  }

  /**
   * Provides metadata for the User table.
   */
  public static class TableMetadata {

    public static final String ID_COLUMN_NAME = "user_id";

    public static final String EMAIL_COLUMN_NAME = "email";
    public static final int EMAIL_LENGTH = 64;

    public static final String FIRST_NAME_COLUMN_NAME = "first_name";
    public static final int FIRST_NAME_LENGTH = 32;

    public static final String SURNAME_COLUMN_NAME = "surname";
    public static final int SURNAME_LENGTH = 32;

    public static final String DATE_OF_BIRTH_COLUMN_NAME = "date_of_birth";

    public static final String PASSWORD_COLUMN_NAME = "password";
    public static final int PASSWORD_LENGTH = 128;

    public static final String PASSWORD_SALT_COLUMN_NAME = "password_salt";
    public static final int PASSWORD_SALT_LENGTH = 128;

    public static final String ACCOUNT_CREATED_TIME_COLUMN_NAME = "account_created_time";

    private TableMetadata() {}
  }

}
