package service;

import entity.Good;
import repository.GoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodService {
	@Autowired
  private final GoodRepository goodRepository;

	public GoodService(GoodRepository goodRepository){
		this.goodRepository = goodRepository;
	}
}
