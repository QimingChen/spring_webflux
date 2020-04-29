package com.example.reactive.controller;

import com.example.reactive.service.GameService;
import java.time.Duration;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/games")
public class GameController {

  @Autowired
  private GameService gameService;

  @PostMapping("/")
  public String createGame(){
    return gameService.createGame();
  }

  @GetMapping("/events/{id}")
  public Flux<String> receiveGameEvents(String id){
    return gameService.events(id);
  }

  @GetMapping("/events/test")
  public Flux<String> fluxtest(){
    return Flux.<String>generate(movieEventSynchronousSink -> {
      String[] theatres = { "PVR", "Gopalan", "Rex", "Big Cinemas", "Multiplex", "Innovative", "Cinepolis" };
      int idx = new Random().nextInt(theatres.length);
      String random = (theatres[idx]);
      movieEventSynchronousSink.next(random);
    }).delayElements(Duration.ofSeconds(5));
  }
}
