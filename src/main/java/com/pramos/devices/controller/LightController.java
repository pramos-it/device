package com.pramos.devices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pramos.devices.domain.Light;
import com.pramos.devices.service.LightService;

@RestController
@RequestMapping("/lights")
public class LightController {
	
	@Autowired
	private LightService lightService;
	
	@GetMapping
	public ResponseEntity<List<Light>> list(){
		return ResponseEntity.ok(lightService.listAll());
	}
	
	@PostMapping
	public ResponseEntity<Light> create() {
		return ResponseEntity.status(HttpStatus.CREATED).body(lightService.create());
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
			lightService.delete(id);
			return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}/toggle")
	public ResponseEntity<Light> toggle(@PathVariable Long id) {
			return ResponseEntity.ok(lightService.toggle(id));
	}
}
