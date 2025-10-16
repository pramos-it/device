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
@Table(name="fan")
public class Fan implements Device, Serializable {	

	private static final long serialVersionUID = -2156082681811722785L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="isOn")
	private boolean isOn;
	
	@Column(name = "speed", length = 1, nullable = false)
	private int speed;
	
	public Fan() {
		super();
		this.speed = 0;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed){
	    this.speed = speed;
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
		Fan other = (Fan) obj;
		return Objects.equals(id, other.id);
	}
}
