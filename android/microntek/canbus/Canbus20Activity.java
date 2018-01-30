package android.microntek.canbus;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Locale;

public class Canbus20Activity extends Activity {
    public static int mk = 0;
    private BroadcastReceiver md = new C0046k(this);
    private String me = "com.microntek.canbus20activity";
    byte[] mf = null;
    private String mg = "Canbus20Activity";
    private ImageButton mh;
    private ImageButton mi;
    private ImageButton mj;
    private ImageButton ml;
    private int mm = 0;
    private int mn = 0;
    private byte[] mo = new byte[3];
    private SharedPreferences mp;
    private boolean mq = true;
    private TextView mr;
    private LinearLayout ms;

    private void rg(byte b) {
        rh((byte) -110, new byte[]{b}, 1);
    }

    private void rh(byte b, byte[] bArr, int i) {
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
        ri(bArr2);
        rj("write:", bArr2);
    }

    private void ri(byte[] bArr) {
        int length = bArr.length;
        String str = "";
        for (int i = 0; i < length; i++) {
            str = str + rk(bArr[i] & 255);
            if (i < length - 1) {
                str = str + ",";
            }
        }
        Log.i(this.mg, "canbus_rsp=" + str);
        BaseApplication.py().qh("canbus_rsp=" + str);
    }

    private void rj(String str, byte[] bArr) {
        String str2 = " ";
        for (int i = 0; i < bArr.length; i++) {
            str2 = str2 + String.format(Locale.US, "%02x ", new Object[]{Byte.valueOf(bArr[i])});
        }
        Log.i(this.mg, str2);
    }

    private String rk(int i) {
        int i2 = i / 10;
        int i3 = i % 10;
        return i2 == 0 ? "" + i3 : "" + i2 + i3;
    }

    private void rl(byte[] bArr) {
        int i = 0;
        byte b = bArr[0];
        if (b == (byte) 18) {
            this.ms.setVisibility(8);
            this.mr.setVisibility(8);
            for (int i2 = 0; i2 < 3; i2++) {
                this.mo[i2] = bArr[i2];
            }
            if (((byte) (this.mo[1] & 8)) == (byte) 8) {
                BaseApplication.py().qh("av_channel_exit=line");
                this.ml.setBackgroundResource(R.drawable.icon_mic);
                mk = 0;
                this.mq = false;
                rg(Byte.MIN_VALUE);
            } else if (((byte) (this.mo[1] & 4)) == (byte) 4) {
                BaseApplication.py().qh("av_channel_enter=line");
                this.ml.setBackgroundResource(R.drawable.icon_phone);
                mk = 0;
                this.mq = false;
                rg(Byte.MIN_VALUE);
            } else if (((byte) (this.mo[1] & 2)) == (byte) 2) {
                BaseApplication.py().qh("av_channel_enter=line");
                this.ml.setBackgroundResource(R.drawable.icon_usb);
                this.ms.setVisibility(0);
                this.mr.setVisibility(0);
                this.mi.setImageResource(R.drawable.ico_pause);
                mk = 1;
            } else if (((byte) (this.mo[1] & 1)) == (byte) 1) {
                this.ml.setBackgroundResource(R.drawable.icon_usb);
                this.ms.setVisibility(0);
                this.mr.setVisibility(0);
                this.mi.setImageResource(R.drawable.ico_play);
                mk = 0;
            } else {
                mk = 0;
                BaseApplication.py().qh("av_channel_exit=line");
                finish();
            }
        }
        if (b == (byte) 23) {
            while (i < 3) {
                this.mo[i] = bArr[i];
                i++;
            }
            this.mm = this.mo[1] & 255;
            this.mn = this.mo[2] & 255;
            String str = "";
            str = "";
            this.mr.setText((this.mm < 10 ? "0" + this.mm : "" + this.mm) + ":" + (this.mn < 10 ? "0" + this.mn : "" + this.mn));
        }
    }

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.canbus20activity);
        this.mf = getIntent().getByteArrayExtra("syncdata");
        this.mp = getSharedPreferences("status1", 0);
        this.mq = this.mp.getBoolean("status", true);
        this.ms = (LinearLayout) findViewById(R.id.usbply);
        this.mj = (ImageButton) findViewById(R.id.btn_prev);
        this.mi = (ImageButton) findViewById(R.id.btn_playpause);
        this.mh = (ImageButton) findViewById(R.id.btn_next);
        this.mr = (TextView) findViewById(R.id.timec);
        this.ml = (ImageButton) findViewById(R.id.img);
        if (this.mq) {
            this.mi.setImageResource(R.drawable.ico_pause);
        } else {
            this.mi.setImageResource(R.drawable.ico_play);
        }
        this.mh.setOnClickListener(new C0047l(this));
        this.mj.setOnClickListener(new C0055m(this));
        this.mi.setOnClickListener(new C0056n(this));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.me);
        registerReceiver(this.md, intentFilter);
        if (this.mf != null) {
            rl(this.mf);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.py().qh("av_channel_exit=line");
    }

    protected void onStop() {
        super.onStop();
        rg(Byte.MIN_VALUE);
        this.mi.setImageResource(R.drawable.ico_play);
        BaseApplication.py().qh("av_channel_exit=line");
        this.mq = false;
        this.mp.edit().putBoolean("status", this.mq).commit();
    }
}
