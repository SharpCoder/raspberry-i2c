package com.raspberry.i2c.device;

import library.I2C;

/**
 * Created by jocole on 7/18/2016.
 */
public class Device {
    private Integer _address;

    public Device(Integer address) throws Exception {
        _address = address;
        initialize();
    }

    public int getAddress() {
        return _address;
    }

    protected void send(int command, int value) {
        I2C.send(getAddress(), command, value);
    }

    protected void send(int command, int value, String options) {
        I2C.send(getAddress(), command, value, options);
    }

    protected Integer receive(int command) {
        return I2C.receive(getAddress(), command);
    }

    protected Integer receive() {
        return I2C.receive(getAddress());
    }

    protected Integer receive(int command, String options) {
        return I2C.receive(getAddress(), command, options);
    }

    protected void initialize() throws Exception {
        throw new Exception("Failed to initialize device.");
    }
}
