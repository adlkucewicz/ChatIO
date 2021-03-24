package io.jabber.server.controller.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void create_UserCanOnlyBeCreatedOnce_UserCreatedFirstTimeOnly() throws Exception {
    String userJson = "{"
        + "\"email\": \"user@test.com\","
        + "\"firstName\": \"John\","
        + "\"surname\": \"Allen\","
        + "\"dateOfBirth\": \"1996-04-16\","
        + "\"password\": \"password01\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isOk());

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_EmailMissing_BadRequest() throws Exception {
    String userJson = "{"
        + "\"firstName\": \"John\","
        + "\"surname\": \"Allen\","
        + "\"dateOfBirth\": \"1996-04-16\","
        + "\"password\": \"password01\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_EmailTooLong_BadRequest() throws Exception {
    String userJson = "{"
        + "\"email\": \"" + "a".repeat(56) + "@test.com\","
        + "\"firstName\": \"John\","
        + "\"surname\": \"Allen\","
        + "\"dateOfBirth\": \"1996-04-16\","
        + "\"password\": \"password01\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_EmailInvalid_BadRequest() throws Exception {
    String userJson = "{"
        + "\"email\": \"Clearly not an email address\","
        + "\"firstName\": \"John\","
        + "\"surname\": \"Allen\","
        + "\"dateOfBirth\": \"1996-04-16\","
        + "\"password\": \"password01\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_FirstNameMissing_BadRequest() throws Exception {
    String userJson = "{"
        + "\"email\": \"firstnamemissing@test.com\","
        + "\"surname\": \"Allen\","
        + "\"dateOfBirth\": \"1996-04-16\","
        + "\"password\": \"password01\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_FirstNameEmpty_BadRequest() throws Exception {
    String userJson = "{"
        + "\"email\": \"firstnameempty@test.com\","
        + "\"firstName\": \"\","
        + "\"surname\": \"Allen\","
        + "\"dateOfBirth\": \"1996-04-16\","
        + "\"password\": \"password01\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_FirstNameTooLong_BadRequest() throws Exception {
    String userJson = "{"
        + "\"email\": \"firstnametoolong@test.com\","
        + "\"firstName\": \"" + "a".repeat(33) + "\","
        + "\"surname\": \"Allen\","
        + "\"dateOfBirth\": \"1996-04-16\","
        + "\"password\": \"password01\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_SurnameMissing_BadRequest() throws Exception {
    String userJson = "{"
        + "\"email\": \"surnamemissing@test.com\","
        + "\"firstName\": \"John\","
        + "\"dateOfBirth\": \"1996-04-16\","
        + "\"password\": \"password01\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_SurnameEmpty_BadRequest() throws Exception {
    String userJson = "{"
        + "\"email\": \"surnameempty@test.com\","
        + "\"firstName\": \"John\","
        + "\"surname\": \"\","
        + "\"dateOfBirth\": \"1996-04-16\","
        + "\"password\": \"password01\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_SurnameTooLong_BadRequest() throws Exception {
    String userJson = "{"
        + "\"email\": \"surnametoolong@test.com\","
        + "\"firstName\": \"John\","
        + "\"surname\": \"" + "a".repeat(33) + "\","
        + "\"dateOfBirth\": \"1996-04-16\","
        + "\"password\": \"password01\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_DateOfBirthMissing_BadRequest() throws Exception {
    String userJson = "{"
        + "\"email\": \"dateofbirthmissing@test.com\","
        + "\"firstName\": \"John\","
        + "\"surname\": \"Allen\","
        + "\"password\": \"password01\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_DateOfBirthInvalid_BadRequest() throws Exception {
    String userJson = "{"
        + "\"email\": \"dateofbirthinvalid@test.com\","
        + "\"firstName\": \"John\","
        + "\"surname\": \"Allen\","
        + "\"dateOfBirth\": \"Not a date\","
        + "\"password\": \"password01\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_PasswordMissing_BadRequest() throws Exception {
    String userJson = "{"
        + "\"email\": \"passwordmissing@test.com\","
        + "\"firstName\": \"John\","
        + "\"surname\": \"Allen\","
        + "\"dateOfBirth\": \"1996-04-16\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_PasswordTooShort_BadRequest() throws Exception {
    String userJson = "{"
        + "\"email\": \"passwordtooshort@test.com\","
        + "\"firstName\": \"John\","
        + "\"surname\": \"Allen\","
        + "\"dateOfBirth\": \"1996-04-16\","
        + "\"password\": \"swallow\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void create_PasswordTooLong_BadRequest() throws Exception {
    String userJson = "{"
        + "\"email\": \"passwordtoolong@test.com\","
        + "\"firstName\": \"John\","
        + "\"surname\": \"Allen\","
        + "\"dateOfBirth\": \"1996-04-16\","
        + "\"password\": \"" + "a".repeat(65) + "\""
        + "}";

    mockMvc.perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    ).andExpect(status().isBadRequest());
  }

}