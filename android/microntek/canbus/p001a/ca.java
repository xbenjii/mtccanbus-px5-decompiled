package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class ca extends C0001b {
    public ca(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 38;
    }

    private void ih(byte[] bArr) {
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
        if ((bArr[0] & 16) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[0] & 4) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        if ((bArr[0] & 1) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[0] & 8) != 0) {
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
        if (this.ei.windLevel > 8) {
            this.ei.windLevel = 8;
        }
        this.ei.windLevelMax = 8;
        int i = bArr[2] & 255;
        this.ei.tempLeft = ij(i);
        i = bArr[3] & 255;
        this.ei.tempRight = ij(i);
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[0] & 2) != 0) {
            this.ei.rearLock = 1;
        } else {
            this.ei.rearLock = 0;
        }
        this.ei.seatShow = false;
    }

    private int ij(int i) {
        return i == 0 ? 0 : i == 255 ? 65535 : (i < 30 || i > 64) ? -1 : i * 5;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        switch (bArr[i + 1]) {
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 5) {
                    byte[] bArr2 = new byte[5];
                    while (i3 < 5) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    if (lx(bArr2)) {
                        ih(bArr2);
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] >= (byte) 2) {
                    ii(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 1) {
                    i3 = bArr[i + 3] & 255;
                    if (i3 <= 50) {
                        i3 = 0 - i3;
                    } else if (i3 >= 128 || i3 <= 178) {
                        i3 -= 128;
                    }
                    i3 = (i3 * 35) / 50;
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
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 9;
        bArr[6] = (byte) 0;
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo38f() {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 10;
        bArr[1] = (byte) -1;
        bArr[2] = (byte) -1;
        bArr[3] = (byte) -1;
        bArr[4] = (byte) -1;
        bArr[5] = (byte) -1;
        bArr[6] = (byte) -1;
        bArr[7] = (byte) -1;
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo39g() {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 7;
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo40h(String str, int i) {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 9;
        bArr[6] = (byte) 0;
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo41i() {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 10;
        bArr[1] = (byte) -1;
        bArr[2] = (byte) -1;
        bArr[3] = (byte) -1;
        bArr[4] = (byte) -1;
        bArr[5] = (byte) -1;
        bArr[6] = (byte) -1;
        bArr[7] = (byte) -1;
        this.ej.ob((byte) -126, bArr, 9);
    }

    void ii(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 1) != 0;
        boolean z3 = (bArr[0] & 2) != 0;
        boolean z4 = (bArr[0] & 4) != 0;
        boolean z5 = (bArr[0] & 8) != 0;
        boolean z6 = (bArr[0] & 16) != 0;
        if ((bArr[0] & 32) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 2;
        bArr[3] = (byte) ((i >> 8) & 255);
        bArr[4] = (byte) (i & 255);
        bArr[7] = (byte) ((i2 / 60) % 60);
        bArr[8] = (byte) (i2 % 60);
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 5;
        bArr[3] = (byte) ((i2 >> 8) & 255);
        bArr[4] = (byte) (i2 & 255);
        bArr[7] = (byte) (((i3 / 1000) / 60) % 60);
        bArr[8] = (byte) ((i3 / 1000) % 60);
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo44l() {
        this.ej.ob((byte) -126, new byte[]{(byte) 15, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1}, 9);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 3;
        bArr[3] = (byte) ((i2 >> 8) & 255);
        bArr[4] = (byte) (i2 & 255);
        bArr[7] = (byte) (((i3 / 1000) / 60) % 60);
        bArr[8] = (byte) ((i3 / 1000) % 60);
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo46n() {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 0;
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 1;
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[2] = (byte) (b + 17);
            bArr[4] = (byte) (i & 255);
            bArr[3] = (byte) ((i >> 8) & 255);
        } else if (b == (byte) 3 && b <= (byte) 4) {
            bArr[2] = (byte) 33;
            bArr[4] = (byte) (i & 255);
            bArr[3] = (byte) ((i >> 8) & 255);
        } else if (b == (byte) 4) {
            bArr[2] = (byte) 34;
            bArr[4] = (byte) (i & 255);
            bArr[3] = (byte) ((i >> 8) & 255);
        }
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
