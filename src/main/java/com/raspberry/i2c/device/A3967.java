package com.raspberry.i2c.device;

/**
 * Created by jocole on 7/18/2016.
 */
public class A3967 extends Device {
    MCP230XX gpio;
    Integer directionPin;
    Integer stepPin;
    Integer resetPin;

    public A3967(MCP230XX gpio, int stepPin, int directionPin, int resetPin) throws Exception {
        super(0x00); // Not an i2c device.
        this.gpio = gpio;
        this.stepPin = stepPin;
        this.directionPin = directionPin;
        this.resetPin = resetPin;
    }

    @Override
    public void initialize() {

    }

    public void step(boolean forward, int steps) throws Exception {
        steps = Math.max(steps, 0);
        gpio.setPin(resetPin, true);

        // Set the direction and then do the steps.
        gpio.setPin(directionPin, forward);
        for (; steps > 0; steps--) {
            gpio.setPin(stepPin, false);
            Thread.sleep(1);
            gpio.setPin(stepPin, true);
        }
        // Set the reset pin to disable the motor while not in use.
        gpio.setPin(resetPin, false);
    }
}
