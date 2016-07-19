package com.raspberry.i2c.device;

/**
 * Created by jocole on 7/18/2016.
 */
public class MCP230XX extends Device {
    public MCP230XX() throws Exception {
        super(0x20);
    }

    @Override
    public void initialize() {
        send(0x00, 0x00);
        send(0x14, 0x00);
    }

    public Integer getAll() {
        return receive(0x14);
    }

    // This is not correct.
    public Integer getPin(Integer pin) {
        return getAll() << pin;
    }

    public void setPin(Integer pin, Boolean high) {
        // Read the current GPIO state.
        int data = getAll();
        if (high) {
            data = data | (0x01 << pin);
        } else {
            data = data & ~(0x01 << pin);
        }
        send(0x14, data);
    }
}
