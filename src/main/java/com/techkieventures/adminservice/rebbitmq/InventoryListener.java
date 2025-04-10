package com.techkieventures.adminservice.rebbitmq;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryListener {

    @Autowired
    private InventoryService inventoryService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void reserveStock(Order order) {
        boolean isStockAvailable = inventoryService.checkStock(order);

        if (isStockAvailable) {
            inventoryService.reserveStock(order);
            System.out.println("Stock Reserved: " + order);
            inventoryService.sendStockReservedEvent(order);
        } else {
            System.out.println("Stock Not Available. Rejecting Order: " + order);
            inventoryService.sendStockRejectedEvent(order);
        }
    }
}
