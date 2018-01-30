package android.microntek.canbus.air;

import android.microntek.canbus.R$styleable;
import android.os.Handler;
import android.os.Message;

class C0033c extends Handler {
    final /* synthetic */ AirControlActivity this$0;

    C0033c(AirControlActivity airControlActivity) {
        this.this$0 = airControlActivity;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case R$styleable.MyButton_imgWidth /*0*/:
                this.this$0.hf.mr();
                return;
            case R$styleable.MyButton_imgHeight /*1*/:
                this.this$0.mt((byte[]) message.obj);
                return;
            case R$styleable.MyButton_imgSrc /*2*/:
                this.this$0.sendRequestAirCMD();
                return;
            default:
                return;
        }
    }
}
