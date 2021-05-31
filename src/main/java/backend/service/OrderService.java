package backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import backend.entity.Order;
import backend.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
  	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository){
		this.orderRepository = orderRepository;
	}

	public Order save(Order order) {
		return orderRepository.save(order);
	}

	public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Modifying
	public Optional<Order> update(Order newOrder) {
        Optional<Order> oldOrder = findById(newOrder.getId());

        if (oldOrder.isPresent()) {
            Order savedOrder = save(newOrder);
            return Optional.of(savedOrder);
        } else {
            return oldOrder;
        }
    }

	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	public List<Order> findAllByOrderByOrderedAtDesc() {
		return orderRepository.findAllByOrderByOrderedAtDesc();
	}
}
