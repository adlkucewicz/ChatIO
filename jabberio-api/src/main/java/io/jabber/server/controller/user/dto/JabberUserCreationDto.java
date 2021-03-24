package io.jabber.server.controller.user.dto;

import io.jabber.server.data.models.user.JabberUser.TableMetadata;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The DTO that is provided when creating a user account.
 */
public class JabberUserCreationDto {

  @NotNull(message = "Email address is mandatory")
  @Size(
      max = TableMetadata.EMAIL_LENGTH,
      message =
          "The email must be " + TableMetadata.EMAIL_LENGTH + " characters or fewer"
  )
  @Email(message = "Email address must be valid")
  private String email;

  @NotEmpty(message = "First name is mandatory")
  @Size(
      max = TableMetadata.FIRST_NAME_LENGTH,
      message = "First name must be " + TableMetadata.FIRST_NAME_LENGTH + " characters or fewer"
  )
  private String firstName;

  @NotEmpty(message = "Surname is mandatory")
  @Size(
      max = TableMetadata.SURNAME_LENGTH,
      message = "Surname must be " + TableMetadata.SURNAME_LENGTH + " characters or fewer"
  )
  private String surname;

  @NotNull(message = "Date of birth is mandatory")
  private LocalDate dateOfBirth;

  @NotNull(message = "Password is mandatory")
  @Size(
      min = 8,
      max = 64,
      message = "Password must be between 8 and 64 characters in length (inclusive)"
  )
  private String password;

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
   * Gets the user's raw text password.
   * @return The user's raw text password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the user's raw text password.
   * @param password The user's raw text password.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JabberUserCreationDto that = (JabberUserCreationDto) o;
    return Objects.equals(email, that.email)
        && Objects.equals(firstName, that.firstName)
        && Objects.equals(surname, that.surname)
        && Objects.equals(dateOfBirth, that.dateOfBirth)
        && Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, firstName, surname, dateOfBirth, password);
  }
}
