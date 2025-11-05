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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pramos.devices.dto.FanDto;
import com.pramos.devices.service.FanService;

@RestController
@RequestMapping("/fans")
public class FanController {

	@Autowired
	private FanService fanService;

	@GetMapping
	public ResponseEntity<List<FanDto>> list() {
		return ResponseEntity.ok(fanService.listAll());
	}

	@PostMapping
	public ResponseEntity<FanDto> create() {
		return ResponseEntity.status(HttpStatus.CREATED).body(fanService.create());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		fanService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}/toggle")
	public ResponseEntity<FanDto> toggle(@PathVariable Long id) {
		return ResponseEntity.ok(fanService.toggle(id));	
	}

	@PatchMapping("/{id}")
	public ResponseEntity<FanDto> updateSpeed(@PathVariable Long id, @RequestParam(name = "speed") Integer s) {
		return ResponseEntity.ok(fanService.updateSpeed(id, s));
	}

}
