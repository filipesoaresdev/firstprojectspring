package com.firstproject.filipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firstproject.filipe.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
