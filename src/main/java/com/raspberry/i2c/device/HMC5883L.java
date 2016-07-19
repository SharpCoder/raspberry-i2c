package com.raspberry.i2c.device;

/**
 * Created by jocole on 7/18/2016.
 */
public class HMC5883L extends Device {
    public HMC5883L() throws Exception {
        super(0x1e);
    }

    @Override
    public void initialize() {
        send(0x3C, 0x00);
        send(0x00, 0x70);
        send(0x01, 0xA0);
        send(0x02, 0x00);
    }

    public int[] getReadings() {
        send(0x3D, 0x06);

        int x1 = receive(0x03);
        int x2 = receive(0x04);
        int z1 = receive(0x05);
        int z2 = receive(0x06);
        int y1 = receive(0x07);
        int y2 = receive(0x08);

        return new int[] {
                compute(x1, x2),
                compute(y1,y2),
                compute(z1,z2)
        };
    }

    private int compute(int a, int b) {
        return (a << 8) | b;
    }
}
