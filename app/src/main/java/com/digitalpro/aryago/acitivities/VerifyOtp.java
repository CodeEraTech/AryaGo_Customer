package com.digitalpro.aryago.acitivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.digitalpro.aryago.R;

public class VerifyOtp extends AppCompatActivity {

    EditText verifyotp;
    ImageView nxtbutton;
    String mobno;
    String PREFS_NAME = "auth_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        Intent intent = getIntent();
        mobno=intent.getStringExtra("mobno");

        setId();
        setOnClickListner();
    }

    public void setId()
    {
        nxtbutton=findViewById(R.id.nxtbutton);
        verifyotp=findViewById(R.id.setotp);
    }
    public void setOnClickListner()
    {
        nxtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (verifyotp.getText().toString().equals("")) {
                    verifyotp.setError("Please Enter Otp to Continue");
                }
                else if(verifyotp.getText().toString().equals("44455"))
                {
                    gotoprofile();

                }
                else
                {
//                  Toast.makeText(VerifyOtp.this,verifyotp.getText().toString() , Toast.LENGTH_SHORT).show();
                    verifyotp.setError("Otp Not Valid");
                }


            }
        });
    }
    public void gotoprofile()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("otpflag",true);
        editor.commit();

        Intent intent = new Intent(getApplicationContext(), SetPorfileDetails.class);
        intent.putExtra("phone", mobno);
        startActivity(intent);
        Toast.makeText(VerifyOtp.this, "Otp Verified Successfully", Toast.LENGTH_SHORT).show();
    }
}
