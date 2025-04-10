package com.techkieventures.adminservice.rebbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Exchange
    public static final String ORDER_EXCHANGE = "order-exchange";

    // Queues
    public static final String ORDER_QUEUE = "order-queue";
    public static final String INVENTORY_QUEUE = "inventory-queue";
    public static final String PAYMENT_QUEUE = "payment-queue";

    // Routing Keys
    public static final String ORDER_CREATED_KEY = "order.created";
    public static final String STOCK_RESERVED_KEY = "stock.reserved";
    public static final String STOCK_REJECTED_KEY = "stock.rejected";

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE, true);
    }

    @Bean
    public Queue inventoryQueue() {
        return new Queue(INVENTORY_QUEUE, true);
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(ORDER_CREATED_KEY);
    }

    @Bean
    public Binding inventoryBinding(Queue inventoryQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(inventoryQueue).to(orderExchange).with(STOCK_RESERVED_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
}
