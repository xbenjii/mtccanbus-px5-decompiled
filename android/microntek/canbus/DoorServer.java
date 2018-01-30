package android.microntek.canbus;

import android.app.Service;
import android.content.Intent;
import android.microntek.canbus.p002b.C0035b;
import android.microntek.canbus.serializable.Door;
import android.os.Handler;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;

public class DoorServer extends Service {
    private int li = 1;
    private Handler lj = new C0042g(this);
    private Door lk;
    private View ll;
    private View lm;
    private boolean ln = false;
    private boolean lo = false;
    private ImageView lp;
    private WindowManager lq;
    private LayoutParams lr;

    private void qn() {
        this.lr = new LayoutParams();
        this.lq = (WindowManager) getApplication().getSystemService("window");
        this.lr.type = 2002;
        this.lr.format = 1;
        if (this.lk.mode == 1) {
            this.lr.flags = 40;
        } else {
            this.lr.flags = 56;
        }
        this.lr.gravity = 81;
        this.lr.x = 0;
        this.lr.y = 0;
        this.lr.width = -2;
        this.lr.height = -2;
        this.lm = LayoutInflater.from(getApplication()).inflate(R.layout.doorlayout, null);
        this.lq.addView(this.lm, this.lr);
        this.lp = (ImageView) this.lm.findViewById(R.id.safety_view);
        this.ll = this.lm.findViewById(R.id.door_view);
        this.lm.setOnTouchListener(new C0043h(this));
    }

    private boolean qo() {
        return this.lk.frontDriver || this.lk.frontPassenger || this.lk.rearLeft || this.lk.rearRight || this.lk.trunk || this.lk.front;
    }

    private void qp() {
        if (this.lk != null) {
            ImageView imageView = (ImageView) this.lm.findViewById(R.id.fleftdoor);
            ImageView imageView2 = (ImageView) this.lm.findViewById(R.id.bleftdoor);
            ImageView imageView3 = (ImageView) this.lm.findViewById(R.id.frightdoor);
            ImageView imageView4 = (ImageView) this.lm.findViewById(R.id.brightdoor);
            ImageView imageView5 = (ImageView) this.lm.findViewById(R.id.doorbg);
            ImageView imageView6 = (ImageView) this.lm.findViewById(R.id.frontdoor);
            if (this.ln) {
                if (this.lk.frontPassenger) {
                    imageView.setImageResource(R.drawable.lf_1);
                } else {
                    imageView.setImageResource(R.drawable.lf_0);
                }
                if (this.lk.frontDriver) {
                    imageView3.setImageResource(R.drawable.rf_1);
                } else {
                    imageView3.setImageResource(R.drawable.rf_0);
                }
            } else {
                if (this.lk.frontDriver) {
                    imageView.setImageResource(R.drawable.lf_1);
                } else {
                    imageView.setImageResource(R.drawable.lf_0);
                }
                if (this.lk.frontPassenger) {
                    imageView3.setImageResource(R.drawable.rf_1);
                } else {
                    imageView3.setImageResource(R.drawable.rf_0);
                }
            }
            if (this.lo) {
                if (this.lk.rearRight) {
                    imageView2.setImageResource(R.drawable.lr_1);
                } else {
                    imageView2.setImageResource(R.drawable.lr_0);
                }
                if (this.lk.rearLeft) {
                    imageView4.setImageResource(R.drawable.rr_1);
                } else {
                    imageView4.setImageResource(R.drawable.rr_0);
                }
            } else {
                if (this.lk.rearLeft) {
                    imageView2.setImageResource(R.drawable.lr_1);
                } else {
                    imageView2.setImageResource(R.drawable.lr_0);
                }
                if (this.lk.rearRight) {
                    imageView4.setImageResource(R.drawable.rr_1);
                } else {
                    imageView4.setImageResource(R.drawable.rr_0);
                }
            }
            if (this.lk.trunk) {
                imageView5.setImageResource(R.drawable.car_1);
            } else {
                imageView5.setImageResource(R.drawable.car_0);
            }
            if (this.lk.front) {
                imageView6.setVisibility(0);
            } else {
                imageView6.setVisibility(8);
            }
            if (this.lk.safety) {
                this.lp.setImageResource(R.drawable.safety_1);
            } else {
                this.lp.setImageResource(R.drawable.safety_0);
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        if (C0035b.lp() > 19) {
            if (BaseApplication.py().qg("cfg_cansub_6=").equals("1")) {
                this.ln = true;
            }
            if (BaseApplication.py().qg("cfg_cansub_8=").equals("1")) {
                this.lo = true;
            }
        } else if (BaseApplication.py().qg("cfg_rudder=").equals("1")) {
            this.ln = true;
        }
    }

    public void onDestroy() {
        this.lj.removeCallbacksAndMessages(null);
        if (this.lm != null) {
            this.lq.removeView(this.lm);
        }
        super.onDestroy();
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        this.li = intent.getIntExtra("canbustype", 1);
        this.lk = (Door) intent.getSerializableExtra("doordata");
        if (this.lk != null) {
            if (this.lm == null) {
                qn();
            }
            this.lj.removeMessages(0);
            if (this.lk.mode == 1) {
                this.lj.removeMessages(1);
                if (qo() && this.lk.safety) {
                    this.ll.setVisibility(0);
                    this.lp.setVisibility(8);
                    this.lj.sendEmptyMessageDelayed(1, 5000);
                } else if (!qo() && this.lk.safety) {
                    this.ll.setVisibility(8);
                    this.lp.setVisibility(0);
                } else if (!qo() || this.lk.safety) {
                    this.lj.sendEmptyMessageDelayed(0, 10);
                } else {
                    this.ll.setVisibility(0);
                    this.lp.setVisibility(8);
                }
            } else {
                this.lj.sendEmptyMessageDelayed(0, 3000);
            }
            qp();
        }
    }
}
