package backend.service;

import backend.entity.AppType;
import backend.entity.Good;
import backend.repository.GoodRepository;

import java.util.List;
import java.util.Optional;
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
	public Optional<Good> findById(Long id) {
		return goodRepository.findById(id);
	}

	public List<Good> findByAppType(AppType appType) {
		return goodRepository.findByAppType(appType);
	}

	public List<Good> findByCompany(String company) {
		return goodRepository.findByCompany(company);
	}

	public List<Good> findByDescriptionContaining(String descriptionPart) {
		return goodRepository.findByDescriptionContaining(descriptionPart);
	}

	public List<Good> findAllByOrderByName() {
		return goodRepository.findAllByOrderByName();
	}
}
