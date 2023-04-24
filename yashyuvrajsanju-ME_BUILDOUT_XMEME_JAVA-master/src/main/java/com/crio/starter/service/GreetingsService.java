package com.crio.starter.service;

import lombok.RequiredArgsConstructor;
import com.crio.starter.data.Memes;
import com.crio.starter.data.PostMeme;
import com.crio.starter.exchange.GetMemeResponse;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.GreetingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GreetingsService {

  @Autowired
  private final GreetingsRepository greetingsRepository;

  @Autowired
  private final MemeRepositoryService memeRepositoryService;

  @Autowired
  private MongoTemplate mongoTemplate;

  public ResponseDto getMessage(String id) {
    return new ResponseDto(greetingsRepository.findByExtId(id).getMessage());
  }

  public GetMemeResponse getAllMemes()
  {
     
      return new GetMemeResponse(memeRepositoryService.findAll());
  }

  public String saveMeme(PostMeme postMeme)
  {
    return memeRepositoryService.saveMeme(postMeme);
  }

  public Memes findById(String id)
  {
    return memeRepositoryService.findById(id);
  }
}
