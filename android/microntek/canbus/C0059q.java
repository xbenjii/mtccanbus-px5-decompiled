package android.microntek.canbus;

import android.view.View;
import android.view.View.OnClickListener;

class C0059q implements OnClickListener {
    final /* synthetic */ Ajxserver ph;

    C0059q(Ajxserver ajxserver) {
        this.ph = ajxserver;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dial_num1:
                this.ph.sf('1');
                if (this.ph.nd) {
                    this.ph.rw((byte) 1);
                    return;
                }
                return;
            case R.id.dial_num2:
                this.ph.sf('2');
                if (this.ph.nd) {
                    this.ph.rw((byte) 2);
                    return;
                }
                return;
            case R.id.dial_num3:
                this.ph.sf('3');
                if (this.ph.nd) {
                    this.ph.rw((byte) 3);
                    return;
                }
                return;
            case R.id.dial_num4:
                this.ph.sf('4');
                if (this.ph.nd) {
                    this.ph.rw((byte) 4);
                    return;
                }
                return;
            case R.id.dial_num5:
                this.ph.sf('5');
                if (this.ph.nd) {
                    this.ph.rw((byte) 5);
                    return;
                }
                return;
            case R.id.dial_num6:
                this.ph.sf('6');
                if (this.ph.nd) {
                    this.ph.rw((byte) 6);
                    return;
                }
                return;
            case R.id.dial_num7:
                this.ph.sf('7');
                if (this.ph.nd) {
                    this.ph.rw((byte) 7);
                    return;
                }
                return;
            case R.id.dial_num8:
                this.ph.sf('8');
                if (this.ph.nd) {
                    this.ph.rw((byte) 8);
                    return;
                }
                return;
            case R.id.dial_num9:
                this.ph.sf('9');
                if (this.ph.nd) {
                    this.ph.rw((byte) 9);
                    return;
                }
                return;
            case R.id.dial_numx:
                this.ph.sf('*');
                if (this.ph.nd) {
                    this.ph.rw((byte) 10);
                    return;
                }
                return;
            case R.id.dial_num0:
                this.ph.sf('0');
                if (this.ph.nd) {
                    this.ph.rw((byte) 0);
                    return;
                }
                return;
            case R.id.dial_nums:
                this.ph.sf('#');
                if (this.ph.nd) {
                    this.ph.rw((byte) 11);
                    return;
                }
                return;
            case R.id.dial_back:
                this.ph.se();
                return;
            case R.id.dial_dialout:
                if (this.ph.ne) {
                    this.ph.rv();
                    return;
                } else if (!this.ph.nd && !this.ph.mw) {
                    this.ph.sa();
                    return;
                } else {
                    return;
                }
            case R.id.dial_handup:
                if (this.ph.ne || this.ph.mw || this.ph.nd) {
                    this.ph.sd();
                    return;
                }
                return;
            case R.id.dial_voiceswitch:
                this.ph.rt((byte) -123, new byte[1], 1);
                return;
            default:
                return;
        }
    }
}
