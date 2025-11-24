package com.pramos.devices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pramos.devices.domain.Fan;
import com.pramos.devices.dto.FanDto;
import com.pramos.devices.exceptions.FanException;
import com.pramos.devices.repository.FanRepository;

@Service
public class FanService {

	@Autowired
	private FanRepository fanRepository;
	
	public List<FanDto> listAll(){		
		List<FanDto> listAllFanDto =  new ArrayList<>();
		
		List<Fan> listAll = fanRepository.findAll();
		if(listAll.isEmpty()) {
			return listAllFanDto;
		}
		
		listAllFanDto = listAll.stream().map(fan -> new FanDto(fan.getId(), fan.isOn(), fan.getSpeed())).collect(Collectors.toList());
		return listAllFanDto;
	}
	
	@Transactional
	public FanDto create() {
		Fan fan = new Fan();
		fan = fanRepository.save(fan);
		FanDto fanDto = new FanDto(fan.getId(), fan.isOn(), fan.getSpeed());		
		return fanDto;
	}
	
	@Transactional
	public void delete(Long id) {
		Fan fan = fanRepository.findById(id).orElseThrow(() -> new FanException("Fan not found: " + id));
        fanRepository.delete(fan);
	}
	
	@Transactional
	public FanDto toggle(Long id) {
		Fan fan = fanRepository.findById(id).orElseThrow(() -> new FanException("Fan not found: " + id));		
		fan.toggler();
		fan = fanRepository.save(fan);			
		FanDto fanDto = new FanDto(fan.getId(), fan.isOn(), fan.getSpeed());	
		return fanDto;		
	}
	
	@Transactional
	public FanDto updateSpeed(Long id, Integer speed) {
	    if (speed == null) {
	        throw new FanException("Speed can not be null");
	    }
	    
	   if (speed < 0 || speed > 3) {
		   throw new FanException("Invalid speed");
	   }
	    
		Fan fan = fanRepository.findById(id).orElseThrow(() -> new FanException("Fan not found: " + id));
		fan.setSpeed(speed);
		
		if (fan.getSpeed() <= 0 && fan.isOn()) {
			fan.turnOff();
		} else if (fan.getSpeed() > 0 && !fan.isOn()) {
			fan.turnOn();
		}
	  	
		fan = fanRepository.save(fan);	
		
		FanDto fanDto = new FanDto(fan.getId(), fan.isOn(), fan.getSpeed());	
		return fanDto;	
	}
	
	@Transactional
	public void updateFanSystem() {
		List<Fan> listFantOn = fanRepository.findAll().stream().filter(Fan::isOn).peek(f -> f.turnOff()).collect(Collectors.toList());
		fanRepository.saveAll(listFantOn);		
	}
}
