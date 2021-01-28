package com.kemalurekli.smssender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber,editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextMessage = findViewById(R.id.editTextMessage);
        editTextNumber = findViewById(R.id.editTextNumber);
    }

    public void sendMessage (View view){
        //Send button onclick method
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
            //Permission is positive
            //Send Sms Method
            sendSms();
        }else {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},100);
        }

    }

    private void sendSms() {
        String phoneNumber = editTextNumber.getText().toString().trim();
        String Message = editTextMessage.getText().toString().trim();
        if (!phoneNumber.equals("") && !Message.equals("")){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber,null,Message,null,null);
            Toast.makeText(MainActivity.this,"Sms sent successfully",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(MainActivity.this,"Please Enter Values",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            sendSms();
        }else {
            Toast.makeText(MainActivity.this,"Permission Denied!",Toast.LENGTH_LONG).show();
        }
    }
}