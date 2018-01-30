package android.microntek.canbus;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class C0043h implements OnTouchListener {
    final /* synthetic */ DoorServer ox;

    C0043h(DoorServer doorServer) {
        this.ox = doorServer;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.ox.lj.removeMessages(0);
        this.ox.lj.sendEmptyMessageDelayed(0, 100);
        return true;
    }
}
