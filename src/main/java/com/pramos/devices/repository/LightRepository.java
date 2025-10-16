package com.pramos.devices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pramos.devices.domain.Light;

@Repository
public interface LightRepository extends JpaRepository<Light, Long> {
	
	@Modifying
	@Query("UPDATE Light l SET l.isOn = FALSE WHERE l.isOn = TRUE")
	public void updateAllLightToOff();

}
