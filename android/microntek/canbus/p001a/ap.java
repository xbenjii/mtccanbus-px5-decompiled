package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;

public class ap extends C0001b {
    public ap(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void dr(byte[] bArr) {
        if ((bArr[0] & 64) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[1] & 64) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[1] & 4) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        if ((bArr[2] & 16) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[2] & 32) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch (bArr[4] & 15) {
            case R$styleable.MyButton_imgWidth /*0*/:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case 12:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
        }
        this.ei.windLevel = bArr[5] & 15;
        this.ei.windLevelMax = 7;
        int i = bArr[6] & 255;
        this.ei.tempLeft = ds(i);
        i = bArr[7] & 255;
        this.ei.tempRight = ds(i);
        if ((bArr[1] & 16) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[0] & 32) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatShow = false;
    }

    private int ds(int i) {
        return i == 254 ? 0 : i == 255 ? 65535 : i < 100 ? i * 5 : -1;
    }

    public void al() {
        this.ej.ol((byte) 106, new byte[]{(byte) 5, (byte) 1, (byte) 49}, 3);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) 34:
                if (bArr[i + 2] < (byte) 7) {
                    return;
                }
                return;
            case (byte) 49:
                if (bArr[i + 2] >= (byte) 12) {
                    byte[] ma = ma(bArr, i + 3, i2);
                    if (lx(ma)) {
                        dr(ma);
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 50:
                if (bArr[i + 2] < (byte) 7) {
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
    }

    public void mo37e() {
    }

    public void mo38f() {
    }

    public void mo39g() {
    }

    public int[][] getAirBtnTable() {
        return new int[][]{new int[]{3842, 23205, 15618, 2, 256}, new int[]{3845, 23205, 15618, 7, 256}, new int[]{3847, 23205, 15618, 13, 256}, new int[]{3848, 23205, 15618, 14, 256}, new int[]{3849, 23205, 15618, 15, 256}, new int[]{3850, 23205, 15618, 16, 256}, new int[]{3853, 23205, 15618, 1, 256}, new int[]{3855, 23205, 15618, 12, 256}, new int[]{3856, 23205, 15618, 11, 256}, new int[]{3865, 23205, 15618, 21, 256}};
    }

    public void mo40h(String str, int i) {
    }

    public void mo41i() {
    }

    public void mo42j(int i, int i2, int i3) {
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
    }

    public void mo44l() {
    }

    public void mo45m(int i, int i2, int i3) {
    }

    public void mo46n() {
    }

    public void mo47o(byte b, int i, byte b2) {
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
