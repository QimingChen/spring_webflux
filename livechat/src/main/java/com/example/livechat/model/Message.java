package com.example.livechat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
public class Message {

  @Id
  private String id;
  private String message;
  private String sender;
  private String sendee;
  private String channelId;

  public Message() {
  }

  public Message(String message, String sender, String sendee, String channelId) {
    this.message = message;
    this.sender = sender;
    this.sendee = sendee;
    this.channelId = channelId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getSendee() {
    return sendee;
  }

  public void setSendee(String sendee) {
    this.sendee = sendee;
  }

  public String getChannelId() {
    return channelId;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }
}
