package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;

public class cm extends C0001b {
    private int cn;

    public cm(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.cn = 0;
        this.eh = 22;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
    }

    public void mo36d() {
    }

    public void mo37e() {
        this.ej.or((byte) -86, new byte[]{(byte) 64, jl(this.cn), (byte) 8, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104}, 12);
    }

    public void mo38f() {
        byte[] bArr = new byte[12];
        bArr[0] = (byte) 81;
        bArr[1] = jl(this.cn);
        this.ej.or((byte) -86, bArr, 12);
    }

    public void mo39g() {
        byte[] bArr = new byte[12];
        bArr[0] = (byte) 83;
        bArr[1] = jl(this.cn);
        this.ej.or((byte) -86, bArr, 12);
    }

    public void mo40h(String str, int i) {
        this.ej.or((byte) -86, new byte[]{(byte) 64, jl(this.cn), (byte) 8, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104}, 12);
    }

    public void mo41i() {
        byte[] bArr = new byte[12];
        bArr[0] = (byte) 81;
        bArr[1] = jl(this.cn);
        this.ej.or((byte) -86, bArr, 12);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[12];
        bArr[0] = (byte) 33;
        bArr[1] = jl(this.cn);
        bArr[2] = (byte) 8;
        bArr[3] = (byte) ((((i2 / 60) / 60) / 10) + 48);
        bArr[4] = (byte) ((((i2 / 60) / 60) % 10) + 48);
        bArr[5] = (byte) 58;
        bArr[6] = (byte) ((((i2 / 60) % 60) / 10) + 48);
        bArr[7] = (byte) ((((i2 / 60) % 60) % 10) + 48);
        bArr[8] = (byte) 58;
        bArr[9] = (byte) (((i2 % 60) / 10) + 48);
        bArr[10] = (byte) (((i2 % 60) % 10) + 48);
        this.ej.or((byte) -86, bArr, 12);
    }

    public byte jl(int i) {
        String str = i + "";
        if (str.length() == 1) {
            return (byte) i;
        }
        int parseInt = Integer.parseInt(str.substring(1));
        return i < 20 ? (byte) (parseInt + 16) : i < 30 ? (byte) (parseInt + 32) : (byte) 48;
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        r0 = new byte[12];
        int i5 = i3 / 1000;
        r0[3] = (byte) ((((i5 / 60) / 60) / 10) + 48);
        r0[4] = (byte) ((((i5 / 60) / 60) % 10) + 48);
        r0[5] = (byte) 58;
        r0[6] = (byte) ((((i5 / 60) % 60) / 10) + 48);
        r0[7] = (byte) ((((i5 / 60) % 60) % 10) + 48);
        r0[8] = (byte) 58;
        r0[9] = (byte) (((i5 % 60) / 10) + 48);
        r0[10] = (byte) (((i5 % 60) % 10) + 48);
        this.ej.or((byte) -86, r0, 12);
    }

    public void mo44l() {
        byte[] bArr = new byte[12];
        bArr[0] = (byte) 85;
        bArr[1] = jl(this.cn);
        this.ej.or((byte) -86, bArr, 12);
    }

    public void mo45m(int i, int i2, int i3) {
        r0 = new byte[12];
        int i4 = i3 / 1000;
        r0[3] = (byte) ((((i4 / 60) / 60) / 10) + 48);
        r0[4] = (byte) ((((i4 / 60) / 60) % 10) + 48);
        r0[5] = (byte) 58;
        r0[6] = (byte) ((((i4 / 60) % 60) / 10) + 48);
        r0[7] = (byte) ((((i4 / 60) % 60) % 10) + 48);
        r0[8] = (byte) 58;
        r0[9] = (byte) (((i4 % 60) / 10) + 48);
        r0[10] = (byte) (((i4 % 60) % 10) + 48);
        this.ej.or((byte) -86, r0, 12);
    }

    public void mo46n() {
        byte[] bArr = new byte[12];
        bArr[0] = (byte) 0;
        bArr[1] = jl(this.cn);
        this.ej.or((byte) -86, bArr, 12);
    }

    public void mo47o(byte b, int i, byte b2) {
        String str = i + "";
        if (str.length() == 1) {
            str = "000" + i;
        } else if (str.length() == 2) {
            str = "00" + i;
        } else if (str.length() == 3) {
            str = "0" + i;
        }
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[12];
        if (b >= (byte) 0 && b <= (byte) 4) {
            bArr[0] = (byte) (b + 17);
            bArr[1] = jl(this.cn);
            bArr[2] = (byte) 8;
            if (str.length() == 5) {
                if (b != (byte) 3 || b > (byte) 4) {
                    bArr[3] = bytes[0];
                    bArr[4] = bytes[1];
                    bArr[5] = bytes[2];
                    bArr[6] = (byte) 46;
                    bArr[7] = bytes[3];
                    bArr[8] = (byte) 77;
                    bArr[9] = (byte) 72;
                    bArr[10] = (byte) 90;
                } else {
                    bArr[3] = bytes[0];
                    bArr[4] = bytes[1];
                    bArr[5] = bytes[2];
                    bArr[6] = bytes[3];
                    bArr[7] = bytes[4];
                    bArr[8] = (byte) 75;
                    bArr[9] = (byte) 72;
                    bArr[10] = (byte) 90;
                }
                bArr[11] = (byte) 0;
            } else if (b < (byte) 3 || b > (byte) 4) {
                bArr[3] = bytes[0];
                bArr[4] = bytes[1];
                bArr[5] = (byte) 46;
                bArr[6] = bytes[2];
                bArr[7] = bytes[3];
                bArr[8] = (byte) 77;
                bArr[9] = (byte) 72;
                bArr[10] = (byte) 90;
                bArr[11] = (byte) 0;
            } else {
                bArr[3] = bytes[0];
                bArr[4] = bytes[1];
                bArr[5] = bytes[2];
                bArr[6] = bytes[3];
                bArr[7] = (byte) 75;
                bArr[8] = (byte) 72;
                bArr[9] = (byte) 90;
                bArr[10] = (byte) 32;
            }
        }
        this.ej.or((byte) -86, bArr, 12);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
        this.cn = i;
        if (this.ej.ko != null) {
            int i2 = this.ej.ky;
            this.ej.getClass();
            if (i2 != 0) {
                this.ej.ou(this.ej.ko);
                return;
            }
            byte[] bArr = new byte[12];
            bArr[0] = (byte) 0;
            bArr[1] = jl(this.cn);
            this.ej.or((byte) -86, bArr, 12);
            return;
        }
        bArr = new byte[12];
        bArr[0] = (byte) 0;
        bArr[1] = jl(this.cn);
        this.ej.or((byte) -86, bArr, 12);
    }
}
