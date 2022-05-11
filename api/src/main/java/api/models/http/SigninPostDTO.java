package api.models.http;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SigninPostDTO {
  @Email
  @NotNull
  @NotBlank
  private String email;

  @NotNull
  @NotBlank
  private String password;

  public SigninPostDTO(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }
}
