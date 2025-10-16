package com.pramos.devices.service.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pramos.devices.service.DeviceService;

@Service
public class UpdateSystemService {
	
	@Autowired
	private DeviceService deviceService;

//	@Scheduled(cron = "0 0 1 1 1 *", zone = "America/Halifax")
	@Scheduled(cron = "0 */2 * * * *", zone = "America/Halifax")
	public void updateSystem() {
		deviceService.updateSystem();
    	System.out.println("System updated.");
	}

}
