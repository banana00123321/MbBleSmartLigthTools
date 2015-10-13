package com.jacob.light.tools;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cvte.ble.sdk.core.BleSdkManager;
import com.cvte.ble.sdk.entity.BleConnectDevice;
import com.cvte.ble.sdk.entity.BleConnectInfo;
import com.cvte.ble.sdk.exception.NotSupportBleException;
import com.cvte.ble.sdk.listener.BleConnectCallback;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {

    private ListView mListView;
    private DeviceAdapter mDeviceAdapter;
    private List<BLEBean> mListBle = new ArrayList<>();
    private BleSdkManager mBleManager;
    private BleConnectDevice mConnectDevice;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_device);

        try {
            mBleManager = BleSdkManager.newInstance(getApplicationContext());
            mBleManager.init();
        } catch (NotSupportBleException e) {
            e.printStackTrace();
        }


        mListView = (ListView) findViewById(R.id.list_view_device);
        mDeviceAdapter = new DeviceAdapter(getApplicationContext(), mListBle);
        mListView.setAdapter(mDeviceAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stopScan();
                BleConnectInfo bleConnectInfo = new TrackerConnectInfo();
                BluetoothDevice device = mDeviceAdapter.getDevice(position).getBluetoothDevice();
                mConnectDevice = new BleConnectDevice(getApplicationContext(),  bleConnectInfo);
                mConnectDevice.getGoogleBle().connect(device,
                        mConnectDevice.getBleConnectInfo(), mConnectCallBack, false);
            }
        });

        Button button = (Button) findViewById(R.id.button_scan_device);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScan();
                mListBle.clear();
            }
        });
    }

    private void startScan() {
        mBleManager.startScan(mBleScanCallback);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopScan();
            }
        }, 5000);
    }

    private void stopScan() {
        mBleManager.stopScan();
    }


    /**
     * 蓝牙扫描设备的回调，
     */
    private BluetoothAdapter.LeScanCallback mBleScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            BLEBean bleBean = new BLEBean();
            bleBean.setBluetoothDevice(device);
            bleBean.setRssi(rssi);
            bleBean.setBroadcast(scanRecord);
            mListBle.add(bleBean);
            mDeviceAdapter.notifyDataSetChanged();
        }
    };

    private BleConnectCallback mConnectCallBack = new BleConnectCallback() {
        @Override
        public void onConnectSuccess(BleConnectInfo bleConnectInfo, BluetoothDevice bluetoothDevice) {
            LogUtils.LOGE("TAG", "****** onConnectSuccess *******");
            Toast.makeText(getApplicationContext(),"onConnectSuccess",Toast.LENGTH_SHORT).show();
            if (mConnectDevice != null) {
                byte[] bytes = hex2Byte("FA1F00");
                mConnectDevice.getGoogleBle().write(bytes, null);
            }
        }

        @Override
        public void onConnectError(BleConnectInfo bleConnectInfo, int errorCode, String reason) {
            LogUtils.LOGE("TAG", "****** onConnectError *******");
            Toast.makeText(getApplicationContext(),"onConnectError",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDeviceFound(BleConnectInfo bleConnectInfo, BluetoothDevice bluetoothDevice) {
            LogUtils.LOGE("TAG", "****** onDeviceFound *******");
        }
    };

    /**
     * 16进制字符串转换成byte数组
     *
     * @return 转换后的byte数组
     */
    public static byte[] hex2Byte(String hex) {
        String digital = "0123456789ABCDEF";
        char[] hex2char = hex.toCharArray();
        byte[] bytes = new byte[hex.length() / 2];
        int temp;
        for (int i = 0; i < bytes.length; i++) {
            // 其实和上面的函数是一样的 multiple 16 就是右移4位 这样就成了高4位了
            // 然后和低四位相加， 相当于 位操作"|"
            //相加后的数字 进行 位 "&" 操作 防止负数的自动扩展. {0xff byte最大表示数}
            temp = digital.indexOf(hex2char[2 * i]) * 16;
            temp += digital.indexOf(hex2char[2 * i + 1]);
            bytes[i] = (byte) (temp & 0xff);
        }
        return bytes;
    }

}
