package com.cvte.ble.sdk.core;

/**
 * Package : com.cvte.ble.sdk.core
 * Author : jacob
 * Date : 15-7-10
 * Description : 这个类是蓝牙sdk的配置类
 */
public class BleSdkConfig {

    /**
     * 蓝牙扫描的时常
     */
    public static int SCAN_TIME = 20 * 1000;

    /**
     * 停止蓝牙扫描的时间，即：扫描20s,停止35s
     */
    public static int STOP_SCAN_TIME = 30 * 1000;

    /**
     * 当蓝牙断开后并且过了10s还处于断开的状态就会提示
     */
    public static int BLE_ALERT_DALEY_WHEN_DISCONNECT = 7 * 1000;
}
