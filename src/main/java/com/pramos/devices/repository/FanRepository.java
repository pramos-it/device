package com.pramos.devices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pramos.devices.domain.Fan;

@Repository
public interface FanRepository extends JpaRepository<Fan, Long> {
	
}
