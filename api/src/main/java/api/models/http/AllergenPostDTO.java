package api.models.http;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AllergenPostDTO {
  @NotNull
  @NotBlank
  private String code;

  @NotNull
  @NotBlank
  private String name;

  public AllergenPostDTO(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return this.code;
  }

  public String getName() {
    return this.name;
  }
}
