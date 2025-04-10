package com.techkieventures.adminservice.rebbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final Map<Long, Integer> stockDatabase = new HashMap<>();

    public InventoryService() {
        // Mock stock database (Product ID -> Available Quantity)
        stockDatabase.put(1L, 10);
        stockDatabase.put(2L, 5);
        stockDatabase.put(3L, 0); // No stock available for product 3
    }

    public boolean checkStock(Order order) {
        int availableStock = stockDatabase.getOrDefault(order.getProductId(), 0);
        return availableStock >= order.getQuantity();
    }

    public void reserveStock(Order order) {
        int availableStock = stockDatabase.get(order.getProductId());
        stockDatabase.put(order.getProductId(), availableStock - order.getQuantity());
    }

    public void sendStockReservedEvent(Order order) {
        System.out.println("Stock Reserved for Order: " + order.getOrderId());
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE, RabbitMQConfig.STOCK_RESERVED_KEY, order);
    }

    public void sendStockRejectedEvent(Order order) {
        System.out.println("Stock Rejected for Order: " + order.getOrderId());
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE, RabbitMQConfig.STOCK_REJECTED_KEY, order);
    }
}
