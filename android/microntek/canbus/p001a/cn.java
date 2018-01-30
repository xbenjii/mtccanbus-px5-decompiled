package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;

public class cn extends C0001b {
    private byte[] co;

    public cn(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.co = new byte[6];
        this.eh = 71;
    }

    private void jm(byte[] bArr) {
        this.ei.seatShow = false;
        this.ei.onOff = true;
        if ((bArr[4] & 2) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[4] & 1) != 0) {
            this.ei.modeAuto = 2;
        } else {
            this.ei.modeAuto = 0;
        }
        if (this.ej.jg == 0) {
            this.ei.acMax = -1;
            if ((bArr[4] & 16) != 0) {
                this.ei.modeDual = 1;
            } else {
                this.ei.modeDual = 0;
            }
        } else if (this.ej.jg == 1) {
            this.ei.modeDual = -1;
            if ((bArr[4] & 16) != 0) {
                this.ei.acMax = 1;
            } else {
                this.ei.acMax = 0;
            }
        }
        switch (bArr[3] & 7) {
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
            default:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
        }
        if ((bArr[4] & 32) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[4] & 64) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        this.ei.windLevel = bArr[2] & 7;
        this.ei.windLevelMax = 7;
        int i = bArr[0] & 255;
        if (i == 36) {
            this.ei.tempLeft = 0;
        } else if (i == 64) {
            this.ei.tempLeft = 65535;
        } else if (i < 34 || i > 62) {
            this.ei.tempLeft = -1;
        } else {
            this.ei.tempLeft = (i * 10) / 2;
        }
        i = bArr[1] & 255;
        if (i == 36) {
            this.ei.tempRight = 0;
        } else if (i == 64) {
            this.ei.tempRight = 65535;
        } else if (i < 34 || i > 62) {
            this.ei.tempRight = -1;
        } else {
            this.ei.tempRight = (i * 10) / 2;
        }
        if ((bArr[4] & 8) != 0) {
            this.ei.windLoop = 1;
        } else if ((bArr[4] & 4) != 0) {
            this.ei.windLoop = 0;
        } else {
            this.ei.windLoop = -1;
        }
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        Object obj = 1;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgWidth /*0*/:
                if (bArr[i + 2] == (byte) 6) {
                    byte[] bArr2 = new byte[5];
                    Object obj2 = null;
                    for (int i3 = 0; i3 < 5; i3++) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        if (bArr2[i3] != this.co[i3]) {
                            obj2 = 1;
                        }
                        this.co[i3] = bArr2[i3];
                    }
                    if (obj2 != null) {
                        jm(bArr2);
                        this.ej.od(this.ei);
                    }
                    if (this.ej.jg == 0) {
                        if ((bArr2[4] & 128) == 0) {
                            obj = null;
                        }
                        int i4 = bArr[(i + 3) + 5] & 255;
                        String str = "";
                        String str2 = obj != null ? " OUT" : i4 >= 200 ? " " + (0 - (255 - i4)) + this.el.getString(R.string.c_dan) : " " + i4 + this.el.getString(R.string.c_dan);
                        Intent intent = new Intent("com.canbus.temperature");
                        intent.putExtra("temperature", str2);
                        this.el.sendBroadcast(intent);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }
}
