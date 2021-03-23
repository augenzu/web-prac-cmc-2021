package backend.service;

import backend.entity.Status;
import backend.repository.StatusRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatusService {
    @Autowired
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    @Transactional
    public Optional<Status> findById(Long id) {
        return statusRepository.findById(id);
    }

    public List<Status> findAllByOrderByName() {
        return statusRepository.findAllByOrderByName();
    }
}
