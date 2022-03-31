package api.models.http;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDeleteDTO {
  @NotNull
  @NotBlank
  private String id;

  public UserDeleteDTO(String id) {
    this.id = id;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
