package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.provider.Settings.System;

public class C0020q extends C0001b {
    private boolean f26p;

    public C0020q(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 60;
    }

    private void bi(byte[] bArr) {
        this.ei.seatShow = false;
        this.ei.wind_FrameShow = false;
        this.ei.modeDual = -1;
        if ((bArr[4] & 15) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[1] & 64) != 0) {
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
        if ((bArr[1] & 16) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[1] & 32) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        if ((bArr[4] & 16) != 0) {
            this.ei.windUp = true;
        } else {
            this.ei.windUp = false;
        }
        if ((bArr[4] & 32) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((bArr[4] & 64) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[4] & 15;
        this.ei.windLevelMax = 7;
        int i = bArr[2] & 255;
        if (i == 1) {
            this.ei.tempLeft = 0;
        } else if (i == 255) {
            this.ei.tempLeft = 65535;
        } else if (i < 116 || i > 144) {
            this.ei.tempLeft = -1;
        } else {
            this.ei.tempLeft = ((i * 10) / 2) - 400;
        }
        i = bArr[3] & 255;
        if (i == 1) {
            this.ei.tempRight = 0;
        } else if (i == 255) {
            this.ei.tempRight = 65535;
        } else if (i < 116 || i > 144) {
            this.ei.tempRight = -1;
        } else {
            this.ei.tempRight = ((i * 10) / 2) - 400;
        }
    }

    private int bk(byte b) {
        return (b & 255) == 255 ? 0 : b & 255;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        byte[] bArr2;
        int i4;
        byte[] bArr3;
        switch (bArr[i + 1]) {
            case (byte) -126:
                if (bArr[i + 2] == (byte) 8) {
                    bArr2 = new byte[8];
                    while (i3 < 8) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    bi(bArr2);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 17:
                if (bArr[i + 2] == (byte) 8) {
                    bArr2 = new byte[2];
                    bArr2[0] = bArr[i + 7];
                    bj(bArr2);
                    i4 = (bArr[i + 10] & 255) + ((bArr[i + 9] & 255) << 8);
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    i4 = (i4 * 35) / 540;
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i4);
                    this.el.sendBroadcast(intent);
                    if ((bArr[i + 3] & 32) != 0) {
                        this.f26p = true;
                        return;
                    } else {
                        this.f26p = false;
                        return;
                    }
                }
                return;
            case (byte) 65:
                if (this.f26p && bArr[i + 2] == (byte) 12) {
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    bArr3 = new byte[12];
                    for (i4 = 0; i4 < 12; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 4;
                    this.ek.back_cnt = 3;
                    this.ek.b1 = bk(bArr3[0]);
                    this.ek.b2 = bk(bArr3[1]);
                    this.ek.b3 = bk(bArr3[3]);
                    this.ek.max = 4;
                    this.ek.front_cnt = 3;
                    this.ek.f1 = bArr3[4];
                    this.ek.f2 = bArr3[5];
                    this.ek.f3 = bArr3[7];
                    if ((bArr3[10] & 1) != 0) {
                        this.ej.oe(this.ek);
                        return;
                    }
                    return;
                }
                return;
            default:
                if (bArr[i + 2] >= (byte) 2) {
                    bArr3 = new byte[((bArr[i + 2] & 255) + 2)];
                    bArr3[0] = (byte) (bArr[i + 1] & 255);
                    bArr3[1] = (byte) (bArr[i + 2] & 255);
                    for (byte b = (byte) 0; b < bArr[i + 2]; b++) {
                        bArr3[b + 2] = bArr[(i + 3) + b];
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
        }
    }

    void bj(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 64) != 0;
        boolean z3 = (bArr[0] & 128) != 0;
        boolean z4 = (bArr[0] & 16) != 0;
        boolean z5 = (bArr[0] & 32) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 0) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    public void mo36d() {
        int i = System.getInt(this.ej.getContentResolver(), "com.microntek.controlsettings.car.type", 1);
        this.ej.ol((byte) 45, new byte[]{(byte) 1, (byte) i}, 2);
        this.ej.oa(1);
        this.ej.oh(1);
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
