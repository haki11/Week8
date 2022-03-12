package com.example.simpleintents;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //
    //
    public void callIntent(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnBrowser:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.cbc.ca"));
                startActivity(intent);
                break;
            case R.id.btnPhoneCall:
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                    return;
                }
                makePhoneCall();
                break;
            case R.id.btnActivity:
                intent = new Intent(this, DisplayActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    Toast.makeText(MainActivity.this, "I can make a phone call", Toast.LENGTH_SHORT)
                            .show();
                    makePhoneCall();
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "Call access denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void makePhoneCall() {
        Intent intent = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:(416)999-0000"));
        startActivity(intent);
    }
}
