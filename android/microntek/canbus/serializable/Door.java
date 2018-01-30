package android.microntek.canbus.serializable;

import java.io.Serializable;

public class Door implements Serializable {
    public boolean front = false;
    public boolean frontDriver = false;
    public boolean frontPassenger = false;
    public int mode = 0;
    public boolean rearLeft = false;
    public boolean rearRight = false;
    public boolean roof = false;
    public boolean safety = false;
    public boolean trunk = false;

    public boolean la(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        boolean z7 = false;
        if (this.frontDriver == z && this.frontPassenger == z2 && this.rearLeft == z3 && this.rearRight == z4 && this.trunk == z5) {
            if (this.front != z6) {
            }
            this.frontDriver = z;
            this.frontPassenger = z2;
            this.rearLeft = z3;
            this.rearRight = z4;
            this.trunk = z5;
            this.front = z6;
            return z7;
        }
        z7 = true;
        this.frontDriver = z;
        this.frontPassenger = z2;
        this.rearLeft = z3;
        this.rearRight = z4;
        this.trunk = z5;
        this.front = z6;
        return z7;
    }

    public boolean lb(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.mode = 1;
        boolean z8 = false;
        if (this.frontDriver == z && this.frontPassenger == z2 && this.rearLeft == z3 && this.rearRight == z4 && this.trunk == z5 && this.front == z6) {
            if (this.safety != z7) {
            }
            this.frontDriver = z;
            this.frontPassenger = z2;
            this.rearLeft = z3;
            this.rearRight = z4;
            this.trunk = z5;
            this.front = z6;
            this.safety = z7;
            return z8;
        }
        z8 = true;
        this.frontDriver = z;
        this.frontPassenger = z2;
        this.rearLeft = z3;
        this.rearRight = z4;
        this.trunk = z5;
        this.front = z6;
        this.safety = z7;
        return z8;
    }
}
