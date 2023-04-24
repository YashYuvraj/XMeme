package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "greetings")
@NoArgsConstructor
public class GreetingsEntity {
  @Id
  private String id;

  private String extId;

  private String message;

}
