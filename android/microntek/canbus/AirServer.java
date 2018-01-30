package android.microntek.canbus;

import android.app.Service;
import android.content.Intent;
import android.microntek.canbus.p002b.C0035b;
import android.microntek.canbus.serializable.AirCondition;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings.System;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AirServer extends Service {
    public static int oo = 1;
    private Handler op = new C0063u(this);
    private AirCondition oq;
    private View or;
    private boolean os = false;
    private boolean ot = false;
    private WindowManager ou;
    private LayoutParams ov;

    private void tp() {
        this.ov = new LayoutParams();
        this.ou = (WindowManager) getApplication().getSystemService("window");
        this.ov.type = 2002;
        this.ov.format = 1;
        this.ov.flags = 56;
        this.ov.gravity = 83;
        this.ov.x = 0;
        this.ov.y = 0;
        this.ov.width = -1;
        this.ov.height = -2;
        this.or = LayoutInflater.from(getApplication()).inflate(R.layout.airlayout, null);
        this.ou.addView(this.or, this.ov);
    }

    private boolean tq() {
        return System.getInt(getContentResolver(), "com.microntek.hiworld.ari", 0) == 0;
    }

    private void tr(int i) {
        int i2;
        ImageView imageView;
        View findViewById;
        if (i == 1) {
            i2 = this.ot ? this.oq.tempRight : this.oq.tempLeft;
            imageView = (ImageView) this.or.findViewById(R.id.temper_type1);
            if (this.oq.tempUnit) {
                imageView.setImageResource(R.drawable.f);
            } else {
                imageView.setImageResource(R.drawable.c);
            }
            findViewById = this.or.findViewById(R.id.leftemper0);
            imageView = (ImageView) this.or.findViewById(R.id.left_hilow);
            if (i2 == 65535) {
                findViewById.setVisibility(8);
                imageView.setImageResource(R.drawable.hi);
                imageView.setVisibility(0);
                return;
            } else if (i2 == 0) {
                findViewById.setVisibility(8);
                imageView.setImageResource(R.drawable.lo);
                imageView.setVisibility(0);
                return;
            } else if (i2 == -1 || i2 >= 1000) {
                findViewById.setVisibility(8);
                imageView.setVisibility(8);
                return;
            } else {
                imageView.setVisibility(8);
                ImageView imageView2 = (ImageView) this.or.findViewById(R.id.left_l);
                ImageView imageView3 = (ImageView) this.or.findViewById(R.id.leftc);
                ((ImageView) this.or.findViewById(R.id.left_h)).setImageResource((i2 / 100) + R.drawable.d_0);
                imageView2.setImageResource(((i2 % 100) / 10) + R.drawable.d_0);
                imageView3.setImageResource((i2 % 10) + R.drawable.dd_0);
                findViewById.setVisibility(0);
                return;
            }
        }
        i2 = this.ot ? this.oq.tempLeft : this.oq.tempRight;
        imageView = (ImageView) this.or.findViewById(R.id.temper_type2);
        findViewById = this.or.findViewById(R.id.rightemper0);
        imageView2 = (ImageView) this.or.findViewById(R.id.right_hilow);
        if (this.oq.tempUnit) {
            imageView.setImageResource(R.drawable.f);
        } else {
            imageView.setImageResource(R.drawable.c);
        }
        if (i2 == 65535) {
            findViewById.setVisibility(8);
            imageView2.setImageResource(R.drawable.hi);
            imageView2.setVisibility(0);
        } else if (i2 == 0) {
            findViewById.setVisibility(8);
            imageView2.setImageResource(R.drawable.lo);
            imageView2.setVisibility(0);
        } else if (i2 == -1 || i2 >= 1000) {
            findViewById.setVisibility(8);
            imageView2.setVisibility(8);
        } else {
            imageView2.setVisibility(8);
            imageView2 = (ImageView) this.or.findViewById(R.id.right_l);
            imageView3 = (ImageView) this.or.findViewById(R.id.rightc);
            ((ImageView) this.or.findViewById(R.id.right_h)).setImageResource((i2 / 100) + R.drawable.d_0);
            imageView2.setImageResource(((i2 % 100) / 10) + R.drawable.d_0);
            imageView3.setImageResource((i2 % 10) + R.drawable.dd_0);
            findViewById.setVisibility(0);
        }
    }

    private void ts(int i) {
        int i2;
        ImageView imageView;
        View findViewById;
        if (i == 1) {
            i2 = this.ot ? this.oq.b_tempRight : this.oq.b_tempLeft;
            imageView = (ImageView) this.or.findViewById(R.id.b_temper_type1);
            if (this.oq.b_tempUnit) {
                imageView.setImageResource(R.drawable.f);
            } else {
                imageView.setImageResource(R.drawable.c);
            }
            findViewById = this.or.findViewById(R.id.b_leftemper0);
            imageView = (ImageView) this.or.findViewById(R.id.b_left_hilow);
            if (i2 == 65535) {
                findViewById.setVisibility(8);
                imageView.setImageResource(R.drawable.hi);
                imageView.setVisibility(0);
                return;
            } else if (i2 == 0) {
                findViewById.setVisibility(8);
                imageView.setImageResource(R.drawable.lo);
                imageView.setVisibility(0);
                return;
            } else if (i2 == -1 || i2 >= 1000) {
                findViewById.setVisibility(8);
                imageView.setVisibility(8);
                return;
            } else {
                imageView.setVisibility(8);
                ImageView imageView2 = (ImageView) this.or.findViewById(R.id.b_left_l);
                ImageView imageView3 = (ImageView) this.or.findViewById(R.id.b_leftc);
                ((ImageView) this.or.findViewById(R.id.b_left_h)).setImageResource((i2 / 100) + R.drawable.d_0);
                imageView2.setImageResource(((i2 % 100) / 10) + R.drawable.d_0);
                imageView3.setImageResource((i2 % 10) + R.drawable.dd_0);
                findViewById.setVisibility(0);
                return;
            }
        }
        i2 = this.ot ? this.oq.b_tempLeft : this.oq.b_tempRight;
        imageView = (ImageView) this.or.findViewById(R.id.b_temper_type2);
        findViewById = this.or.findViewById(R.id.b_rightemper0);
        imageView2 = (ImageView) this.or.findViewById(R.id.b_right_hilow);
        if (this.oq.b_tempUnit) {
            imageView.setImageResource(R.drawable.f);
        } else {
            imageView.setImageResource(R.drawable.c);
        }
        if (i2 == 65535) {
            findViewById.setVisibility(8);
            imageView2.setImageResource(R.drawable.hi);
            imageView2.setVisibility(0);
        } else if (i2 == 0) {
            findViewById.setVisibility(8);
            imageView2.setImageResource(R.drawable.lo);
            imageView2.setVisibility(0);
        } else if (i2 == -1 || i2 >= 1000) {
            findViewById.setVisibility(8);
            imageView2.setVisibility(8);
        } else {
            imageView2.setVisibility(8);
            imageView2 = (ImageView) this.or.findViewById(R.id.b_right_l);
            imageView3 = (ImageView) this.or.findViewById(R.id.b_rightc);
            ((ImageView) this.or.findViewById(R.id.b_right_h)).setImageResource((i2 / 100) + R.drawable.d_0);
            imageView2.setImageResource(((i2 % 100) / 10) + R.drawable.d_0);
            imageView3.setImageResource((i2 % 10) + R.drawable.dd_0);
            findViewById.setVisibility(0);
        }
    }

    private void tt() {
        if (this.or != null) {
            FrameLayout frameLayout = (FrameLayout) this.or.findViewById(R.id.hotleverview);
            ImageView imageView = (ImageView) this.or.findViewById(R.id.left_hot_level);
            ImageView imageView2 = (ImageView) this.or.findViewById(R.id.right_hot_level);
            ImageView imageView3 = (ImageView) this.or.findViewById(R.id.wind_cyc);
            ImageView imageView4 = (ImageView) this.or.findViewById(R.id.windfun);
            ImageView imageView5 = (ImageView) this.or.findViewById(R.id.windauto);
            if (this.oq.seatShow) {
                int i = this.ot ? this.oq.seatHotRight : this.oq.seatHotLeft;
                if (i < 0 || i > 3) {
                    imageView.setVisibility(4);
                } else {
                    imageView.setImageResource(i + R.drawable.left_hot_level0);
                    imageView.setVisibility(0);
                }
                int i2 = this.ot ? this.oq.seatHotLeft : this.oq.seatHotRight;
                if (i2 < 0 || i2 > 3) {
                    imageView2.setVisibility(4);
                } else {
                    imageView2.setImageResource(i2 + R.drawable.right_hot_level0);
                    imageView2.setVisibility(0);
                }
                frameLayout.setVisibility(0);
            } else {
                frameLayout.setVisibility(8);
            }
            if (this.oq.windLoop == -1) {
                imageView3.setVisibility(4);
            } else {
                if (this.oq.windLoop == 1) {
                    imageView3.setImageResource(R.drawable.wind_in1);
                } else if (this.oq.windLoop == 2) {
                    imageView3.setImageResource(R.drawable.wind12_asq);
                } else {
                    imageView3.setImageResource(R.drawable.wind_out1);
                }
                imageView3.setVisibility(0);
            }
            if (this.oq.modeAuto == 2) {
                imageView4.setImageResource(R.drawable.big_fun);
                imageView5.setVisibility(0);
            } else if (this.oq.modeAuto == 1) {
                imageView4.setImageResource(R.drawable.small_fun);
                imageView5.setVisibility(0);
            } else {
                imageView4.setImageResource(R.drawable.nor_fun);
                imageView5.setVisibility(4);
            }
            imageView4.setVisibility(0);
            tr(1);
            tr(2);
            ImageView imageView6 = (ImageView) this.or.findViewById(R.id.windmax);
            imageView = (ImageView) this.or.findViewById(R.id.windup);
            imageView2 = (ImageView) this.or.findViewById(R.id.windmid);
            imageView3 = (ImageView) this.or.findViewById(R.id.winddown);
            FrameLayout frameLayout2 = (FrameLayout) this.or.findViewById(R.id.ari_state);
            if (this.oq.ariStateShow) {
                frameLayout2.setVisibility(0);
            } else {
                frameLayout2.setVisibility(8);
            }
            if (this.oq.windFrontMax) {
                imageView.setVisibility(8);
                imageView2.setVisibility(8);
                imageView3.setVisibility(8);
                imageView6.setVisibility(0);
            } else {
                imageView6.setVisibility(8);
                imageView.setVisibility(0);
                imageView2.setVisibility(0);
                imageView3.setVisibility(0);
                if (this.oq.windUp) {
                    imageView.setImageResource(R.drawable.wind_up1);
                } else {
                    imageView.setImageResource(R.drawable.wind_up0);
                }
                if (this.oq.windMid) {
                    imageView2.setImageResource(R.drawable.wind_balance1);
                } else {
                    imageView2.setImageResource(R.drawable.wind_balance0);
                }
                if (this.oq.windDown) {
                    imageView3.setImageResource(R.drawable.wind_down1);
                } else {
                    imageView3.setImageResource(R.drawable.wind_down0);
                }
            }
            imageView6 = (ImageView) this.or.findViewById(R.id.backwindow);
            if (this.oq.windRearShow) {
                if (this.oq.windRear) {
                    imageView6.setImageResource(R.drawable.add_hot1);
                } else {
                    imageView6.setImageResource(R.drawable.add_hot0);
                }
                imageView6.setVisibility(0);
            } else {
                imageView6.setVisibility(8);
            }
            imageView6 = (ImageView) this.or.findViewById(R.id.ac);
            if (this.oq.modeAc) {
                imageView6.setImageResource(R.drawable.ac1);
            } else {
                imageView6.setImageResource(R.drawable.ac0);
            }
            imageView6 = (ImageView) this.or.findViewById(R.id.dual);
            if (this.oq.modeDual == -1) {
                imageView6.setVisibility(4);
            } else {
                if (this.oq.DIS_IMG == 1) {
                    if (this.oq.modeDual == 1) {
                        imageView6.setImageResource(R.drawable.img_auto1);
                    } else {
                        imageView6.setImageResource(R.drawable.img_auto0);
                    }
                } else if (this.oq.modeDual == 1) {
                    imageView6.setImageResource(R.drawable.dual1);
                } else {
                    imageView6.setImageResource(R.drawable.dual0);
                }
                imageView6.setVisibility(0);
            }
            frameLayout = (FrameLayout) this.or.findViewById(R.id.wind_Frame);
            if (this.oq.wind_FrameShow) {
                frameLayout.setVisibility(0);
            } else {
                frameLayout.setVisibility(8);
            }
            imageView6 = (ImageView) this.or.findViewById(R.id.rearlock);
            if (this.oq.rearLock == -1) {
                imageView6.setVisibility(4);
            } else {
                if (this.oq.DIS_IMG1 == 1) {
                    if (this.oq.rearLock == 1) {
                        imageView6.setImageResource(R.drawable.img_eco0);
                    } else {
                        imageView6.setImageResource(R.drawable.img_eco1);
                    }
                } else if (this.oq.rearLock == 1) {
                    imageView6.setImageResource(R.drawable.rearlock1);
                } else {
                    imageView6.setImageResource(R.drawable.rearlock0);
                }
                imageView6.setVisibility(0);
            }
            imageView6 = (ImageView) this.or.findViewById(R.id.acmax);
            if (this.oq.acMax == -1) {
                imageView6.setVisibility(4);
            } else {
                if (this.oq.acMax == 1) {
                    imageView6.setImageResource(R.drawable.acmax1);
                } else {
                    imageView6.setImageResource(R.drawable.acmax0);
                }
                imageView6.setVisibility(0);
            }
            imageView6 = (ImageView) this.or.findViewById(R.id.windlevel);
            if (this.oq.windLevel < 0 || this.oq.windLevel > this.oq.windLevelMax) {
                imageView6.setVisibility(4);
            } else {
                if (this.oq.windLevelMax == 7) {
                    imageView6.setImageResource(this.oq.windLevel + R.drawable.hot_level0);
                } else if (this.oq.windLevelMax == 4) {
                    imageView6.setImageResource(this.oq.windLevel + R.drawable.hot_5level0);
                } else if (this.oq.windLevelMax == 6) {
                    imageView6.setImageResource(this.oq.windLevel + R.drawable.hot_6level0);
                } else if (this.oq.windLevelMax == 15) {
                    if (this.oq.windLevel <= 9) {
                        imageView6.setImageResource(this.oq.windLevel + R.drawable.hot_15levelw0);
                    } else {
                        imageView6.setImageResource((this.oq.windLevel + R.drawable.hot_15level1p0) - 10);
                    }
                } else if (this.oq.windLevelMax == 8) {
                    imageView6.setImageResource(this.oq.windLevel + R.drawable.hot_8level0);
                }
                imageView6.setVisibility(0);
            }
            this.or.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        }
    }

    private void tu() {
        if (this.or != null) {
            LinearLayout linearLayout = (LinearLayout) this.or.findViewById(R.id.b_Linear_view);
            if (this.oq.b_onOff) {
                linearLayout.setVisibility(0);
                FrameLayout frameLayout = (FrameLayout) this.or.findViewById(R.id.b_lefttemper0_view);
                FrameLayout frameLayout2 = (FrameLayout) this.or.findViewById(R.id.b_rightemper0_view);
                if (this.oq.b_leftShow) {
                    frameLayout.setVisibility(0);
                } else {
                    frameLayout.setVisibility(8);
                }
                if (this.oq.b_rightShow) {
                    frameLayout2.setVisibility(0);
                } else {
                    frameLayout2.setVisibility(8);
                }
                frameLayout = (FrameLayout) this.or.findViewById(R.id.b_hotleverview);
                ImageView imageView = (ImageView) this.or.findViewById(R.id.b_left_hot_level);
                ImageView imageView2 = (ImageView) this.or.findViewById(R.id.b_right_hot_level);
                ImageView imageView3 = (ImageView) this.or.findViewById(R.id.b_wind_cyc);
                ImageView imageView4 = (ImageView) this.or.findViewById(R.id.b_windfun);
                ImageView imageView5 = (ImageView) this.or.findViewById(R.id.b_windauto);
                if (this.oq.b_seatShow) {
                    int i = this.ot ? this.oq.b_seatHotRight : this.oq.b_seatHotLeft;
                    if (i < 0 || i > 3) {
                        imageView.setVisibility(4);
                    } else {
                        imageView.setImageResource(i + R.drawable.left_hot_level0);
                        imageView.setVisibility(0);
                    }
                    int i2 = this.ot ? this.oq.b_seatHotLeft : this.oq.b_seatHotRight;
                    if (i2 < 0 || i2 > 3) {
                        imageView2.setVisibility(4);
                    } else {
                        imageView2.setImageResource(i2 + R.drawable.right_hot_level0);
                        imageView2.setVisibility(0);
                    }
                    frameLayout.setVisibility(0);
                } else {
                    frameLayout.setVisibility(8);
                }
                if (this.oq.b_windLoop == -1) {
                    imageView3.setVisibility(4);
                } else {
                    if (this.oq.b_windLoop == 1) {
                        imageView3.setImageResource(R.drawable.wind_in1);
                    } else if (this.oq.b_windLoop == 2) {
                        imageView3.setImageResource(R.drawable.wind12_asq);
                    } else {
                        imageView3.setImageResource(R.drawable.wind_out1);
                    }
                    imageView3.setVisibility(0);
                }
                if (this.oq.b_modeAuto == 2) {
                    imageView4.setImageResource(R.drawable.big_fun);
                    imageView5.setVisibility(0);
                } else if (this.oq.b_modeAuto == 1) {
                    imageView4.setImageResource(R.drawable.small_fun);
                    imageView5.setVisibility(0);
                } else {
                    imageView4.setImageResource(R.drawable.nor_fun);
                    imageView5.setVisibility(4);
                }
                imageView4.setVisibility(0);
                ts(1);
                ts(2);
                ImageView imageView6 = (ImageView) this.or.findViewById(R.id.b_windmax);
                imageView = (ImageView) this.or.findViewById(R.id.b_windup);
                imageView2 = (ImageView) this.or.findViewById(R.id.b_windmid);
                imageView3 = (ImageView) this.or.findViewById(R.id.b_winddown);
                FrameLayout frameLayout3 = (FrameLayout) this.or.findViewById(R.id.b_ari_state);
                if (this.oq.b_ariStateShow) {
                    frameLayout3.setVisibility(0);
                } else {
                    frameLayout3.setVisibility(8);
                }
                if (this.oq.b_windFrontMax) {
                    imageView.setVisibility(8);
                    imageView2.setVisibility(8);
                    imageView3.setVisibility(8);
                    imageView6.setVisibility(0);
                } else {
                    imageView6.setVisibility(8);
                    imageView.setVisibility(0);
                    imageView2.setVisibility(0);
                    imageView3.setVisibility(0);
                    if (this.oq.b_windUp) {
                        imageView.setImageResource(R.drawable.wind_up1);
                    } else {
                        imageView.setImageResource(R.drawable.wind_up0);
                    }
                    if (this.oq.b_windMid) {
                        imageView2.setImageResource(R.drawable.wind_balance1);
                    } else {
                        imageView2.setImageResource(R.drawable.wind_balance0);
                    }
                    if (this.oq.b_windDown) {
                        imageView3.setImageResource(R.drawable.wind_down1);
                    } else {
                        imageView3.setImageResource(R.drawable.wind_down0);
                    }
                }
                imageView6 = (ImageView) this.or.findViewById(R.id.b_backwindow);
                if (this.oq.b_windRearShow) {
                    if (this.oq.b_windRear) {
                        imageView6.setImageResource(R.drawable.add_hot1);
                    } else {
                        imageView6.setImageResource(R.drawable.add_hot0);
                    }
                    imageView6.setVisibility(0);
                } else {
                    imageView6.setVisibility(8);
                }
                imageView6 = (ImageView) this.or.findViewById(R.id.b_ac);
                if (this.oq.b_modeAc) {
                    imageView6.setImageResource(R.drawable.ac1);
                } else {
                    imageView6.setImageResource(R.drawable.ac0);
                }
                imageView6 = (ImageView) this.or.findViewById(R.id.b_dual);
                if (this.oq.b_modeDual == -1) {
                    imageView6.setVisibility(4);
                } else {
                    if (this.oq.B_DIS_IMG == 1) {
                        if (this.oq.b_modeDual == 1) {
                            imageView6.setImageResource(R.drawable.img_auto1);
                        } else {
                            imageView6.setImageResource(R.drawable.img_auto0);
                        }
                    } else if (this.oq.b_modeDual == 1) {
                        imageView6.setImageResource(R.drawable.dual1);
                    } else {
                        imageView6.setImageResource(R.drawable.dual0);
                    }
                    imageView6.setVisibility(0);
                }
                frameLayout = (FrameLayout) this.or.findViewById(R.id.b_wind_Frame);
                if (this.oq.b_wind_FrameShow) {
                    frameLayout.setVisibility(0);
                } else {
                    frameLayout.setVisibility(8);
                }
                imageView6 = (ImageView) this.or.findViewById(R.id.b_rearlock);
                if (this.oq.b_rearLock == -1) {
                    imageView6.setVisibility(4);
                } else {
                    if (this.oq.b_rearLock == 1) {
                        imageView6.setImageResource(R.drawable.rearlock1);
                    } else {
                        imageView6.setImageResource(R.drawable.rearlock0);
                    }
                    imageView6.setVisibility(0);
                }
                imageView6 = (ImageView) this.or.findViewById(R.id.b_acmax);
                if (this.oq.b_acMax == -1) {
                    imageView6.setVisibility(4);
                } else {
                    if (this.oq.acMax == 1) {
                        imageView6.setImageResource(R.drawable.acmax1);
                    } else {
                        imageView6.setImageResource(R.drawable.acmax0);
                    }
                    imageView6.setVisibility(0);
                }
                imageView6 = (ImageView) this.or.findViewById(R.id.b_windlevel);
                if (this.oq.b_windLevel < 0 || this.oq.b_windLevel > this.oq.b_windLevelMax) {
                    imageView6.setVisibility(4);
                } else {
                    if (this.oq.b_windLevelMax == 7) {
                        imageView6.setImageResource(this.oq.b_windLevel + R.drawable.hot_level0);
                    } else if (this.oq.b_windLevelMax == 4) {
                        imageView6.setImageResource(this.oq.b_windLevel + R.drawable.hot_5level0);
                    } else if (this.oq.b_windLevelMax == 15) {
                        if (this.oq.b_windLevel <= 9) {
                            imageView6.setImageResource(this.oq.b_windLevel + R.drawable.hot_15levelw0);
                        } else {
                            imageView6.setImageResource((this.oq.b_windLevel + R.drawable.hot_15level1p0) - 10);
                        }
                    } else if (this.oq.b_windLevelMax == 8) {
                        imageView6.setImageResource(this.oq.b_windLevel + R.drawable.hot_8level0);
                    }
                    imageView6.setVisibility(0);
                }
                this.or.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
                return;
            }
            linearLayout.setVisibility(8);
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        if (C0035b.lp() > 19) {
            int qj = BaseApplication.py().qj();
            if (qj == 1) {
                this.ot = true;
            } else if (qj == 2) {
                this.os = true;
            }
        } else if (BaseApplication.py().qg("cfg_rudder=").equals("1")) {
            this.ot = true;
        }
    }

    public void onDestroy() {
        if (this.or != null) {
            this.ou.removeView(this.or);
        }
        super.onDestroy();
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        if (!tq() || this.os) {
            this.op.removeMessages(0);
            this.op.sendEmptyMessage(0);
        } else {
            oo = intent.getIntExtra("canbustype", 1);
            this.oq = (AirCondition) intent.getSerializableExtra("airdata");
            if (this.oq != null) {
                if (this.oq.onOff) {
                    if (this.or == null) {
                        tp();
                    }
                    this.op.removeMessages(0);
                    this.op.sendEmptyMessageDelayed(0, 3000);
                    tt();
                    tu();
                } else {
                    this.op.removeMessages(0);
                    this.op.sendEmptyMessage(0);
                }
            }
        }
    }
}
