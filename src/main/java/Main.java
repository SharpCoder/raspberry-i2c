import com.raspberry.i2c.device.A3967;
import com.raspberry.i2c.device.AA256;
import com.raspberry.i2c.device.HMC5883L;
import com.raspberry.i2c.device.MCP230XX;

/**
 * Created by jocole on 7/18/2016.
 */
public class Main {
    public static void main(String[] args) {
        // Here is how you can instantiate each of these devices.
        try {
            final AA256 eeprom = new AA256();
            final MCP230XX gpio = new MCP230XX();
            final HMC5883L compass = new HMC5883L();
            int stepPin = 1, directionPin = 2, resetPin = 3;
            final A3967 motor = new A3967(gpio, stepPin, directionPin, resetPin);

            // Here's how to use each of the things!
            // Reset the eeprom memory.
            eeprom.reset();
            // Flash some data to it.
            eeprom.flash("Hello, world!\0");
            // Read all the data back (until you get to a \0");
            String eepromData = eeprom.read();
            // Set a GPIO pin.
            gpio.setPin(1, true);
            // Get a GPIO pin (whether it's on or not). (I think).
            int isOn = gpio.getPin(1);
            // Move the motor.
            motor.step(true, 100);
            // Get the compass value.
            // NOTE: Completely broken right now. Just a placeholder
            // until I fix it.
            compass.getReadings();

        }catch (Exception err) {

        }

        System.out.println("Finished!");
    }
}
