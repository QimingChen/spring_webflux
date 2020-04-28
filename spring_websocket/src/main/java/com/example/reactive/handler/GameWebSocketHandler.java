package com.example.reactive.handler;

import com.example.reactive.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GameWebSocketHandler implements WebSocketHandler {

  @Autowired
  GameService gameService;

  @Override
  public Mono<Void> handle(WebSocketSession session) {
    System.out.println(session.getId());

    return session.send(session.receive().map(msg -> {
      String info = msg.getPayloadAsText();
      if ("create".equals(info)) {
        return gameService.createGame();
      } else if (info != null && info.contains("link")) {
        return "linking";
      } else if (info != null && info.contains("send")) {
        return "sending";
      } else if(info !=null && info.contains("delete")){
        return "RECEIVED ON SERVER :: " + info + " " + process(msg.getPayloadAsText());
      }
      return null;
    }).map(session::textMessage));
  }

  private static String process(String msg) {
    System.out.println("'" + msg + "'");
    if ("create".equals(msg)) {
      return "creating";
    } else if (msg != null && msg.contains("link")) {
      return "linking";
    }

    return "not valid";
  }
}
