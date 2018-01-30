package android.microntek.canbus.air;

import android.microntek.canbus.BaseApplication;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class C0031a implements OnClickListener, OnTouchListener {
    private int ex = -1;
    private long ey = 0;
    private ImageButton ez;
    private ImageButton fa;
    protected AirControlActivity fb;
    private ImageButton fc;
    private ImageButton fd;
    private ImageButton fe;
    private ImageButton ff;
    private ImageButton fg;
    private ImageButton fh;
    private ImageButton fi;
    private ImageButton fj;
    private ImageButton fk;
    private ImageButton fl;
    private ImageButton fm;
    private ImageButton fn;
    private ImageButton fo;
    private View fp;
    private ImageButton fq;
    private ImageButton fr;
    private TextView fs;
    private TextView ft;
    private ImageButton fu;
    private ImageButton fv;
    private ImageButton fw;
    private ImageButton fx;
    private ImageButton fy;
    private ImageButton fz;
    private ImageButton ga;
    private ImageButton gb;
    private ImageButton gc;
    private ImageButton gd;
    private ImageButton ge;
    private ImageButton gf;
    private int gg = this.ex;
    protected byte gh = (byte) 0;
    protected byte gi = (byte) 0;
    protected byte gj = (byte) 0;
    protected byte gk = (byte) 0;
    protected byte gl = (byte) 0;

    public C0031a(AirControlActivity airControlActivity) {
        this.fb = airControlActivity;
    }

    private View mk(int i) {
        View findViewById = this.fb.findViewById(i);
        findViewById.setOnTouchListener(this);
        return findViewById;
    }

    private boolean mo(int i) {
        int[] airBtnTable = this.fb.getAirBtnTable();
        if (airBtnTable.length > 1) {
            for (int i2 : airBtnTable) {
                if ((i2 & 65535) == i) {
                    return true;
                }
            }
        }
        return false;
    }

    private void mq(int i, int i2) {
        switch (i) {
            case R.id.con_acmax:
                this.fb.ms(3841, i2);
                return;
            case R.id.con_ac:
                this.fb.ms(3842, i2);
                return;
            case R.id.con_windmode:
                this.fb.ms(3865, i2);
                return;
            case R.id.con_rearlock:
                this.fb.ms(3843, i2);
                return;
            case R.id.con_windfrontmax:
                this.fb.ms(3872, i2);
                return;
            case R.id.con_windrear:
                this.fb.ms(3844, i2);
                return;
            case R.id.con_loop:
                this.fb.ms(3845, i2);
                return;
            case R.id.con_auto:
                this.fb.ms(3846, i2);
                return;
            case R.id.con_loopa:
                this.fb.ms(3873, i2);
                return;
            case R.id.con_normal:
                this.fb.ms(3874, i2);
                return;
            case R.id.con_soft:
                this.fb.ms(3875, i2);
                return;
            case R.id.con_fast:
                this.fb.ms(3876, i2);
                return;
            case R.id.con_left_temp_up:
                this.fb.ms(3847, i2);
                return;
            case R.id.con_left_temp_down:
                this.fb.ms(3848, i2);
                return;
            case R.id.con_winddown:
                this.fb.ms(3858, i2);
                return;
            case R.id.con_windmid:
                this.fb.ms(3860, i2);
                return;
            case R.id.con_windup:
                this.fb.ms(3859, i2);
                return;
            case R.id.con_windmiddown:
                this.fb.ms(3863, i2);
                return;
            case R.id.con_windupdown:
                this.fb.ms(3861, i2);
                return;
            case R.id.con_windupmid:
                this.fb.ms(3862, i2);
                return;
            case R.id.con_seathotleft:
                this.fb.ms(3851, i2);
                return;
            case R.id.con_seathotright:
                this.fb.ms(3852, i2);
                return;
            case R.id.con_right_temp_up:
                this.fb.ms(3849, i2);
                return;
            case R.id.con_right_temp_down:
                this.fb.ms(3850, i2);
                return;
            case R.id.con_onoff:
                this.fb.ms(3853, i2);
                return;
            case R.id.con_windleveldown:
                this.fb.ms(3855, i2);
                return;
            case R.id.con_windlevelup:
                this.fb.ms(3856, i2);
                return;
            case R.id.con_sync:
                this.fb.ms(3857, i2);
                return;
            default:
                return;
        }
    }

    public String ml(int i) {
        if (i == 0) {
            return "LO";
        }
        if (i == 65535) {
            return "HI";
        }
        if (i == -1) {
            return "";
        }
        String string = this.fb.getTempUnit() ? this.fb.getString(R.string.f_dan) : this.fb.getString(R.string.c_dan);
        return i % 2 != 0 ? (((float) i) / 10.0f) + string : (i / 10) + string;
    }

    public int mm() {
        return R.layout.air_control;
    }

    public byte[] mn() {
        return new byte[]{this.gh, this.gi, this.gj, this.gk, this.gl};
    }

    public void mp() {
        this.fa = (ImageButton) mk(R.id.con_acmax);
        this.ez = (ImageButton) mk(R.id.con_ac);
        this.fk = (ImageButton) mk(R.id.con_rearlock);
        this.gc = (ImageButton) mk(R.id.con_windrear);
        this.fg = (ImageButton) mk(R.id.con_loop);
        this.fc = (ImageButton) mk(R.id.con_auto);
        this.ff = (ImageButton) mk(R.id.con_left_temp_up);
        this.fe = (ImageButton) mk(R.id.con_left_temp_down);
        this.fm = (ImageButton) mk(R.id.con_right_temp_up);
        this.fl = (ImageButton) mk(R.id.con_right_temp_down);
        this.fn = (ImageButton) mk(R.id.con_seathotleft);
        this.fo = (ImageButton) mk(R.id.con_seathotright);
        this.fj = (ImageButton) mk(R.id.con_onoff);
        this.fx = (ImageButton) mk(R.id.con_windleveldown);
        this.fy = (ImageButton) mk(R.id.con_windlevelup);
        this.fr = (ImageButton) mk(R.id.con_sync);
        this.fv = (ImageButton) mk(R.id.con_windfrontmax);
        this.fd = (ImageButton) mk(R.id.con_fast);
        this.fh = (ImageButton) mk(R.id.con_loopa);
        this.fi = (ImageButton) mk(R.id.con_normal);
        this.fq = (ImageButton) mk(R.id.con_soft);
        this.fu = (ImageButton) mk(R.id.con_winddown);
        this.fz = (ImageButton) mk(R.id.con_windmid);
        this.gd = (ImageButton) mk(R.id.con_windup);
        this.ge = (ImageButton) mk(R.id.con_windupdown);
        this.ga = (ImageButton) mk(R.id.con_windmiddown);
        this.gf = (ImageButton) mk(R.id.con_windupmid);
        this.gb = (ImageButton) mk(R.id.con_windmode);
        this.fw = (ImageButton) this.fb.findViewById(R.id.con_windlevel);
        this.fp = this.fb.findViewById(R.id.con_seathotview);
        this.ft = (TextView) this.fb.findViewById(R.id.con_txt_right_temp);
        this.fs = (TextView) this.fb.findViewById(R.id.con_txt_left_temp);
    }

    public void mr() {
        boolean z = true;
        int[] airBtnTable = this.fb.getAirBtnTable();
        if (airBtnTable.length > 1) {
            for (int i : airBtnTable) {
                switch (i & 65535) {
                    case 3841:
                        this.fa.setVisibility(0);
                        break;
                    case 3842:
                        this.ez.setVisibility(0);
                        break;
                    case 3843:
                        this.fk.setVisibility(0);
                        break;
                    case 3844:
                        this.gc.setVisibility(0);
                        break;
                    case 3845:
                        this.fg.setVisibility(0);
                        break;
                    case 3846:
                        this.fc.setVisibility(0);
                        break;
                    case 3847:
                    case 3848:
                        this.ff.setVisibility(0);
                        this.fs.setVisibility(0);
                        this.fe.setVisibility(0);
                        break;
                    case 3849:
                    case 3850:
                        this.fm.setVisibility(0);
                        this.ft.setVisibility(0);
                        this.fl.setVisibility(0);
                        break;
                    case 3851:
                    case 3852:
                        this.fn.setVisibility(0);
                        this.fo.setVisibility(0);
                        this.fp.setVisibility(0);
                        break;
                    case 3853:
                        this.fj.setVisibility(0);
                        break;
                    case 3855:
                    case 3856:
                        this.fw.setVisibility(0);
                        this.fx.setVisibility(0);
                        this.fy.setVisibility(0);
                        break;
                    case 3857:
                        this.fr.setVisibility(0);
                        break;
                    case 3858:
                        this.fu.setVisibility(0);
                        break;
                    case 3859:
                        this.gd.setVisibility(0);
                        break;
                    case 3860:
                        this.fz.setVisibility(0);
                        break;
                    case 3861:
                        this.ge.setVisibility(0);
                        break;
                    case 3862:
                        this.gf.setVisibility(0);
                        break;
                    case 3863:
                        this.ga.setVisibility(0);
                        break;
                    case 3865:
                        this.gb.setVisibility(0);
                        break;
                    case 3872:
                        this.fv.setVisibility(0);
                        break;
                    case 3873:
                        this.fh.setVisibility(0);
                        break;
                    case 3874:
                        this.fi.setVisibility(0);
                        break;
                    case 3875:
                        this.fq.setVisibility(0);
                        break;
                    case 3876:
                        this.fd.setVisibility(0);
                        break;
                    default:
                        break;
                }
            }
        }
        this.fj.setSelected(this.fb.getOnOff());
        this.fa.setSelected(this.fb.getAcMax() == 1);
        this.ez.setSelected(this.fb.getModeAc());
        this.fk.setSelected(this.fb.getRearLock() == 1);
        this.gc.setSelected(this.fb.getWindRear());
        switch (this.fb.getWindLoop()) {
            case R$styleable.MyButton_imgWidth /*0*/:
                this.fg.setImageResource(R.drawable.img_air_loop_out);
                break;
            case R$styleable.MyButton_imgHeight /*1*/:
                this.fg.setImageResource(R.drawable.img_air_loop_in);
                break;
            case R$styleable.MyButton_imgSrc /*2*/:
                this.fg.setImageResource(R.drawable.img_air_loop_aqs);
                break;
        }
        int windLevel = this.fb.getWindLevel();
        if (windLevel >= 0 && windLevel <= 8) {
            this.fw.setImageResource(windLevel + R.drawable.img_air_hot_level0);
        }
        this.fc.setSelected(this.fb.getModeAuto() >= 1);
        this.fr.setSelected(this.fb.getModeDual() == 1);
        this.fv.setSelected(this.fb.getWindFrontMax());
        this.ft.setText(ml(this.fb.getTempRight()));
        this.fs.setText(ml(this.fb.getTempLeft()));
        if (!(mo(3864) && this.fb.getWindUp() && this.fb.getWindMid() && this.fb.getWindDown())) {
            if (mo(3862) && this.fb.getWindUp() && this.fb.getWindMid()) {
                this.fz.setSelected(false);
                this.gd.setSelected(false);
                this.fu.setSelected(false);
                this.gf.setSelected(true);
                this.ge.setSelected(false);
                this.ga.setSelected(false);
            } else if (mo(3861) && this.fb.getWindUp() && this.fb.getWindDown()) {
                this.fz.setSelected(false);
                this.gd.setSelected(false);
                this.fu.setSelected(false);
                this.gf.setSelected(false);
                this.ge.setSelected(true);
                this.ga.setSelected(false);
            } else if (mo(3863) && this.fb.getWindMid() && this.fb.getWindDown()) {
                this.fz.setSelected(false);
                this.gd.setSelected(false);
                this.fu.setSelected(false);
                this.gf.setSelected(false);
                this.ge.setSelected(false);
                this.ga.setSelected(true);
            } else {
                this.fz.setSelected(this.fb.getWindMid());
                this.gd.setSelected(this.fb.getWindUp());
                this.fu.setSelected(this.fb.getWindDown());
                this.gf.setSelected(false);
                this.ge.setSelected(false);
                this.ga.setSelected(false);
            }
        }
        windLevel = this.fb.getSeatHotLeft();
        if (windLevel >= 0 && windLevel <= 3) {
            this.fn.setImageResource(windLevel + R.drawable.img_air_seathotleft0);
        }
        windLevel = this.fb.getSeatHotRight();
        if (windLevel >= 0 && windLevel <= 3) {
            this.fo.setImageResource(windLevel + R.drawable.img_air_seathotright0);
        }
        this.fd.setSelected(this.fb.getFast() == 1);
        this.fq.setSelected(this.fb.getSoft() == 1);
        this.fh.setSelected(this.fb.getLoopa() == 1);
        ImageButton imageButton = this.fi;
        if (this.fb.getNormal() != 1) {
            z = false;
        }
        imageButton.setSelected(z);
    }

    public void onClick(View view) {
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int id = view.getId();
        if (this.gg == this.ex || this.gg == id) {
            if (action == 1) {
                BaseApplication.py().qh("ctl_beep=1");
                this.gg = this.ex;
                this.ey = 0;
                mq(id, 0);
            } else if (action == 3) {
                this.gg = this.ex;
                this.ey = 0;
            }
            if (action == 0) {
                this.gg = id;
                this.ey = 0;
                mq(id, 1);
            } else if (action == 2) {
                this.gg = id;
                this.gg = id;
                long eventTime = motionEvent.getEventTime() - motionEvent.getDownTime();
                if (eventTime - this.ey >= ((long) (this.ey == 0 ? 400 : 200))) {
                    this.ey = eventTime;
                    mq(id, 2);
                }
            }
        }
        return false;
    }
}
