package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;

public class cb extends C0001b {
    private byte[] cd;

    public cb(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.cd = new byte[5];
        this.eh = 42;
        this.ei = new AirCondition();
    }

    private void ik(byte[] bArr) {
        this.ei.seatShow = false;
        this.ei.windLoop = -1;
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
        int i;
        if ((bArr[4] & 1) == 0) {
            this.ei.tempUnit = false;
            i = bArr[2] & 255;
            if (i == 0) {
                this.ei.tempLeft = 0;
            } else if (i == 31) {
                this.ei.tempLeft = 65535;
            } else if (i >= 1 && i <= 29) {
                this.ei.tempLeft = (i + 35) * 5;
            } else if (i < 32 || i > 35) {
                this.ei.tempLeft = -1;
            } else {
                this.ei.tempLeft = i * 5;
            }
            i = bArr[3] & 255;
            if (i == 0) {
                this.ei.tempRight = 0;
            } else if (i == 31) {
                this.ei.tempRight = 65535;
            } else if (i >= 1 && i <= 29) {
                this.ei.tempRight = (i + 35) * 5;
            } else if (i < 32 || i > 35) {
                this.ei.tempRight = -1;
            } else {
                this.ei.tempRight = i * 5;
            }
        } else {
            this.ei.tempUnit = true;
            i = bArr[2] & 255;
            if (i == 0) {
                this.ei.tempLeft = 0;
            } else if (i == 255) {
                this.ei.tempLeft = 65535;
            } else {
                this.ei.tempLeft = i * 10;
            }
            i = bArr[3] & 255;
            if (i == 0) {
                this.ei.tempRight = 0;
            } else if (i == 255) {
                this.ei.tempRight = 65535;
            } else {
                this.ei.tempRight = i * 10;
            }
        }
        if ((bArr[4] & 8) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        byte[] bArr2;
        int i4;
        byte[] bArr3;
        switch (bArr[i + 1]) {
            case (byte) 29:
                if (this.eh != 42 && bArr[i + 2] == (byte) 4 && this.ej.oc() != 0) {
                    bArr2 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 4;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr2[0];
                    this.ek.f2 = bArr2[1];
                    this.ek.f3 = bArr2[2];
                    this.ek.f4 = bArr2[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 30:
                if (this.eh != 42 && bArr[i + 2] == (byte) 5 && this.ej.oc() != 0) {
                    bArr2 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 4;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr2[0];
                    this.ek.b2 = bArr2[1];
                    this.ek.b3 = bArr2[2];
                    this.ek.b4 = bArr2[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 31:
                if (bArr[i + 2] == (byte) 2) {
                    bArr3 = new byte[4];
                    bArr3[0] = (byte) 31;
                    bArr3[1] = (byte) 2;
                    while (i3 < 2) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 33:
                if (bArr[i + 2] == (byte) 7) {
                    bArr3 = new byte[9];
                    bArr3[0] = (byte) 33;
                    bArr3[1] = (byte) 7;
                    while (i3 < 7) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] == (byte) 3) {
                    bArr3 = new byte[5];
                    bArr3[0] = (byte) 34;
                    bArr3[1] = (byte) 3;
                    while (i3 < 3) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] == (byte) 13) {
                    bArr3 = new byte[15];
                    bArr3[0] = (byte) 35;
                    bArr3[1] = (byte) 13;
                    while (i3 < 13) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2) {
                    bArr2 = new byte[2];
                    for (i4 = 0; i4 < 2; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                    }
                    il(bArr2);
                    this.ej.oj(new byte[]{(byte) 36, (byte) 1, bArr2[1]});
                    return;
                }
                return;
            case (byte) 37:
                if (bArr[i + 2] == (byte) 6) {
                    bArr3 = new byte[8];
                    bArr3[0] = (byte) 37;
                    bArr3[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 38:
                if (bArr[i + 2] == (byte) 4) {
                    bArr3 = new byte[6];
                    bArr3[0] = (byte) 38;
                    bArr3[1] = (byte) 4;
                    while (i3 < 4) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 39:
                if (bArr[i + 2] == (byte) 31) {
                    bArr3 = new byte[33];
                    bArr3[0] = (byte) 39;
                    bArr3[1] = (byte) 31;
                    while (i3 < 31) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 40:
                if (bArr[i + 2] == (byte) 5) {
                    bArr2 = new byte[5];
                    for (i4 = 0; i4 < 5; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                        if (bArr2[i4] != this.cd[i4]) {
                            i3 = (byte) 1;
                        }
                        this.cd[i4] = bArr2[i4];
                    }
                    if (i3 != 0 && (bArr2[1] & 16) != 0) {
                        ik(bArr2);
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] == (byte) 2) {
                    i3 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i3 >= 2048) {
                        i3 -= 4096;
                    }
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", (0 - i3) / 10);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 49:
                if (bArr[i + 2] != (byte) 5) {
                    return;
                }
                return;
            case (byte) 50:
                if (bArr[i + 2] != (byte) 1) {
                    return;
                }
                return;
            case (byte) 80:
                if (bArr[i + 2] == (byte) 2) {
                    this.ej.oj(new byte[]{(byte) 80, bArr[i + 3]});
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

    public void mo40h(String str, int i) {
    }

    public void mo41i() {
    }

    void il(byte[] bArr) {
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
