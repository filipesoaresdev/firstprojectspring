package com.firstproject.filipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firstproject.filipe.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
