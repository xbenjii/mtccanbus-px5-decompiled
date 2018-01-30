package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import java.util.Locale;

public class bz extends C0001b {
    public bz(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 13;
        this.ei = new AirCondition();
    }

    private void ig(byte[] bArr) {
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
        if ((bArr[1] & 64) != 0) {
            this.ei.modeDual = 0;
        } else {
            this.ei.modeDual = 1;
        }
        if ((bArr[1] & 32) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[0] & 2) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        if ((bArr[2] & 64) != 0) {
            this.ei.windUp = true;
        } else {
            this.ei.windUp = false;
        }
        if ((bArr[2] & 32) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((bArr[1] & 16) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        if ((bArr[1] & 16) != 0) {
            this.ei.windLevel = 0;
        } else {
            this.ei.windLevel = bArr[1] & 15;
        }
        if (this.ei.windLevel == 8) {
            this.ei.windLevel = 7;
        }
        this.ei.windLevelMax = 7;
        int i = bArr[3] & 255;
        if (i == 0) {
            this.ei.tempLeft = 0;
        } else if (i == 63) {
            this.ei.tempLeft = 65535;
        } else if (i < 11 || i > 44) {
            this.ei.tempLeft = -1;
        } else {
            this.ei.tempLeft = (i + 19) * 5;
        }
        i = bArr[4] & 255;
        if (i == 0) {
            this.ei.tempRight = 0;
        } else if (i == 63) {
            this.ei.tempRight = 65535;
        } else if (i < 11 || i > 44) {
            this.ei.tempRight = -1;
        } else {
            this.ei.tempRight = (i + 19) * 5;
        }
        if ((bArr[0] & 8) != 0) {
            this.ei.windLoop = 2;
        } else if ((bArr[0] & 4) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.rearLock = -1;
        this.ei.acMax = -1;
        this.ei.seatHotLeft = (bArr[5] & 224) >> 5;
        this.ei.seatHotRight = (bArr[5] & 29) >> 2;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) 8:
                if (bArr[i + 2] == (byte) 7) {
                    byte[] bArr2 = new byte[7];
                    for (int i3 = 0; i3 < 7; i3++) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                    }
                    ig(bArr2);
                    this.ej.od(this.ei);
                    float f = ((float) (bArr2[6] & 255)) - 100.0f;
                    String str = "";
                    String str2 = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(f * 0.5f)}) + this.el.getString(R.string.c_dan);
                    Intent intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str2);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 31:
                if (bArr[i + 2] != (byte) 3) {
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
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
