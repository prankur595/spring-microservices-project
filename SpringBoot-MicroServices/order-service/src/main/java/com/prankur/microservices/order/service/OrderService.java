package com.prankur.microservices.order.service;

import com.prankur.microservices.order.client.InventoryClient;
import com.prankur.microservices.order.dto.OrderRequestDTO;
import com.prankur.microservices.order.dto.OrderResponseDTO;
import com.prankur.microservices.order.events.OrderPlacedEvent;
import com.prankur.microservices.order.exceptions.QuantityNotPresentException;
import com.prankur.microservices.order.model.Order;
import com.prankur.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;

    private final InventoryClient inventoryClient;

    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public OrderResponseDTO placeOrder( OrderRequestDTO orderRequestDTO)
    {
        boolean isProductInStock = inventoryClient.isInStock(orderRequestDTO.skuCode(), orderRequestDTO.quantity());
        if (isProductInStock)
        {
            Order order = Order.builder()
                               .orderNumber(UUID.randomUUID().toString())
                               .skuCode(orderRequestDTO.skuCode())
                               .price(orderRequestDTO.price())
                               .quantity(orderRequestDTO.quantity())
                               .build();

            orderRepository.save(order);
            //Send notification to kafka
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmailId(orderRequestDTO.userdetails().email());
            orderPlacedEvent.setFirstName(orderRequestDTO.userdetails().firstName());
            orderPlacedEvent.setLastName(orderRequestDTO.userdetails().lastName());
            log.info("Started to send the message of order-placed to kafka with emailid: " + orderPlacedEvent.getEmailId());
            kafkaTemplate.send("order-placed",orderPlacedEvent);
            log.info("Sent the message of order-placed to kafka " + orderPlacedEvent);

            return new OrderResponseDTO(order.getOrderNumber(), order.getSkuCode(), order.getPrice(),
                                        order.getQuantity());
        }
        else
        {
            throw new QuantityNotPresentException("Product with skuCode " + orderRequestDTO.skuCode() + " is not present in quantity requested");
        }

    }


    public List<OrderResponseDTO> getAllOrders()
    {
        return orderRepository
                    .findAll()
                    .stream()
                    .map(order -> new OrderResponseDTO(order.getOrderNumber(),order.getSkuCode(),order.getPrice(),order.getQuantity()))
                    .collect(Collectors.toList());
    }
}
