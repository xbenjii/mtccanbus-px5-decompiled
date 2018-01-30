package android.microntek.canbus.p001a;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.os.UserHandle;

public class co extends C0001b {
    private boolean cp = true;
    private boolean cq = false;

    public co(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void jn(byte[] bArr) {
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
        if ((bArr[0] & 4) != 0) {
            this.ei.modeAuto = 2;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[0] & 8) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[0] & 2) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        if ((bArr[4] & 4) != 0) {
            this.ei.windUp = true;
        } else {
            this.ei.windUp = false;
        }
        if ((bArr[4] & 2) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((bArr[4] & 1) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[3] & 15;
        this.ei.windLevelMax = 7;
        int i = bArr[1] & 255;
        this.ei.tempLeft = jq(i);
        i = bArr[2] & 255;
        this.ei.tempRight = jq(i);
        if ((bArr[0] & 1) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatShow = false;
    }

    private void jp() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(new ComponentName("com.android.launcher", "com.android.launcher2.CarLogoActivity"));
        intent.addFlags(807600128);
        try {
            this.ej.startActivityAsUser(intent, UserHandle.CURRENT_OR_SELF);
        } catch (ActivityNotFoundException e) {
        }
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        switch (bArr[i + 1]) {
            case (byte) 9:
                if (bArr[i + 2] == (byte) 1) {
                    i3 = bArr[i + 3] & 255;
                    if (i3 <= 80) {
                        lz(((i3 - 40) * 30) / 40);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 11:
                if (bArr[i + 2] >= (byte) 8) {
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    int[] iArr = new int[8];
                    for (int i4 = 0; i4 < 8; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4];
                        if (iArr[i4] > 2) {
                            iArr[i4] = 0 - (iArr[i4] - 10);
                        } else {
                            iArr[i4] = 0;
                        }
                    }
                    this.ek.max = 7;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = iArr[4];
                    this.ek.b2 = iArr[5];
                    this.ek.b3 = iArr[6];
                    this.ek.b4 = iArr[7];
                    this.ek.front_cnt = 4;
                    this.ek.f1 = iArr[0];
                    this.ek.f2 = iArr[1];
                    this.ek.f3 = iArr[2];
                    this.ek.f4 = iArr[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 14:
                if (bArr[i + 2] >= (byte) 5) {
                    byte[] bArr2 = new byte[5];
                    while (i3 < 5) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    jn(bArr2);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 18:
                if (bArr[i + 2] >= (byte) 1) {
                    switch (bArr[i + 3] & 255) {
                        case 233:
                            if (!this.ej.pa().equals("com.android.launcher2.CarLogoActivity")) {
                                jp();
                                return;
                            }
                            return;
                        case 234:
                            this.el.sendBroadcast(new Intent("com.microntek.hla.car"));
                            return;
                        case 235:
                            lv("av_mute=true");
                            return;
                        case 236:
                            lv("av_mute=false");
                            return;
                        default:
                            return;
                    }
                }
                return;
            case (byte) 19:
                if (bArr[i + 2] >= (byte) 1) {
                    switch (bArr[i + 3] & 255) {
                        case 231:
                            if (!this.cq) {
                                lv("ctl_key=13");
                                return;
                            }
                            return;
                        case 232:
                            if (this.cq) {
                                lv("ctl_key=13");
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
                return;
            case (byte) 32:
                if (bArr[i + 2] >= (byte) 0) {
                    mi(ma(bArr, i + 3, (i + 3) + (bArr[i + 2] & 255)));
                    return;
                }
                return;
            case (byte) 50:
                if (bArr[i + 2] >= (byte) 2) {
                    Intent intent = new Intent("com.microntek.canbus.speed");
                    intent.putExtra("speed", "" + (((((bArr[i + 4] & 255) << 8) | (bArr[i + 3] & 255)) / 16) / 10));
                    this.el.sendBroadcast(intent);
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
        this.ej.oo(new byte[]{(byte) 85, (byte) -86, (byte) 3, (byte) 17, (byte) -1, (byte) -1, (byte) 18});
    }

    public void mo37e() {
    }

    public void mo38f() {
    }

    public void mo39g() {
    }

    public void mo40h(String str, int i) {
        if (i == 1) {
            if (this.cp) {
                this.cp = false;
                this.ej.oo(new byte[]{(byte) 85, (byte) -86, (byte) 3, (byte) 17, (byte) 18, (byte) 1, (byte) 39});
            }
        } else if (!this.cp) {
            this.cp = true;
            this.ej.oo(new byte[]{(byte) 85, (byte) -86, (byte) 3, (byte) 17, (byte) 18, (byte) 0, (byte) 38});
        }
    }

    public void mo41i() {
    }

    public void mo42j(int i, int i2, int i3) {
    }

    public void jo(int i) {
        if (i == 1) {
            this.cq = true;
            this.ej.oo(new byte[]{(byte) 85, (byte) -86, (byte) 3, (byte) 17, (byte) 19, (byte) 1, (byte) 40});
            return;
        }
        this.cq = false;
        this.ej.oo(new byte[]{(byte) 85, (byte) -86, (byte) 3, (byte) 17, (byte) 19, (byte) 0, (byte) 39});
    }

    int jq(int i) {
        return i == 0 ? 0 : i == 255 ? 65535 : (i < 16 || i > 28) ? -1 : i * 10;
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
