package com.personal.tools.simplestvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

    private static final String LOG_TAG = "VirtualApp";
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, getResources().getString(R.string.admob_app_id));

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        findViewById(R.id.btn_start_ngame).setOnClickListener(this);
        findViewById(R.id.btn_start_speedmobile).setOnClickListener(this);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_start_ngame:
            {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.tencent.tmgp.sgame");
                if (intent != null){
                    Log.d(LOG_TAG,"intent:" + intent);
                    startActivity(intent);
                }
            }
                break;
            case R.id.btn_start_speedmobile:{
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.tencent.tmgp.speedmobile");
                if (intent != null){
                    Log.d(LOG_TAG,"intent:" + intent);
                    startActivity(intent);
                }
            }
                break;
            default:
                break;
        }
    }
}
