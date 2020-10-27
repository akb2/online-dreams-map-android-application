package com.online.dreams_map.SysLibs;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.online.dreams_map.R;

import static android.content.Context.TELEPHONY_SERVICE;





public class DeviceID {
    private Context activity;



    public DeviceID(Activity activity){
        this.activity = activity;
    }



    // Получить EMEI
    public String getIMEI() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(TELEPHONY_SERVICE);
            String devicIMEI = telephonyManager.getDeviceId();

            return devicIMEI;
        }

        catch(SecurityException e)
            {}

        return "000000000000000";
    }



    // Получить номер телефона
    public String getPhoneNumber() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(TELEPHONY_SERVICE);
            String phoneNumber = telephonyManager.getLine1Number();

            return phoneNumber;
        }

        catch(SecurityException e)
            {}

        return "+000000000000";
    }



    // Получить псевдо ID железа
    public String pseudoID() {
        String pseudoID =
            "35" +
            Build.BOARD.length()%10 + Build.BRAND.length()%10 +
            Build.CPU_ABI.length()%10 + Build.DEVICE.length()%10 +
            Build.DISPLAY.length()%10 + Build.HOST.length()%10 +
            Build.ID.length()%10 + Build.MANUFACTURER.length()%10 +
            Build.MODEL.length()%10 + Build.PRODUCT.length()%10 +
            Build.TAGS.length()%10 + Build.TYPE.length()%10 +
            Build.USER.length()%10
        ;

        return pseudoID;
    }



    // Получить AndroidID
    public String androidID() {
        String androidID = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);

        return androidID;
    }



    // Получить WiFi Mac
    public String wifiMac() {
        WifiManager wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
        String wifiMac = wifiManager.getConnectionInfo().getMacAddress();

        return wifiMac == null? "": wifiMac;
    }



    // Получить BlueTooth Mac
    public String bluetoothMac() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        String blueToothMac = bluetoothAdapter.getAddress();

        return blueToothMac;
    }



    // Комбинация всех методов
    public String uniqueHash() {
        MD5 md5 = new MD5();

        return md5.generate(
            activity.getResources().getString(R.string.secret_api_code) +
            getIMEI() +
            getPhoneNumber() +
            pseudoID() +
            androidID() +
            wifiMac() +
            bluetoothMac()
        );
    }

}
