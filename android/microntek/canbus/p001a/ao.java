package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;

public class ao extends C0001b {
    public ao(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 11;
    }

    private void m233do() {
        this.ej.ob((byte) -64, new byte[]{(byte) 7, (byte) 48}, 2);
    }

    private void dp(byte[] bArr) {
        this.ei.seatShow = false;
        this.ei.windRearShow = false;
        if ((bArr[0] & 128) == 0 && (bArr[1] & 16) == 0) {
            this.ei.onOff = false;
        } else {
            this.ei.onOff = true;
        }
        if ((bArr[0] & 64) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[0] & 16) != 0) {
            this.ei.modeAuto = 2;
        } else if ((bArr[0] & 8) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[0] & 4) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        if ((bArr[0] & 2) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[0] & 1) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        if ((bArr[1] & 128) != 0) {
            this.ei.windUp = true;
        } else {
            this.ei.windUp = false;
        }
        if ((bArr[1] & 64) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((bArr[1] & 32) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[1] & 15;
        this.ei.windLevelMax = 7;
        int i = bArr[2] & 255;
        this.ei.tempLeft = dq(i);
        i = bArr[3] & 255;
        this.ei.tempRight = dq(i);
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatHotLeft = (bArr[4] & 48) >> 4;
        this.ei.seatHotRight = bArr[4] & 3;
    }

    private int dq(int i) {
        return i == 31 ? 0 : i == 57 ? 65535 : (i < 32 || i > 56) ? -1 : (i * 10) / 2;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        byte[] bArr2;
        int i4;
        switch (bArr[i + 1]) {
            case (byte) 33:
                if (bArr[i + 2] == (byte) 9) {
                    byte[] bArr3 = new byte[9];
                    while (i3 < 9) {
                        bArr3[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    dp(bArr3);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] == (byte) 4) {
                    bArr2 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        if (bArr[(i + 3) + i4] == (byte) 0) {
                            bArr2[i4] = (byte) 0;
                        } else if (bArr[(i + 3) + i4] < (byte) 3) {
                            bArr2[i4] = (byte) 1;
                        } else {
                            bArr2[i4] = (byte) (bArr[(i + 3) + i4] / 2);
                        }
                    }
                    this.ek.max = 15;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr2[0];
                    this.ek.b2 = bArr2[1];
                    this.ek.b3 = bArr2[2];
                    this.ek.b4 = bArr2[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] == (byte) 6) {
                    bArr2 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        if (bArr[(i + 3) + i4] == (byte) 0) {
                            bArr2[i4] = (byte) 0;
                        } else if (bArr[(i + 3) + i4] < (byte) 3) {
                            bArr2[i4] = (byte) 1;
                        } else {
                            bArr2[i4] = (byte) (bArr[(i + 3) + i4] / 2);
                        }
                    }
                    this.ek.max = 15;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr2[0];
                    this.ek.f2 = bArr2[1];
                    this.ek.f3 = bArr2[2];
                    this.ek.f4 = bArr2[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oa(1);
    }

    public void mo37e() {
        m233do();
    }

    public void mo38f() {
        m233do();
    }

    public void mo39g() {
        m233do();
    }

    public void mo40h(String str, int i) {
        m233do();
    }

    public void mo41i() {
        m233do();
    }

    public void mo42j(int i, int i2, int i3) {
        this.ej.ob((byte) -64, new byte[]{(byte) 2, (byte) 48}, 2);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        m233do();
    }

    public void mo44l() {
        m233do();
    }

    public void mo45m(int i, int i2, int i3) {
        m233do();
    }

    public void mo46n() {
        this.ej.ob((byte) -64, new byte[2], 2);
    }

    public void mo47o(byte b, int i, byte b2) {
        this.ej.ob((byte) -64, new byte[]{(byte) 1, (byte) 1}, 2);
        byte[] bArr = new byte[4];
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[1] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[0] = (byte) 16;
            bArr[1] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
        }
        this.ej.ob((byte) -62, bArr, 4);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
