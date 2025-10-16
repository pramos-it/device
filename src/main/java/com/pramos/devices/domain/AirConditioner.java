package com.pramos.devices.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="air-conditioner")
public class AirConditioner implements Device, Serializable {

	private static final long serialVersionUID = 6543851455827373653L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="isOn")
	private boolean isOn;
	
	@Column(name = "thermostat", nullable = false)
	private Double thermostat;	
	
	@Column(name = "thermostatOffMode", nullable = false)
	private Double thermostatOffMode;
	
	public AirConditioner() {
		super();
		this.thermostat = 24.5;
		this.thermostatOffMode = 0.0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getThermostat() {
		return thermostat;
	}

	public void setThermostat(Double thermostat) {
		this.thermostat = thermostat;
	}

	public Double getThermostatOffMode() {
		return thermostatOffMode;
	}

	public void setThermostatOffMode(Double thermostatOffMode) {
		this.thermostatOffMode = thermostatOffMode;
	}

	@Override
	public boolean isOn() {
		return isOn;
	}

	@Override
	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	
	@Override
	public boolean turnOff() {
		setOn(false);
        return false; 
	}

	@Override
	public boolean turnOn() {
		setOn(true);
        return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirConditioner other = (AirConditioner) obj;
		return Objects.equals(id, other.id);
	}

}
