package android.microntek.canbus.p000c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.BaseApplication;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;
import android.microntek.canbus.serializable.Radar;
import android.microntek.canbus.serializable.Warning;
import android.os.UserHandle;
import java.util.Arrays;

public class C0001b {
    public int eh;
    protected AirCondition ei = new AirCondition();
    public CanBusServer ej;
    protected Radar ek = new Radar();
    public Context el;
    protected Door em = new Door();
    protected int en = 0;
    protected int eo = 0;
    public int[] ep = new int[16];
    public boolean eq = true;
    protected int er = 0;
    protected Warning es = new Warning();
    private byte[] et = new byte[255];
    public byte eu = (byte) -1;
    public int ev = 256;
    public boolean ew = true;

    public C0001b(CanBusServer canBusServer, Context context) {
        this.ej = canBusServer;
        this.el = context;
    }

    public void ak(int i, int i2, int i3) {
    }

    public void al() {
    }

    public void at() {
    }

    public void mo35b(byte[] bArr, int i, int i2) {
    }

    public void bg() {
    }

    public void bh() {
        mo46n();
    }

    public void bu(byte[] bArr) {
    }

    public void cz(int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        while (i4 < getAirBtnTable().length && getAirBtnTable()[i4].length >= 3) {
            int i5;
            int i6;
            int i7;
            byte[] bArr;
            if (i2 == 1 && i == getAirBtnTable()[i4][0]) {
                i5 = getAirBtnTable()[i4][1];
                i6 = (getAirBtnTable()[i4][2] >> 8) & 255;
                i7 = getAirBtnTable()[i4][2] & 255;
                bArr = new byte[i7];
                while (i3 < i7 - 1) {
                    bArr[i3] = (byte) getAirBtnTable()[i4][i3 + 3];
                    i3++;
                }
                i4 = (getAirBtnTable()[i4][(i7 + 3) - 1] >> 8) & 255;
                bArr[i7 - 1] = (byte) i4;
                if (i4 != 0) {
                    mj(i5, i6, bArr);
                    return;
                }
                return;
            } else if (i2 == 0 && i == getAirBtnTable()[i4][0]) {
                i5 = getAirBtnTable()[i4][1];
                i6 = (getAirBtnTable()[i4][2] >> 8) & 255;
                i7 = getAirBtnTable()[i4][2] & 255;
                bArr = new byte[i7];
                while (i3 < i7) {
                    bArr[i3] = (byte) (getAirBtnTable()[i4][i3 + 3] & 255);
                    i3++;
                }
                mj(i5, i6, bArr);
                return;
            } else if (i2 == 2 && i == getAirBtnTable()[i4][0]) {
                i5 = getAirBtnTable()[i4][1];
                i6 = (getAirBtnTable()[i4][2] >> 8) & 255;
                i7 = getAirBtnTable()[i4][2] & 255;
                bArr = new byte[i7];
                while (i3 < i7 - 1) {
                    bArr[i3] = (byte) getAirBtnTable()[i4][i3 + 3];
                    i3++;
                }
                i4 = (getAirBtnTable()[i4][(i7 + 3) - 1] >> 16) & 255;
                bArr[i7 - 1] = (byte) i4;
                if (i4 != 0) {
                    mj(i5, i6, bArr);
                    return;
                }
                return;
            } else {
                i4++;
            }
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

    public int[][] getAirBtnTable() {
        return new int[][]{new int[]{3841, 46, 43010, 1, 0}};
    }

    public void mo40h(String str, int i) {
    }

    public void ht(int i) {
    }

    public void hw(int i, int i2) {
    }

    public void mo41i() {
    }

    public void mo42j(int i, int i2, int i3) {
    }

    public void jo(int i) {
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
    }

    public void mo44l() {
    }

    public void lq(String str) {
    }

    protected String lu() {
        return BaseApplication.py().qg("sta_mcu_version=");
    }

    protected void lv(String str) {
        BaseApplication.py().qh(str);
    }

    protected int lw() {
        return BaseApplication.py().qk();
    }

    protected boolean lx(byte[] bArr) {
        boolean z = false;
        int length = bArr.length;
        if (length < this.et.length) {
            for (int i = 0; i < length; i++) {
                if (bArr[i] != this.et[i]) {
                    z = true;
                }
                this.et[i] = bArr[i];
            }
        }
        return z;
    }

    protected void ly(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4 = bArr[i + 2] & 255;
        byte[] bArr2 = new byte[(i4 + 2)];
        bArr2[0] = bArr[i + 1];
        bArr2[1] = (byte) i4;
        while (i3 < i4) {
            bArr2[i3 + 2] = bArr[(i + 3) + i3];
            i3++;
        }
        this.ej.oj(bArr2);
    }

    protected void lz(int i) {
        Intent intent = new Intent("com.microntek.canbusbackview");
        intent.putExtra("canbustype", this.eh);
        intent.putExtra("lfribackview", i);
        this.el.sendBroadcastAsUser(intent, UserHandle.ALL);
    }

    public void mo45m(int i, int i2, int i3) {
    }

    protected byte[] ma(byte[] bArr, int i, int i2) {
        return Arrays.copyOfRange(bArr, i, i2);
    }

    public void mb() {
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
    }

    protected boolean mc(byte b, int i) {
        this.er = b & 255;
        return this.er >= i + -3 && this.er <= i + 5;
    }

    protected void md(String str) {
        Intent intent = new Intent("com.canbus.temperature");
        intent.putExtra("temperature", str);
        this.el.sendBroadcastAsUser(intent, UserHandle.ALL);
    }

    protected int me() {
        return this.ej.jg;
    }

    public void mf() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("android.microntek.canbus", "android.microntek.canbus.WarningServer"));
        intent.putExtra("warningdata", this.es);
        this.el.startServiceAsUser(intent, UserHandle.OWNER);
    }

    protected void mg(int i) {
        Intent intent = new Intent("com.microntek.canbus.speed");
        intent.putExtra("speed", "" + i);
        this.el.sendBroadcastAsUser(intent, UserHandle.ALL);
    }

    protected String mh() {
        return this.ej.jh == null ? "" : this.ej.jh;
    }

    protected void mi(byte[] bArr) {
        String str = "";
        try {
            str = new String(bArr, "gb2312");
        } catch (Exception e) {
            str = "";
        }
        this.ej.os(str);
    }

    public void mj(int i, int i2, byte[] bArr) {
        if (i == 46) {
            this.ej.ob((byte) i2, bArr, bArr.length);
        } else if (i == 43605) {
            this.ej.ok((byte) i2, bArr, bArr.length);
        } else if (i == 253) {
            this.ej.on((byte) i2, bArr, bArr.length);
        } else if (i == 85) {
            this.ej.or((byte) i2, bArr, bArr.length);
        } else if (i == 4048) {
            this.ej.oi((byte) i2, bArr, bArr.length);
        } else if (i == 23205) {
            this.ej.ol((byte) i2, bArr, bArr.length);
        }
    }

    public void mo46n() {
    }

    public void mo47o(byte b, int i, byte b2) {
    }

    public void mo48p() {
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
