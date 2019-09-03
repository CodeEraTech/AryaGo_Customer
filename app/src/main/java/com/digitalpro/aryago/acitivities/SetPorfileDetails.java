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

public class SetPorfileDetails extends AppCompatActivity {

    EditText username,useremail;
    ImageView nxtbutton;
    String mobno;
    String PREFS_NAME = "auth_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_porfile_details);

        Intent intent = getIntent();
        mobno=intent.getStringExtra("phone");

        setId();
        setOnClickListner();
    }
    public void setId()
    {
        username=findViewById(R.id.username);
        useremail=findViewById(R.id.useremail);
        nxtbutton=findViewById(R.id.nxtbutton);

    }
    public void setOnClickListner()
    {
        nxtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.getText().toString().equals("")) {
                    username.setError("Please Enter Your Full Name");
                }
                else if(username.getText().toString().equals(""))
                {
                    useremail.setError("Please Enter Your Email");
                }
                else
                {
                    setprofile(username.getText().toString(),useremail.getText().toString());
                }


            }
        });
    }
    public void setprofile(String name,String email)
    {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", "1");
        editor.putString("name", name);
        editor.putString("phone", mobno);
        editor.putString("email", email);
        editor.putBoolean("profileflag",true);
        editor.commit();

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        Toast.makeText(SetPorfileDetails.this, "User Detail Saved Successfully", Toast.LENGTH_SHORT).show();
    }
}
