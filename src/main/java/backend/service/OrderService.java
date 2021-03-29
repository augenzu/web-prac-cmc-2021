package backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.entity.Good;
import backend.entity.Order;
import backend.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
  	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository){
		this.orderRepository = orderRepository;
	}

	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	public List<Order> findAllByOrderByOrderedAt() {
		return orderRepository.findAllByOrderByOrderedAt();
	}

	public List<Good> findOrderGoodsById(Long id) {
		return orderRepository.findOrderGoodsById(id);
	}
}
