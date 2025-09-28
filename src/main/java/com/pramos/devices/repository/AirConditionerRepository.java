package com.pramos.devices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pramos.devices.domain.AirConditioner;

@Repository
public interface AirConditionerRepository extends JpaRepository<AirConditioner, Long> {
	
}
