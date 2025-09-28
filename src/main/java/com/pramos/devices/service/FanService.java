package com.pramos.devices.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pramos.devices.domain.Fan;
import com.pramos.devices.exceptions.FanException;
import com.pramos.devices.repository.FanRepository;

@Service
public class FanService {

	@Autowired
	private FanRepository fanRepository;
	
	public List<Fan> listAll(){
		return fanRepository.findAll();
	}
	
	@Transactional
	public Fan create() {
		Fan fan = new Fan();
		return fanRepository.save(fan);
	}
	
	@Transactional
	public void delete(Long id) {
		Fan fan = fanRepository.findById(id).orElseThrow(() -> new FanException("Fan not found: " + id));
        fanRepository.delete(fan);
	}
	
	@Transactional
	public Fan toggle(Long id) {
		Fan fan = fanRepository.findById(id).orElseThrow(() -> new FanException("Fan not found: " + id));
		fan.toggler();
		return fanRepository.save(fan);		
	}
	
	@Transactional
	public Fan updateSpeed(Long id, Integer speed) {
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
	  	
		return fanRepository.save(fan);
	}
	
	@Transactional
	public void updateFanSystem() {
		List<Fan> listFantOn = fanRepository.findAll().stream().filter(Fan::isOn).peek(f -> f.turnOff()).collect(Collectors.toList());
		fanRepository.saveAll(listFantOn);		
	}
}
