package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import java.util.Locale;

public class aw extends C0001b {
    public aw(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void ef(byte[] bArr) {
        if ((bArr[4] & 16) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[3] & 1) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[4] & 128) != 0) {
            this.ei.modeAuto = 2;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[4] & 64) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        if ((bArr[3] & 8) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[3] & 4) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        if ((bArr[3] & 64) != 0) {
            this.ei.windUp = true;
        } else {
            this.ei.windUp = false;
        }
        if ((bArr[3] & 16) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((bArr[3] & 32) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[2] & 15;
        this.ei.windLevelMax = 7;
        int i = bArr[0] & 255;
        this.ei.tempLeft = eg(i);
        i = bArr[1] & 255;
        this.ei.tempRight = eg(i);
        if ((bArr[3] & 2) == 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[4] & 32) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatShow = false;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgHeight /*1*/:
                if (bArr[i + 2] >= (byte) 6) {
                    int i3;
                    byte[] bArr2 = new byte[6];
                    for (i3 = 0; i3 < 5; i3++) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                    }
                    if (lx(bArr2)) {
                        ef(bArr2);
                        this.ej.od(this.ei);
                    }
                    int i4 = bArr[(i + 3) + 5] & 255;
                    String str = "";
                    if (i4 != 255) {
                        i3 = i4 - 128;
                        str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i3)}) + this.el.getString(R.string.c_dan);
                    }
                    Intent intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    int eg(int i) {
        return i == 0 ? 0 : i == 255 ? 65535 : (i < 18 || i > 32) ? -1 : i * 10;
    }
}
