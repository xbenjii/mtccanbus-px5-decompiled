package android.microntek.canbus.update;

import android.microntek.canbus.R$styleable;
import android.util.Log;
import java.io.RandomAccessFile;

public class C0067d extends C0064c {
    private static int im = 64;
    private static byte[] io;
    private static long iq = -1;
    private static long ir = -1;
    private byte[] in = new byte[]{(byte) 1, (byte) 0, (byte) 0, (byte) -106, (byte) 0, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 18, (byte) 52, (byte) 86, (byte) 120};
    private int ip = 0;

    public C0067d(UpdateService updateService) {
        super(updateService);
    }

    private int nx(int i) {
        int i2 = im;
        if (((long) i) >= ir) {
            return 0;
        }
        byte b = (byte) 3;
        if (((long) (im + i)) >= ir) {
            b = (byte) 4;
            i2 = (int) (ir - ((long) i));
        }
        byte[] bArr = new byte[(i2 + 1)];
        bArr[0] = b;
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3 + 1] = io[i + i3];
        }
        nw((byte) -32, bArr, i2 + 1);
        return i + i2;
    }

    public void nr(byte[] bArr) {
        switch (bArr[1] & 255) {
            case 225:
                switch (bArr[3] & 255) {
                    case R$styleable.MyButton_imgWidth /*0*/:
                        this.ip = 0;
                        return;
                    case R$styleable.MyButton_imgSrc /*2*/:
                        this.il.nn("fail !!! 0x02");
                        return;
                    case R$styleable.MyButton_imgSrc1 /*3*/:
                        this.il.nn("fail !!! 0x03");
                        return;
                    case R$styleable.MyButton_imgWidth2 /*5*/:
                        this.il.nn("fail !!! 0x05");
                        return;
                    case 10:
                        this.il.nn("Download sucess!!!");
                        return;
                    case 11:
                        this.il.nn("fail !!! 0x0B");
                        return;
                    case 12:
                        this.il.nn("fail !!! 0x0C");
                        return;
                    case 14:
                        this.il.nn("fail !!! 0x0E");
                        return;
                    case 15:
                        this.il.nn("fail !!! 0x0F");
                        return;
                    case 129:
                        this.ip = nx(this.ip);
                        this.il.nn("Downloading....");
                        return;
                    default:
                        this.il.nn("err: " + (bArr[3] & 255));
                        return;
                }
            default:
                return;
        }
    }

    public void ns(String str) {
        try {
            this.ip = 0;
            nw((byte) -32, this.in, 13);
            RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
            ir = randomAccessFile.length();
            if (ir > 0) {
                io = new byte[((int) ir)];
                randomAccessFile.readFully(io);
                iq = 0;
                for (int i = 0; ((long) i) < ir; i++) {
                    iq += (long) (io[i] & 255);
                }
                nw((byte) -32, new byte[]{(byte) 2, (byte) ((int) ((ir >> 32) & 255)), (byte) ((int) ((ir >> 16) & 255)), (byte) ((int) ((ir >> 8) & 255)), (byte) ((int) (ir & 255)), (byte) ((int) ((iq >> 32) & 255)), (byte) ((int) ((iq >> 16) & 255)), (byte) ((int) ((iq >> 8) & 255)), (byte) ((int) (iq & 255))}, 9);
            }
        } catch (Exception e) {
            Log.i("CanBusUpdateService", "Update>>err: " + e.getMessage());
        }
    }

    public void nw(byte b, byte[] bArr, int i) {
        new Thread(new C0069f(this, b, bArr, i)).start();
    }
}
