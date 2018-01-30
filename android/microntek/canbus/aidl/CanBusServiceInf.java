package android.microntek.canbus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface CanBusServiceInf extends IInterface {

    public abstract class Stub extends Binder implements CanBusServiceInf {
        private static final String DESCRIPTOR = "android.microntek.canbus.aidl.CanBusServiceInf";
        static final int TRANSACTION_getAcMax = 24;
        static final int TRANSACTION_getAirBtnTable = 5;
        static final int TRANSACTION_getAriFloatViewShow = 34;
        static final int TRANSACTION_getAriStateShow = 32;
        static final int TRANSACTION_getFast = 26;
        static final int TRANSACTION_getLoopa = 27;
        static final int TRANSACTION_getModeAc = 22;
        static final int TRANSACTION_getModeAuto = 16;
        static final int TRANSACTION_getModeDual = 25;
        static final int TRANSACTION_getNormal = 28;
        static final int TRANSACTION_getOnOff = 6;
        static final int TRANSACTION_getRearLock = 23;
        static final int TRANSACTION_getSeatHotLeft = 8;
        static final int TRANSACTION_getSeatHotRight = 9;
        static final int TRANSACTION_getSeatShow = 7;
        static final int TRANSACTION_getSoft = 29;
        static final int TRANSACTION_getTempLeft = 19;
        static final int TRANSACTION_getTempRight = 20;
        static final int TRANSACTION_getTempUnit = 21;
        static final int TRANSACTION_getWindDown = 13;
        static final int TRANSACTION_getWindFrontMax = 14;
        static final int TRANSACTION_getWindLevel = 17;
        static final int TRANSACTION_getWindLevelMax = 18;
        static final int TRANSACTION_getWindLoop = 10;
        static final int TRANSACTION_getWindMid = 12;
        static final int TRANSACTION_getWindRear = 15;
        static final int TRANSACTION_getWindRearShow = 30;
        static final int TRANSACTION_getWindUp = 11;
        static final int TRANSACTION_getWind_FrameShow = 31;
        static final int TRANSACTION_registerCallback = 2;
        static final int TRANSACTION_sendAirKeyDown = 1;
        static final int TRANSACTION_sendRequestAirCMD = 4;
        static final int TRANSACTION_setAriFloatViewShow = 33;
        static final int TRANSACTION_unregisterCallback = 3;

        class Proxy implements CanBusServiceInf {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public int getAcMax() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getAcMax, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int[] getAirBtnTable() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    int[] createIntArray = obtain2.createIntArray();
                    return createIntArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getAriFloatViewShow() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getAriFloatViewShow, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getAriStateShow() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getAriStateShow, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getFast() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getFast, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public int getLoopa() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getLoopa, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getModeAc() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getModeAc, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getModeAuto() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getModeAuto, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getModeDual() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getModeDual, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getNormal() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getNormal, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getOnOff() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getRearLock() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getRearLock, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getSeatHotLeft() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getSeatHotLeft, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getSeatHotRight() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getSeatHotRight, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getSeatShow() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getSeatShow, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getSoft() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getSoft, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getTempLeft() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getTempLeft, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getTempRight() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getTempRight, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getTempUnit() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getTempUnit, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getWindDown() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getWindDown, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getWindFrontMax() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getWindFrontMax, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getWindLevel() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getWindLevel, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getWindLevelMax() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getWindLevelMax, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getWindLoop() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getWindLoop, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getWindMid() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getWindMid, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getWindRear() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getWindRear, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getWindRearShow() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getWindRearShow, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getWindUp() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getWindUp, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getWind_FrameShow() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getWind_FrameShow, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registerCallback(ICanBusAidlCallBack iCanBusAidlCallBack) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iCanBusAidlCallBack != null) {
                        iBinder = iCanBusAidlCallBack.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void sendAirKeyDown(int i, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void sendRequestAirCMD() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setAriFloatViewShow(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_setAriFloatViewShow, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregisterCallback(ICanBusAidlCallBack iCanBusAidlCallBack) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iCanBusAidlCallBack != null) {
                        iBinder = iCanBusAidlCallBack.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static CanBusServiceInf asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof CanBusServiceInf)) ? new Proxy(iBinder) : (CanBusServiceInf) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            boolean onOff;
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendAirKeyDown(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCallback(android.microntek.canbus.aidl.ICanBusAidlCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCallback(android.microntek.canbus.aidl.ICanBusAidlCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendRequestAirCMD();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] airBtnTable = getAirBtnTable();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(airBtnTable);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOff = getOnOff();
                    parcel2.writeNoException();
                    if (onOff) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getSeatShow /*7*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOff = getSeatShow();
                    parcel2.writeNoException();
                    if (onOff) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getSeatHotLeft /*8*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getSeatHotLeft();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getSeatHotRight /*9*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getSeatHotRight();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getWindLoop /*10*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getWindLoop();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getWindUp /*11*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOff = getWindUp();
                    parcel2.writeNoException();
                    if (onOff) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getWindMid /*12*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOff = getWindMid();
                    parcel2.writeNoException();
                    if (onOff) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getWindDown /*13*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOff = getWindDown();
                    parcel2.writeNoException();
                    if (onOff) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getWindFrontMax /*14*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOff = getWindFrontMax();
                    parcel2.writeNoException();
                    if (onOff) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getWindRear /*15*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOff = getWindRear();
                    parcel2.writeNoException();
                    if (onOff) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getModeAuto /*16*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getModeAuto();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getWindLevel /*17*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getWindLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getWindLevelMax /*18*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getWindLevelMax();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getTempLeft /*19*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getTempLeft();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getTempRight /*20*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getTempRight();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getTempUnit /*21*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOff = getTempUnit();
                    parcel2.writeNoException();
                    if (onOff) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getModeAc /*22*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOff = getModeAc();
                    parcel2.writeNoException();
                    if (onOff) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getRearLock /*23*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getRearLock();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getAcMax /*24*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getAcMax();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getModeDual /*25*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getModeDual();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getFast /*26*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getFast();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getLoopa /*27*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getLoopa();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getNormal /*28*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getNormal();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getSoft /*29*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getSoft();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getWindRearShow /*30*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOff = getWindRearShow();
                    parcel2.writeNoException();
                    if (onOff) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getWind_FrameShow /*31*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOff = getWind_FrameShow();
                    parcel2.writeNoException();
                    if (onOff) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getAriStateShow /*32*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOff = getAriStateShow();
                    parcel2.writeNoException();
                    if (onOff) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_setAriFloatViewShow /*33*/:
                    boolean z;
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    setAriFloatViewShow(z);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_getAriFloatViewShow /*34*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOff = getAriFloatViewShow();
                    parcel2.writeNoException();
                    if (onOff) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int getAcMax();

    int[] getAirBtnTable();

    boolean getAriFloatViewShow();

    boolean getAriStateShow();

    int getFast();

    int getLoopa();

    boolean getModeAc();

    int getModeAuto();

    int getModeDual();

    int getNormal();

    boolean getOnOff();

    int getRearLock();

    int getSeatHotLeft();

    int getSeatHotRight();

    boolean getSeatShow();

    int getSoft();

    int getTempLeft();

    int getTempRight();

    boolean getTempUnit();

    boolean getWindDown();

    boolean getWindFrontMax();

    int getWindLevel();

    int getWindLevelMax();

    int getWindLoop();

    boolean getWindMid();

    boolean getWindRear();

    boolean getWindRearShow();

    boolean getWindUp();

    boolean getWind_FrameShow();

    void registerCallback(ICanBusAidlCallBack iCanBusAidlCallBack);

    void sendAirKeyDown(int i, int i2);

    void sendRequestAirCMD();

    void setAriFloatViewShow(boolean z);

    void unregisterCallback(ICanBusAidlCallBack iCanBusAidlCallBack);
}
