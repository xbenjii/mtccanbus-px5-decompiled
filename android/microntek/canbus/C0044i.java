package android.microntek.canbus;

import android.os.Handler;
import android.os.Message;

class C0044i extends Handler {
    final /* synthetic */ WarningServer oy;

    C0044i(WarningServer warningServer) {
        this.oy = warningServer;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 256) {
            this.oy.lw.removeMessages(256);
            int i = message.arg1;
            int i2 = message.arg2;
            if (i >= this.oy.ma.lc()) {
                this.oy.stopSelf();
                return;
            }
            int i3;
            int i4;
            int length = this.oy.warningInfo[i].length;
            int length2 = this.oy.warningInfo[i][i2].length;
            int i5 = this.oy.warningInfo[i][i2][0];
            if (length > 1) {
                length = (length2 <= 1 || (i5 & 255) != 1) ? 0 : 1;
                i3 = i2 + 1;
                if (i3 > 7) {
                    i4 = i + 1;
                    i3 = 0;
                } else {
                    i4 = i;
                }
            } else {
                length = 0;
                i3 = i2;
                i4 = i + 1;
                i2 = 0;
                i = 0;
            }
            Message obtainMessage = this.oy.lw.obtainMessage();
            obtainMessage.what = 256;
            obtainMessage.arg1 = i4;
            obtainMessage.arg2 = i3;
            if (i < this.oy.ma.lc()) {
                if (length != 0) {
                    if (length2 >= 3) {
                        this.oy.lx.setVisibility(0);
                        this.oy.lx.setImageResource(this.oy.warningInfo[i][i2][2]);
                    } else {
                        this.oy.lx.setVisibility(4);
                    }
                    if (length2 >= 4) {
                        this.oy.ly.setVisibility(0);
                        this.oy.ly.setImageResource(this.oy.warningInfo[i][i2][3]);
                    } else {
                        this.oy.ly.setVisibility(4);
                    }
                    if (length2 >= 2) {
                        this.oy.lz.setVisibility(0);
                        this.oy.lz.setText(this.oy.getString(this.oy.warningInfo[i][i2][1]));
                        this.oy.lz.lh(this.oy.getString(this.oy.warningInfo[i][i2][1]));
                    } else {
                        this.oy.lz.setVisibility(4);
                    }
                    this.oy.qw();
                    this.oy.lu.setOnClickListener(new C0045j(this, obtainMessage));
                    if ((65280 & i5) == 61440) {
                        this.oy.lw.sendMessageDelayed(obtainMessage, 5000);
                    }
                } else {
                    this.oy.qv();
                    this.oy.lw.sendMessageDelayed(obtainMessage, 2);
                }
            }
        }
    }
}
