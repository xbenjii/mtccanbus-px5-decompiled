package android.microntek.canbus.air;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.microntek.canbus.aidl.CanBusServiceInf;
import android.microntek.canbus.aidl.ICanBusAidlCallBack;
import android.microntek.canbus.aidl.ICanBusAidlCallBack.Stub;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

public class AirControlActivity extends Activity {
    private final int gm = 1;
    private final int gn = 2;
    private final int go = 0;
    private final String gp = "android.microntek.canbus";
    private final String gq = "android.microntek.canbus.CanBusServer";
    public int gr = 2;
    public int gs = 3;
    public int gt = 4;
    public int gu = 5;
    public int gv = 6;
    public int gw = 7;
    public int gx = 8;
    public int gy = 9;
    public int gz = 1;
    public int ha = 0;
    private ICanBusAidlCallBack hb = new C00302();
    private ServiceConnection hc = new C0032b(this);
    private Handler hd = new C0033c(this);
    private CanBusServiceInf he;
    private C0031a hf;

    class C00302 extends Stub {
        C00302() {
        }

        public void CanBusServiceData(byte[] bArr) {
            Message obtainMessage = AirControlActivity.this.hd.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = bArr;
            AirControlActivity.this.hd.sendMessage(obtainMessage);
        }

        public void UpdateAirUiView() {
            AirControlActivity.this.hd.removeMessages(0);
            AirControlActivity.this.hd.sendEmptyMessageDelayed(0, 5);
        }
    }

    private void mt(byte[] bArr) {
        int i = 0;
        while (i < this.hf.mn().length && this.hf.mn()[i] != bArr[this.ha]) {
            i++;
        }
    }

    public void finish() {
        setAriFloatViewShow(true);
        super.finish();
    }

    public int getAcMax() {
        try {
            if (this.he != null) {
                return this.he.getAcMax();
            }
        } catch (RemoteException e) {
        }
        return 0;
    }

    public int[] getAirBtnTable() {
        try {
            if (this.he != null) {
                return this.he.getAirBtnTable();
            }
        } catch (RemoteException e) {
        }
        return new int[]{0};
    }

    public int getFast() {
        try {
            if (this.he != null) {
                return this.he.getFast();
            }
        } catch (RemoteException e) {
        }
        return -1;
    }

    public int getLoopa() {
        try {
            if (this.he != null) {
                return this.he.getLoopa();
            }
        } catch (RemoteException e) {
        }
        return -1;
    }

    public boolean getModeAc() {
        try {
            if (this.he != null) {
                return this.he.getModeAc();
            }
        } catch (RemoteException e) {
        }
        return false;
    }

    public int getModeAuto() {
        try {
            if (this.he != null) {
                return this.he.getModeAuto();
            }
        } catch (RemoteException e) {
        }
        return 0;
    }

    public int getModeDual() {
        try {
            if (this.he != null) {
                return this.he.getModeDual();
            }
        } catch (RemoteException e) {
        }
        return 0;
    }

    public int getNormal() {
        try {
            if (this.he != null) {
                return this.he.getNormal();
            }
        } catch (RemoteException e) {
        }
        return -1;
    }

    public boolean getOnOff() {
        try {
            if (this.he != null) {
                return this.he.getOnOff();
            }
        } catch (RemoteException e) {
        }
        return false;
    }

    public int getRearLock() {
        try {
            if (this.he != null) {
                return this.he.getRearLock();
            }
        } catch (RemoteException e) {
        }
        return 0;
    }

    public int getSeatHotLeft() {
        try {
            if (this.he != null) {
                return this.he.getSeatHotLeft();
            }
        } catch (RemoteException e) {
        }
        return 0;
    }

    public int getSeatHotRight() {
        try {
            if (this.he != null) {
                return this.he.getSeatHotRight();
            }
        } catch (RemoteException e) {
        }
        return 0;
    }

    public int getSoft() {
        try {
            if (this.he != null) {
                return this.he.getSoft();
            }
        } catch (RemoteException e) {
        }
        return -1;
    }

    public int getTempLeft() {
        try {
            if (this.he != null) {
                return this.he.getTempLeft();
            }
        } catch (RemoteException e) {
        }
        return 0;
    }

    public int getTempRight() {
        try {
            if (this.he != null) {
                return this.he.getTempRight();
            }
        } catch (RemoteException e) {
        }
        return 0;
    }

    public boolean getTempUnit() {
        try {
            if (this.he != null) {
                return this.he.getTempUnit();
            }
        } catch (RemoteException e) {
        }
        return false;
    }

    public boolean getWindDown() {
        try {
            if (this.he != null) {
                return this.he.getWindDown();
            }
        } catch (RemoteException e) {
        }
        return false;
    }

    public boolean getWindFrontMax() {
        try {
            if (this.he != null) {
                return this.he.getWindFrontMax();
            }
        } catch (RemoteException e) {
        }
        return false;
    }

    public int getWindLevel() {
        try {
            if (this.he != null) {
                return this.he.getWindLevel();
            }
        } catch (RemoteException e) {
        }
        return 0;
    }

    public int getWindLoop() {
        try {
            if (this.he != null) {
                return this.he.getWindLoop();
            }
        } catch (RemoteException e) {
        }
        return 0;
    }

    public boolean getWindMid() {
        try {
            if (this.he != null) {
                return this.he.getWindMid();
            }
        } catch (RemoteException e) {
        }
        return false;
    }

    public boolean getWindRear() {
        try {
            if (this.he != null) {
                return this.he.getWindRear();
            }
        } catch (RemoteException e) {
        }
        return false;
    }

    public boolean getWindUp() {
        try {
            if (this.he != null) {
                return this.he.getWindUp();
            }
        } catch (RemoteException e) {
        }
        return false;
    }

    public void ms(int i, int i2) {
        try {
            this.he.sendAirKeyDown(i, i2);
        } catch (RemoteException e) {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("CanBus", "AirControlActivity onCreate");
        this.hf = new C0031a(this);
        setContentView(this.hf.mm());
        this.hf.mp();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("android.microntek.canbus", "android.microntek.canbus.CanBusServer"));
        bindService(intent, this.hc, 1);
    }

    protected void onDestroy() {
        this.hd.removeCallbacks(null);
        try {
            setAriFloatViewShow(true);
            if (this.he != null) {
                this.he.unregisterCallback(this.hb);
            }
            unbindService(this.hc);
        } catch (Exception e) {
        }
        super.onDestroy();
    }

    protected void onPause() {
        setAriFloatViewShow(true);
        super.onPause();
    }

    protected void onRestart() {
        super.onRestart();
        sendRequestAirCMD();
    }

    protected void onResume() {
        super.onResume();
        setAriFloatViewShow(false);
    }

    public void sendRequestAirCMD() {
        try {
            this.he.sendRequestAirCMD();
        } catch (RemoteException e) {
        }
    }

    public void setAriFloatViewShow(boolean z) {
        try {
            if (this.he != null) {
                this.he.setAriFloatViewShow(z);
            }
        } catch (RemoteException e) {
        }
    }
}
