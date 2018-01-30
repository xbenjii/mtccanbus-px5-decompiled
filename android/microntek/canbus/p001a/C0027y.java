package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.os.Handler;
import java.util.Locale;

public class C0027y extends C0001b {
    byte[] f27r = new byte[6];
    private Handler f28s = null;

    public C0027y(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void bv(byte[] bArr) {
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
        if ((bArr[0] & 16) != 0) {
            this.ei.modeAuto = 2;
        } else if ((bArr[0] & 8) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        this.ei.modeDual = -1;
        if ((bArr[0] & 2) != 0) {
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
        if (i == 0) {
            this.ei.tempRight = 0;
        } else if (i == 31) {
            this.ei.tempRight = 65535;
        } else if (i < 1 || i > 17) {
            this.ei.tempRight = -1;
        } else {
            this.ei.tempRight = (i + 35) * 5;
        }
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.rearLock = -1;
        this.ei.acMax = -1;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3;
        Intent intent;
        switch (bArr[i + 1]) {
            case (byte) 33:
                if (bArr[i + 2] == (byte) 5) {
                    String str;
                    byte[] bArr2 = new byte[5];
                    int i4 = 0;
                    i3 = 0;
                    while (i4 < 5) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                        if (!(bArr2[i4] == this.f27r[i4] || i4 == 3)) {
                            i3 = 1;
                        }
                        this.f27r[i4] = bArr2[i4];
                        i4++;
                    }
                    if (!((bArr2[1] & 16) == 0 || r0 == 0)) {
                        bv(bArr2);
                        this.ej.od(this.ei);
                    }
                    i3 = (bArr2[3] & 255) - 40;
                    String str2 = "";
                    if ((bArr2[4] & 240) != 240) {
                        str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i3)}) + this.el.getString(R.string.f_dan);
                    } else {
                        str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i3)}) + this.el.getString(R.string.c_dan);
                    }
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2) {
                    byte[] bArr3 = new byte[2];
                    for (i3 = 0; i3 < 2; i3++) {
                        bArr3[i3] = bArr[(i + 3) + i3];
                    }
                    bw(bArr3);
                    if (this.f28s == null) {
                        return;
                    }
                    if ((bArr3[0] & 1) != 0) {
                        this.f28s.removeMessages(0);
                        this.f28s.sendEmptyMessageDelayed(0, 10);
                        return;
                    }
                    this.f28s.removeMessages(0);
                    return;
                }
                return;
            case (byte) 38:
                if (bArr[i + 2] == (byte) 2) {
                    i3 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i3 >= 32768) {
                        i3 -= 65536;
                    }
                    i3 = (i3 * 30) / 450;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", 0 - i3);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    void bw(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[1] & 1) != 0;
        boolean z3 = (bArr[1] & 2) != 0;
        boolean z4 = (bArr[1] & 4) != 0;
        boolean z5 = (bArr[1] & 8) != 0;
        boolean z6 = (bArr[1] & 16) != 0;
        if ((bArr[1] & 0) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    public void mo36d() {
        this.ej.oh(1);
        this.f28s = new dk(this);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
