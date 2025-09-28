package com.pramos.devices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pramos.devices.domain.Light;

@Repository
public interface LightRepository extends JpaRepository<Light, Long> {

}
