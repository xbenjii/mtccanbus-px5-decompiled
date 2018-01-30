package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;
import android.microntek.canbus.serializable.Radar;
import java.util.Locale;

public class ae extends C0001b {
    byte[] f12v;
    private boolean f13w;
    private boolean f14x;

    public ae(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.f13w = true;
        this.f14x = true;
        this.f12v = new byte[8];
        this.eh = 26;
        this.ei = new AirCondition();
        this.em = new Door();
    }

    private void ce(byte[] bArr) {
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
        if ((bArr[0] & 1) != 0) {
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
        } else if (i < 2 || i > 254) {
            this.ei.tempLeft = -1;
        } else {
            this.ei.tempLeft = i * 10;
        }
        i = bArr[3] & 255;
        if (i == 1) {
            this.ei.tempRight = 0;
        } else if (i == 255) {
            this.ei.tempRight = 65535;
        } else if (i < 2 || i > 254) {
            this.ei.tempRight = -1;
        } else {
            this.ei.tempRight = i * 10;
        }
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 2;
        } else if ((bArr[0] & 16) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[1] & 128) != 0) {
            this.ei.rearLock = 1;
        } else {
            this.ei.rearLock = 0;
        }
        if ((bArr[0] & 2) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatHotLeft = (bArr[1] & 12) >> 2;
        this.ei.seatHotRight = bArr[1] & 3;
        this.ej.od(this.ei);
    }

    private int cg(int i) {
        int i2 = 10;
        int i3 = i & 255;
        if (i3 > 150 || i3 == 0) {
            i2 = 0;
        } else if (i3 >= 10) {
            i2 = i3;
        }
        return i2 / 10;
    }

    private void ch(Radar radar) {
        this.ej.oe(radar);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4;
        int i5;
        switch (bArr[i + 1]) {
            case (byte) -16:
                if (bArr[i + 2] == (byte) 17) {
                    byte[] bArr2 = new byte[18];
                    bArr2[0] = bArr[i + 1];
                    while (i3 < 17) {
                        bArr2[i3 + 1] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 71:
                if (bArr[i + 2] == (byte) 14) {
                    this.ej.oj(new byte[]{bArr[i + 1], bArr[i + 16]});
                    return;
                }
                return;
            case (byte) 114:
                if (bArr[i + 2] == (byte) 14) {
                    int i6 = bArr[i + 7] & 255;
                    i4 = bArr[i + 8] & 255;
                    if (!(i6 == 255 && i4 == 255)) {
                        i5 = 256;
                        if (i6 == 255) {
                            i5 = i4;
                        }
                        if (i4 == 255) {
                            i5 = 0 - i6;
                        }
                        if (i5 != 256) {
                            i5 = (i5 * 30) / 135;
                            Intent intent = new Intent("com.microntek.canbusbackview");
                            intent.putExtra("canbustype", this.eh);
                            intent.putExtra("lfribackview", i5);
                            this.el.sendBroadcast(intent);
                        }
                    }
                    if (((byte) (bArr[i + 3] & 32)) == (byte) 32) {
                        for (i5 = 0; i5 <= 7; i5++) {
                            this.ep[i5] = bArr[(i + 9) + i5];
                        }
                        this.ek.max = 15;
                        this.ek.front_cnt = 4;
                        this.ek.f1 = cg(this.ep[4]);
                        this.ek.f2 = cg(this.ep[5]);
                        this.ek.f3 = cg(this.ep[6]);
                        this.ek.f4 = cg(this.ep[7]);
                        this.ek.back_cnt = 4;
                        this.ek.b1 = cg(this.ep[0]);
                        this.ek.b2 = cg(this.ep[1]);
                        this.ek.b3 = cg(this.ep[2]);
                        this.ek.b4 = cg(this.ep[3]);
                        if (this.ej.oc() == 0) {
                            this.ek.zero_show = false;
                        } else {
                            this.ek.zero_show = true;
                        }
                        ch(this.ek);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 115:
                if (bArr[i + 2] == (byte) 8) {
                    byte[] bArr3 = new byte[8];
                    i5 = 0;
                    for (i4 = 0; i4 < 8; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                        if (bArr3[i4] != this.f12v[i4]) {
                            i5 = true;
                        }
                        this.f12v[i4] = bArr3[i4];
                    }
                    if (!(i5 == 0 || (bArr3[0] & 128) == 0)) {
                        ce(bArr3);
                    }
                    if ((bArr3[7] & 1) != 0) {
                        cf(bArr3);
                    }
                    String str = "";
                    float f = (((float) (bArr3[6] & 255)) * 0.5f) - 40.0f;
                    String str2 = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(f)}) + this.el.getString(R.string.c_dan);
                    Intent intent2 = new Intent("com.canbus.temperature");
                    intent2.putExtra("temperature", str2);
                    this.el.sendBroadcast(intent2);
                    return;
                }
                return;
            default:
                if (bArr[i + 2] == (byte) 10) {
                    byte[] bArr4 = new byte[11];
                    bArr4[0] = bArr[i + 1];
                    for (i5 = 0; i5 < 10; i5++) {
                        bArr4[i5 + 1] = bArr[(i + 3) + i5];
                    }
                    this.ej.oj(bArr4);
                    return;
                }
                return;
        }
    }

    void cf(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[7] & 64) != 0;
        boolean z3 = (bArr[7] & 128) != 0;
        boolean z4 = (bArr[7] & 32) != 0;
        boolean z5 = (bArr[7] & 16) != 0;
        boolean z6 = (bArr[7] & 8) != 0;
        if ((bArr[7] & 4) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    public void mo36d() {
        this.ej.oa(1);
        this.ej.oh(1);
    }

    public void mo37e() {
        this.ej.ok((byte) -46, new byte[]{(byte) 10, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo38f() {
        this.ej.ok((byte) -46, new byte[]{(byte) 8, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo39g() {
        this.ej.ok((byte) -46, new byte[]{(byte) 12, (byte) 65, (byte) 86, (byte) 73, (byte) 78, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo40h(String str, int i) {
        this.ej.ok((byte) -46, new byte[]{(byte) 10, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo41i() {
        this.ej.ok((byte) -46, new byte[]{(byte) 8, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 7, (byte) 68, (byte) 86, (byte) 68, (byte) 58, (byte) 32, (byte) 32, (byte) 58, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        bArr[5] = (byte) (((i2 / 60) / 10) + 48);
        bArr[6] = (byte) (((i2 % 60) % 10) + 48);
        bArr[8] = (byte) (((i2 / 60) / 10) + 48);
        bArr[9] = (byte) (((i2 % 60) % 10) + 48);
        this.ej.ok((byte) -46, bArr, 13);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        r0 = new byte[13];
        int i5 = i3 / 1000;
        r0[3] = (byte) ((((i5 / 60) / 60) / 10) + 48);
        r0[4] = (byte) ((((i5 / 60) / 60) % 10) + 48);
        r0[5] = (byte) 58;
        r0[6] = (byte) ((((i5 / 60) % 60) / 10) + 48);
        r0[7] = (byte) ((((i5 / 60) % 60) % 10) + 48);
        r0[8] = (byte) 58;
        r0[9] = (byte) (((i5 % 60) / 10) + 48);
        r0[10] = (byte) (((i5 % 60) % 10) + 48);
        r0[11] = (byte) 32;
        r0[12] = (byte) 32;
        this.ej.ok((byte) -46, r0, 13);
    }

    public void mo44l() {
        this.ej.ok((byte) -46, new byte[]{(byte) 13, (byte) 32, (byte) 109, (byte) 111, (byte) 118, (byte) 105, (byte) 101, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo45m(int i, int i2, int i3) {
        r0 = new byte[13];
        int i4 = i3 / 1000;
        r0[5] = (byte) ((((i4 / 60) / 60) / 10) + 48);
        r0[6] = (byte) ((((i4 / 60) / 60) % 10) + 48);
        r0[7] = (byte) 58;
        r0[8] = (byte) ((((i4 / 60) % 60) / 10) + 48);
        r0[9] = (byte) ((((i4 / 60) % 60) % 10) + 48);
        r0[10] = (byte) 58;
        r0[11] = (byte) (((i4 % 60) / 10) + 48);
        r0[12] = (byte) (((i4 % 60) % 10) + 48);
        this.ej.ok((byte) -46, r0, 13);
    }

    public void mo46n() {
        this.ej.ok((byte) -46, new byte[]{(byte) 0, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[]{(byte) 1, (byte) 45, (byte) 1, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) (b + 1);
            bArr[1] = (byte) 48;
            bArr[2] = (byte) (b2 + 49);
            if (this.eh == 26) {
                bArr[1] = (byte) 32;
                bArr[2] = (byte) 32;
            }
            if (i > 9999) {
                bArr[4] = (byte) ((i / 10000) + 48);
                bArr[5] = (byte) (((i / 1000) % 10) + 48);
                bArr[6] = (byte) (((i / 100) % 10) + 48);
                bArr[7] = (byte) 46;
                bArr[8] = (byte) (((i / 10) % 10) + 48);
                bArr[9] = (byte) ((i % 10) + 48);
            } else {
                bArr[5] = (byte) ((i / 1000) + 48);
                bArr[6] = (byte) (((i / 100) % 10) + 48);
                bArr[7] = (byte) 46;
                bArr[8] = (byte) (((i / 10) % 10) + 48);
                bArr[9] = (byte) ((i % 10) + 48);
            }
            bArr[10] = (byte) 77;
            bArr[11] = (byte) 72;
            bArr[12] = (byte) 90;
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[0] = (byte) (b - 2);
            bArr[1] = (byte) 48;
            bArr[2] = (byte) (b2 + 49);
            if (this.eh == 26) {
                bArr[0] = (byte) (b + 1);
                bArr[1] = (byte) 32;
                bArr[2] = (byte) 32;
            }
            if (i > 999) {
                bArr[4] = (byte) ((i / 1000) + 48);
                bArr[5] = (byte) (((i / 100) % 10) + 48);
                bArr[6] = (byte) (((i / 10) % 10) + 48);
                bArr[7] = (byte) ((i % 10) + 48);
            } else {
                bArr[5] = (byte) ((i / 100) + 48);
                bArr[6] = (byte) (((i / 10) % 10) + 48);
                bArr[7] = (byte) ((i % 10) + 48);
            }
            bArr[9] = (byte) 75;
            bArr[10] = (byte) 72;
            bArr[11] = (byte) 90;
        }
        this.ej.ok((byte) -46, bArr, 13);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
