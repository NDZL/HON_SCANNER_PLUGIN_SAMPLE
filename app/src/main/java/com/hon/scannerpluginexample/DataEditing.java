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
        String scanResult = intent.getStringExtra("data");//Read the scan result from the Intent
        Bundle bundle = new Bundle();

        //convert data to hex string
        /*
        byte[] digits = ScanResult.getBytes();
        Formatter formatter = new Formatter();
        for (byte b : digits) {
            formatter.format("%02x", b);
        }
        String hex = formatter.toString();

        // add prefix
        hex = prefix+hex;
        */

        String INTER_CHAR_DELAY = "{d500}";
        StringBuilder sb_data = new StringBuilder();
        StringBuilder sb_delay= new StringBuilder();
        char[] chars = scanResult.toCharArray();
        for (char ch: chars) {
            sb_data.append(ch);
            sb_data.append(INTER_CHAR_DELAY);
            sb_delay.append(INTER_CHAR_DELAY+";");
        }


        String delayedScannedData = sb_data.toString();
        String delayString = sb_delay.toString();
        Toast.makeText(context, "<"+delayedScannedData+">" , Toast.LENGTH_LONG).show();

        bundle.putString("data", delayedScannedData);
        bundle.putString("delayStr", delayString);

        setResultExtras(bundle);
    }

}
