package android.microntek.canbus;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.microntek.canbus.serializable.Radar;
import android.microntek.canbus.view.Cicleview;
import android.os.Handler;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageButton;

public class RadarServer extends Service {
    public static int ob = 1;
    private Handler oc = new C0061s(this);
    private boolean od;
    private View oe;
    private View of;
    private Radar og;
    private WindowManager oh;
    private WindowManager oi;
    private ImageButton oj;
    private SharedPreferences ok;
    private Cicleview ol;
    private LayoutParams om;
    private LayoutParams on;

    private void tg() {
        this.on = new LayoutParams();
        this.oi = (WindowManager) getApplication().getSystemService("window");
        this.on.type = 2002;
        this.on.format = 1;
        this.on.flags = 40;
        this.on.gravity = 83;
        this.on.x = 0;
        this.on.y = 0;
        this.on.width = -2;
        this.on.height = -2;
        this.of = LayoutInflater.from(getApplication()).inflate(R.layout.radarlayout_display, null);
        this.oi.addView(this.of, this.on);
        this.oj = (ImageButton) this.of.findViewById(R.id.radar_display);
        this.oj.setOnClickListener(new C0062t(this));
    }

    private void th() {
        this.om = new LayoutParams();
        this.oh = (WindowManager) getApplication().getSystemService("window");
        this.om.type = 2002;
        this.om.format = 1;
        this.om.flags = 56;
        this.om.gravity = 83;
        this.om.x = 0;
        this.om.y = 0;
        this.om.width = -2;
        this.om.height = -2;
        this.oe = LayoutInflater.from(getApplication()).inflate(R.layout.radarlayout, null);
        if (this.od) {
            this.oh.addView(this.oe, this.om);
        }
        this.ol = (Cicleview) this.oe.findViewById(R.id.myview);
    }

    private void ti() {
        if (this.oe == null) {
            th();
            tg();
        }
        this.ol.li(this.og);
        this.oe.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.ok = getSharedPreferences("spRadarService", 0);
        this.od = this.ok.getBoolean("isRadarDisplay", true);
    }

    public void onDestroy() {
        if (this.oe != null) {
            if (this.od) {
                this.oh.removeView(this.oe);
            }
            this.oi.removeView(this.of);
        }
        super.onDestroy();
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        ob = intent.getIntExtra("canbustype", 1);
        this.og = (Radar) intent.getSerializableExtra("radardata");
        this.oc.removeMessages(0);
        if (!this.og.zero_show && this.og.f1 == 0 && this.og.f2 == 0 && this.og.f3 == 0 && this.og.f4 == 0 && this.og.f5 == 0 && this.og.f6 == 0 && this.og.b1 == 0 && this.og.b2 == 0 && this.og.b3 == 0 && this.og.b4 == 0) {
            this.oc.sendEmptyMessage(0);
            return;
        }
        if (this.og.view_show) {
            this.oc.sendEmptyMessageDelayed(0, 3000);
        } else {
            this.oc.removeMessages(0);
        }
        ti();
    }
}
