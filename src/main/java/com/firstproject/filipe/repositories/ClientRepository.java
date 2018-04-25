package com.firstproject.filipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firstproject.filipe.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
