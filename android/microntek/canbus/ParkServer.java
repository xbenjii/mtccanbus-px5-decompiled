package android.microntek.canbus;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ParkServer extends Service {
    public static int nk = 1;
    private byte[] nj = null;
    private TextView nl;
    private TextView nm;
    private Handler nn = new C0060r(this);
    private ImageView no;
    private ImageView np;
    private ImageView nq;
    private ImageView nr;
    private Context ns;
    private View nt;
    private WindowManager nu;
    private FrameLayout nv;
    private TextView nw;
    private LinearLayout nx;
    private TextView ny;
    private ImageView nz;
    private LayoutParams oa;
    private int time = 0;

    private void su() {
        this.nn.removeMessages(0);
        this.nn.sendEmptyMessage(0);
    }

    private void sv() {
        if (BaseApplication.py().qe() == 0) {
            this.nr.setVisibility(8);
            this.nz.setVisibility(8);
            this.np.setVisibility(0);
            this.no.setVisibility(0);
            return;
        }
        this.np.setVisibility(4);
        this.no.setVisibility(8);
    }

    private void sw() {
        this.oa = new LayoutParams();
        this.nu = (WindowManager) getApplication().getSystemService("window");
        this.oa.type = 2003;
        this.oa.format = 1;
        this.oa.flags = 40;
        this.oa.gravity = 80;
        this.oa.x = 0;
        this.oa.y = 0;
        this.oa.width = -1;
        this.oa.height = -1;
        this.nt = LayoutInflater.from(getApplication()).inflate(R.layout.canbus62autopark, null);
        this.nu.addView(this.nt, this.oa);
        sx(this.nt);
        this.nt.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
    }

    private void sx(View view) {
        this.nv = (FrameLayout) view.findViewById(R.id.park);
        this.nx = (LinearLayout) view.findViewById(R.id.prompt);
        this.nr = (ImageView) view.findViewById(R.id.lf_round);
        this.nz = (ImageView) view.findViewById(R.id.ri_round);
        this.nq = (ImageView) view.findViewById(R.id.img_prompt);
        this.ny = (TextView) view.findViewById(R.id.prompt_info);
        this.nw = (TextView) view.findViewById(R.id.park_info);
        this.nl = (TextView) view.findViewById(R.id.careful);
        this.nm = (TextView) view.findViewById(R.id.countdown);
        this.np = (ImageView) view.findViewById(R.id.img_info);
        this.no = (ImageView) view.findViewById(R.id.img_bg);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void sy(byte[] r10) {
        /*
        r9 = this;
        r8 = 2130837537; // 0x7f020021 float:1.728003E38 double:1.052773624E-314;
        r7 = 2130837513; // 0x7f020009 float:1.7279982E38 double:1.052773612E-314;
        r6 = 2130837512; // 0x7f020008 float:1.727998E38 double:1.0527736115E-314;
        r5 = 2130837561; // 0x7f020039 float:1.728008E38 double:1.052773636E-314;
        r1 = 0;
        r0 = r9.nv;
        if (r0 == 0) goto L_0x0013;
    L_0x0011:
        if (r10 != 0) goto L_0x0014;
    L_0x0013:
        return;
    L_0x0014:
        r0 = r10.length;
        r2 = 2;
        if (r0 > r2) goto L_0x0019;
    L_0x0018:
        return;
    L_0x0019:
        r0 = r10[r1];
        r2 = 40;
        if (r0 != r2) goto L_0x0051;
    L_0x001f:
        r0 = 1;
        r0 = r10[r0];
        r0 = r0 & 1;
        if (r0 == 0) goto L_0x018c;
    L_0x0026:
        r0 = 2;
        r0 = r10[r0];
        r0 = r0 & 127;
        r2 = 39;
        if (r0 <= r2) goto L_0x0030;
    L_0x002f:
        r0 = r1;
    L_0x0030:
        r2 = r9.nw;
        r3 = r9.ns;
        r4 = 2131099661; // 0x7f06000d float:1.7811682E38 double:1.0529031304E-314;
        r4 = r4 + r0;
        r3 = r3.getString(r4);
        r2.setText(r3);
        switch(r0) {
            case 2: goto L_0x0052;
            case 3: goto L_0x005b;
            case 4: goto L_0x0062;
            case 5: goto L_0x0069;
            case 6: goto L_0x0073;
            case 7: goto L_0x007a;
            case 8: goto L_0x0081;
            case 9: goto L_0x0088;
            case 10: goto L_0x008f;
            case 11: goto L_0x0096;
            case 12: goto L_0x009d;
            case 13: goto L_0x00a4;
            case 14: goto L_0x00ae;
            case 15: goto L_0x00b8;
            case 16: goto L_0x00bf;
            case 17: goto L_0x00c7;
            case 18: goto L_0x00cf;
            case 19: goto L_0x00d7;
            case 20: goto L_0x00df;
            case 21: goto L_0x00e7;
            case 22: goto L_0x00ef;
            case 23: goto L_0x00f7;
            case 24: goto L_0x00ff;
            case 25: goto L_0x0107;
            case 26: goto L_0x010f;
            case 27: goto L_0x0117;
            case 28: goto L_0x0123;
            case 29: goto L_0x012f;
            case 30: goto L_0x0137;
            case 31: goto L_0x013f;
            case 32: goto L_0x0147;
            case 33: goto L_0x014f;
            case 34: goto L_0x0157;
            case 35: goto L_0x015f;
            case 36: goto L_0x0167;
            case 37: goto L_0x016f;
            case 38: goto L_0x0177;
            case 39: goto L_0x017f;
            default: goto L_0x0042;
        };
    L_0x0042:
        r2 = 2;
        if (r0 < r2) goto L_0x0187;
    L_0x0045:
        r2 = 39;
        if (r0 > r2) goto L_0x0187;
    L_0x0049:
        r0 = r9.nv;
        r0.setVisibility(r1);
    L_0x004e:
        r9.sv();
    L_0x0051:
        return;
    L_0x0052:
        r2 = 8;
        r3 = 2131099702; // 0x7f060036 float:1.7811765E38 double:1.0529031506E-314;
        r9.sz(r5, r2, r3, r1);
        goto L_0x0042;
    L_0x005b:
        r2 = 2131099703; // 0x7f060037 float:1.7811767E38 double:1.052903151E-314;
        r9.sz(r5, r1, r2, r1);
        goto L_0x0042;
    L_0x0062:
        r2 = 2130837802; // 0x7f02012a float:1.7280568E38 double:1.052773755E-314;
        r9.tc(r5, r2);
        goto L_0x0042;
    L_0x0069:
        r2 = 2130837572; // 0x7f020044 float:1.7280102E38 double:1.052773641E-314;
        r3 = 2130837737; // 0x7f0200e9 float:1.7280437E38 double:1.0527737227E-314;
        r9.tb(r2, r3);
        goto L_0x0042;
    L_0x0073:
        r2 = 2131099704; // 0x7f060038 float:1.7811769E38 double:1.0529031516E-314;
        r9.sz(r5, r1, r2, r1);
        goto L_0x0042;
    L_0x007a:
        r2 = 2130837571; // 0x7f020043 float:1.72801E38 double:1.0527736407E-314;
        r9.tb(r2, r7);
        goto L_0x0042;
    L_0x0081:
        r2 = 2130837570; // 0x7f020042 float:1.7280098E38 double:1.05277364E-314;
        r9.tb(r2, r7);
        goto L_0x0042;
    L_0x0088:
        r2 = 2130837571; // 0x7f020043 float:1.72801E38 double:1.0527736407E-314;
        r9.tb(r2, r7);
        goto L_0x0042;
    L_0x008f:
        r2 = 2130837570; // 0x7f020042 float:1.7280098E38 double:1.05277364E-314;
        r9.tb(r2, r7);
        goto L_0x0042;
    L_0x0096:
        r2 = 2130837576; // 0x7f020048 float:1.728011E38 double:1.052773643E-314;
        r9.tb(r2, r8);
        goto L_0x0042;
    L_0x009d:
        r2 = 2130837575; // 0x7f020047 float:1.7280108E38 double:1.0527736427E-314;
        r9.tb(r2, r8);
        goto L_0x0042;
    L_0x00a4:
        r2 = 2130837576; // 0x7f020048 float:1.728011E38 double:1.052773643E-314;
        r3 = 2130837536; // 0x7f020020 float:1.7280029E38 double:1.0527736234E-314;
        r9.tb(r2, r3);
        goto L_0x0042;
    L_0x00ae:
        r2 = 2130837575; // 0x7f020047 float:1.7280108E38 double:1.0527736427E-314;
        r3 = 2130837536; // 0x7f020020 float:1.7280029E38 double:1.0527736234E-314;
        r9.tb(r2, r3);
        goto L_0x0042;
    L_0x00b8:
        r2 = 2130837574; // 0x7f020046 float:1.7280106E38 double:1.052773642E-314;
        r9.tb(r2, r6);
        goto L_0x0042;
    L_0x00bf:
        r2 = 2130837569; // 0x7f020041 float:1.7280096E38 double:1.0527736397E-314;
        r9.tb(r2, r6);
        goto L_0x0042;
    L_0x00c7:
        r2 = 2130837574; // 0x7f020046 float:1.7280106E38 double:1.052773642E-314;
        r9.tb(r2, r6);
        goto L_0x0042;
    L_0x00cf:
        r2 = 2130837569; // 0x7f020041 float:1.7280096E38 double:1.0527736397E-314;
        r9.tb(r2, r6);
        goto L_0x0042;
    L_0x00d7:
        r2 = 2130837568; // 0x7f020040 float:1.7280094E38 double:1.052773639E-314;
        r9.tb(r2, r8);
        goto L_0x0042;
    L_0x00df:
        r2 = 2130837567; // 0x7f02003f float:1.7280092E38 double:1.0527736387E-314;
        r9.tb(r2, r8);
        goto L_0x0042;
    L_0x00e7:
        r2 = 2130837566; // 0x7f02003e float:1.728009E38 double:1.052773638E-314;
        r9.tb(r2, r7);
        goto L_0x0042;
    L_0x00ef:
        r2 = 2130837565; // 0x7f02003d float:1.7280088E38 double:1.0527736377E-314;
        r9.tb(r2, r7);
        goto L_0x0042;
    L_0x00f7:
        r2 = 2130837564; // 0x7f02003c float:1.7280086E38 double:1.052773637E-314;
        r9.tb(r2, r8);
        goto L_0x0042;
    L_0x00ff:
        r2 = 2130837563; // 0x7f02003b float:1.7280084E38 double:1.0527736367E-314;
        r9.tb(r2, r8);
        goto L_0x0042;
    L_0x0107:
        r2 = 2130837562; // 0x7f02003a float:1.7280082E38 double:1.0527736363E-314;
        r9.tb(r2, r6);
        goto L_0x0042;
    L_0x010f:
        r2 = 2130837573; // 0x7f020045 float:1.7280104E38 double:1.0527736417E-314;
        r9.tb(r2, r6);
        goto L_0x0042;
    L_0x0117:
        r2 = 8;
        r3 = 2131099705; // 0x7f060039 float:1.781177E38 double:1.052903152E-314;
        r4 = 8;
        r9.sz(r5, r2, r3, r4);
        goto L_0x0042;
    L_0x0123:
        r2 = 8;
        r3 = 2131099706; // 0x7f06003a float:1.7811773E38 double:1.0529031526E-314;
        r4 = 8;
        r9.ta(r5, r2, r3, r4);
        goto L_0x0042;
    L_0x012f:
        r2 = 2131099707; // 0x7f06003b float:1.7811775E38 double:1.052903153E-314;
        r9.sz(r5, r1, r2, r1);
        goto L_0x0042;
    L_0x0137:
        r2 = 2131099708; // 0x7f06003c float:1.7811777E38 double:1.0529031536E-314;
        r9.sz(r5, r1, r2, r1);
        goto L_0x0042;
    L_0x013f:
        r2 = 2131099709; // 0x7f06003d float:1.7811779E38 double:1.052903154E-314;
        r9.sz(r5, r1, r2, r1);
        goto L_0x0042;
    L_0x0147:
        r2 = 2131099710; // 0x7f06003e float:1.781178E38 double:1.0529031546E-314;
        r9.sz(r5, r1, r2, r1);
        goto L_0x0042;
    L_0x014f:
        r2 = 2131099711; // 0x7f06003f float:1.7811783E38 double:1.052903155E-314;
        r9.sz(r5, r1, r2, r1);
        goto L_0x0042;
    L_0x0157:
        r2 = 2131099712; // 0x7f060040 float:1.7811785E38 double:1.0529031556E-314;
        r9.sz(r5, r1, r2, r1);
        goto L_0x0042;
    L_0x015f:
        r2 = 2130837566; // 0x7f02003e float:1.728009E38 double:1.052773638E-314;
        r9.tb(r2, r7);
        goto L_0x0042;
    L_0x0167:
        r2 = 2130837565; // 0x7f02003d float:1.7280088E38 double:1.0527736377E-314;
        r9.tb(r2, r7);
        goto L_0x0042;
    L_0x016f:
        r2 = 2130837562; // 0x7f02003a float:1.7280082E38 double:1.0527736363E-314;
        r9.tb(r2, r6);
        goto L_0x0042;
    L_0x0177:
        r2 = 2130837573; // 0x7f020045 float:1.7280104E38 double:1.0527736417E-314;
        r9.tb(r2, r6);
        goto L_0x0042;
    L_0x017f:
        r2 = 2131099707; // 0x7f06003b float:1.7811775E38 double:1.052903153E-314;
        r9.sz(r5, r1, r2, r1);
        goto L_0x0042;
    L_0x0187:
        r9.su();
        goto L_0x004e;
    L_0x018c:
        r9.su();
        goto L_0x0051;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.microntek.canbus.ParkServer.sy(byte[]):void");
    }

    private void sz(int i, int i2, int i3, int i4) {
        this.nr.setVisibility(8);
        this.nz.setVisibility(8);
        this.nl.setVisibility(i2);
        this.nx.setVisibility(0);
        this.nq.setVisibility(i4);
        this.ny.setText(this.ns.getString(i3));
        this.nm.setVisibility(8);
        this.np.setBackgroundResource(i);
        this.time = 0;
    }

    private void ta(int i, int i2, int i3, int i4) {
        this.nr.setVisibility(8);
        this.nz.setVisibility(8);
        this.nl.setVisibility(i2);
        this.nx.setVisibility(0);
        this.nq.setVisibility(i4);
        this.ny.setText(this.ns.getString(i3));
        if (this.time <= 9) {
            this.nm.setText(this.time + " S");
            this.time++;
        }
        this.nm.setVisibility(0);
        this.np.setBackgroundResource(i);
    }

    private void tb(int i, int i2) {
        this.nx.setVisibility(8);
        this.nr.setVisibility(0);
        this.nz.setVisibility(8);
        this.nr.setImageResource(i2);
        this.time = 0;
        this.np.setBackgroundResource(i);
    }

    private void tc(int i, int i2) {
        this.nx.setVisibility(8);
        this.nr.setVisibility(8);
        this.nz.setVisibility(0);
        this.nz.setImageResource(i2);
        this.time = 0;
        this.np.setBackgroundResource(i);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        this.ns = getApplicationContext();
        super.onCreate();
        Log.i("ParkServer", "ParkServer onCreate");
    }

    public void onDestroy() {
        if (this.nt != null) {
            this.nu.removeView(this.nt);
        }
        this.nn.removeMessages(1);
        super.onDestroy();
        Log.i("ParkServer", "ParkServer onDestroy");
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        nk = intent.getIntExtra("canbustype", 0);
        this.nj = (byte[]) intent.getSerializableExtra("park");
        if (this.nt == null) {
            sw();
        }
        sy(this.nj);
        this.nn.sendEmptyMessage(1);
    }
}
