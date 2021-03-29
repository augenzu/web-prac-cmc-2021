package backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.entity.AppType;
import backend.entity.Good;
import backend.repository.AppTypeRepository;

@Service
public class AppTypeService {
    @Autowired
    private final AppTypeRepository appTypeRepository;

    public AppTypeService(AppTypeRepository appTypeRepository){
        this.appTypeRepository = appTypeRepository;
    }

    public Optional<AppType> findByName(String name) {
        return appTypeRepository.findByName(name);
    }

    public List<AppType> findAllByOrderByName() {
        return appTypeRepository.findAllByOrderByName();
    }

    public List<Good> findGoodsByAppTypeName(String appTypeName) {
        return appTypeRepository.findGoodsByAppTypeName(appTypeName);
    }
}
