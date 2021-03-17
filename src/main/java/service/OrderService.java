package service;

import entity.Order;
import repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
  private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository){
		this.orderRepository = orderRepository;
	} 
}
