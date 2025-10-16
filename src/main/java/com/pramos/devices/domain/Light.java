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
@Table(name="light")
public class Light implements Device, Serializable {	
	
	private static final long serialVersionUID = 435706412048182295L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="isOn")
	private boolean isOn;
	
	public Light() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return Objects.hash(id, isOn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Light other = (Light) obj;
		return Objects.equals(id, other.id) && isOn == other.isOn;
	}

	
}
