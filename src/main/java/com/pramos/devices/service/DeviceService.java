package com.pramos.devices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeviceService {
	
	@Autowired
    private LightService lightService;
    
	@Autowired
    private FanService fanService;    
    
    @Autowired
    private AirConditionerService airConditionerService;
	
    @Transactional
	public void updateSystem() {
    	lightService.updateLightSystem();    	
    	fanService.updateFanSystem();
    	airConditionerService.updateAirConditionerSystem();
    }
	
}
