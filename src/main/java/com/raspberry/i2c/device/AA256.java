package com.raspberry.i2c.device;

/**
 * Created by jocole on 7/18/2016.
 */
public class AA256 extends Device {
    public AA256() throws Exception {
        super(0x50);
    }

    @Override
    public void initialize() throws Exception {

    }

    private void eeWrite(int eeaddress, byte data) {
        int[] buffer = new int[] {
                ((eeaddress >> 8) & 0x00FF),
                (eeaddress & 0x00FF),
                data
        };

        // Note we add "wp" because this chip deals with words, not bytes.
        send(buffer[0], buffer[2] << 8 | buffer[1], "wp");
        try {
            Thread.sleep(15);
        }catch(Exception e) {
        }
    }

    private byte eeRead(int eeaddress) {
        int[] buffer = new int[] {
                (eeaddress >> 8),
                (eeaddress & 0xFF)
        };

        send(buffer[0], buffer[1]);

        try {
            Thread.sleep(15);
        }catch(Exception e) {
        }

        return (byte)(int)receive();
    }

    public void reset() {
        for (int i =0; i < 0x7D00; i++) {
            eeWrite(i, (byte)0x00);
        }
    }

    public void flash(String msg) {
        int address = 16;
        for (char data : msg.toCharArray()) {
            eeWrite(address++, (byte)data);
        }
    }

    public String read() {
        int length = 0x7D00;
        StringBuilder builder = new StringBuilder();

        // Change i to whatever starting point you want. Though I've found the first 16
        // bytes appear to be not usable.
        for ( int i = 16; i < length; i++ ) {
            char c = (char)eeRead(i);

            // Note this is my own format. You can remove this if you would like to read
            // all the things.
            if (c == '\0') break;
            builder.append(c);
        }

        return builder.toString();
    }
}
