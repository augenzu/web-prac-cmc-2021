package backend.service;

import backend.entity.Good;
import backend.repository.GoodRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodService {
	@Autowired
  	private final GoodRepository goodRepository;

	public GoodService(GoodRepository goodRepository){
		this.goodRepository = goodRepository;
	}

	@Transactional
	public List<Good> findAll(){
        return goodRepository.findAll();
    }

	@Transactional
	public List<Good> findAllById(Long id){
		return goodRepository.findAllById(id);
	}
}
