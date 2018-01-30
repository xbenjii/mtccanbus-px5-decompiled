package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.provider.Settings.System;

public class C0018o extends C0001b {
    private byte[] f22l;
    private boolean f23m;
    private boolean f24n;
    private boolean f25o;

    public C0018o(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.f22l = new byte[8];
        this.f24n = true;
        this.f23m = true;
        this.f25o = true;
        this.eh = 72;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        byte[] bArr2;
        switch (bArr[i + 1]) {
            case (byte) 20:
                if (bArr[i + 2] == (byte) 1) {
                    byte b = bArr[i + 3];
                    Intent intent = new Intent("com.microntek.light");
                    intent.putExtra("keyCode", b & 255);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2) {
                    bArr2 = new byte[2];
                    while (i3 < 2) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    bf(bArr2);
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
                if (bArr[i + 2] == (byte) 5) {
                    bArr2 = new byte[7];
                    bArr2[0] = (byte) 114;
                    bArr2[1] = (byte) 5;
                    while (i3 < 5) {
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
                }
                return;
            case (byte) 121:
                if (bArr[i + 2] == (byte) 1 && this.f25o) {
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

    void bf(byte[] bArr) {
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

    public void bg() {
        this.f25o = false;
        System.putInt(this.ej.getContentResolver(), "PowerState", 1);
    }

    public void bh() {
        this.f25o = true;
        this.ej.og(new byte[]{(byte) -1, (byte) -1, (byte) 0});
        System.putInt(this.ej.getContentResolver(), "PowerState", 0);
    }

    public void mo36d() {
        System.putInt(this.ej.getContentResolver(), "PowerState", 0);
    }
}
