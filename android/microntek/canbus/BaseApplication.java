package android.microntek.canbus;

import android.app.Application;
import android.microntek.CarManager;
import android.microntek.canbus.p002b.C0035b;
import android.util.Log;

public class BaseApplication extends Application {
    private static BaseApplication lf;
    private int kz = 0;
    private int la = 0;
    private int lb = 0;
    private int lc = 0;
    private boolean ld;
    private boolean le = true;
    private CarManager lg;
    private C0029a lh;

    public static BaseApplication py() {
        return lf;
    }

    private void ql() {
        if (C0035b.lp() > 19) {
            try {
                this.lc = Integer.parseInt(qg("cfg_cansub_1="));
            } catch (Exception e) {
            }
            try {
                this.la = Integer.parseInt(qg("cfg_cansub_3="));
            } catch (Exception e2) {
            }
            try {
                this.kz = Integer.parseInt(qg("cfg_cansub_7="));
            } catch (Exception e3) {
            }
        } else {
            try {
                this.lc = Integer.parseInt(qg("cfg_canbus_cfg="));
            } catch (Exception e4) {
            }
            this.lc >>= 4;
        }
        try {
            this.lb = Integer.parseInt(qg("cfg_canbus="));
        } catch (Exception e5) {
        }
    }

    public void onCreate() {
        super.onCreate();
        lf = this;
        this.lg = new CarManager();
        this.lh = new C0029a();
        ql();
        Log.i("CanBusServer", "CanBus Application  onCreate! ");
    }

    public int pz() {
        return this.lb;
    }

    public int qa() {
        return this.lc;
    }

    public boolean qb() {
        return this.le;
    }

    public void qc(boolean z) {
        this.le = z;
    }

    public boolean qd(int i) {
        return i == 26 || i == 27 || i == 58 || i == 47 || i == 60 || i == 59 || i == 64 || i == 16 || i == 32;
    }

    public int qe() {
        return this.lg.getBooleanState("backview") ? 1 : 0;
    }

    public void qf() {
        this.lh.ny(lf);
    }

    public String qg(String str) {
        return this.lg.getParameters(str);
    }

    public void qh(String str) {
        this.lg.setParameters(str);
    }

    public boolean qi() {
        return this.ld;
    }

    public int qj() {
        return this.kz;
    }

    public int qk() {
        return this.la;
    }

    public void qm(boolean z) {
        this.ld = z;
    }
}
