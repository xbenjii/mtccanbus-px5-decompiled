package android.microntek.canbus;

import android.app.Service;
import android.content.Intent;
import android.microntek.canbus.serializable.Warning;
import android.microntek.canbus.view.MyTextView;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;

public class WarningServer extends Service {
    private final int ls = 256;
    private boolean lt = false;
    private Button lu;
    private View lv;
    private Handler lw = new C0044i(this);
    private ImageView lx;
    private ImageView ly;
    private MyTextView lz;
    private Warning ma = null;
    private WindowManager mb;
    private LayoutParams mc;
    private int[][][] warningInfo = null;

    private boolean qt(int[][][] iArr) {
        if (this.warningInfo == null || iArr.length != this.warningInfo.length) {
            return true;
        }
        for (int i = 0; i < iArr.length; i++) {
            for (int i2 = 0; i2 < iArr[i].length; i2++) {
                for (int i3 = 0; i3 < iArr[i][i2].length; i3++) {
                    if (iArr[i][i2][0] != this.warningInfo[i][i2][0]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void qu() {
        this.mc = new LayoutParams();
        this.mb = (WindowManager) getApplication().getSystemService("window");
        this.mc.type = 2002;
        this.mc.format = 1;
        this.mc.flags = 40;
        this.mc.gravity = 1;
        this.mc.x = 0;
        this.mc.y = 0;
        this.mc.width = -2;
        this.mc.height = -2;
        this.lv = LayoutInflater.from(getApplication()).inflate(R.layout.warning_view_dialog, null);
        this.lu = (Button) this.lv.findViewById(R.id.btn_ok);
        this.lx = (ImageView) this.lv.findViewById(R.id.icon_img);
        this.ly = (ImageView) this.lv.findViewById(R.id.icon_state);
        this.lz = (MyTextView) this.lv.findViewById(R.id.str_text);
        this.lv.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
    }

    private void qv() {
        if (this.lt) {
            this.lt = false;
            this.mb.removeView(this.lv);
        }
    }

    private void qw() {
        if (!this.lt) {
            this.lt = true;
            this.mb.addView(this.lv, this.mc);
            this.lv.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        Log.i("CanBusServer", "CanBus>>WarningServer>>onCreate>>");
        qu();
    }

    public void onDestroy() {
        this.lw.removeMessages(256);
        if (this.lv != null && this.lt) {
            this.mb.removeView(this.lv);
        }
        super.onDestroy();
        Log.i("CanBusServer", "CanBus>>WarningServer>>onDestroy>>");
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        this.ma = (Warning) intent.getSerializableExtra("warningdata");
        if (this.ma != null && qt(this.ma.ld())) {
            this.warningInfo = this.ma.ld();
            this.lw.removeMessages(256);
            Message obtainMessage = this.lw.obtainMessage();
            obtainMessage.what = 256;
            obtainMessage.arg1 = 0;
            this.lw.sendMessageDelayed(obtainMessage, 50);
        }
    }
}
