package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;

public class C0004b extends C0001b {
    private boolean f18i;
    private boolean f19j;

    public C0004b(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.f19j = true;
        this.f18i = true;
        this.eh = 6;
        this.ei = new AirCondition();
    }

    private void m400w(byte[] bArr) {
        this.ei.seatShow = false;
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
        this.ei.tempUnit = (bArr[4] & 64) != 0;
        int i = bArr[2] & 255;
        if (i == 0) {
            this.ei.tempLeft = 0;
        } else if (i == 127) {
            this.ei.tempLeft = 65535;
        } else if (i < 31 || i > 59) {
            this.ei.tempLeft = -1;
        } else {
            this.ei.tempLeft = i * 5;
            if (this.ei.tempUnit) {
                this.ei.tempLeft = ((this.ei.tempLeft * 9) / 5) + 320;
            }
        }
        i = bArr[3] & 255;
        if (i == 0) {
            this.ei.tempRight = 0;
        } else if (i == 127) {
            this.ei.tempRight = 65535;
        } else if (i < 31 || i > 59) {
            this.ei.tempRight = -1;
        } else {
            this.ei.tempRight = i * 5;
            if (this.ei.tempUnit) {
                this.ei.tempRight = ((this.ei.tempRight * 9) / 5) + 320;
            }
        }
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.rearLock = -1;
        if ((bArr[4] & 4) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
    }

    private void m401y(byte b) {
        this.ej.ob((byte) 39, new byte[]{(byte) 2, b}, 2);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        byte[] bArr2;
        byte[] bArr3;
        int i4;
        switch (bArr[i + 1]) {
            case (byte) -126:
                if (bArr[i + 2] == (byte) 6) {
                    bArr2 = new byte[8];
                    bArr2[0] = (byte) -126;
                    bArr2[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 33:
                if (bArr[i + 2] == (byte) 6) {
                    bArr2 = new byte[6];
                    while (i3 < 6) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    m400w(bArr2);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] == (byte) 4) {
                    bArr3 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        if (bArr[(i + 3) + i4] == (byte) 0) {
                            bArr3[i4] = (byte) 0;
                        } else if (bArr[(i + 3) + i4] < (byte) 3) {
                            bArr3[i4] = (byte) 1;
                        } else {
                            bArr3[i4] = (byte) (bArr[(i + 3) + i4] / 2);
                        }
                    }
                    this.ek.max = 15;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr3[0];
                    this.ek.b2 = bArr3[1];
                    this.ek.b3 = bArr3[2];
                    this.ek.b4 = bArr3[3];
                    if (!this.f19j) {
                        this.ek.front_cnt = 0;
                        this.ek.f1 = 0;
                        this.ek.f2 = 0;
                        this.ek.f3 = 0;
                        this.ek.f4 = 0;
                    }
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] == (byte) 6) {
                    bArr3 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        if (bArr[(i + 3) + i4] == (byte) 0) {
                            bArr3[i4] = (byte) 0;
                        } else if (bArr[(i + 3) + i4] < (byte) 3) {
                            bArr3[i4] = (byte) 1;
                        } else {
                            bArr3[i4] = (byte) (bArr[(i + 3) + i4] / 2);
                        }
                    }
                    this.ek.max = 15;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr3[0];
                    this.ek.f2 = bArr3[1];
                    this.ek.f3 = bArr3[2];
                    this.ek.f4 = bArr3[3];
                    if (!this.f18i) {
                        this.ek.back_cnt = 0;
                        this.ek.b1 = 0;
                        this.ek.b2 = 0;
                        this.ek.b3 = 0;
                        this.ek.b4 = 0;
                    }
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2 || bArr[i + 2] == (byte) 5) {
                    bArr2 = new byte[2];
                    while (i3 < 2) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    m418x(bArr2);
                    return;
                }
                return;
            case (byte) 37:
                if (bArr[i + 2] == (byte) 2) {
                    if ((bArr[i + 3] & 4) == 4) {
                        this.f19j = true;
                    } else {
                        this.f19j = false;
                        this.ek.max = 15;
                        this.ek.front_cnt = 0;
                        this.ek.f1 = 0;
                        this.ek.f2 = 0;
                        this.ek.f3 = 0;
                        this.ek.f4 = 0;
                    }
                    if ((bArr[i + 3] & 8) == 8) {
                        this.f18i = true;
                    } else {
                        this.f18i = false;
                        this.ek.max = 15;
                        this.ek.back_cnt = 0;
                        this.ek.b1 = 0;
                        this.ek.b2 = 0;
                        this.ek.b3 = 0;
                        this.ek.b4 = 0;
                    }
                    if (!this.f18i && !this.f19j) {
                        this.ek.zero_show = false;
                        this.ej.oe(this.ek);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 38:
                if (bArr[i + 2] != (byte) 6) {
                    return;
                }
                return;
            case (byte) 39:
                if (bArr[i + 2] >= (byte) 1) {
                    byte b = bArr[i + 3];
                    return;
                }
                return;
            case (byte) 48:
                if (bArr[i + 2] != (byte) 16) {
                    return;
                }
                return;
            case (byte) 64:
                if (bArr[i + 2] == (byte) 1) {
                    bArr2 = new byte[3];
                    bArr2[0] = (byte) 64;
                    bArr2[1] = (byte) 1;
                    while (i3 < 1) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 80:
                if (bArr[i + 2] == (byte) 8) {
                    bArr2 = new byte[10];
                    bArr2[0] = (byte) 80;
                    bArr2[1] = (byte) 8;
                    while (i3 < 8) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 81:
                i4 = bArr[i + 2] & 255;
                bArr3 = new byte[(i4 + 2)];
                bArr3[0] = (byte) 81;
                bArr3[1] = bArr[i + 2];
                while (i3 < i4) {
                    bArr3[i3 + 2] = bArr[(i + 3) + i3];
                    i3++;
                }
                this.ej.og(bArr3);
                return;
            case (byte) 82:
                if (bArr[i + 2] == (byte) 3) {
                    bArr2 = new byte[5];
                    bArr2[0] = (byte) 82;
                    bArr2[1] = (byte) 3;
                    while (i3 < 3) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 83:
                if (bArr[i + 2] == (byte) 3) {
                    bArr2 = new byte[5];
                    bArr2[0] = (byte) 83;
                    bArr2[1] = (byte) 3;
                    while (i3 < 3) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 112:
                if (bArr[i + 2] == (byte) 20) {
                    bArr2 = new byte[22];
                    bArr2[0] = (byte) 112;
                    bArr2[1] = (byte) 20;
                    while (i3 < 20) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 113:
                if (bArr[i + 2] == (byte) 20) {
                    bArr2 = new byte[22];
                    bArr2[0] = (byte) 113;
                    bArr2[1] = (byte) 20;
                    while (i3 < 20) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 114:
                if (bArr[i + 2] == (byte) 20) {
                    bArr2 = new byte[22];
                    bArr2[0] = (byte) 114;
                    bArr2[1] = (byte) 20;
                    while (i3 < 20) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 120:
                if (bArr[i + 2] == (byte) 5) {
                    bArr2 = new byte[7];
                    bArr2[0] = (byte) 120;
                    bArr2[1] = (byte) 5;
                    while (i3 < 5) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                } else if (bArr[i + 2] == (byte) 3) {
                    bArr2 = new byte[7];
                    bArr2[0] = (byte) 120;
                    bArr2[1] = (byte) 3;
                    while (i3 < 3) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                } else {
                    return;
                }
            case (byte) 121:
                if (bArr[i + 2] == (byte) 1) {
                    bArr2 = new byte[3];
                    bArr2[0] = (byte) 121;
                    bArr2[1] = (byte) 1;
                    while (i3 < 1) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oa(1);
        mo48p();
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

    public void mo48p() {
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        if (language.equals("zh")) {
            m401y((byte) 27);
        } else if (language.equals("en")) {
            m401y((byte) 2);
        } else if (language.equals("de")) {
            m401y((byte) 4);
        } else if (language.equals("it")) {
            m401y((byte) 5);
        } else if (language.equals("fr")) {
            m401y((byte) 6);
        } else if (language.equals("es")) {
            m401y((byte) 8);
        } else if (language.equals("tr")) {
            m401y((byte) 10);
        } else if (language.equals("ru")) {
            m401y((byte) 11);
        } else if (language.equals("nl")) {
            m401y((byte) 12);
        } else if (language.equals("pl")) {
            m401y((byte) 14);
        } else if (language.equals("sv")) {
            m401y((byte) 18);
        } else if (language.equals("pt")) {
            m401y((byte) 22);
        }
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }

    void m418x(byte[] bArr) {
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
}
