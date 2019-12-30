package com.itcast.springboot_rabbitmq_producer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    //交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "item_topic_exchange";

    //队列名称
    public static final String ITEM_QUEUE = "item_queue";

    @Bean("itemTopicExchange")
    public Exchange getExchang() {
        Exchange exchang = ExchangeBuilder.topicExchange(ITEM_TOPIC_EXCHANGE).durable(true).build();
        return exchang;
    }

    @Bean("itemQueue")
    public Queue getQueue() {
        Queue queue = QueueBuilder.durable(ITEM_QUEUE).build();
        return queue;
    }

    @Bean
    public Binding getBinding(@Qualifier("itemTopicExchange") Exchange exchange, @Qualifier("itemQueue") Queue queue) {
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
        return binding;
    }
}
