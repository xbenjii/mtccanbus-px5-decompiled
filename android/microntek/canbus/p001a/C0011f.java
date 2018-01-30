package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.provider.Settings.System;

public class C0011f extends C0001b {
    public C0011f(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 1;
    }

    private void an(byte[] bArr) {
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
        if (i == 0) {
            this.ei.tempLeft = 0;
        } else if (i == 31) {
            this.ei.tempLeft = 65535;
        } else if (i < 1 || i > 17) {
            this.ei.tempLeft = -1;
        } else {
            this.ei.tempLeft = (i + 35) * 5;
        }
        i = bArr[3] & 255;
        if (i == 0) {
            this.ei.tempRight = 0;
        } else if (i == 31) {
            this.ei.tempRight = 65535;
        } else if (i < 1 || i > 17) {
            this.ei.tempRight = -1;
        } else {
            this.ei.tempRight = (i + 35) * 5;
        }
        if ((bArr[0] & 32) == 0) {
            this.ei.windLoop = 0;
        } else if ((bArr[4] & 128) != 0) {
            this.ei.windLoop = 2;
        } else {
            this.ei.windLoop = 1;
        }
        if ((bArr[4] & 8) != 0) {
            this.ei.rearLock = 1;
        } else {
            this.ei.rearLock = 0;
        }
        if ((bArr[4] & 4) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatHotLeft = (bArr[4] & 48) >> 4;
        this.ei.seatHotRight = bArr[4] & 3;
    }

    private int ap(int i) {
        if (i < 0) {
            i = 0 - i;
        }
        int i2 = System.getInt(this.ej.getContentResolver(), "canbus1_angle", 450);
        if (i > i2 * 2) {
            System.putInt(this.ej.getContentResolver(), "canbus1_angle", 12376);
        }
        return i2;
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    void ao(byte[] bArr) {
        boolean z = true;
        Door door;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        if (this.ej.jg == 1) {
            door = this.em;
            z2 = (bArr[1] & 2) != 0;
            z3 = (bArr[1] & 1) != 0;
            z4 = (bArr[1] & 4) != 0;
            z5 = (bArr[1] & 8) != 0;
            z6 = (bArr[1] & 16) != 0;
            if ((bArr[1] & 32) == 0) {
                z = false;
            }
            if (door.la(z2, z3, z4, z5, z6, z) && System.getInt(this.ej.getContentResolver(), "com.microntek.controlinfo.door", 0) == 0) {
                this.ej.of(this.em);
                return;
            }
            return;
        }
        door = this.em;
        z2 = (bArr[1] & 1) != 0;
        z3 = (bArr[1] & 2) != 0;
        z4 = (bArr[1] & 4) != 0;
        z5 = (bArr[1] & 8) != 0;
        z6 = (bArr[1] & 16) != 0;
        if ((bArr[1] & 32) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z) && System.getInt(this.ej.getContentResolver(), "com.microntek.controlinfo.door", 0) == 0) {
            this.ej.of(this.em);
        }
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        byte[] bArr2;
        byte[] bArr3;
        int i4;
        switch (bArr[i + 1]) {
            case (byte) 22:
                if (bArr[i + 2] >= (byte) 2) {
                    Intent intent = new Intent("com.microntek.canbus.speed");
                    intent.putExtra("speed", "" + ((((bArr[i + 4] & 255) << 8) | (bArr[i + 3] & 255)) / 16));
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 5) {
                    bArr2 = new byte[5];
                    while (i3 < 5) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    an(bArr2);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] >= (byte) 4) {
                    bArr3 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    mb();
                    this.ek.max = 10;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr3[0];
                    this.ek.b2 = bArr3[1];
                    this.ek.b3 = bArr3[2];
                    this.ek.b4 = bArr3[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] >= (byte) 4) {
                    bArr3 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    mb();
                    this.ek.max = 10;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr3[0];
                    this.ek.f2 = bArr3[1];
                    this.ek.f3 = bArr3[2];
                    this.ek.f4 = bArr3[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] >= (byte) 2) {
                    bArr3 = new byte[2];
                    for (i4 = 0; i4 < 2; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    if (System.getInt(this.ej.getContentResolver(), "com.microntek.controlinfo.door", 0) != 1) {
                        ao(bArr3);
                        this.ej.oj(new byte[]{(byte) 36, (byte) 2, bArr[i + 3], bArr[i + 4]});
                        return;
                    }
                    return;
                }
                return;
            case (byte) 38:
                if (bArr[i + 2] >= (byte) 2) {
                    i3 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i3 >= 32768) {
                        i3 -= 65536;
                    }
                    i3 = (i3 * 30) / ap(i3);
                    Intent intent2 = new Intent("com.microntek.canbusbackview");
                    intent2.putExtra("canbustype", this.eh);
                    intent2.putExtra("lfribackview", 0 - i3);
                    this.el.sendBroadcast(intent2);
                    return;
                }
                return;
            case (byte) 65:
                bArr2 = new byte[20];
                while (i3 < 20) {
                    bArr2[i3] = bArr[(i + 1) + i3];
                    i3++;
                }
                this.ej.oj(bArr2);
                if (bArr[i + 3] == (byte) 1) {
                    ao(new byte[]{bArr[i + 4], (byte) (r0[1] & 31)});
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        System.putInt(this.ej.getContentResolver(), "com.microntek.controlinfo.door", 0);
        byte[] bArr = new byte[]{(byte) 65, (byte) 1};
        this.ej.ob((byte) -112, bArr, 2);
        this.ej.oa(1);
        this.ej.oh(1);
        bArr[0] = (byte) 39;
        bArr[1] = (byte) 0;
        this.ej.ob((byte) -112, bArr, 2);
    }

    public void mo37e() {
        this.ej.ob((byte) -64, new byte[]{(byte) 11, (byte) 48}, 2);
    }

    public void mo38f() {
        this.ej.ob((byte) -64, new byte[]{(byte) 10, (byte) 48}, 2);
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 34;
        this.ej.ob((byte) -61, bArr, 6);
    }

    public void mo39g() {
        this.ej.ob((byte) -64, new byte[]{(byte) 7, (byte) 48}, 2);
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 34;
        this.ej.ob((byte) -61, bArr, 6);
    }

    public void mo40h(String str, int i) {
        this.ej.ob((byte) -64, new byte[]{(byte) 0, (byte) 34}, 2);
    }

    public void mo41i() {
        this.ej.ob((byte) -64, new byte[]{(byte) 10, (byte) 48}, 2);
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 34;
        this.ej.ob((byte) -61, bArr, 6);
    }

    public void mo42j(int i, int i2, int i3) {
        this.ej.ob((byte) -64, new byte[]{(byte) 2, (byte) 34}, 2);
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 34;
        this.ej.ob((byte) -61, bArr, 6);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        this.ej.ob((byte) -64, new byte[]{(byte) 6, (byte) 18}, 2);
        byte[] bArr = new byte[6];
        bArr[3] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[1] = (byte) (i2 & 255);
        bArr[0] = (byte) ((i2 >> 8) & 255);
        this.ej.ob((byte) -61, bArr, 6);
    }

    public void mo45m(int i, int i2, int i3) {
        this.ej.ob((byte) -64, new byte[]{(byte) 9, (byte) 16}, 2);
        this.ej.ob((byte) -61, new byte[]{(byte) 0, (byte) (i2 & 255), (byte) (i & 255), (byte) (((i3 / 1000) / 60) / 60), (byte) (((i3 / 1000) / 60) % 60), (byte) ((i3 / 1000) % 60)}, 6);
    }

    public void mo46n() {
        this.ej.ob((byte) -64, new byte[]{(byte) 0, (byte) 34}, 2);
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 34;
        this.ej.ob((byte) -61, bArr, 6);
    }

    public void mo47o(byte b, int i, byte b2) {
        this.ej.ob((byte) -64, new byte[]{(byte) 1, (byte) 1}, 2);
        byte[] bArr = new byte[4];
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) (b + 1);
            bArr[1] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
        } else if (b == (byte) 3) {
            bArr[0] = (byte) 17;
            bArr[1] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
        } else if (b == (byte) 4) {
            bArr[0] = (byte) 18;
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
