package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.text.format.Time;

public class bp extends C0001b {
    byte[] be;

    public bp(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.be = new byte[6];
        this.eh = 54;
    }

    private void gg(byte[] bArr) {
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
        if ((bArr[0] & 4) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        if ((bArr[0] & 2) != 0) {
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
            this.ei.windUp = false;
            this.ei.windMid = true;
            this.ei.windDown = true;
        } else if ((bArr[1] & 64) != 0) {
            this.ei.windUp = false;
            this.ei.windMid = true;
            this.ei.windDown = false;
        } else if ((bArr[1] & 32) != 0) {
            this.ei.windUp = false;
            this.ei.windMid = false;
            this.ei.windDown = true;
        } else if ((bArr[1] & 16) != 0) {
            this.ei.windUp = true;
            this.ei.windMid = false;
            this.ei.windDown = true;
        } else {
            this.ei.windUp = false;
            this.ei.windMid = false;
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[1] & 15;
        this.ei.windLevelMax = 8;
        int i = bArr[2] & 127;
        if (bArr[2] < (byte) 0) {
            if (i == 0) {
                this.ei.tempLeft = 0;
            } else if (i == 31) {
                this.ei.tempLeft = 65535;
            } else if (i < 1 || i > 28) {
                this.ei.tempLeft = -1;
            } else {
                if (i <= 3) {
                    i = 3;
                }
                this.ei.tempLeft = ((i / 3) + 17) * 10;
                if (this.ei.tempUnit) {
                    this.ei.tempLeft = (int) ((((double) this.ei.tempLeft) * 1.8d) + 320.0d);
                }
            }
        } else if (i == 0) {
            this.ei.tempLeft = 0;
        } else if (i == 31) {
            this.ei.tempLeft = 65535;
        } else if (i <= 9) {
            this.ei.tempLeft = (i + 17) * 10;
            if (this.ei.tempUnit) {
                this.ei.tempLeft = (int) ((((double) this.ei.tempLeft) * 1.8d) + 320.0d);
            }
        } else {
            this.ei.tempLeft = -1;
        }
        this.ei.tempRight = this.ei.tempLeft;
        if ((bArr[0] & 32) == 0) {
            this.ei.windLoop = 0;
        } else if ((bArr[4] & 32) != 0) {
            this.ei.windLoop = 2;
        } else {
            this.ei.windLoop = 1;
        }
        if ((bArr[0] & 1) != 0) {
            this.ei.rearLock = 1;
        } else {
            this.ei.rearLock = 0;
        }
        if ((bArr[4] & 4) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        boolean z = true;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        int i3;
        Intent intent;
        byte[] bArr2;
        switch (bArr[i + 1]) {
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 6) {
                    byte[] bArr3 = new byte[5];
                    boolean z2 = false;
                    for (int i4 = 0; i4 < 5; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                        if (bArr3[i4] != this.be[i4]) {
                            z2 = true;
                        }
                        this.be[i4] = bArr3[i4];
                    }
                    AirCondition airCondition = this.ei;
                    if ((bArr3[4] & 1) == 0) {
                        z = false;
                    }
                    airCondition.tempUnit = z;
                    if (z2) {
                        gg(bArr3);
                        this.ej.od(this.ei);
                    }
                    i3 = bArr[i + 8] & 127;
                    if ((bArr[i + 8] & 128) != 0) {
                        i3 = 0 - i3;
                    }
                    String str = "";
                    String str2 = !this.ei.tempUnit ? i3 + this.el.getString(R.string.c_dan) : ((int) ((((double) i3) * 1.8d) + 32.0d)) + this.el.getString(R.string.f_dan);
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str2);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] == (byte) 1) {
                    bArr2 = new byte[]{bArr[i + 3]};
                    this.ek.max = 9;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr2[0];
                    this.ek.b2 = bArr2[0];
                    this.ek.b3 = bArr2[0];
                    this.ek.b4 = bArr2[0];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] == (byte) 1) {
                    bArr2 = new byte[]{bArr[i + 3]};
                    this.ek.max = 9;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr2[0];
                    this.ek.f2 = bArr2[0];
                    this.ek.f3 = bArr2[0];
                    this.ek.f4 = bArr2[0];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2) {
                    gh(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] == (byte) 2) {
                    i3 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i3 >= 24576 && i3 <= 40959) {
                        i3 = ((i3 - 32768) * 35) / 8191;
                        intent = new Intent("com.microntek.canbusbackview");
                        intent.putExtra("canbustype", this.eh);
                        intent.putExtra("lfribackview", 0 - i3);
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

    public void mo36d() {
        this.ej.oa(1);
        this.ej.oh(1);
        int i = System.getInt(this.ej.getContentResolver(), "canbus54_mInstrument_style", 0);
        this.ej.ob((byte) -103, new byte[]{(byte) 0, (byte) i, (byte) 0, (byte) 0}, 4);
    }

    public void mo37e() {
    }

    public void mo38f() {
    }

    public void mo39g() {
    }

    void gh(byte[] bArr) {
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
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        boolean is24HourFormat = DateFormat.is24HourFormat(this.el);
        byte[] bArr = new byte[7];
        bArr[0] = (byte) ((time.year - 2000) & 255);
        bArr[1] = (byte) ((time.month + 1) & 255);
        bArr[2] = (byte) (time.monthDay & 255);
        if (!is24HourFormat) {
            if (this.en > 12) {
                this.en -= 12;
            }
            this.en |= 128;
        }
        bArr[3] = (byte) (this.en & 255);
        bArr[4] = (byte) (this.eo & 255);
        this.ej.ob((byte) -90, bArr, 7);
    }

    public void mo50r(int i) {
    }
}
