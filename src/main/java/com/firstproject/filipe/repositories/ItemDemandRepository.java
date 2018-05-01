package com.firstproject.filipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firstproject.filipe.domain.ItemDemand;

@Repository
public interface ItemDemandRepository extends JpaRepository<ItemDemand, Integer>{

}
