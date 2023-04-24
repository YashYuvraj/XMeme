package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.crio.starter.data.Memes;
import com.crio.starter.data.MemesEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetMemeResponse {
    List<Memes> memes;
}
