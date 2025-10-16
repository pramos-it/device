package com.pramos.devices.domain;

interface Device {
	
	boolean isOn();
    void setOn(boolean state); 

    boolean turnOff(); 
    boolean turnOn();
  
    default boolean toggler() {
        if (isOn()) {
            return turnOff();
        } else {
            return turnOn();
        }
    }
}
