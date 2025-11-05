package com.pramos.devices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pramos.devices.domain.AirConditioner;
import com.pramos.devices.dto.AirConditionerDto;
import com.pramos.devices.exceptions.AirConditionerException;
import com.pramos.devices.repository.AirConditionerRepository;

@Service
public class AirConditionerService {
	
	@Autowired
	private AirConditionerRepository airConditionerRepository;
	
	public List<AirConditionerDto> listAll(){
		List<AirConditionerDto> listAllAirConditionerDto =  new ArrayList<>();
		
		List<AirConditioner> listAll = airConditionerRepository.findAll();
		if(listAll.isEmpty()) {
			return listAllAirConditionerDto;
		}
		
		listAllAirConditionerDto = listAll.stream().map(airCond -> new AirConditionerDto(airCond.getId(), airCond.isOn(), airCond.getThermostat(), airCond.getThermostatOffMode())).collect(Collectors.toList());
		return listAllAirConditionerDto;
	}
	
	@Transactional
	public AirConditionerDto create() {
		AirConditioner airConditioner = new AirConditioner();
		airConditioner = airConditionerRepository.save(airConditioner);
		
		AirConditionerDto airConditionerDto = new AirConditionerDto(airConditioner.getId(), airConditioner.isOn(), airConditioner.getThermostat(), airConditioner.getThermostatOffMode());
		return airConditionerDto;
	}
	
	@Transactional
	public void delete(Long id) {
		AirConditioner airConditioner = airConditionerRepository.findById(id).orElseThrow(()-> new AirConditionerException("Air conditioner not found: " + id));
		airConditionerRepository.delete(airConditioner);		
	}
	
	@Transactional
	public AirConditionerDto toggle(Long id) {
		AirConditioner airConditioner = airConditionerRepository.findById(id).orElseThrow(()-> new AirConditionerException("Air conditioner not found: " + id));
		airConditioner.toggler();		
		airConditioner = airConditionerRepository.save(airConditioner);
		
		AirConditionerDto airConditionerDto = new AirConditionerDto(airConditioner.getId(), airConditioner.isOn(), airConditioner.getThermostat(), airConditioner.getThermostatOffMode());
		return airConditionerDto;	
	}
	
	@Transactional
	public AirConditionerDto updateThermostat(Long id, Double thermostat) {
		if (thermostat == null) {
			throw new AirConditionerException("Thermostat can be not null.");
		}
		
		AirConditioner airConditioner = airConditionerRepository.findById(id).orElseThrow(()-> new AirConditionerException("Air conditioner not found: " + id));
		airConditioner.setThermostat(thermostat);
		
		if(thermostat < airConditioner.getThermostatOffMode() && airConditioner.isOn()) {
			airConditioner.turnOff();
		} 
		
		airConditioner = airConditionerRepository.save(airConditioner);
		
		AirConditionerDto airConditionerDto = new AirConditionerDto(airConditioner.getId(), airConditioner.isOn(), airConditioner.getThermostat(), airConditioner.getThermostatOffMode());
		return airConditionerDto;
	}
	
	@Transactional
	public AirConditionerDto updateThermostatOffMode(Long id, Double thermostatOffMode) {
		if (thermostatOffMode == null) {
			throw new AirConditionerException("Thermostat mode off can be not null.");
		}
		
		AirConditioner airConditioner = airConditionerRepository.findById(id).get();
		airConditioner.setThermostatOffMode(thermostatOffMode);
		
		if(airConditioner.getThermostat() < thermostatOffMode && airConditioner.isOn()) {
			airConditioner.turnOff();
		} 
		
		airConditioner = airConditionerRepository.save(airConditioner);
		
		AirConditionerDto airConditionerDto = new AirConditionerDto(airConditioner.getId(), airConditioner.isOn(), airConditioner.getThermostat(), airConditioner.getThermostatOffMode());
		return airConditionerDto;	
	}
	
	@Transactional
	public AirConditionerDto updateThermostatAndThermostatOffMode(Long id, Double thermostat, Double thermostatOffMode) {
		if (thermostat == null || thermostatOffMode == null) {
			throw new AirConditionerException("Thermostat and thermostatOffMode are required.");
		}
		
		AirConditioner airConditioner = airConditionerRepository.findById(id).orElseThrow(()-> new AirConditionerException("Air conditioner not found: " + id));
		airConditioner.setThermostat(thermostat);
		airConditioner.setThermostatOffMode(thermostatOffMode);
		
		if(thermostat <= thermostatOffMode && airConditioner.isOn()) {
			airConditioner.turnOff();
		} else if (thermostat > thermostatOffMode && !airConditioner.isOn()){
			airConditioner.turnOn();
		}
		
		airConditioner = airConditionerRepository.save(airConditioner);
		
		AirConditionerDto airConditionerDto = new AirConditionerDto(airConditioner.getId(), airConditioner.isOn(), airConditioner.getThermostat(), airConditioner.getThermostatOffMode());
		return airConditionerDto;
	}
	
	@Transactional
	public void updateAirConditionerSystem() {
		List<AirConditioner> listAirCond = airConditionerRepository.findAll();		
		if(listAirCond.isEmpty()) return;
		
		List<AirConditioner> listAirCondOn = listAirCond.stream().filter(AirConditioner::isOn).peek(a -> a.turnOff()).collect(Collectors.toList());
		airConditionerRepository.saveAll(listAirCondOn);		
	}

}
