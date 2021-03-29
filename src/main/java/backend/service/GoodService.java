package backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.entity.Good;
import backend.repository.GoodRepository;

@Service
public class GoodService {
	@Autowired
  	private final GoodRepository goodRepository;

	public Good save(Good good) {
		return goodRepository.save(good);
	}

	public GoodService(GoodRepository goodRepository){
		this.goodRepository = goodRepository;
	}

	public List<Good> findAll() {
		return goodRepository.findAll();
	}

	public List<Good> findByNameContaining(String part) {
		return goodRepository.findByNameContainingIgnoreCase(part);
	}

	public List<Good> findByCompanyContaining(String part) {
		return goodRepository.findByCompanyContainingIgnoreCase(part);
	}

	public List<Good> findByAssemblyPlaceContaining(String part) {
		return goodRepository.findByAssemblyPlaceContainingIgnoreCase(part);
	}

	public List<Good> findByDescriptionContaining(String part) {
		return goodRepository.findByDescriptionContainingIgnoreCase(part);
	}

	public List<Good> findByPriceBetween(Double low, double high) {
		return goodRepository.findByPriceBetween(low, high);
	}
}
