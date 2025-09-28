package com.pramos.devices.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Device implements Serializable {

	private static final long serialVersionUID = 1658047260790741366L;

	public abstract void deviceTurnOff();	
	public abstract void deviceTurnOn();
	
	@Column(name="is_on")
	private boolean isOn;
	
	public Device() {
		this.isOn = false;
	}
	
	public boolean isOn() {
		return isOn;
	}
	
	public final void toggler() { 
        if (this.isOn) {
            this.turnOff();
        } else {
            this.turnOn();
        }
    }
	
	public final void turnOff() {
		this.isOn = false;
		this.deviceTurnOff();

	}

	public final void turnOn() {
		this.isOn = true;
		this.deviceTurnOn();
	}	
}
