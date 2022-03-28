package api.services;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class JsonService {
  private ObjectMapper objectMapper;

  @PostConstruct
  private void initObjectMapper() {
    this.objectMapper = new ObjectMapper();
  }

  public String stringify(String key, Object message) throws JsonProcessingException {
    return ("{" + this.objectMapper.writeValueAsString(key) + " : " + this.objectMapper.writeValueAsString(message)
        + "}");
  }

}
