package com.pramos.devices.domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="fan")
public class Fan extends Device {	

	private static final long serialVersionUID = 5611833297319106851L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	public void deviceTurnOff() {
		System.out.println("Fan off.");
	}

	@Override
	public void deviceTurnOn() {
		System.out.println("Fan on");
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
