package com.prankur.microservices.order.controller;

import com.prankur.microservices.order.dto.OrderRequestDTO;
import com.prankur.microservices.order.dto.OrderResponseDTO;
import com.prankur.microservices.order.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
//@RequiredArgsConstructor
@AllArgsConstructor
public class OrderController
{
//    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequestDTO orderRequestDTO)
    {
        System.out.println(orderRequestDTO);
        orderService.placeOrder(orderRequestDTO);
        return "Order Placed Successfully";
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDTO> getAllOrders()
    {
        return orderService.getAllOrders();
    }

}
