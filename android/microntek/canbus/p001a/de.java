package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import java.util.Locale;

public class de extends C0001b {
    public de(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void kv(byte[] bArr) {
        this.ei.onOff = true;
        if ((bArr[0] & 64) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[0] & 1) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[0] & 2) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch (bArr[1] & 15) {
            case R$styleable.MyButton_imgHeight /*1*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgSrc /*2*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = true;
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
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            default:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
        }
        this.ei.windLevel = (bArr[2] & 15) > 7 ? 7 : bArr[2] & 15;
        this.ei.windLevelMax = 7;
        int i = bArr[3] & 255;
        if (i == 17) {
            this.ei.tempLeft = 0;
        } else if (i == 32) {
            this.ei.tempLeft = 65535;
        } else if (i < 18 || i > 31) {
            this.ei.tempLeft = -1;
        } else {
            this.ei.tempLeft = i * 10;
        }
        this.ei.tempRight = this.ei.tempLeft;
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[0] & 4) != 0) {
            this.ei.rearLock = 1;
        } else {
            this.ei.rearLock = 0;
        }
        this.ei.seatShow = false;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int[] iArr;
        int i4;
        switch (bArr[i + 1]) {
            case (byte) 35:
                if (bArr[i + 2] >= (byte) 4) {
                    byte[] bArr2 = new byte[4];
                    while (i3 < 4) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    kv(bArr2);
                    if (lx(bArr2)) {
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] >= (byte) 2) {
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    iArr = new int[2];
                    i4 = 0;
                    while (i4 < 2) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
                        if (iArr[i4] >= 80 && iArr[i4] <= 120) {
                            iArr[i4] = 3;
                        } else if (iArr[i4] >= 40 && iArr[i4] <= 70) {
                            iArr[i4] = 2;
                        } else if (iArr[i4] < 0 || iArr[i4] > 30) {
                            iArr[i4] = 0;
                        } else {
                            iArr[i4] = 1;
                        }
                        i4++;
                    }
                    this.ek.max = 3;
                    this.ek.back_cnt = 2;
                    this.ek.b1 = iArr[0];
                    this.ek.b2 = iArr[1];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 37:
                if (bArr[i + 2] >= (byte) 4) {
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    iArr = new int[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4] & 15;
                        if (iArr[i4] > 10) {
                            iArr[i4] = 0;
                        } else {
                            iArr[i4] = iArr[i4] + 1;
                        }
                    }
                    this.ek.max = 10;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = iArr[0];
                    this.ek.b2 = iArr[1];
                    this.ek.b3 = iArr[2];
                    this.ek.b4 = iArr[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 40:
                if (bArr[i + 2] >= (byte) 2) {
                    kw(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 54:
                if (bArr[i + 2] >= (byte) 1) {
                    i4 = bArr[i + 3] & 127;
                    String str = "";
                    if ((bArr[i + 3] & 128) != 0) {
                        i4 = 0 - i4;
                    }
                    String str2 = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i4)}) + this.el.getString(R.string.c_dan);
                    Intent intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str2);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 99:
                if (bArr[i + 2] != (byte) 2) {
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oa(1);
    }

    void kw(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 64) != 0;
        boolean z3 = (bArr[0] & 128) != 0;
        boolean z4 = (bArr[0] & 16) != 0;
        boolean z5 = (bArr[0] & 32) != 0;
        boolean z6 = (bArr[0] & 0) != 0;
        if ((bArr[0] & 0) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }
}
