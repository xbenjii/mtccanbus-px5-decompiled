package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;

public class bq extends C0001b {
    public bq(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void gi(byte[] bArr) {
        this.ei.viewShow = (bArr[1] & 16) != 0;
        if ((bArr[0] & 128) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[0] & 64) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[0] & 2) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[4] & 64) != 0) {
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
        this.ei.windLevelMax = 8;
        int i = bArr[2] & 255;
        AirCondition airCondition = this.ei;
        i = gk(i);
        this.ei.tempLeft = i;
        airCondition.tempRight = i;
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[4] & 128) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatShow = false;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int[] iArr;
        int i4;
        switch (bArr[i + 1]) {
            case (byte) 20:
                if (bArr[i + 2] < (byte) 2) {
                    return;
                }
                return;
            case (byte) 32:
                if (bArr[i + 2] < (byte) 2) {
                    return;
                }
                return;
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 7) {
                    byte[] bArr2 = new byte[7];
                    while (i3 < 7) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    gi(bArr2);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] >= (byte) 4) {
                    iArr = new int[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
                        if (iArr[i4] != 0) {
                            iArr[i4] = 0 - (iArr[i4] - 5);
                        }
                    }
                    this.ek.max = 4;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = iArr[0];
                    this.ek.b2 = iArr[1];
                    this.ek.b3 = iArr[2];
                    this.ek.b4 = iArr[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] >= (byte) 4) {
                    iArr = new int[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
                        if (iArr[i4] != 0) {
                            iArr[i4] = 0 - (iArr[i4] - 5);
                        }
                    }
                    this.ek.max = 4;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = iArr[0];
                    this.ek.f2 = iArr[1];
                    this.ek.f3 = iArr[2];
                    this.ek.f4 = iArr[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] >= (byte) 2) {
                    gj(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] >= (byte) 2) {
                    lz((((((bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8)) - 5120) + 2816) * 30) / 5120);
                    return;
                }
                return;
            case (byte) 48:
                if (bArr[i + 2] < (byte) 2) {
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oa(1);
        this.ej.oh(1);
    }

    public void mo37e() {
    }

    public void mo38f() {
    }

    public void mo39g() {
    }

    void gj(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 64) != 0;
        boolean z3 = (bArr[0] & 128) != 0;
        boolean z4 = (bArr[0] & 16) != 0;
        boolean z5 = (bArr[0] & 32) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 4) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    int gk(int i) {
        return (i < 1 || i > 15) ? -1 : (i * 10) + 150;
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
