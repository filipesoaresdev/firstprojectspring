package com.firstproject.filipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firstproject.filipe.domain.Demand;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer>{

}
