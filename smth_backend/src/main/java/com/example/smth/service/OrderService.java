package com.example.smth.service;


import com.example.smth.entities.OrderEntity;
import com.example.smth.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Transactional
    public OrderEntity create(OrderEntity orderEntity) {
        try {
            return orderRepository.save(orderEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public OrderEntity findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }
    public OrderEntity update(Long id, OrderEntity orderEntity) {
        if(orderRepository.existsById(id)) {
            return orderRepository.save(orderEntity);
        }
        else{
            throw new RuntimeException("Order not found");
        }
    }
    public List<OrderEntity> getAllOrders() {

        return orderRepository.findAll();
    }
    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    public boolean deleteOrderById(Long id) {
        if(orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        }
        return true;
    }

}
