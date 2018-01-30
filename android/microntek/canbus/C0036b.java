package android.microntek.canbus;

import android.os.Handler;
import android.os.Message;

class C0036b extends Handler {
    final /* synthetic */ CanBusServer this$0;

    C0036b(CanBusServer canBusServer) {
        this.this$0 = canBusServer;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case R$styleable.MyButton_imgHeight /*1*/:
                if (this.this$0.kl != null) {
                    this.this$0.kl.bu((byte[]) message.obj);
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc /*2*/:
                if (this.this$0.kl != null) {
                    this.this$0.kl.mo49q();
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                int i = message.arg1;
                int i2 = message.arg2;
                if (this.this$0.kl != null) {
                    this.this$0.kl.mo50r(i);
                    this.this$0.kl.hw(i, i2);
                }
                if (this.this$0.ku != null) {
                    this.this$0.ku.mo50r(i);
                    return;
                }
                return;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.this$0.oq((byte[]) message.obj, message.arg1);
                return;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                if (this.this$0.ku != null) {
                    this.this$0.ku.lq((String) message.obj);
                    return;
                }
                return;
            case 7:
                if (this.this$0.kl != null && this.this$0.oc() == 1) {
                    this.this$0.kl.at();
                }
                this.this$0.kp.removeMessages(7);
                this.this$0.kp.sendEmptyMessageDelayed(7, 400);
                return;
            default:
                return;
        }
    }
}
