package android.microntek.canbus.serializable;

import java.io.Serializable;

public class Warning implements Serializable {
    private int warningCount = 0;
    private int[][][] warningInfo;

    public Warning() {
        int[][][] iArr = new int[1][][];
        iArr[0] = new int[][]{new int[]{0, 0, 0, 0}, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{0, 0, 0}};
        this.warningInfo = iArr;
    }

    public int lc() {
        return this.warningCount;
    }

    public int[][][] ld() {
        return this.warningInfo;
    }

    public void le(int[][][] iArr) {
        this.warningInfo = iArr;
    }

    public void lf(int i) {
        this.warningCount = i;
    }
}
