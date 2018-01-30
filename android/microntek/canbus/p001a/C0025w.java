package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;
import android.provider.Settings.System;

public class C0025w extends C0001b {
    public C0025w(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void bq(byte[] bArr) {
        if ((bArr[1] & 15) > 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[0] & 8) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
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
        switch (bArr[2] & 255) {
            case R$styleable.MyButton_imgWidth /*0*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgHeight /*1*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc /*2*/:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            default:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
        }
        this.ei.windLevel = bArr[1] & 15;
        this.ei.windLevelMax = 8;
        int i = bArr[3] & 255;
        AirCondition airCondition = this.ei;
        i = bs(i);
        this.ei.tempLeft = i;
        airCondition.tempRight = i;
        if ((bArr[0] & 4) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatShow = false;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4;
        switch (bArr[i + 1]) {
            case (byte) 22:
                if (bArr[i + 2] < (byte) 2) {
                    return;
                }
                return;
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 4) {
                    byte[] bArr2 = new byte[4];
                    while (i3 < 4) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    bq(bArr2);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] >= (byte) 4) {
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    this.ek.max = 3;
                    for (i4 = 0; i4 < 4; i4++) {
                        this.ep[i4] = bArr[(i + 3) + i4] & 255;
                        this.ep[i4] = 0 - (this.ep[i4] - 4);
                    }
                    this.ek.back_cnt = 4;
                    this.ek.b1 = this.ep[0];
                    this.ek.b2 = this.ep[1];
                    this.ek.b3 = this.ep[2];
                    this.ek.b4 = this.ep[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] >= (byte) 4) {
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    this.ek.max = 3;
                    for (i4 = 0; i4 < 4; i4++) {
                        this.ep[i4] = bArr[(i + 3) + i4] & 255;
                        this.ep[i4] = 0 - (this.ep[i4] - 4);
                    }
                    this.ek.front_cnt = 2;
                    this.ek.f1 = this.ep[0];
                    this.ek.f2 = this.ep[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 58:
                if (bArr[i + 2] >= (byte) 3) {
                    br(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            default:
                return;
        }
    }

    void br(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 16) != 0;
        boolean z3 = (bArr[0] & 32) != 0;
        boolean z4 = (bArr[0] & 4) != 0;
        boolean z5 = (bArr[0] & 8) != 0;
        boolean z6 = (bArr[0] & 2) != 0;
        if ((bArr[0] & 1) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    int bs(int i) {
        return (i < 1 || i > 16) ? -1 : (i * 10) + 150;
    }

    public int bt(String str) {
        return System.getInt(this.ej.getContentResolver(), str, 0);
    }

    public void mo36d() {
        this.ej.oa(1);
        this.ej.ob((byte) -123, new byte[]{(byte) bt("mParking_mode"), (byte) bt("mMaintenance")}, 2);
    }

    public void mo37e() {
    }

    public void mo38f() {
    }

    public void mo39g() {
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
