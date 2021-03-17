package backend.service;

import backend.entity.Order;
import backend.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
	@Autowired
  	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository){
		this.orderRepository = orderRepository;
	} 
}
