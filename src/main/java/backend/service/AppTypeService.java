package backend.service;

import backend.entity.AppType;
import backend.repository.AppTypeRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppTypeService {
    @Autowired
    private final AppTypeRepository appTypeRepository;

    public AppTypeService(AppTypeRepository appTypeRepository){
        this.appTypeRepository = appTypeRepository;
    }

    @Transactional
    public Optional<AppType> findById(Long id) {
        return appTypeRepository.findById(id);
    }

    public List<AppType> findAllByOrderByName() {
        return appTypeRepository.findAllByOrderByName();
    }

    public List<AppType> findAll() {
        return appTypeRepository.findAll();
    }
}
