package com.wesine.www.activity1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    Button btnTel, btnSms, btnMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTel = findViewById(R.id.btnTel);
        btnSms = findViewById(R.id.btnSms);
        btnMedia = findViewById(R.id.btnMedia);

        btnTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:10086");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("smsto:10086");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "hello");
                startActivity(intent);
            }
        });

        btnMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra("sms_body","hello");
                Uri uri = Uri.parse("content://media/external/images/media/23");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.setType("image/png");
                startActivity(intent);
            }
        });
    }

}
