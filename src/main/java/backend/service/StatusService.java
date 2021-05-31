package backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import backend.entity.Status;
import backend.repository.StatusRepository;

@Service
public class StatusService {
    @Autowired
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    public Status save(Status status) {
        return statusRepository.save(status);
    }

    public void delete(Status status) {
        statusRepository.delete(status);
    }

    @Modifying
    public Optional<Status> update(Status newStatus) {
        Optional<Status> oldStatus = findByName(newStatus.getName());

        if (oldStatus.isPresent()) {
            Status savedStatus = save(newStatus);
            return Optional.of(savedStatus);
        } else {
            return oldStatus;
        }
    }

    public Optional<Status> findByName(String name) {
        return statusRepository.findByName(name);
    }

    public Status processing() {
        return findByName("processing").get();
    }

    public Status complete() {
        return findByName("complete").get();
    }

    public Status delivered() {
        return findByName("delivered").get();
    }
}
