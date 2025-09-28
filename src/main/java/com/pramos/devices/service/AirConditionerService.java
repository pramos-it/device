package com.pramos.devices.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pramos.devices.domain.AirConditioner;
import com.pramos.devices.exceptions.AirConditionerException;
import com.pramos.devices.repository.AirConditionerRepository;

@Service
public class AirConditionerService {
	
	@Autowired
	private AirConditionerRepository airConditionerRepository;
	
	public List<AirConditioner> listAll(){
		return airConditionerRepository.findAll();
	}
	
	@Transactional
	public AirConditioner create() {
		AirConditioner airConditioner = new AirConditioner();
		return airConditionerRepository.save(airConditioner);
	}
	
	@Transactional
	public void delete(Long id) {
		AirConditioner airConditioner = airConditionerRepository.findById(id).orElseThrow(()-> new AirConditionerException("Air conditioner not found: " + id));
		airConditionerRepository.delete(airConditioner);		
	}
	
	@Transactional
	public AirConditioner toggle(Long id) {
		AirConditioner airConditioner = airConditionerRepository.findById(id).orElseThrow(()-> new AirConditionerException("Air conditioner not found: " + id));
		airConditioner.toggler();
		
		return airConditionerRepository.save(airConditioner);		
	}
	
	@Transactional
	public AirConditioner updateThermostat(Long id, Double thermostat) {
		if (thermostat == null) {
			throw new AirConditionerException("Thermostat can be not null.");
		}
		
		AirConditioner airCond = airConditionerRepository.findById(id).orElseThrow(()-> new AirConditionerException("Air conditioner not found: " + id));
		airCond.setThermostat(thermostat);
		
		if(thermostat <= airCond.getThermostatOffMode() && airCond.isOn()) {
			airCond.turnOff();
		} else if (thermostat > airCond.getThermostatOffMode() && !airCond.isOn()){
			airCond.turnOn();
		}
		
		return airConditionerRepository.save(airCond);
	}
	
	@Transactional
	public AirConditioner updateThermostatOffMode(Long id, Double thermostatOffMode) {
		if (thermostatOffMode == null) {
			throw new AirConditionerException("Thermostat mode off can be not null.");
		}
		
		AirConditioner airCond = airConditionerRepository.findById(id).get();
		airCond.setThermostatOffMode(thermostatOffMode);
		
		if(airCond.getThermostat() <= thermostatOffMode && airCond.isOn()) {
			airCond.turnOff();
		} else {
			airCond.turnOn();
		}
		
		return airConditionerRepository.save(airCond);		
	}
	
	@Transactional
	public AirConditioner updateThermostatAndThermostatOffMode(Long id, Double thermostat, Double thermostatOffMode) {
		if (thermostat == null || thermostatOffMode == null) {
			throw new AirConditionerException("Thermostat and thermostatOffMode are required.");
		}
		
		AirConditioner airCond = airConditionerRepository.findById(id).orElseThrow(()-> new AirConditionerException("Air conditioner not found: " + id));
		airCond.setThermostat(thermostat);
		airCond.setThermostatOffMode(thermostatOffMode);
		
		if(thermostat <= thermostatOffMode && airCond.isOn()) {
			airCond.turnOff();
		} else if (thermostat > thermostatOffMode && !airCond.isOn()){
			airCond.turnOn();
		}
		
		return airConditionerRepository.save(airCond);
	}
	
	@Transactional
	public void updateAirConditionerSystem() {
		List<AirConditioner> listAirCond = airConditionerRepository.findAll();		
		if(listAirCond.isEmpty()) return;
		
		List<AirConditioner> listAirCondOn = listAirCond.stream().filter(AirConditioner::isOn).peek(a -> a.turnOff()).collect(Collectors.toList());
		airConditionerRepository.saveAll(listAirCondOn);		
	}

}
