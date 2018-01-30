package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class C0013h extends C0001b {
    public C0013h(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 52;
    }

    private void ar(byte[] bArr) {
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
        if ((bArr[0] & 8) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[4] & 128) != 0) {
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
        this.ei.windLevelMax = 7;
        this.ei.tempUnit = (bArr[4] & 1) != 0;
        this.ei.tempLeft = au(bArr[2] & 255);
        this.ei.tempRight = au(bArr[3] & 255);
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatShow = false;
    }

    void as(byte[] bArr) {
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

    public void at() {
        byte[] bArr = new byte[2];
        bArr[0] = (byte) 48;
        this.ej.ob((byte) -126, bArr, 2);
    }

    int au(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 31) {
            return 65535;
        }
        if (i >= 1 && i <= 28) {
            return !this.ei.tempUnit ? ((i * 10) / 2) + 155 : (i * 10) + 590;
        } else {
            if (i < 32 || i > 36) {
                return -1;
            }
            if (!this.ei.tempUnit) {
                i = ((i * 10) / 2) + 160;
            }
            return i;
        }
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        byte[] bArr2;
        switch (bArr[i + 1]) {
            case (byte) 35:
                if (bArr[i + 2] >= (byte) 7) {
                    bArr2 = new byte[7];
                    while (i3 < 7) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    ar(bArr2);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 40:
                if (bArr[i + 2] == (byte) 2) {
                    bArr2 = new byte[]{bArr[i + 3]};
                    if ((bArr2[0] & 1) != 0) {
                        as(bArr2);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 48:
                if (bArr[i + 2] == (byte) 2) {
                    i3 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i3 >= 32768) {
                        i3 -= 65536;
                    }
                    i3 = (i3 * 30) / 6144;
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i3);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
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
