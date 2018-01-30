package android.microntek.canbus;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SerialManager;
import android.hardware.SerialPort;
import android.microntek.CarManager;
import android.microntek.canbus.aidl.CanBusServiceInf.Stub;
import android.microntek.canbus.aidl.ICanBusAidlCallBack;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.p000c.C0037a;
import android.microntek.canbus.p001a.C0002a;
import android.microntek.canbus.p001a.C0003u;
import android.microntek.canbus.p001a.C0004b;
import android.microntek.canbus.p001a.C0005k;
import android.microntek.canbus.p001a.C0006c;
import android.microntek.canbus.p001a.C0007i;
import android.microntek.canbus.p001a.C0008d;
import android.microntek.canbus.p001a.C0010e;
import android.microntek.canbus.p001a.C0011f;
import android.microntek.canbus.p001a.C0012g;
import android.microntek.canbus.p001a.C0013h;
import android.microntek.canbus.p001a.C0014j;
import android.microntek.canbus.p001a.C0015l;
import android.microntek.canbus.p001a.C0016m;
import android.microntek.canbus.p001a.C0017n;
import android.microntek.canbus.p001a.C0018o;
import android.microntek.canbus.p001a.C0019p;
import android.microntek.canbus.p001a.C0020q;
import android.microntek.canbus.p001a.C0021r;
import android.microntek.canbus.p001a.C0022s;
import android.microntek.canbus.p001a.C0023t;
import android.microntek.canbus.p001a.C0024v;
import android.microntek.canbus.p001a.C0025w;
import android.microntek.canbus.p001a.C0026x;
import android.microntek.canbus.p001a.C0027y;
import android.microntek.canbus.p001a.C0028z;
import android.microntek.canbus.p001a.aa;
import android.microntek.canbus.p001a.ab;
import android.microntek.canbus.p001a.ac;
import android.microntek.canbus.p001a.ad;
import android.microntek.canbus.p001a.ae;
import android.microntek.canbus.p001a.af;
import android.microntek.canbus.p001a.ag;
import android.microntek.canbus.p001a.ah;
import android.microntek.canbus.p001a.ai;
import android.microntek.canbus.p001a.aj;
import android.microntek.canbus.p001a.ak;
import android.microntek.canbus.p001a.al;
import android.microntek.canbus.p001a.am;
import android.microntek.canbus.p001a.an;
import android.microntek.canbus.p001a.ao;
import android.microntek.canbus.p001a.ap;
import android.microntek.canbus.p001a.aq;
import android.microntek.canbus.p001a.ar;
import android.microntek.canbus.p001a.as;
import android.microntek.canbus.p001a.at;
import android.microntek.canbus.p001a.au;
import android.microntek.canbus.p001a.av;
import android.microntek.canbus.p001a.aw;
import android.microntek.canbus.p001a.ax;
import android.microntek.canbus.p001a.ay;
import android.microntek.canbus.p001a.az;
import android.microntek.canbus.p001a.ba;
import android.microntek.canbus.p001a.bb;
import android.microntek.canbus.p001a.bc;
import android.microntek.canbus.p001a.bd;
import android.microntek.canbus.p001a.be;
import android.microntek.canbus.p001a.bf;
import android.microntek.canbus.p001a.bg;
import android.microntek.canbus.p001a.bh;
import android.microntek.canbus.p001a.bi;
import android.microntek.canbus.p001a.bj;
import android.microntek.canbus.p001a.bk;
import android.microntek.canbus.p001a.bl;
import android.microntek.canbus.p001a.bm;
import android.microntek.canbus.p001a.bn;
import android.microntek.canbus.p001a.bo;
import android.microntek.canbus.p001a.bp;
import android.microntek.canbus.p001a.bq;
import android.microntek.canbus.p001a.br;
import android.microntek.canbus.p001a.bs;
import android.microntek.canbus.p001a.bt;
import android.microntek.canbus.p001a.bu;
import android.microntek.canbus.p001a.bv;
import android.microntek.canbus.p001a.bw;
import android.microntek.canbus.p001a.bx;
import android.microntek.canbus.p001a.by;
import android.microntek.canbus.p001a.bz;
import android.microntek.canbus.p001a.ca;
import android.microntek.canbus.p001a.cb;
import android.microntek.canbus.p001a.cc;
import android.microntek.canbus.p001a.cd;
import android.microntek.canbus.p001a.ce;
import android.microntek.canbus.p001a.cf;
import android.microntek.canbus.p001a.cg;
import android.microntek.canbus.p001a.ch;
import android.microntek.canbus.p001a.ci;
import android.microntek.canbus.p001a.cj;
import android.microntek.canbus.p001a.ck;
import android.microntek.canbus.p001a.cl;
import android.microntek.canbus.p001a.cm;
import android.microntek.canbus.p001a.cn;
import android.microntek.canbus.p001a.co;
import android.microntek.canbus.p001a.cp;
import android.microntek.canbus.p001a.cq;
import android.microntek.canbus.p001a.cr;
import android.microntek.canbus.p001a.cs;
import android.microntek.canbus.p001a.ct;
import android.microntek.canbus.p001a.cu;
import android.microntek.canbus.p001a.cv;
import android.microntek.canbus.p001a.cw;
import android.microntek.canbus.p001a.cx;
import android.microntek.canbus.p001a.cy;
import android.microntek.canbus.p001a.cz;
import android.microntek.canbus.p001a.da;
import android.microntek.canbus.p001a.db;
import android.microntek.canbus.p001a.dc;
import android.microntek.canbus.p001a.dd;
import android.microntek.canbus.p001a.de;
import android.microntek.canbus.p001a.df;
import android.microntek.canbus.p001a.dg;
import android.microntek.canbus.p001a.dh;
import android.microntek.canbus.p001a.di;
import android.microntek.canbus.p001a.dj;
import android.microntek.canbus.p002b.C0034a;
import android.microntek.canbus.p002b.C0035b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;
import android.microntek.canbus.serializable.Radar;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.UserHandle;
import android.provider.Settings.System;
import android.util.Log;
import com.p003a.C0070a;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Locale;

public class CanBusServer extends Service {
    private static int kg = 0;
    private static byte[] kx = new byte[1024];
    public int jg = 0;
    public String jh = "";
    private BroadcastReceiver ji = new C0038c(this);
    private BroadcastReceiver jj = new C0039d(this);
    private RemoteCallbackList<ICanBusAidlCallBack> jk = new RemoteCallbackList();
    private final int jl = 7;
    private final int jm = 4;
    private final int jn = 1;
    private final int jo = 5;
    private final int jp = 6;
    private final int jq = 2;
    private final int jr = 3;
    private final boolean js = false;
    private final int jt = 11;
    private final int ju = 6;
    private final int jv = 1;
    private final int jw = 2;
    private final int jx = 7;
    private final int jy = 10;
    private final int jz = 5;
    private final int ka = 4;
    private final int kb = 3;
    private final int kc = 8;
    public final int kd = 0;
    private final int ke = 9;
    private final int kf = 12;
    private int kh = 0;
    private AirCondition ki;
    private Stub kj = new C00002();
    private boolean kk = false;
    private C0001b kl = null;
    protected CarManager km = null;
    private Context kn;
    public Intent ko = null;
    private Handler kp = new C0036b(this);
    private ByteBuffer kq;
    private boolean kr = true;
    private String ks = "88888888888";
    private long kt = -1;
    private C0001b ku = null;
    private SerialPort kv;
    public String kw = "";
    public int ky = 0;

    class C00002 extends Stub {
        C00002() {
        }

        public int getAcMax() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.acMax : 0;
        }

        public int[] getAirBtnTable() {
            if (CanBusServer.this.kl != null) {
                int[] iArr = new int[CanBusServer.this.kl.getAirBtnTable().length];
                for (int i = 0; i < CanBusServer.this.kl.getAirBtnTable().length; i++) {
                    iArr[i] = CanBusServer.this.kl.getAirBtnTable()[i][0];
                }
                return iArr;
            }
            return new int[]{0};
        }

        public boolean getAriFloatViewShow() {
            return CanBusServer.this.kr;
        }

        public boolean getAriStateShow() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.ariStateShow : false;
        }

        public int getFast() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.fast : -1;
        }

        public int getLoopa() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.loopa : -1;
        }

        public boolean getModeAc() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.modeAc : false;
        }

        public int getModeAuto() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.modeAuto : 0;
        }

        public int getModeDual() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.modeDual : 0;
        }

        public int getNormal() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.normal : -1;
        }

        public boolean getOnOff() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.onOff : false;
        }

        public int getRearLock() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.rearLock : 0;
        }

        public int getSeatHotLeft() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.seatHotLeft : 0;
        }

        public int getSeatHotRight() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.seatHotRight : 0;
        }

        public boolean getSeatShow() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.seatShow : false;
        }

        public int getSoft() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.soft : -1;
        }

        public int getTempLeft() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.tempLeft : -1;
        }

        public int getTempRight() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.tempRight : -1;
        }

        public boolean getTempUnit() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.tempUnit : false;
        }

        public boolean getWindDown() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.windDown : false;
        }

        public boolean getWindFrontMax() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.windFrontMax : false;
        }

        public int getWindLevel() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.windLevel : 0;
        }

        public int getWindLevelMax() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.windLevelMax : 0;
        }

        public int getWindLoop() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.windLoop : 0;
        }

        public boolean getWindMid() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.windMid : false;
        }

        public boolean getWindRear() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.windRear : false;
        }

        public boolean getWindRearShow() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.windRearShow : false;
        }

        public boolean getWindUp() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.windUp : false;
        }

        public boolean getWind_FrameShow() {
            return CanBusServer.this.ki != null ? CanBusServer.this.ki.wind_FrameShow : false;
        }

        public void registerCallback(ICanBusAidlCallBack iCanBusAidlCallBack) {
            CanBusServer.this.jk.register(iCanBusAidlCallBack);
        }

        public void sendAirKeyDown(int i, int i2) {
            if (CanBusServer.this.kl != null) {
                CanBusServer.this.kl.cz(i, i2);
            }
        }

        public void sendRequestAirCMD() {
            if (CanBusServer.this.kl != null) {
                CanBusServer.this.kl.al();
            }
        }

        public void setAriFloatViewShow(boolean z) {
            CanBusServer.this.kr = z;
        }

        public void unregisterCallback(ICanBusAidlCallBack iCanBusAidlCallBack) {
            CanBusServer.this.jk.unregister(iCanBusAidlCallBack);
        }
    }

    private void oq(byte[] bArr, int i) {
        int i2;
        if (kg + i >= 1024) {
            kg = 0;
            if (i >= 1024) {
                i = 1024;
            }
        }
        for (i2 = 0; i2 < i; i2++) {
            kx[kg + i2] = bArr[i2];
        }
        int i3 = i + kg;
        int i4 = 0;
        while (i4 < i3) {
            i2 = oy(kx, i4, i3);
            if (i2 == -1) {
                break;
            }
            i4 = i2 & 65535;
            int i5 = (i2 >> 16) & 65535;
            i2 = i5 + 1;
            ov(kx, i4, i5);
            i4 = i2;
        }
        kg = i3 - i4;
        for (i2 = 0; i2 < kg; i2++) {
            kx[i2] = kx[i2 + i4];
        }
    }

    private void ov(byte[] bArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = 1; i4 < i2 - i; i4++) {
            i3 = (short) (i3 + bArr[i + i4]);
        }
        if (((byte) ((i3 & 255) ^ 255)) == bArr[i2]) {
            if (this.kl != null) {
                this.kl.mo35b(bArr, i, i2);
            }
            pc(bArr, i, i2);
        }
    }

    private void ow(int i) {
        if (this.kl != null) {
            this.kl.ht(i);
        }
    }

    private void ox() {
        this.jg = BaseApplication.py().qa();
        this.kh = BaseApplication.py().pz();
        if (C0035b.lp() <= 23) {
            BaseApplication.py().qf();
        }
        switch (this.kh) {
            case R$styleable.MyButton_imgHeight /*1*/:
                this.kl = new C0011f(this, this.kn);
                break;
            case R$styleable.MyButton_imgSrc /*2*/:
                this.kl = new cr(this, this.kn);
                break;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.kl = new as(this, this.kn);
                break;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                this.kl = new bh(this, this.kn);
                break;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.kl = new C0005k(this, this.kn);
                break;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                this.kl = new C0004b(this, this.kn);
                break;
            case 7:
                this.kl = new C0003u(this, this.kn);
                break;
            case 8:
                this.kl = new aa(this, this.kn);
                break;
            case 9:
                this.kl = new bn(this, this.kn);
                break;
            case 10:
                this.kl = new bx(this, this.kn);
                break;
            case 11:
                this.kl = new ao(this, this.kn);
                break;
            case 12:
                this.kl = new ce(this, this.kn);
                break;
            case 13:
                this.kl = new bz(this, this.kn);
                break;
            case 14:
                this.kl = new C0017n(this, this.kn);
                break;
            case 15:
                this.kl = new C0002a(this, this.kn);
                break;
            case 16:
                this.kl = new C0007i(this, this.kn);
                break;
            case 17:
                this.kl = new ai(this, this.kn);
                break;
            case 18:
                this.kl = new C0019p(this, this.kn);
                break;
            case 19:
                this.kl = new cp(this, this.kn);
                break;
            case 20:
                this.kl = new cd(this, this.kn);
                break;
            case 21:
                this.kl = new bt(this, this.kn);
                break;
            case 22:
                this.kl = new cm(this, this.kn);
                break;
            case 23:
                this.kl = new cu(this, this.kn);
                break;
            case 24:
                this.kl = new C0014j(this, this.kn);
                break;
            case 25:
                this.kl = new C0015l(this, this.kn);
                break;
            case 26:
                this.kl = new ae(this, this.kn);
                break;
            case 27:
                this.kl = new dc(this, this.kn);
                break;
            case 28:
                this.kl = new cc(this, this.kn);
                break;
            case 29:
                this.kl = new dj(this, this.kn);
                break;
            case 30:
                this.kl = new ba(this, this.kn);
                break;
            case 31:
                this.kl = new ab(this, this.kn);
                break;
            case 32:
                this.kl = new ck(this, this.kn);
                break;
            case 33:
                this.kl = new cv(this, this.kn);
                break;
            case 34:
                this.kl = new C0008d(this, this.kn);
                break;
            case 35:
                this.kl = new da(this, this.kn);
                break;
            case 36:
                this.kl = new an(this, this.kn);
                break;
            case 37:
                this.kl = new ci(this, this.kn);
                break;
            case 38:
                this.kl = new ca(this, this.kn);
                break;
            case 39:
                this.kl = new cf(this, this.kn);
                break;
            case 40:
                this.kl = new az(this, this.kn);
                break;
            case 41:
                this.kl = new C0012g(this, this.kn);
                break;
            case 42:
                this.kl = new cb(this, this.kn);
                break;
            case 43:
                this.kl = new db(this, this.kn);
                break;
            case 44:
                this.kl = new C0026x(this, this.kn);
                break;
            case 45:
                this.kl = new di(this, this.kn);
                break;
            case 46:
                this.kl = new bf(this, this.kn);
                break;
            case 47:
                this.kl = new bi(this, this.kn);
                break;
            case 48:
                this.kl = new bj(this, this.kn);
                break;
            case 49:
                this.kl = new cs(this, this.kn);
                break;
            case 50:
                this.kl = new am(this, this.kn);
                break;
            case 51:
                this.kl = new al(this, this.kn);
                break;
            case 52:
                this.kl = new C0013h(this, this.kn);
                break;
            case 53:
                this.kl = new C0028z(this, this.kn);
                break;
            case 54:
                this.kl = new bp(this, this.kn);
                break;
            case 55:
                this.kl = new ct(this, this.kn);
                break;
            case 56:
                this.kl = new C0021r(this, this.kn);
                break;
            case 57:
                this.kl = new C0022s(this, this.kn);
                break;
            case 58:
                this.kl = new ay(this, this.kn);
                break;
            case 59:
                this.kl = new aj(this, this.kn);
                break;
            case 60:
                this.kl = new C0020q(this, this.kn);
                break;
            case 61:
                this.kl = new bs(this, this.kn);
                break;
            case 62:
                this.kl = new bv(this, this.kn);
                break;
            case 63:
                this.kl = new bk(this, this.kn);
                break;
            case 64:
                this.kl = new cz(this, this.kn);
                break;
            case 65:
                this.kl = new ak(this, this.kn);
                break;
            case 66:
                this.kl = new dg(this, this.kn);
                break;
            case 67:
                this.kl = new dh(this, this.kn);
                break;
            case 68:
                this.kl = new au(this, this.kn);
                break;
            case 69:
                this.kl = new bl(this, this.kn);
                break;
            case 70:
                this.kl = new ac(this, this.kn);
                break;
            case 71:
                this.kl = new cn(this, this.kn);
                break;
            case 72:
                this.kl = new C0018o(this, this.kn);
                break;
            case 73:
                this.kl = new C0027y(this, this.kn);
                break;
            case 74:
                this.kl = new aq(this, this.kn);
                break;
            case 75:
                this.kl = new br(this, this.kn);
                break;
            case 76:
                this.kl = new af(this, this.kn);
                break;
            case 77:
                this.kl = new ch(this, this.kn);
                break;
            case 78:
                this.kl = new cl(this, this.kn);
                break;
            case 79:
                this.kl = new cq(this, this.kn);
                break;
            case 80:
                this.kl = new cj(this, this.kn);
                break;
            case 81:
                this.kl = new bm(this, this.kn);
                break;
            case 82:
                this.kl = new C0024v(this, this.kn);
                break;
            case 83:
                this.kl = new ah(this, this.kn);
                break;
            case 84:
                this.kl = new C0016m(this, this.kn);
                break;
            case 85:
                this.kl = new bg(this, this.kn);
                break;
            case 86:
                this.kl = new df(this, this.kn);
                break;
            case 87:
                this.kl = new av(this, this.kn);
                break;
            case 88:
                this.kl = new bc(this, this.kn);
                break;
            case 89:
                this.kl = new C0023t(this, this.kn);
                break;
            case 90:
                this.kl = new cy(this, this.kn);
                break;
            case 91:
                this.kl = new ax(this, this.kn);
                break;
            case 92:
                this.kl = new bb(this, this.kn);
                break;
            case 93:
                this.kl = new de(this, this.kn);
                break;
            case 94:
                this.kl = new C0006c(this, this.kn);
                break;
            case 95:
                this.kl = new aw(this, this.kn);
                break;
            case 96:
                this.kl = new bq(this, this.kn);
                break;
            case 97:
                this.kl = new co(this, this.kn);
                break;
            case 98:
                this.kl = new C0010e(this, this.kn);
                break;
            case 99:
                this.kl = new cg(this, this.kn);
                break;
            case 100:
                this.kl = new C0025w(this, this.kn);
                break;
            case 101:
                this.kl = new bd(this, this.kn);
                break;
            case 102:
                this.kl = new ap(this, this.kn);
                break;
            case 103:
                this.kl = new bw(this, this.kn);
                break;
            case 104:
                this.kl = new by(this, this.kn);
                break;
            case 105:
                this.kl = new ar(this, this.kn);
                break;
            case 106:
                this.kl = new bu(this, this.kn);
                break;
            case 107:
                this.kl = new bo(this, this.kn);
                break;
            case 108:
                this.kl = new at(this, this.kn);
                break;
            case 109:
                this.kl = new ad(this, this.kn);
                break;
            case 110:
                this.kl = new ag(this, this.kn);
                break;
            case 111:
                this.kl = new be(this, this.kn);
                break;
            case 112:
                this.kl = new cx(this, this.kn);
                break;
            case 113:
                this.kl = new dd(this, this.kn);
                break;
            case 114:
                this.kl = new cw(this, this.kn);
                break;
        }
        this.kw = BaseApplication.py().qg("sta_mcu_version=");
        this.jh = C0070a.tv("ro.product.customer", "HCT");
        if ((this.jh != null && this.jh.startsWith("KLD")) || this.kw.contains("KLD")) {
            this.ku = new C0037a(this, this.kn);
        }
        ph();
        pg();
        oa(0);
        oh(0);
        os("");
    }

    private int oy(byte[] bArr, int i, int i2) {
        while (i < i2) {
            if (bArr[i] == (byte) 46) {
                if (i2 - i > 3) {
                    int i3 = bArr[i + 2] + 4;
                    int i4 = (i3 + i) - 1;
                    if (i2 - i >= i3) {
                        return (i4 << 16) | i;
                    }
                }
                return -1;
            }
            i++;
        }
        return -1;
    }

    private byte oz(int i) {
        int i2 = (i >> 4) & 15;
        int i3 = i & 15;
        return (i2 != 7 || i3 < 3 || i3 > 4) ? (i2 != 7 || i3 < 5) ? (byte) i3 : (byte) (i3 - 2) : (byte) 2;
    }

    private void pc(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        int i4 = bArr[i + 2] & 255;
        if (i3 > 3 && i3 < 100) {
            if ((pb() || C0035b.ea || BaseApplication.py().qi()) && i4 >= 1) {
                byte[] bArr2 = new byte[(i3 + 1)];
                for (i4 = 0; i4 <= i3; i4++) {
                    if (i + i4 < 1024) {
                        bArr2[i4] = bArr[i + i4];
                    }
                }
                Intent intent = new Intent("com.microntek.canbus.receiver");
                intent.putExtra("com.microntek.receiverkey", bArr2);
                this.kn.sendBroadcastAsUser(intent, UserHandle.CURRENT_OR_SELF);
            }
        }
    }

    private void pg() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.microntek.kglradiowritexml", "com.microntek.kglradiowritexml.XMLSercice"));
            this.kn.startServiceAsUser(intent, UserHandle.OWNER);
        } catch (Exception e) {
        }
    }

    private void ph() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.microntek.tpms", "com.microntek.tpms.MyService"));
            this.kn.startServiceAsUser(intent, UserHandle.OWNER);
        } catch (Exception e) {
        }
    }

    public synchronized void UpdateAirUiView() {
        try {
            int beginBroadcast = this.jk.beginBroadcast();
            for (int i = 0; i < beginBroadcast; i++) {
                ((ICanBusAidlCallBack) this.jk.getBroadcastItem(i)).UpdateAirUiView();
            }
            this.jk.finishBroadcast();
        } catch (Exception e) {
        }
    }

    public void oa(int i) {
        System.putInt(getContentResolver(), "com.microntek.RadarViewShow", i);
    }

    public void ob(byte b, byte[] bArr, int i) {
        int i2 = 0;
        byte[] bArr2 = new byte[(i + 4)];
        bArr2[0] = (byte) 46;
        bArr2[1] = b;
        bArr2[2] = (byte) (i & 255);
        int i3 = (short) (bArr2[1] + bArr2[2]);
        while (i2 < i) {
            bArr2[i2 + 3] = bArr[i2];
            i3 = (short) (i3 + bArr2[i2 + 3]);
            i2++;
        }
        bArr2[i + 3] = (byte) ((i3 & 255) ^ 255);
        oo(bArr2);
    }

    public int oc() {
        return BaseApplication.py().qe();
    }

    public void od(AirCondition airCondition) {
        try {
            this.ki = airCondition;
            UpdateAirUiView();
            if (this.kr && this.ki.viewShow && !C0034a.ln(this.kn)) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("android.microntek.canbus", "android.microntek.canbus.AirServer"));
                intent.putExtra("canbustype", this.kh);
                intent.putExtra("airdata", this.ki);
                this.kn.startServiceAsUser(intent, UserHandle.OWNER);
            }
        } catch (Exception e) {
        }
    }

    public void oe(Radar radar) {
        try {
            if (System.getInt(getContentResolver(), "PackingRadarEN", 1) == 1) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("android.microntek.canbus", "android.microntek.canbus.RadarServer"));
                intent.putExtra("canbustype", this.kh);
                intent.putExtra("radardata", radar);
                this.kn.startServiceAsUser(intent, UserHandle.OWNER);
            }
        } catch (Exception e) {
        }
    }

    public void of(Door door) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("android.microntek.canbus", "android.microntek.canbus.DoorServer"));
            intent.putExtra("canbustype", this.kh);
            intent.putExtra("doordata", door);
            this.kn.startServiceAsUser(intent, UserHandle.OWNER);
        } catch (Exception e) {
        }
    }

    public void og(byte[] bArr) {
        if (bArr[0] == (byte) 121 && bArr[2] > (byte) 0 && bArr[2] < (byte) 5) {
            String str = this.kh == 72 ? "com.microntek.sync.SyncCanBus72" : "com.microntek.sync.SyncCanBus6";
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(new ComponentName("com.microntek.sync", str));
            intent.addFlags(807600128);
            intent.putExtra("canbus", 1);
            try {
                startActivityAsUser(intent, UserHandle.CURRENT_OR_SELF);
            } catch (ActivityNotFoundException e) {
                Log.e("CanBusServer", "SYNC activity not found!");
            }
        }
        Intent intent2 = new Intent("com.microntek.sync");
        intent2.putExtra("syncdata", bArr);
        sendBroadcastAsUser(intent2, UserHandle.CURRENT_OR_SELF);
    }

    public void oh(int i) {
        System.putInt(getContentResolver(), "com.microntek.ReverseViewShow", i);
    }

    public void oi(byte b, byte[] bArr, int i) {
        int i2 = 0;
        byte[] bArr2 = new byte[(i + 4)];
        bArr2[0] = (byte) -3;
        bArr2[1] = (byte) ((i + 3) & 255);
        bArr2[2] = b;
        int i3 = (short) (bArr2[1] + bArr2[2]);
        while (i2 < i) {
            bArr2[i2 + 3] = bArr[i2];
            i3 = (short) (i3 + bArr2[i2 + 3]);
            i2++;
        }
        bArr2[i + 3] = (byte) (i3 & 255);
        oo(bArr2);
    }

    public void oj(byte[] bArr) {
        Intent intent = new Intent("com.microntek.sync");
        intent.putExtra("syncdata", bArr);
        this.kn.sendBroadcastAsUser(intent, UserHandle.CURRENT_OR_SELF);
    }

    public void ok(byte b, byte[] bArr, int i) {
        int i2 = 0;
        byte[] bArr2 = new byte[(i + 5)];
        bArr2[0] = (byte) -86;
        bArr2[1] = (byte) 85;
        bArr2[2] = (byte) (i & 255);
        bArr2[3] = b;
        int i3 = (short) (bArr2[2] + bArr2[3]);
        while (i2 < i) {
            bArr2[i2 + 4] = bArr[i2];
            i3 = (short) (i3 + bArr2[i2 + 4]);
            i2++;
        }
        bArr2[i + 4] = (byte) ((i3 - 1) & 255);
        oo(bArr2);
    }

    public void ol(byte b, byte[] bArr, int i) {
        int i2 = 0;
        byte[] bArr2 = new byte[(i + 5)];
        bArr2[0] = (byte) 90;
        bArr2[1] = (byte) -91;
        bArr2[2] = (byte) (i & 255);
        bArr2[3] = b;
        int i3 = (short) (bArr2[2] + bArr2[3]);
        while (i2 < i) {
            bArr2[i2 + 4] = bArr[i2];
            i3 = (short) (i3 + bArr2[i2 + 4]);
            i2++;
        }
        bArr2[i + 4] = (byte) ((i3 - 1) & 255);
        oo(bArr2);
    }

    public void om(byte[] bArr) {
        Intent intent = new Intent("com.microntek.sync");
        intent.putExtra("syncdata", bArr);
        this.kn.sendBroadcastAsUser(intent, UserHandle.CURRENT_OR_SELF);
    }

    public void on(byte b, byte[] bArr, int i) {
        int i2 = 0;
        byte[] bArr2 = new byte[(i + 5)];
        bArr2[0] = (byte) -3;
        bArr2[1] = (byte) ((i + 4) & 255);
        bArr2[2] = b;
        int i3 = (short) (bArr2[1] + bArr2[2]);
        while (i2 < i) {
            bArr2[i2 + 3] = bArr[i2];
            i3 = (short) (i3 + bArr2[i2 + 3]);
            i2++;
        }
        bArr2[i + 3] = (byte) ((i3 >> 8) & 255);
        bArr2[i + 4] = (byte) (i3 & 255);
        oo(bArr2);
    }

    public IBinder onBind(Intent intent) {
        return this.kj;
    }

    public void onCreate() {
        super.onCreate();
        this.kn = getApplicationContext();
        ox();
        op();
        Log.i("CanBusServer", "CanBus is Start 6.0 ! " + this.kh);
        Log.i("CanBusServer", "CanBus is Items ! " + this.jg);
    }

    public void onDestroy() {
        this.km.detach();
        unregisterReceiver(this.ji);
        if (this.kv != null) {
            try {
                this.kv.close();
            } catch (IOException e) {
            }
            this.kv = null;
        }
        super.onDestroy();
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
    }

    public void oo(byte[] bArr) {
        String str = "";
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            str = str + String.format(Locale.US, "%d", new Object[]{Integer.valueOf(bArr[i] & 255)});
            if (i < length - 1) {
                str = str + ",";
            }
        }
        this.kp.removeMessages(1);
        BaseApplication.py().qh("canbus_rsp=" + str);
        Message obtainMessage = this.kp.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = bArr;
        this.kp.sendMessageDelayed(obtainMessage, 300);
    }

    public void op() {
        SerialManager serialManager = (SerialManager) this.kn.getSystemService("serial");
        this.kq = ByteBuffer.allocate(1024);
        try {
            this.kv = serialManager.openSerialPort("/dev/ttyV0", 38400);
            if (this.kv != null) {
                new Thread(new C0040e(this)).start();
            } else {
                Log.i("CanBusServer", "open canbus serial fail!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("CanBusServer", "open canbus serial fail!");
        }
        this.km = new CarManager();
        this.km.attach(new C0041f(this), "CarEvent,KeyDown");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.microntek.canbusdisplay");
        intentFilter.addAction("com.microntek.canbus.send");
        intentFilter.addAction("com.microntek.canbus20activity");
        intentFilter.addAction("com.microntek.bootcheck");
        intentFilter.addAction("com.microntek.bt.report");
        intentFilter.addAction("com.microntek.screenOnOff");
        registerReceiver(this.ji, intentFilter);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("com.microntek.VOLUME_CHANGED");
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        intentFilter.addAction("com.microntek.irkeyDown");
        registerReceiver(this.jj, intentFilter);
        if (this.kl != null) {
            this.kl.mo36d();
        }
        if (this.ku != null) {
            this.ku.mo36d();
        }
        this.kp.removeMessages(2);
        this.kp.sendEmptyMessageDelayed(2, 1000);
        System.putInt(getContentResolver(), "com.microntek.hiworld.ari", 0);
        this.kp.removeMessages(7);
        this.kp.sendEmptyMessageDelayed(7, 400);
    }

    public void or(byte b, byte[] bArr, int i) {
        int i2 = 0;
        byte[] bArr2 = new byte[(i + 4)];
        bArr2[0] = (byte) 85;
        bArr2[1] = b;
        bArr2[2] = (byte) (i & 255);
        int i3 = (short) bArr2[2];
        while (i2 < i) {
            bArr2[i2 + 3] = bArr[i2];
            i3 = (short) (i3 + bArr2[i2 + 3]);
            i2++;
        }
        bArr2[i + 3] = (byte) ((i3 - 1) & 255);
        oo(bArr2);
    }

    public void os(String str) {
        System.putString(getContentResolver(), "com.microntek.canbusversion", str);
    }

    public void ot(String str) {
        BaseApplication.py().qh("ctl_lcd=" + (str + ""));
        this.kp.removeMessages(6);
        Message obtainMessage = this.kp.obtainMessage();
        obtainMessage.what = 6;
        obtainMessage.obj = str;
        this.kp.sendMessageDelayed(obtainMessage, 3000);
    }

    public void ou(Intent intent) {
        String stringExtra = intent.getStringExtra("type");
        if (stringExtra == null) {
            return;
        }
        int intExtra;
        int intExtra2;
        long j;
        if (stringExtra.equals("radio") && this.ky == 9) {
            this.ko = intent;
            intExtra = intent.getIntExtra("group", 0);
            intExtra2 = intent.getIntExtra("fre", 87500);
            byte byteExtra = intent.getByteExtra("index", (byte) 0);
            j = (long) ((intExtra + intExtra2) + byteExtra);
            if (!this.kk && this.kt != j) {
                this.kt = j;
                if (this.kl != null) {
                    this.kl.mo47o(oz(intExtra), intExtra2, byteExtra);
                }
                if (this.ku != null) {
                    this.ku.mo47o((byte) intExtra, intExtra2, byteExtra);
                }
            }
        } else if (stringExtra.equals("dvd") && this.ky == 10) {
            this.ko = intent;
            intExtra = intent.getIntExtra("item", 0);
            intExtra2 = intent.getIntExtra("all", 0);
            r2 = intent.getIntExtra("time", 0);
            int intExtra3 = intent.getIntExtra("mDiskType", -1);
            j = (long) (((intExtra2 + intExtra) + r2) + intExtra3);
            if (!this.kk && this.kt != j) {
                if (this.kl != null) {
                    this.kl.mo42j(intExtra, r2, intExtra3);
                }
                if (this.ku != null) {
                    this.ku.mo42j(intExtra, r2, intExtra3);
                }
            }
        } else if (stringExtra.equals("music") && this.ky == 3) {
            this.ko = intent;
            intExtra = intent.getIntExtra("all", 0);
            intExtra2 = intent.getIntExtra("cur", 0);
            r2 = intent.getIntExtra("time", 0);
            j = (long) ((intExtra + intExtra2) + r2);
            if (!this.kk && this.kt != j) {
                if (this.kl != null) {
                    this.kl.mo45m(intExtra, intExtra2, r2);
                }
                if (this.ku != null) {
                    this.ku.mo45m(intExtra, intExtra2, r2);
                }
            }
        } else if (stringExtra.equals("usb") && this.ky == 12) {
            this.ko = intent;
            intExtra = intent.getIntExtra("all", 0);
            intExtra2 = intent.getIntExtra("cur", 0);
            r2 = intent.getIntExtra("time", 0);
            j = (long) ((intExtra + intExtra2) + r2);
            if (!this.kk && this.kt != j) {
                if (this.kl != null) {
                    this.kl.mo45m(intExtra, intExtra2, r2);
                }
                if (this.ku != null) {
                    this.ku.mo45m(intExtra, intExtra2, r2);
                }
            }
        } else if (stringExtra.equals("movie") && this.ky == 4) {
            this.ko = intent;
            intExtra = intent.getIntExtra("all", 0);
            intExtra2 = intent.getIntExtra("cur", 0);
            r2 = intent.getIntExtra("time", 0);
            j = (long) ((r2 + intExtra) + intExtra2);
            if (!this.kk && this.kt != j) {
                this.kt = j;
                if (this.kl != null) {
                    this.kl.mo44l();
                    this.kl.ak(intExtra, intExtra2, r2);
                }
                if (this.ku != null) {
                    this.ku.mo44l();
                }
            }
        } else if (stringExtra.equals("bluetooth") && this.ky == 2) {
            this.ko = intent;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo40h(this.ks, 0);
                }
                if (this.ku != null) {
                    this.ku.mo40h(this.ks, 0);
                }
            }
        } else if (stringExtra.equals("a2dp") && this.ky == 11) {
            this.ko = intent;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo37e();
                }
                if (this.ku != null) {
                    this.ku.mo37e();
                }
            }
        } else if (stringExtra.equals("avin") && this.ky == 1) {
            this.ko = intent;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo39g();
                }
                if (this.ku != null) {
                    this.ku.mo39g();
                }
            }
        } else if (stringExtra.equals("dtv") && this.ky == 7) {
            this.ko = intent;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo41i();
                }
                if (this.ku != null) {
                    this.ku.mo41i();
                }
            }
        } else if (stringExtra.equals("ntv") && this.ky == 8) {
            this.ko = intent;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo41i();
                }
                if (this.ku != null) {
                    this.ku.mo41i();
                }
            }
        } else if (stringExtra.equals("atv") && this.ky == 6) {
            this.ko = intent;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo38f();
                }
                if (this.ku != null) {
                    this.ku.mo38f();
                }
            }
        } else if (stringExtra.equals("ipod") && this.ky == 5) {
            this.ko = intent;
            intExtra2 = intent.getIntExtra("all", 0);
            r2 = intent.getIntExtra("cur", 0);
            int intExtra4 = intent.getIntExtra("time", 0);
            int intExtra5 = intent.getIntExtra("musicposition", 0);
            byte byteExtra2 = intent.getByteExtra("bili", (byte) 0);
            long j2 = (long) ((((intExtra2 + r2) + intExtra4) + intExtra5) + byteExtra2);
            if (!this.kk && this.kt != j2) {
                if (this.kl != null) {
                    this.kl.mo43k(intExtra2, r2, byteExtra2, intExtra5, intExtra4);
                }
                if (this.ku != null) {
                    this.ku.mo43k(intExtra2, r2, byteExtra2, intExtra5, intExtra4);
                }
            }
        } else if (stringExtra.equals("ipod-on")) {
            this.ko = intent;
            this.ky = 5;
            this.kt = -1;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo43k(0, 0, (byte) 0, 0, 0);
                }
                if (this.ku != null) {
                    this.ku.mo43k(0, 0, (byte) 0, 0, 0);
                }
            }
        } else if (stringExtra.equals("radio-on")) {
            this.ko = intent;
            this.ky = 9;
            this.kt = -1;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo47o((byte) 0, 0, (byte) 0);
                }
                if (this.ku != null) {
                    this.ku.mo47o((byte) 0, 0, (byte) 0);
                }
            }
        } else if (stringExtra.equals("movie-on")) {
            this.ko = intent;
            this.ky = 4;
            this.kt = -1;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo44l();
                    this.kl.ak(0, 0, 0);
                }
                if (this.ku != null) {
                    this.ku.mo44l();
                }
            }
        } else if (stringExtra.equals("ntv-on")) {
            this.ko = intent;
            this.ky = 8;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo41i();
                }
                if (this.ku != null) {
                    this.ku.mo41i();
                }
            }
        } else if (stringExtra.equals("atv-on")) {
            this.ko = intent;
            this.ky = 6;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo38f();
                }
                if (this.ku != null) {
                    this.ku.mo38f();
                }
            }
        } else if (stringExtra.equals("dtv-on")) {
            this.ko = intent;
            this.ky = 7;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo41i();
                }
                if (this.ku != null) {
                    this.ku.mo41i();
                }
            }
        } else if (stringExtra.equals("avin-on")) {
            this.ko = intent;
            this.ky = 1;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo39g();
                }
                if (this.ku != null) {
                    this.ku.mo39g();
                }
            }
        } else if (stringExtra.equals("bluetooth-on")) {
            this.ko = intent;
            this.ky = 2;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo40h(this.ks, 0);
                }
                if (this.ku != null) {
                    this.ku.mo40h(this.ks, 0);
                }
            }
        } else if (stringExtra.equals("a2dp-on")) {
            this.ko = intent;
            this.ky = 11;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo37e();
                }
                if (this.ku != null) {
                    this.ku.mo37e();
                }
            }
        } else if (stringExtra.equals("dvd-on")) {
            this.ko = intent;
            this.ky = 10;
            this.kt = -1;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo42j(0, 0, 0);
                }
                if (this.ku != null) {
                    this.ku.mo42j(0, 0, -1);
                }
            }
        } else if (stringExtra.equals("music-on")) {
            this.ko = intent;
            this.ky = 3;
            this.kt = -1;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo45m(0, 0, 0);
                }
                if (this.ku != null) {
                    this.ku.mo45m(0, 0, 0);
                }
            }
        } else if (stringExtra.equals("usb-on")) {
            this.ko = intent;
            this.ky = 12;
            this.kt = -1;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo45m(0, 0, 0);
                }
                if (this.ku != null) {
                    this.ku.mo45m(0, 0, 0);
                }
            }
        } else if (stringExtra.equals("ipod-off") && this.ky == 5) {
            this.ko = intent;
            this.ky = 0;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                }
            }
        } else if (stringExtra.equals("radio-off") && this.ky == 9) {
            this.ko = intent;
            this.ky = 0;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                }
            }
        } else if (stringExtra.equals("movie-off") && this.ky == 4) {
            this.ko = intent;
            this.ky = 0;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                }
            }
        } else if (stringExtra.equals("ntv-off") && this.ky == 8) {
            this.ko = intent;
            this.ky = 0;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                }
            }
        } else if (stringExtra.equals("atv-off") && this.ky == 6) {
            this.ko = intent;
            this.ky = 0;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                }
            }
        } else if (stringExtra.equals("dtv-off") && this.ky == 7) {
            this.ko = intent;
            this.ky = 0;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                }
            }
        } else if (stringExtra.equals("avin-off") && this.ky == 1) {
            this.ko = intent;
            this.ky = 0;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                }
            }
        } else if (stringExtra.equals("bluetooth-off") && this.ky == 2) {
            this.ko = intent;
            this.ky = 0;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                }
            }
        } else if (stringExtra.equals("a2dp-off") && this.ky == 11) {
            this.ko = intent;
            this.ky = 0;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                }
            }
        } else if (stringExtra.equals("dvd-off") && this.ky == 10) {
            this.ko = intent;
            this.ky = 0;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                }
            }
        } else if (stringExtra.equals("music-off") && this.ky == 3) {
            this.ko = intent;
            this.ky = 0;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                }
            }
        } else if (stringExtra.equals("usb-off") && this.ky == 12) {
            this.ko = intent;
            this.ky = 0;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                }
            }
        } else if (this.ky == 0 && stringExtra.contains("-off")) {
            this.ko = intent;
            this.ky = 0;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                }
            }
        } else if (stringExtra.equals("on")) {
            this.ko = intent;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.bh();
                }
                if (this.ku != null) {
                    this.ku.bh();
                }
            }
        } else if (stringExtra.equals("off")) {
            this.ko = intent;
            if (!this.kk) {
                if (this.kl != null) {
                    this.kl.mo46n();
                    this.kl.bg();
                }
                if (this.ku != null) {
                    this.ku.mo46n();
                    this.ku.bg();
                }
            }
        }
    }

    public String pa() {
        return ((RunningTaskInfo) ((ActivityManager) getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getClassName();
    }

    public boolean pb() {
        return BaseApplication.py().qd(this.kh);
    }

    public void pd(int i, int i2) {
        int parseInt;
        int i3;
        int i4 = 14;
        String string = System.getString(getContentResolver(), "KeyBalance");
        if (string != null) {
            String[] split = string.split(",");
            try {
                parseInt = Integer.parseInt(split[0]);
                try {
                    i4 = parseInt;
                    parseInt = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    i3 = i4;
                    i4 = parseInt;
                    parseInt = i3;
                    if (i == -1) {
                        i = i4;
                    }
                    if (i2 == -1) {
                        i2 = parseInt;
                    }
                    System.putString(getContentResolver(), "KeyBalance", i + "," + i2);
                    this.kn.sendBroadcast(new Intent("com.microntek.balancechange"));
                }
            } catch (NumberFormatException e2) {
                parseInt = i4;
                i3 = i4;
                i4 = parseInt;
                parseInt = i3;
                if (i == -1) {
                    i = i4;
                }
                if (i2 == -1) {
                    i2 = parseInt;
                }
                System.putString(getContentResolver(), "KeyBalance", i + "," + i2);
                this.kn.sendBroadcast(new Intent("com.microntek.balancechange"));
            }
        }
        parseInt = i4;
        if (i == -1) {
            i = i4;
        }
        if (i2 == -1) {
            i2 = parseInt;
        }
        System.putString(getContentResolver(), "KeyBalance", i + "," + i2);
        this.kn.sendBroadcast(new Intent("com.microntek.balancechange"));
    }

    public void pe(int i, int i2, int i3) {
        int parseInt;
        int parseInt2;
        int i4 = 10;
        System.putInt(getContentResolver(), "KeyEQmode", 0);
        String string = System.getString(getContentResolver(), "KeyCustomEQ");
        if (string != null) {
            String[] split = string.split(",");
            int i5;
            try {
                parseInt = Integer.parseInt(split[0]);
                try {
                    parseInt2 = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    parseInt2 = i4;
                    i5 = parseInt2;
                    parseInt2 = i4;
                    i4 = i5;
                    if (i == -1) {
                        i = parseInt2;
                    }
                    if (i2 == -1) {
                        i2 = i4;
                    }
                    if (i3 == -1) {
                        i3 = parseInt;
                    }
                    System.putInt(getContentResolver(), "KeyEQmode", 0);
                    System.putString(getContentResolver(), "KeyCustomEQ", i3 + "," + i2 + "," + i);
                    this.kn.sendBroadcast(new Intent("com.microntek.eqchange"));
                }
                try {
                    i5 = parseInt2;
                    parseInt2 = Integer.parseInt(split[2]);
                    i4 = i5;
                } catch (NumberFormatException e2) {
                    i5 = parseInt2;
                    parseInt2 = i4;
                    i4 = i5;
                    if (i == -1) {
                        i = parseInt2;
                    }
                    if (i2 == -1) {
                        i2 = i4;
                    }
                    if (i3 == -1) {
                        i3 = parseInt;
                    }
                    System.putInt(getContentResolver(), "KeyEQmode", 0);
                    System.putString(getContentResolver(), "KeyCustomEQ", i3 + "," + i2 + "," + i);
                    this.kn.sendBroadcast(new Intent("com.microntek.eqchange"));
                }
            } catch (NumberFormatException e3) {
                parseInt = i4;
                parseInt2 = i4;
                i5 = parseInt2;
                parseInt2 = i4;
                i4 = i5;
                if (i == -1) {
                    i = parseInt2;
                }
                if (i2 == -1) {
                    i2 = i4;
                }
                if (i3 == -1) {
                    i3 = parseInt;
                }
                System.putInt(getContentResolver(), "KeyEQmode", 0);
                System.putString(getContentResolver(), "KeyCustomEQ", i3 + "," + i2 + "," + i);
                this.kn.sendBroadcast(new Intent("com.microntek.eqchange"));
            }
        }
        parseInt = i4;
        parseInt2 = i4;
        if (i == -1) {
            i = parseInt2;
        }
        if (i2 == -1) {
            i2 = i4;
        }
        if (i3 == -1) {
            i3 = parseInt;
        }
        System.putInt(getContentResolver(), "KeyEQmode", 0);
        System.putString(getContentResolver(), "KeyCustomEQ", i3 + "," + i2 + "," + i);
        this.kn.sendBroadcast(new Intent("com.microntek.eqchange"));
    }

    public void pf() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.hiworld.canbus.services", "com.hiworld.canbus.services.SeiralService"));
            this.kn.startServiceAsUser(intent, UserHandle.OWNER);
        } catch (Exception e) {
        }
    }
}
