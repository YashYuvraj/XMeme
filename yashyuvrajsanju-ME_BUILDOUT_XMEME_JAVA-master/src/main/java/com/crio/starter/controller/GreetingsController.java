package com.crio.starter.controller;

import com.crio.starter.data.Memes;
import com.crio.starter.data.PostMeme;
import com.crio.starter.exchange.GetMemeResponse;
import com.crio.starter.exchange.PostMemeResponse;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.GreetingsService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GreetingsController {

  @Autowired
  private final GreetingsService greetingsService;

  @GetMapping("/say-hello")
  public ResponseDto sayHello(@RequestParam String messageId) {
    return greetingsService.getMessage(messageId);
  }

  @GetMapping("/memes/")
  public List<Memes> getMemes()
  {
    GetMemeResponse getMemeResponse;
    getMemeResponse=greetingsService.getAllMemes();
    return getMemeResponse.getMemes();
  }

  @PostMapping("/memes/")
  public ResponseEntity<PostMemeResponse> postMemes(@RequestBody PostMeme postMeme)
  {
    if(postMeme.getCaption()==null||postMeme.getName()==null||postMeme.getUrl()==null)
    return new ResponseEntity<PostMemeResponse>(HttpStatus.BAD_REQUEST);
    
    PostMemeResponse postMemeResponse=new PostMemeResponse(greetingsService.saveMeme(postMeme));
    if(postMemeResponse == null||postMemeResponse.getId()==null){
      return new ResponseEntity<PostMemeResponse>(HttpStatus.CONFLICT);
  }
     return ResponseEntity.ok().body(postMemeResponse);
  }

  @GetMapping("/memes/{id}")
  public ResponseEntity<Memes> getMemesById(@PathVariable("id") String id)
  {
    Memes memes=greetingsService.findById(id);
    if(memes==null)
    return ResponseEntity.badRequest().body(null);
     return ResponseEntity.ok().body(memes);
  }

}
