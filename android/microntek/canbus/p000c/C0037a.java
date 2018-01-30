package android.microntek.canbus.p000c;

import android.content.Context;
import android.microntek.canbus.CanBusServer;

public class C0037a extends C0001b {
    private boolean eb = false;
    private boolean ec = false;
    private int ed = 256;
    private String[] ee = new String[]{"FM1", "FM2", "FM3", "FM4", "FM5", "AM1", "AM2", "&&&", "&&&", "&&&"};
    private String ef = "-000000000";
    private String eg = "OFF&&&&&&&&-000000000";

    public C0037a(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void lr() {
        if (this.eb) {
            this.eb = false;
            this.ej.ot("1");
        }
    }

    private void ls() {
        if (!this.eb) {
            this.eb = true;
            this.ej.ot("0");
        }
    }

    private String lt(int i) {
        if (i == 0) {
            return this.ef;
        }
        String str = "" + (1 << i);
        String str2 = "";
        for (int i2 = 0; i2 < 9 - str.length(); i2++) {
            str2 = str2 + "0";
        }
        return "-" + str2 + str;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
    }

    public void mo36d() {
        mo46n();
    }

    public void mo37e() {
        String str = "A2DP&&&&&&&" + this.ef;
        this.ej.ot(str);
        this.eg = str;
        lr();
    }

    public void mo38f() {
        String str = "ATV&&&&&&&&" + this.ef;
        this.ej.ot(str);
        this.eg = str;
        lr();
    }

    public void mo39g() {
        String str = "AVIN&&&&&&&" + this.ef;
        this.ej.ot(str);
        this.eg = str;
        lr();
    }

    public void mo40h(String str, int i) {
        if (1 == i) {
            this.ec = true;
            this.ej.ot("BT&&&&&&&&&" + this.ef);
            lr();
            return;
        }
        this.ec = false;
    }

    public void mo41i() {
        String str = "DTV&&&&&&&&" + this.ef;
        this.ej.ot(str);
        this.eg = str;
        lr();
    }

    public void mo42j(int i, int i2, int i3) {
        String str = "";
        str = "";
        str = ((i == 0 && i2 == 0) || i3 == -1) ? "DISC&&&&&&&" + this.ef : ((i / 100) % 10) + "" + ((i / 10) % 10) + "" + (i % 10) + "-&" + (((i2 / 60) % 60) / 10) + "" + (((i2 / 60) % 60) % 10) + "." + ((i2 % 60) / 10) + "" + ((i2 % 60) % 10) + "&" + (i3 == 2 ? lt(17) : lt(19));
        this.ej.ot(str);
        this.eg = str;
        lr();
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        String str = "";
        int i5 = i3 / 1000;
        str = i != 0 ? "" + ((i2 / 100) % 10) + "" + ((i2 / 10) % 10) + "" + (i2 % 10) + "&" + "&" + (((i5 / 60) % 60) / 10) + "" + (((i5 / 60) % 60) % 10) + "." + ((i5 % 60) / 10) + "" + ((i5 % 60) % 10) + "&" : "IPOD&&&&&&&";
        this.ej.ot(str + this.ef);
        this.eg = str + this.ef;
        lr();
    }

    public void mo44l() {
        String str = "AV&&&&&&&&&" + this.ef;
        this.ej.ot(str);
        this.eg = str;
        lr();
    }

    public void lq(String str) {
        if (!this.ec) {
            if (str == null || str.length() <= 2) {
                this.ej.ot(this.eg);
            } else if (str.substring(0, 3).toString().equals("VOL")) {
                this.ej.ot(this.eg);
            } else {
                this.ej.ot(str);
            }
        }
    }

    public void mo45m(int i, int i2, int i3) {
        String str = "";
        int i4 = i3 / 1000;
        if (i != 0) {
            str = ("" + ((i2 / 100) % 10) + "" + ((i2 / 10) % 10) + "" + (i2 % 10) + "-" + "&" + (((i4 / 60) % 60) / 10) + "" + (((i4 / 60) % 60) % 10) + "." + ((i4 % 60) / 10) + "" + ((i4 % 60) % 10) + "&") + lt(20);
        } else {
            str = "MP3&&&&&&&&" + this.ef;
        }
        this.ej.ot(str);
        this.eg = str;
        lr();
    }

    public void mo46n() {
        String str = "OFF&&&&&&&&" + lt(0);
        this.eg = str;
        ls();
        this.ej.ot(str);
    }

    public void mo47o(byte b, int i, byte b2) {
        String str;
        int i2 = 2;
        String str2 = "&&&&&&&&&&&-000000000";
        int i3 = (b >> 4) & 15;
        int i4 = b & 15;
        if (i3 != 7) {
            i2 = 0;
        }
        if (i4 >= 0 && i4 <= ((byte) (i2 + 2))) {
            str = this.ee[i4];
            int i5 = i / 10;
            str = i5 > 999 ? str + (i5 / 1000) + "&" + ((i5 / 100) % 10) + "" + ((i5 / 10) % 10) + "." + (i5 % 10) + "0&" + lt(0) : i5 > 99 ? str + "&&" + ((i5 / 100) % 10) + "" + ((i5 / 10) % 10) + "." + (i5 % 10) + "0&" + lt(0) : str + "&&&&&&&&-000000000";
        } else if (i4 < ((byte) (i2 + 3)) || i4 > ((byte) (i2 + 4))) {
            str = str2;
        } else {
            String[] strArr = this.ee;
            if (i3 == 5) {
                i4 += 2;
            }
            str = strArr[i4];
            str = i > 999 ? str + (i / 1000) + "&" + ((i / 100) % 10) + "" + ((i / 10) % 10) + "&" + (i % 10) + "&&" + lt(0) : i > 99 ? str + "&&" + ((i / 100) % 10) + "" + ((i / 10) % 10) + "&" + (i % 10) + "&&" + lt(0) : str + "&&&&&&&&-000000000";
        }
        this.ej.ot(str);
        this.eg = str;
        lr();
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
        if (!this.ec) {
            if (i == this.ed || this.ed == 256) {
                this.ed = i;
            } else {
                this.ed = i;
                this.ej.ot("VOL&&" + (i / 10) + "" + (i % 10) + "&&&&" + this.ef);
            }
        }
    }
}
