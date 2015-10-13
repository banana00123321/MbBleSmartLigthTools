package com.jacob.light.tools;

import android.app.Application;

import com.cvte.ble.sdk.core.BleSdkManager;
import com.cvte.ble.sdk.exception.NotSupportBleException;
import com.cvte.ble.sdk.utils.BleUtils;

/**
 * Package : com.jacob.ble.factory
 * Author : jacob
 * Date : 15-9-17
 * Description :�����������xxx
 */
public class BleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BleUtils.isBleSupported(this)) {
            try {
                BleSdkManager.newInstance(this).init();
            } catch (NotSupportBleException e) {
            }
        }
    }
}
