package com.example.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  @Autowired
  private RabbitMqService rabbitMqService;
  static int count = 0;

  public String createGame(){
    String id = rabbitMqService.createTopicQueue(String.valueOf(count++));
    return "created queue" + id ;
  }

  public String linkToGame(String id){
    return "linked to queue" + id;
  }

  public String deleteGame(String id){
    String deletedId = rabbitMqService.removeTopicQueue(id);
    return "deleted" + id;
  }
}
