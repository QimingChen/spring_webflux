package com.example.reactive.config.rabbitmq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
  @Bean
  public ConnectionFactory connectionFactory() {
    ConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
    return connectionFactory;
  }

  @Bean
  public AmqpAdmin admin() {
    return new RabbitAdmin(connectionFactory());
  }

  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange("qc-tictactoe-game");
  }

}
