package com.pramos.devices.domain;

interface Device {
	
	Boolean isOn();
    void setOn(Boolean state); 

    Boolean turnOff(); 
    Boolean turnOn();
  
    default Boolean toggler() {
        if (isOn()) {
            return turnOff();
        } else {
            return turnOn();
        }
    }
}
