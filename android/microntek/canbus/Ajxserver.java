package android.microntek.canbus;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.microntek.canbus.view.MyButton;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings.System;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.p003a.C0070a;
import java.util.Locale;

public class Ajxserver extends Service {
    private BroadcastReceiver mt = new C0058p(this);
    private int mu = 0;
    private String mv;
    private boolean mw = false;
    private StringBuffer mx = new StringBuffer(32);
    private Handler my = new C0057o(this);
    private View mz;
    private WindowManager na;
    private TextView nb;
    OnClickListener nc = new C0059q(this);
    private boolean nd = false;
    private boolean ne = false;
    private ImageView nf;
    private byte[] ng = null;
    private TextView nh;
    private LayoutParams ni;

    private void rv() {
        rt((byte) -123, new byte[]{(byte) 2}, 1);
    }

    private void rw(byte b) {
        rt((byte) -123, new byte[]{(byte) ((b + 128) & 255)}, 1);
    }

    public static void rx(Context context) {
        try {
            Object systemService = context.getSystemService("statusbar");
            (VERSION.SDK_INT <= 16 ? systemService.getClass().getMethod("collapse", new Class[0]) : systemService.getClass().getMethod("collapsePanels", new Class[0])).invoke(systemService, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ry() {
        this.ni = new LayoutParams();
        this.na = (WindowManager) getApplication().getSystemService("window");
        this.ni.type = 2007;
        this.ni.format = 1;
        this.ni.flags = 56;
        this.ni.gravity = 81;
        this.ni.x = 0;
        this.ni.y = 0;
        this.ni.width = -1;
        this.ni.height = -1;
        this.mz = LayoutInflater.from(getApplication()).inflate(R.layout.ajx, null);
        if (this.mu != 12) {
            this.mz.findViewById(R.id.img_ajx).setBackgroundResource(R.drawable.icon_phone);
        }
        this.na.addView(this.mz, this.ni);
        this.mz.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
    }

    private void rz() {
        this.ni = new LayoutParams();
        this.na = (WindowManager) getApplication().getSystemService("window");
        this.ni.type = 2007;
        this.ni.format = 1;
        this.ni.flags = 40;
        this.ni.gravity = 81;
        this.ni.x = 0;
        this.ni.y = 0;
        this.ni.width = -1;
        this.ni.height = -1;
        this.mz = LayoutInflater.from(getApplication()).inflate(R.layout.dial, null);
        this.na.addView(this.mz, this.ni);
        sb(this.mz);
        this.mz.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
    }

    private void sa() {
        int i;
        int i2 = 0;
        byte[] bArr = new byte[10];
        for (i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) -1;
        }
        StringBuffer stringBuffer = new StringBuffer(32);
        if (this.mx.length() != 0) {
            for (i = 0; i < this.mx.length(); i++) {
                if (this.mx.charAt(i) == '*') {
                    stringBuffer.append('A');
                } else if (this.mx.charAt(i) == '#') {
                    stringBuffer.append('B');
                } else if (this.mx.charAt(i) != ' ') {
                    stringBuffer.append(this.mx.charAt(i));
                } else {
                    stringBuffer.append(this.mx.charAt(70));
                }
            }
            String str = "";
            for (i = 0; i < stringBuffer.length(); i++) {
                str = str + String.valueOf(stringBuffer.charAt(i));
            }
            String str2 = str.length() % 2 != 0 ? str + "FFF" : str + "FF";
            int i3 = 0;
            while (i2 < str2.length() - 2) {
                if (!(i2 == 0 || i2 % 2 == 0)) {
                    bArr[i3] = (byte) Integer.parseInt(str2.substring(i2 - 1, i2 + 1), 16);
                    i3++;
                }
                i2++;
            }
            rt((byte) -122, bArr, 10);
        }
    }

    private void sb(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.layout);
        if (this.mv != null && this.mv.equals("TZY")) {
            linearLayout.setBackgroundResource(R.drawable.bg_am);
        } else if (this.mv == null || !this.mv.equals("TZY2")) {
            linearLayout.setBackgroundColor(-16777216);
        } else {
            linearLayout.setBackgroundResource(R.drawable.bg_tzy2);
        }
        this.nb = (TextView) view.findViewById(R.id.dial_numbers);
        this.nf = (ImageView) view.findViewById(R.id.dial_status);
        this.nh = (TextView) view.findViewById(R.id.dial_time);
        MyButton myButton = (MyButton) view.findViewById(R.id.dial_dialout);
        MyButton myButton2 = (MyButton) view.findViewById(R.id.dial_handup);
        MyButton myButton3 = (MyButton) view.findViewById(R.id.dial_voiceswitch);
        MyButton myButton4 = (MyButton) view.findViewById(R.id.dial_num0);
        MyButton myButton5 = (MyButton) view.findViewById(R.id.dial_num1);
        MyButton myButton6 = (MyButton) view.findViewById(R.id.dial_num2);
        MyButton myButton7 = (MyButton) view.findViewById(R.id.dial_num3);
        MyButton myButton8 = (MyButton) view.findViewById(R.id.dial_num4);
        MyButton myButton9 = (MyButton) view.findViewById(R.id.dial_num5);
        MyButton myButton10 = (MyButton) view.findViewById(R.id.dial_num6);
        MyButton myButton11 = (MyButton) view.findViewById(R.id.dial_num7);
        MyButton myButton12 = (MyButton) view.findViewById(R.id.dial_num8);
        MyButton myButton13 = (MyButton) view.findViewById(R.id.dial_num9);
        MyButton myButton14 = (MyButton) view.findViewById(R.id.dial_numx);
        MyButton myButton15 = (MyButton) view.findViewById(R.id.dial_nums);
        ((MyButton) view.findViewById(R.id.dial_back)).setOnClickListener(this.nc);
        myButton.setOnClickListener(this.nc);
        myButton2.setOnClickListener(this.nc);
        myButton3.setOnClickListener(this.nc);
        myButton4.setOnClickListener(this.nc);
        myButton5.setOnClickListener(this.nc);
        myButton6.setOnClickListener(this.nc);
        myButton7.setOnClickListener(this.nc);
        myButton8.setOnClickListener(this.nc);
        myButton9.setOnClickListener(this.nc);
        myButton10.setOnClickListener(this.nc);
        myButton11.setOnClickListener(this.nc);
        myButton12.setOnClickListener(this.nc);
        myButton13.setOnClickListener(this.nc);
        myButton14.setOnClickListener(this.nc);
        myButton15.setOnClickListener(this.nc);
    }

    private void sc() {
        this.mu = BaseApplication.py().pz();
    }

    private void sd() {
        rt((byte) -123, new byte[]{(byte) 3}, 1);
    }

    private void se() {
        if (this.mx.length() > 0) {
            this.mx.deleteCharAt(this.mx.length() - 1);
            sh(this.mx.toString());
        }
    }

    private void sf(char c) {
        if (this.mx.length() < 20) {
            this.mx.append(c);
            sh(this.mx.toString());
        }
    }

    private void sg(byte[] bArr) {
        byte b = bArr[0];
        int length = bArr.length;
        if (length > 2) {
            int i;
            if (b == (byte) 8) {
                this.ng = new byte[(length - 2)];
                for (i = 0; i < length; i++) {
                    if (i >= 2) {
                        this.ng[i - 2] = bArr[i];
                    }
                }
                CharSequence charSequence = "";
                for (i = 0; i < this.ng.length; i++) {
                    if (((byte) (this.ng[i] & 255)) != (byte) -1) {
                        charSequence = ((byte) (this.ng[i] & 15)) == (byte) 15 ? charSequence + "" + Integer.toHexString((this.ng[i] & 255) >> 4) : charSequence + "" + Integer.toHexString((this.ng[i] & 240) >> 4) + "" + Integer.toHexString(this.ng[i] & 15);
                    }
                }
                this.nb.setText(charSequence);
            }
            if (b == (byte) 9) {
                this.ng = new byte[(length - 2)];
                for (i = 0; i < length; i++) {
                    if (i >= 2) {
                        this.ng[i - 2] = bArr[i];
                    }
                }
                this.nh.setVisibility(0);
                if (this.ng[0] == (byte) 4) {
                    this.nd = true;
                    this.mw = false;
                    this.ne = false;
                    this.nh.setText(getString(R.string.phone_on));
                } else if (this.ng[0] == (byte) 3) {
                    this.mw = true;
                    this.nd = false;
                    this.ne = false;
                    this.nh.setText(getString(R.string.phone_state));
                } else if (this.ng[0] == (byte) 2) {
                    this.nd = false;
                    this.mw = false;
                    this.ne = true;
                    this.nh.setText(getString(R.string.is_phone));
                } else {
                    this.nd = false;
                    this.mw = false;
                    this.ne = false;
                    this.nh.setText("");
                    this.nh.setVisibility(4);
                }
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        System.putInt(getContentResolver(), "canbus.ajx.state", 1);
        sc();
        this.mv = C0070a.tv("ro.product.customer", "HCT");
        if (this.mu == 5 || this.mu == 55 || this.mu == 69) {
            rz();
        } else {
            ry();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.microntek.ajx");
        intentFilter.addAction("com.microntek.sync");
        registerReceiver(this.mt, intentFilter);
        rx(this);
    }

    public void onDestroy() {
        if (!(this.mu == 5 || this.mu == 55)) {
            if (this.mu == 69) {
            }
            unregisterReceiver(this.mt);
            if (this.mz != null) {
                this.na.removeView(this.mz);
            }
            System.putInt(getContentResolver(), "canbus.ajx.state", 0);
            super.onDestroy();
        }
        if (this.ne || this.mw || this.nd) {
            sd();
        }
        unregisterReceiver(this.mt);
        if (this.mz != null) {
            this.na.removeView(this.mz);
        }
        System.putInt(getContentResolver(), "canbus.ajx.state", 0);
        super.onDestroy();
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
    }

    public void rt(byte b, byte[] bArr, int i) {
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
        ru(bArr2);
    }

    public void ru(byte[] bArr) {
        String str = "";
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            str = str + String.format(Locale.US, "%d", new Object[]{Integer.valueOf(bArr[i] & 255)});
            if (i < length - 1) {
                str = str + ",";
            }
        }
        BaseApplication.py().qh("canbus_rsp=" + str);
    }

    public void sh(String str) {
        if (this.nb != null && str != null) {
            this.nb.setText(str);
        }
    }
}
