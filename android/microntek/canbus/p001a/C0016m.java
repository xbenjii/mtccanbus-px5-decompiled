package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class C0016m extends C0001b {
    public C0016m(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 84;
    }

    private void bb(byte[] bArr) {
        this.ei.seatShow = false;
        if ((bArr[0] & 64) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[0] & 1) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[0] & 8) != 0) {
            this.ei.modeAuto = 2;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[4] & 255) == 7) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[4] & 255) == 8) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch (bArr[4] & 255) {
            case R$styleable.MyButton_imgSrc /*2*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
        }
        this.ei.windLevel = bArr[5] & 7;
        this.ei.windLevelMax = 7;
        int i = bArr[6] & 255;
        this.ei.tempLeft = bd(i);
        i = bArr[7] & 255;
        this.ei.tempRight = bd(i);
        if ((bArr[1] & 16) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[4] & 4) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4;
        switch (bArr[i + 1]) {
            case (byte) 17:
                if (bArr[i + 2] == (byte) 14) {
                    i4 = bArr[i + 9] & 255;
                    i3 = (bArr[i + 10] & 255) + ((i4 & 127) * 256);
                    if ((i4 & 128) == 0) {
                        i3 = 0 - i3;
                    }
                    i3 = (i3 * 30) / 5000;
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i3);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 18:
                if (bArr[i + 2] == (byte) 10) {
                    bc(new byte[]{bArr[i + 5]});
                    return;
                }
                return;
            case (byte) 19:
                if (bArr[i + 2] != (byte) 14) {
                    return;
                }
                return;
            case (byte) 49:
                if (mc(bArr[i + 2], 12)) {
                    byte[] bArr2 = new byte[this.er];
                    while (i3 < this.er) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    if (lx(bArr2)) {
                        bb(bArr2);
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 65:
                if (mc(bArr[i + 2], 12)) {
                    int[] iArr = new int[this.er];
                    for (i4 = 0; i4 < this.er; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
                        if (iArr[i4] > 4) {
                            iArr[i4] = 0;
                        }
                    }
                    if (iArr[10] == 1) {
                        if (this.ej.oc() == 0) {
                            this.ek.zero_show = false;
                        } else {
                            this.ek.zero_show = true;
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
                }
                return;
            case (byte) 81:
                if (bArr[i + 2] != (byte) 14) {
                    return;
                }
                return;
            default:
                i4 = i2 - i;
                if (i4 > 3 && i4 <= 40) {
                    byte[] bArr3 = new byte[i4];
                    while (i3 < i4) {
                        bArr3[i3] = bArr[(i + 1) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
        }
    }

    void bc(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 128) != 0;
        boolean z3 = (bArr[0] & 64) != 0;
        boolean z4 = (bArr[0] & 32) != 0;
        boolean z5 = (bArr[0] & 16) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 4) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    int bd(int i) {
        return i == 254 ? 0 : i == 255 ? 65535 : i < 100 ? i * 10 : -1;
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
