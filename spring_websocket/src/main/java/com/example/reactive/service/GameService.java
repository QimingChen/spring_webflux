package com.example.reactive.service;

import java.time.Duration;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.TopicProcessor;
import reactor.util.concurrent.Queues;

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

  public Flux<String> events(String id) {
    return Flux.
  }

  private void publishEvent(String queue, String eventMsg) {
    rabbitMqService.sendMessage(queue, eventMsg);
  }

}
