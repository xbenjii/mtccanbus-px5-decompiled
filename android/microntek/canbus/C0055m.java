package android.microntek.canbus;

import android.view.View;
import android.view.View.OnClickListener;

class C0055m implements OnClickListener {
    final /* synthetic */ Canbus20Activity pd;

    C0055m(Canbus20Activity canbus20Activity) {
        this.pd = canbus20Activity;
    }

    public void onClick(View view) {
        this.pd.rg((byte) -127);
        this.pd.rg((byte) 4);
        this.pd.mi.setImageResource(R.drawable.ico_pause);
        this.pd.mq = true;
        this.pd.mp.edit().putBoolean("status", this.pd.mq).commit();
    }
}
