package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import java.util.Arrays;
import java.util.Locale;

public class C0008d extends C0001b {
    public C0008d(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 34;
        this.ei = new AirCondition();
    }

    private void ac(byte[] bArr) {
        this.ei.seatShow = false;
        this.ei.onOff = true;
        if ((bArr[1] & 1) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[1] & 4) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[1] & 64) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[1] & 32) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        if ((bArr[1] & 8) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((bArr[1] & 16) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[2] & 15;
        this.ei.windLevelMax = 7;
        int i = bArr[0] & 255;
        this.ei.tempLeft = ag(i, bArr[3] & 15);
        this.ei.tempRight = this.ei.tempLeft;
        if ((bArr[1] & 128) == 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.DIS_IMG1 = 1;
        if ((bArr[1] & 2) != 0) {
            this.ei.rearLock = 1;
        } else {
            this.ei.rearLock = 0;
        }
    }

    private void ad(byte[] bArr) {
        this.ei.seatShow = false;
        if ((bArr[2] & 15) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[1] & 1) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[1] & 4) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[2] & 32) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        if ((bArr[1] & 64) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[1] & 32) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        if ((bArr[1] & 8) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((bArr[1] & 16) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[2] & 15;
        this.ei.windLevelMax = 7;
        int i = bArr[0] & 255;
        this.ei.tempLeft = ag(i, bArr[3] & 15);
        i = bArr[6] & 255;
        this.ei.tempRight = ag(i, (bArr[3] >> 4) & 15);
        if ((bArr[1] & 128) == 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.DIS_IMG1 = 1;
        if ((bArr[1] & 2) != 0) {
            this.ei.rearLock = 1;
        } else {
            this.ei.rearLock = 0;
        }
    }

    private int ae(int i, int i2) {
        return i == 1 ? i2 < 254 ? (i2 * 20) / 254 : 0 : i == 0 ? i2 <= 29 ? i2 + 1 : i2 < 254 ? (i2 * 30) / 254 : 0 : 0;
    }

    private int af(int i, int i2) {
        return i == 1 ? 20 : i2 > 30 ? 30 : i2;
    }

    int ag(int i, int i2) {
        return i == 0 ? 0 : i == 255 ? 65535 : (i < 0 || i >= 100) ? i == 254 ? -1 : -1 : (i * 10) + i2;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        String str;
        String str2;
        Intent intent;
        int i3;
        switch (bArr[i + 1]) {
            case (byte) 16:
                if (bArr[i + 2] >= (byte) 5) {
                    byte[] copyOfRange = Arrays.copyOfRange(bArr, i + 3, i2);
                    if ((copyOfRange[2] & 128) != 0) {
                        float f = (((float) (copyOfRange[3] & 15)) * 0.1f) + ((float) (copyOfRange[0] & 255));
                        str = "";
                        str2 = String.format(Locale.US, " %.0f", new Object[]{Float.valueOf(f - 100.0f)}) + this.el.getString(R.string.c_dan);
                        intent = new Intent("com.canbus.temperature");
                        intent.putExtra("temperature", str2);
                        this.el.sendBroadcast(intent);
                        return;
                    } else if (lx(copyOfRange)) {
                        ac(copyOfRange);
                        this.ej.od(this.ei);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case (byte) 19:
                if (bArr[i + 2] >= (byte) 6) {
                    int i4 = bArr[i + 2] & 15;
                    byte[] bArr2 = new byte[(i4 + 3)];
                    for (i3 = 0; i3 < i4; i3++) {
                        if (i3 != 5) {
                            bArr2[i3] = bArr[(i + 3) + i3];
                        }
                    }
                    if (lx(bArr2)) {
                        ad(bArr2);
                        this.ej.od(this.ei);
                    }
                    i3 = bArr[(i + 2) + 5] & 127;
                    str = "";
                    if (i3 != 127) {
                        if ((bArr[(i + 2) + 5] & 128) != 0) {
                            i3 = 0 - i3;
                        }
                        str2 = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i3)}) + this.el.getString(R.string.c_dan);
                    } else {
                        str2 = str;
                    }
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str2);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] >= (byte) 2) {
                    i3 = ((bArr[i + 4] & 255) << 8) | (bArr[i + 3] & 255);
                    if (i3 >= 2064 && i3 <= 13424) {
                        lz(0 - ((((i3 - 2063) - 5680) * 30) / 5680));
                        return;
                    }
                    return;
                }
                return;
            case (byte) 50:
                if (bArr[i + 2] >= (byte) 6) {
                    for (i3 = 0; i3 < 6; i3++) {
                        this.ep[i3] = bArr[(i + 3) + i3];
                    }
                    this.ek.max = af(this.ep[0] & 255, this.ep[1] & 255);
                    this.ek.back_cnt = 4;
                    this.ek.b1 = ae(this.ep[0] & 255, this.ep[2] & 255);
                    this.ek.b2 = ae(this.ep[0] & 255, this.ep[3] & 255);
                    this.ek.b3 = ae(this.ep[0] & 255, this.ep[4] & 255);
                    this.ek.b4 = ae(this.ep[0] & 255, this.ep[5] & 255);
                    this.ej.oe(this.ek);
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
        this.ej.oi((byte) 7, new byte[]{(byte) 32, (byte) 32, (byte) 65, (byte) 50, (byte) 68, (byte) 80, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 12);
    }

    public void mo38f() {
        this.ej.oi((byte) 7, new byte[]{(byte) 32, (byte) 32, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 12);
    }

    public void mo39g() {
        this.ej.oi((byte) 7, new byte[]{(byte) 32, (byte) 32, (byte) 65, (byte) 86, (byte) 73, (byte) 78, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 12);
    }

    public void mo40h(String str, int i) {
        this.ej.oi((byte) 7, new byte[]{(byte) 32, (byte) 32, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104, (byte) 32}, 12);
    }

    public void mo41i() {
        this.ej.oi((byte) 7, new byte[]{(byte) 32, (byte) 32, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 12);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        bArr[2] = (byte) ((((i2 / 60) / 60) / 10) + 48);
        bArr[3] = (byte) ((((i2 / 60) / 60) % 10) + 48);
        bArr[4] = (byte) 58;
        bArr[5] = (byte) ((((i2 / 60) % 60) / 10) + 48);
        bArr[6] = (byte) ((((i2 / 60) % 60) % 10) + 48);
        bArr[7] = (byte) 58;
        bArr[8] = (byte) (((i2 % 60) / 10) + 48);
        bArr[9] = (byte) (((i2 % 60) % 10) + 48);
        this.ej.oi((byte) 7, bArr, 12);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        byte[] bArr = new byte[]{(byte) 73, (byte) 80, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        int i5 = i3 / 1000;
        bArr[4] = (byte) ((((i5 / 60) / 60) / 10) + 48);
        bArr[5] = (byte) ((((i5 / 60) / 60) % 10) + 48);
        bArr[6] = (byte) 58;
        bArr[7] = (byte) ((((i5 / 60) % 60) / 10) + 48);
        bArr[8] = (byte) ((((i5 / 60) % 60) % 10) + 48);
        bArr[9] = (byte) 58;
        bArr[10] = (byte) (((i5 % 60) / 10) + 48);
        bArr[11] = (byte) (((i5 % 60) % 10) + 48);
        this.ej.oi((byte) 7, bArr, 12);
    }

    public void mo44l() {
        this.ej.oi((byte) 7, new byte[]{(byte) 32, (byte) 32, (byte) 77, (byte) 79, (byte) 86, (byte) 73, (byte) 69, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 12);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 83, (byte) 68, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        int i4 = i3 / 1000;
        bArr[3] = (byte) ((((i4 / 60) / 60) / 10) + 48);
        bArr[4] = (byte) ((((i4 / 60) / 60) % 10) + 48);
        bArr[5] = (byte) 58;
        bArr[6] = (byte) ((((i4 / 60) % 60) / 10) + 48);
        bArr[7] = (byte) ((((i4 / 60) % 60) % 10) + 48);
        bArr[8] = (byte) 58;
        bArr[9] = (byte) (((i4 % 60) / 10) + 48);
        bArr[10] = (byte) (((i4 % 60) % 10) + 48);
        this.ej.oi((byte) 7, bArr, 12);
    }

    public void mo46n() {
        this.ej.oi((byte) 7, new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 12);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[]{(byte) 1, (byte) 45, (byte) 1, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) 70;
            bArr[1] = (byte) 77;
            bArr[2] = (byte) (b + 49);
            if (i > 9999) {
                bArr[4] = (byte) ((i / 10000) + 48);
                bArr[5] = (byte) (((i / 1000) % 10) + 48);
                bArr[6] = (byte) (((i / 100) % 10) + 48);
                bArr[7] = (byte) 46;
                bArr[8] = (byte) (((i / 10) % 10) + 48);
            } else {
                bArr[5] = (byte) ((i / 1000) + 48);
                bArr[6] = (byte) (((i / 100) % 10) + 48);
                bArr[7] = (byte) 46;
                bArr[8] = (byte) (((i / 10) % 10) + 48);
            }
            bArr[9] = (byte) 77;
            bArr[10] = (byte) 72;
            bArr[11] = (byte) 90;
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[0] = (byte) 65;
            bArr[1] = (byte) 77;
            bArr[2] = (byte) (b + 46);
            if (i > 999) {
                bArr[4] = (byte) ((i / 1000) + 48);
                bArr[5] = (byte) (((i / 100) % 10) + 48);
                bArr[6] = (byte) (((i / 10) % 10) + 48);
                bArr[7] = (byte) ((i % 10) + 48);
            } else {
                bArr[5] = (byte) ((i / 100) + 48);
                bArr[6] = (byte) (((i / 10) % 10) + 48);
                bArr[7] = (byte) ((i % 10) + 48);
            }
            bArr[9] = (byte) 75;
            bArr[10] = (byte) 72;
            bArr[11] = (byte) 90;
        }
        this.ej.oi((byte) 7, bArr, 12);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
