package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import java.util.Locale;

public class ax extends C0001b {
    public ax(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 38;
    }

    private void eh(byte[] bArr) {
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
        if ((bArr[0] & 2) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        if (bArr[1] == (byte) 1) {
            this.ei.windUp = false;
            this.ei.windMid = true;
            this.ei.windDown = false;
        } else if (bArr[1] == (byte) 2) {
            this.ei.windUp = false;
            this.ei.windMid = true;
            this.ei.windDown = true;
        } else if (bArr[1] == (byte) 3) {
            this.ei.windUp = false;
            this.ei.windMid = false;
            this.ei.windDown = true;
        } else if (bArr[1] == (byte) 4) {
            this.ei.windUp = true;
            this.ei.windMid = false;
            this.ei.windDown = true;
        } else if (bArr[1] == (byte) 5) {
            this.ei.windUp = false;
            this.ei.windMid = true;
            this.ei.windDown = true;
        } else {
            this.ei.windUp = false;
            this.ei.windMid = false;
            this.ei.windDown = false;
        }
        this.ei.windLevel = (bArr[2] & 15) == 0 ? 0 : (bArr[2] & 15) - 1;
        this.ei.windLevelMax = 7;
        this.ei.tempLeft = ej(bArr[3] & 255);
        this.ei.tempRight = this.ei.tempLeft;
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatShow = false;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3;
        Intent intent;
        switch (bArr[i + 1]) {
            case (byte) 35:
                if (bArr[i + 2] == (byte) 4) {
                    byte[] bArr2 = new byte[4];
                    for (i3 = 0; i3 < 4; i3++) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                    }
                    eh(bArr2);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 3) {
                    int[] iArr = new int[3];
                    for (i3 = 0; i3 < 3; i3++) {
                        iArr[i3] = (bArr[(i + 3) + i3] & 255) + 1;
                    }
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    this.ek.max = 5;
                    this.ek.back_cnt = 3;
                    this.ek.b1 = iArr[0];
                    this.ek.b2 = iArr[1];
                    this.ek.b3 = iArr[2];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 40:
                if (bArr[i + 2] == (byte) 1) {
                    ei(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 48:
                if (bArr[i + 2] == (byte) 2) {
                    i3 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i3 >= 32768) {
                        i3 -= 65536;
                    }
                    i3 = (i3 * 30) / 5600;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", 0 - i3);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 54:
                if (bArr[i + 2] == (byte) 1) {
                    i3 = bArr[i + 3] & 127;
                    String str = "";
                    if ((bArr[i + 3] & 128) != 0) {
                        i3 = 0 - i3;
                    }
                    String str2 = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i3)}) + this.el.getString(R.string.c_dan);
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str2);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oh(1);
        this.ej.oa(1);
    }

    public void mo37e() {
    }

    void ei(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 64) != 0;
        boolean z3 = (bArr[0] & 128) != 0;
        boolean z4 = (bArr[0] & 16) != 0;
        boolean z5 = (bArr[0] & 32) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 4) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    int ej(int i) {
        return i == 0 ? 0 : i == 16 ? 65535 : (i < 1 || i > 15) ? -1 : (i * 10) + 160;
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
