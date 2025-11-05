package com.pramos.devices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pramos.devices.domain.Light;
import com.pramos.devices.dto.LightDto;
import com.pramos.devices.exceptions.LightException;
import com.pramos.devices.repository.LightRepository;

@Service
public class LightService {
	
	@Autowired
	private LightRepository lightRepository;
    
    public List<LightDto> listAll(){
    	List<LightDto> listAllDto = new ArrayList<>();
    	
    	List<Light> listAll = lightRepository.findAll();
    	if(listAll.isEmpty()) {
    		return listAllDto;
    	} 
    	
    	listAllDto = listAll.stream().map(light -> new LightDto(light.getId(), light.isOn())).collect(Collectors.toList());
    	return listAllDto;
	}
	
    @Transactional
	public LightDto create() {
		Light light = new Light();
		light = lightRepository.save(light);
		LightDto lightDto = new LightDto(light.getId(), light.isOn());
		return lightDto;
	}	
	
	@Transactional
	public void delete(Long id) {
		Light light = lightRepository.findById(id).orElseThrow(() -> new LightException("Light not found: " + id));
		lightRepository.delete(light);
	}
	
	@Transactional
	public LightDto toggle(Long id) {
		Light light = lightRepository.findById(id).orElseThrow(() -> new LightException("Light not found: " + id));
		light.toggler();
		light = lightRepository.save(light);
		
		LightDto lightDto = new LightDto(light.getId(), light.isOn());
		return lightDto;
	}
	
	@Transactional
	public void updateLightSystem() {
		lightRepository.updateAllLightToOff();	
	}
}
