package com.crio.starter.service;

import java.util.ArrayList;
import java.util.List;

import com.crio.starter.data.Memes;
import com.crio.starter.data.MemesEntity;
import com.crio.starter.data.PostMeme;
import com.crio.starter.repository.MemesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MemeRepositoryService {
   
    @Autowired
    private MemesRepository memesRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    Integer i=1;

    public List<Memes> findAll()
    {
        List<Memes> list=new ArrayList<>();
        List<MemesEntity>  memesEntity=memesRepository.findAll();


        if(memesEntity.size()>100)
        {
            for(int i=memesEntity.size()-1;i>=memesEntity.size()-100;i--)
            {
                MemesEntity m=memesEntity.get(i);
             Memes obj=new Memes(m.getId(),m.getName(),m.getUrl(),m.getCaption());
             list.add(obj);
            }
        }
        else
        {
        for(MemesEntity m:memesEntity)
        {
            Memes obj=new Memes(m.getId(),m.getName(),m.getUrl(),m.getCaption());
            list.add(obj);
        }
    }
        return list;
    }

    public String saveMeme(PostMeme postMeme)
    {
        if(memesRepository.findByName(postMeme.getName())!=null)
        return null;
        MemesEntity m=new MemesEntity(String.valueOf(i++),postMeme.getName(),postMeme.getUrl(),postMeme.getCaption());
        memesRepository.save(m);
        return String.valueOf(i-1);
    }

    public Memes findById(String id)
    {
     /*  MemesEntity me=memesRepository.findByUniqueId(id);
      return new Memes(me.getUniqueId(),me.getName(),me.getUrl(),me.getCaption());*/
      List<MemesEntity>  memesEntity=memesRepository.findAll();
      for(MemesEntity m:memesEntity)
      {
        if(m.getId().equals(id))
          return new Memes(m.getId(),m.getName(),m.getUrl(),m.getCaption());
      }
      return null;
    }
}
