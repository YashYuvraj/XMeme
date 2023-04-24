package com.crio.starter.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "memes")
@NoArgsConstructor
@AllArgsConstructor
public class MemesEntity {
    
    private String id;
    private String name;
    private String url;
    private String caption;

    
}
