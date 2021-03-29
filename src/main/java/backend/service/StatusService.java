package backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.entity.Order;
import backend.entity.Status;
import backend.repository.StatusRepository;

@Service
public class StatusService {
    @Autowired
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    public Optional<Status> findByName(String name) {
        return statusRepository.findByName(name);
    }
    
    public List<Status> findAllByOrderByName() {
        return statusRepository.findAllByOrderByName();
    }

    public List<Order> findOrdersByStatusName(String statusName) {
        return statusRepository.findOrdersByStatusName(statusName);
    }
}
