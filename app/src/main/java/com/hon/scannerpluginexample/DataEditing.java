package com.hon.scannerpluginexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Set;


public class DataEditing extends BroadcastReceiver {

    public String prefix;

    public DataEditing() {
        prefix="HEX={d2500}";
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String ScanResult = intent.getStringExtra("data");//Read the scan result from the Intent
        Bundle bundle = new Bundle();

        //convert data to hex string
        byte[] digits = ScanResult.getBytes();
        Formatter formatter = new Formatter();
        for (byte b : digits) {
            formatter.format("%02x", b);
        }
        String hex = formatter.toString();

        // add prefix
        hex = prefix+hex;
        bundle.putString("data", hex);
        //bundle.putString("delayStr", "{d2500}");

        setResultExtras(bundle);
    }

}
