package com.firstproject.filipe.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstproject.filipe.domain.Demand;
import com.firstproject.filipe.repositories.DemandRepository;

@Service
public class DemandService {
	
	@Autowired
	private DemandRepository demandRepository;

	public Demand find(Integer id) {
		Optional<Demand> demand = demandRepository.findById(id);
		return demand.orElse(null);
	}
	
	
}
