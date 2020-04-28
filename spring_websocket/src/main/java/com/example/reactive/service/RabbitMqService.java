package com.example.reactive.service;

import com.example.reactive.config.rabbitmq.MessageListenerContainerFactory;
import javax.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

  @Autowired
  private AmqpTemplate amqpTemplate;

  @Autowired
  private AmqpAdmin amqpAdmin;

  @Autowired
  private TopicExchange topicExchange;

  @Autowired
  private MessageListenerContainerFactory messageListenerContainerFactory;

  public String createTopicQueue( String id) {

    Exchange ex = ExchangeBuilder.topicExchange(topicExchange.getName()).durable(true).build();

    amqpAdmin.declareExchange(ex);

    Queue q = new Queue(id);
    amqpAdmin.declareQueue(q);

    Binding b = BindingBuilder.bind(q).to(ex).with(id).noargs();

    amqpAdmin.declareBinding(b);

    return id;
  }

  public String removeTopicQueue(String id) {

    Exchange ex = ExchangeBuilder.topicExchange(topicExchange.getName()).durable(true).build();

    amqpAdmin.declareExchange(ex);

    Queue q = new Queue(id);
    Binding b = BindingBuilder.bind(q).to(ex).with(id).noargs();

    amqpAdmin.removeBinding(b);
    amqpAdmin.deleteQueue(id);

    return id;
  }
}
