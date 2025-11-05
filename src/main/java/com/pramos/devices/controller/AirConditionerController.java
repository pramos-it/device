package com.pramos.devices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pramos.devices.dto.AirConditionerDto;
import com.pramos.devices.service.AirConditionerService;

@RestController
@RequestMapping("/airconditioners")
public class AirConditionerController {
	
	@Autowired
	private AirConditionerService airConditionerService;

	@GetMapping
	public ResponseEntity<List<AirConditionerDto>> list() {
		return ResponseEntity.ok(airConditionerService.listAll());
	}

	@PostMapping
	public ResponseEntity<AirConditionerDto> create() {
		return ResponseEntity.status(HttpStatus.CREATED).body(airConditionerService.create());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		airConditionerService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}/toggle")
	public ResponseEntity<AirConditionerDto> toggle(@PathVariable Long id) {
		return ResponseEntity.ok(airConditionerService.toggle(id));	
	}
	
	@PatchMapping("/{id}/thermostat")
	public ResponseEntity<AirConditionerDto> updateThermostat(@PathVariable Long id, @RequestParam(name = "value") Double t) {
		return ResponseEntity.ok(airConditionerService.updateThermostat(id, t));		
	}

	@PatchMapping("/{id}/thermoOffMode")
	public ResponseEntity<AirConditionerDto> updateThermostatOffMode(@PathVariable Long id, @RequestParam(name = "value") Double tom) {
		return ResponseEntity.ok(airConditionerService.updateThermostatOffMode(id, tom));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<AirConditionerDto> updateThermostatAndThermostatOffMode(@PathVariable Long id, @RequestBody AirConditionerDto airCondDto) {
		return ResponseEntity.ok(airConditionerService.updateThermostatAndThermostatOffMode(id, airCondDto.thermostat(), airCondDto.thermostatOffMode()));
	}
	
}
