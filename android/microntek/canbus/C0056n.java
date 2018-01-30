package android.microntek.canbus;

import android.view.View;
import android.view.View.OnClickListener;

class C0056n implements OnClickListener {
    final /* synthetic */ Canbus20Activity pe;

    C0056n(Canbus20Activity canbus20Activity) {
        this.pe = canbus20Activity;
    }

    public void onClick(View view) {
        if (this.pe.mq) {
            this.pe.mq = false;
            this.pe.rg(Byte.MIN_VALUE);
            this.pe.mi.setImageResource(R.drawable.ico_play);
            this.pe.mp.edit().putBoolean("status", this.pe.mq).commit();
            Canbus20Activity.mk = 0;
            return;
        }
        this.pe.mq = true;
        this.pe.rg((byte) -127);
        Canbus20Activity.mk = 1;
        BaseApplication.py().qh("av_channel_enter=line");
        this.pe.mi.setImageResource(R.drawable.ico_pause);
        this.pe.mp.edit().putBoolean("status", this.pe.mq).commit();
    }
}
