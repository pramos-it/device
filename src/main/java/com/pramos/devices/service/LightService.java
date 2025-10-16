package com.pramos.devices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pramos.devices.domain.Light;
import com.pramos.devices.exceptions.LightException;
import com.pramos.devices.repository.LightRepository;

@Service
public class LightService {
	
	@Autowired
	private LightRepository lightRepository;
    
    public List<Light> listAll(){
		return lightRepository.findAll();
	}
	
    @Transactional
	public Light create() {
		Light light = new Light();
		return lightRepository.save(light);
	}	
	
	@Transactional
	public void delete(Long id) {
		Light light = lightRepository.findById(id).orElseThrow(() -> new LightException("Light not found: " + id));
		lightRepository.delete(light);
	}
	
	@Transactional
	public Light toggle(Long id) {
		Light light = lightRepository.findById(id).orElseThrow(() -> new LightException("Light not found: " + id));
		light.toggler();
		return lightRepository.save(light);		
	}
	
	@Transactional
	public void updateLightSystem() {
		lightRepository.updateAllLightToOff();	
	}
}
