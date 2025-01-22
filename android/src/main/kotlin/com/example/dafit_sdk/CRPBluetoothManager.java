//package com.example.dafit_sdk;
//
//import android.annotation.SuppressLint;
//import android.bluetooth.BluetoothDevice;
//import android.content.Context;
//import androidx.annotation.NonNull;
//import com.crrepa.w0.c;
//import com.realsil.sdk.core.bluetooth.BluetoothProfileCallback;
//import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
//import com.realsil.sdk.core.bluetooth.RtkBluetoothManager;
//import com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback;
//import com.realsil.sdk.core.bluetooth.compat.BluetoothDeviceCompat;
//
//public class CRPBluetoothManager {
//    private static CRPBluetoothManager INSTANCE;
//    private BluetoothBondStateListener bondStateListener;
//
//    public static CRPBluetoothManager getInstance(@NonNull Context var0) {
//        if (INSTANCE == null) {
//            Class var1 = CRPBluetoothManager.class;
//            synchronized(CRPBluetoothManager.class){}
//
//            Throwable var10000;
//            boolean var10001;
//            CRPBluetoothManager var14;
//            try {
//                var14 = INSTANCE;
//            } catch (Throwable var13) {
//                var10000 = var13;
//                var10001 = false;
//                throw var10000;
//            }
//
//            if (var14 == null) {
//                try {
//                    INSTANCE = new CRPBluetoothManager(var0);
//                } catch (Throwable var12) {
//                    var10000 = var12;
//                    var10001 = false;
//                    throw var10000;
//                }
//            }
//
//            try {
//                return INSTANCE;
//            } catch (Throwable var11) {
//                var10000 = var11;
//                var10001 = false;
//                throw var10000;
//            }
//        } else {
//            return INSTANCE;
//        }
//    }
//
//    private CRPBluetoothManager(Context var1) {
//        RtkBluetoothManager.initial(var1);
//        RtkBluetoothManager.getInstance().addManagerCallback(new RtkBluetoothManagerCallback() {
//            public void onBondStateChanged(BluetoothDevice var1, int var2) {
//                super.onBondStateChanged(var1, var2);
//                c.a("onBondStateChanged: " + var2);
//                if (CRPBluetoothManager.this.bondStateListener != null) {
//                    CRPBluetoothManager.this.bondStateListener.onBondStateChanged(var1, var2);
//                }
//
//            }
//        });
//        BluetoothProfileManager.initial(var1);
//        BluetoothProfileManager.getInstance().addManagerCallback(new BluetoothProfileCallback() {
//            public void onHfpConnectionStateChanged(BluetoothDevice var1, int var2) {
//                super.onHfpConnectionStateChanged(var1, var2);
//                c.a("onHfpConnectionStateChanged: " + var2);
//                if (CRPBluetoothManager.this.bondStateListener != null) {
//                    CRPBluetoothManager.this.bondStateListener.onConnectionState(var1, 4, var2);
//                }
//
//            }
//
//            public void onA2dpStateChanged(BluetoothDevice var1, int var2) {
//                super.onA2dpStateChanged(var1, var2);
//                c.a("onA2dpStateChanged: " + var2);
//                if (CRPBluetoothManager.this.bondStateListener != null) {
//                    CRPBluetoothManager.this.bondStateListener.onConnectionState(var1, 1, var2);
//                }
//
//            }
//        });
//    }
//
//    public boolean createBond(@NonNull BluetoothDevice var1) {
//        c.a("createBond: " + var1.getAddress());
//        return BluetoothDeviceCompat.createBond(var1);
//    }
//
//    public boolean createBondOfClassic(@NonNull BluetoothDevice var1) {
//        c.a("createBondOfClassic: " + var1.getAddress());
//        return BluetoothDeviceCompat.createBond(var1, 1);
//    }
//
//    public boolean getConnectionState(@NonNull BluetoothDevice var1, int var2) {
//        return BluetoothProfileManager.getInstance().getConnectionState(var2, var1) == 2;
//    }
//
//    public boolean connectHeadset(@NonNull BluetoothDevice var1) {
//        c.a("connectHeadset: " + var1.getAddress());
//        BluetoothProfileManager var2;
//        boolean var10000 = (var2 = BluetoothProfileManager.getInstance()).getProfileProxy(4);
//        c.a("headsetProfileProxy: " + var10000);
//        return var10000 ? var2.connectHfpAg(var1.getAddress()) : false;
//    }
//
//    public boolean connectA2dp(@NonNull BluetoothDevice var1) {
//        c.a("connectA2dp: " + var1.getAddress());
//        BluetoothProfileManager var2;
//        boolean var10000 = (var2 = BluetoothProfileManager.getInstance()).getProfileProxy(1);
//        c.a("a2dpProfileProxy: " + var10000);
//        return var10000 ? var2.connectA2dpSource(var1) : false;
//    }
//
//    public boolean hasProfile(int var1) {
//        return BluetoothProfileManager.getInstance().getProfileProxy(var1);
//    }
//
//    public boolean disconnectHeadset(@NonNull BluetoothDevice var1) {
//        c.a("disconnectHeadset: " + var1.getAddress());
//        return BluetoothProfileManager.getInstance().disconnectHfp(var1.getAddress());
//    }
//
//    public boolean disconnectA2dp(@NonNull BluetoothDevice var1) {
//        c.a("disconnectA2dp: " + var1.getAddress());
//        return BluetoothProfileManager.getInstance().disconnectA2dpSource(var1.getAddress());
//    }
//
//    @SuppressLint({"MissingPermission"})
//    public boolean unBondDevice(BluetoothDevice var1) {
//        if (var1 == null) {
//            return false;
//        } else {
//            return var1.getBondState() != 10 ? BluetoothDeviceCompat.removeBond(var1) : true;
//        }
//    }
//
//    public void setBondStateListener(BluetoothBondStateListener var1) {
//        this.bondStateListener = var1;
//    }
//
//    public interface BluetoothBondStateListener {
//        int INDICATOR_A2DP = 1;
//        int INDICATOR_HEADSET = 4;
//
//        void onBondStateChanged(BluetoothDevice var1, int var2);
//
//        void onConnectionState(BluetoothDevice var1, int var2, int var3);
//    }
//}
