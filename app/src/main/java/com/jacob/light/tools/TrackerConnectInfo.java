package com.jacob.light.tools;

import android.bluetooth.BluetoothDevice;

import com.cvte.ble.sdk.entity.BleConnectInfo;

import java.util.Arrays;
import java.util.UUID;

/**
 * Package : com.jacob.ble.ui
 * Author : jacob
 * Date : 15-7-10
 * Description : 这个类是用来封装需要进行蓝牙随行的设备的信息
 */
public class TrackerConnectInfo extends BleConnectInfo {


    public TrackerConnectInfo() {
    }


    @Override
    public String getBroadCommand() {
        return "";
    }

    @Override
    public String getSingleTag() {
        return "";
    }

    @Override
    public String getVerifyCommand() {
        return "";
    }


    @Override
    public UUID getWriteCharacteristicUUID() {
        return UUID.fromString("0000ffb1-0000-1000-8000-00805f9b34fb");
    }

    @Override
    public UUID getServiceUUID() {
        return UUID.fromString("0000ffb0-0000-1000-8000-00805f9b34fb");
    }

    @Override
    public UUID getReadCharacteristicUUID() {
        return UUID.fromString("0000ffb1-0000-1000-8000-00805f9b34fb");
    }

    @Override
    public UUID getCharacteristicDescriptorUUID() {
        return null;
    }

    @Override
    public UUID getNotificationService() {
        return null;
    }

    @Override
    public boolean shouldConnectDevice(BluetoothDevice bluetoothDevice, byte[] bytes) {
        return false;
    }


}
