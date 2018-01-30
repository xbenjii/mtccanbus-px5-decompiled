package android.microntek.canbus;

import android.view.View;
import android.view.View.OnClickListener;

class C0047l implements OnClickListener {
    final /* synthetic */ Canbus20Activity pc;

    C0047l(Canbus20Activity canbus20Activity) {
        this.pc = canbus20Activity;
    }

    public void onClick(View view) {
        this.pc.rg((byte) -127);
        this.pc.rg((byte) 3);
        this.pc.mi.setImageResource(R.drawable.ico_pause);
        this.pc.mq = true;
        this.pc.mp.edit().putBoolean("status", this.pc.mq).commit();
    }
}
