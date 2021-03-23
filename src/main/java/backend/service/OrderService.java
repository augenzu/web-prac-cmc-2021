package backend.service;

import backend.entity.Order;
import backend.repository.OrderRepository;
import backend.entity.Status;

import java.util.List;
import java.util.Optional;
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

	@Transactional
	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	public List<Order> findByStatus(Status status) {
		return orderRepository.findByStatus(status);
	}

	public List<Order> findAllByOrderByTime() {
		return orderRepository.findAllByOrderByTime();
	}
}
