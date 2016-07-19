package library;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by jocole on 7/18/2016.
 */
public class I2C {

    private static String getExecString(String command, String[] args) {
        StringBuilder result = new StringBuilder();
        result.append(command);
        result.append(" ");
        for(String arg : args) {
            result.append(arg);
            result.append(" ");
        }
        return result.toString();
    }

    private static String getIntegerString(String str) {
        return str.replace("0x", "");
    }

    private static void reset() {
        String cmdText = "i2cdetect";
        String[] args = {
            "-y",
            "1"
        };

        try {
            Runtime.getRuntime().exec(getExecString(cmdText, args));
        } catch (Exception err) {
        }
    }

    public static void send(int address, int command, int value) {
        send(address, command, value, "");
    }

    public static void send(int address, int command, int value, String options) {
        send(address, command, value, options, false);
    }

    public static void send(int address, int command, int value, String options, boolean error) {
        // Fixes a bug with the i2c device on the raspberry pi.
        String cmdText = "i2cset";
        String[] args = {
            "-y",
            "1",
            "" + address,
            "" + command,
            "" + value,
            options
        };

        try {
            Runtime.getRuntime().exec(getExecString(cmdText, args));
        } catch( Exception err) {
            // Sometimes an error happens because of a bug in the i2c device drivers.
            // Retry after calling i2cdetect (because that fixes it for some reason).
            // But only retry once.
            if (error) {
            } else {
                reset();
                send(address, command, value, options, true);
            }

        }
    }

    public static Integer receive(int address) {
        return receive(address, 0, "skip");
    }

    public static Integer receive(int address, int command) {
        return receive(address, command, "");
    }

    public static Integer receive(int address, int command, String options) {
        String cmdText = "i2cget";
        String[] args = {
            "-y",
            "1",
            "" + address,
            "" + command,
            options
        };

        if (options.equals("skip")) {
            args = new String[] {
                "-y",
                "1",
                "" + address
            };
        }

        try {
            String line;
            StringBuilder result = new StringBuilder();
            Process proc = Runtime.getRuntime().exec(getExecString(cmdText, args));
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(proc.getInputStream())
            );

            while((line = input.readLine()) != null) {
                result.append(line);
            }
            input.close();
            return Integer.parseInt(getIntegerString(result.toString()), 16);
        } catch ( Exception err) {
            return -1;
        }
    }
}
