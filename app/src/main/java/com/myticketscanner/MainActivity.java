package com.myticketscanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button scanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = (Button) findViewById(R.id.scanButton);
        scanButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == scanButton){
            startActivity(new Intent(this, QrCodeScannerActivity.class));
        }
    }
}
