package android.microntek.canbus;

import android.view.View;
import android.view.View.OnClickListener;

class C0062t implements OnClickListener {
    final /* synthetic */ RadarServer pk;

    C0062t(RadarServer radarServer) {
        this.pk = radarServer;
    }

    public void onClick(View view) {
        if (this.pk.od) {
            this.pk.od = false;
            if (this.pk.oe != null) {
                this.pk.oh.removeView(this.pk.oe);
                this.pk.ok.edit().putBoolean("isRadarDisplay", false).commit();
                return;
            }
            return;
        }
        this.pk.od = true;
        if (this.pk.oe != null) {
            this.pk.oh.addView(this.pk.oe, this.pk.om);
            this.pk.ok.edit().putBoolean("isRadarDisplay", true).commit();
        }
    }
}
