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
	private Boolean isOn;
	
	@Column(name = "speed", length = 1, nullable = false)
	private Integer speed;
	
	public Fan() {
		this.isOn = false;
		this.speed = 0;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed){
	    this.speed = speed;
	}

	@Override
	public Boolean isOn() {
		return isOn;
	}

	@Override
	public void setOn(Boolean isOn) {		
		this.isOn = isOn;
	}
	
	@Override
	public Boolean turnOff() {
		setOn(false);
		setSpeed(0);
        return false; 
	}

	@Override
	public Boolean turnOn() {
		setOn(true);
		setSpeed(1);
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
