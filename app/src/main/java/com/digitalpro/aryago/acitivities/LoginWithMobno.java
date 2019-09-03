package com.digitalpro.aryago.acitivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.digitalpro.aryago.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginWithMobno extends AppCompatActivity {

    ImageView nxtbutton;
    EditText mobno;
    String PREFS_NAME = "auth_info";
    int mobile;
    String inputmobileno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_mobno);

        setId();
        setOnClickListner();
    }

    public void setId()
    {
        nxtbutton=findViewById(R.id.nxtbutton);
        mobno=findViewById(R.id.mobno);

    }
    public void setOnClickListner()
    {
        nxtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mobno.getText().toString().equals("")) {
                    mobno.setError("Please Enter Mobile No to Continue");
                }
                else if(mobno.getText().toString().length()<10)
                {
                    mobno.setError("Please Enter Valid Mobile No");
                }
                else
                {
                    SendOtp(mobno.getText().toString());
                }
            }
        });
    }
    public void SendOtp(String mobno)
    {
        String url = "http://smsportal.digitave.com/http-api.php?username=aryago&password=pwd527&senderid=ARYAGO&route=1&number="+
                mobno+"&message=AryaGo Otp - 44455";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Log.e("jsonObject", String.valueOf(jsonObject));
                        try {
                            Toast.makeText(LoginWithMobno.this, "Hello All", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.data != null) {
                            String jsonError = new String(networkResponse.data);
                            Log.e("jsonError",jsonError);
                            try {
                                JSONObject jsonObject = new JSONObject(jsonError);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                })
        {
            @Override
            public String getBodyContentType()
            {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

//            @Override
//            public Map getHeaders()
//            {   HashMap headers = new HashMap();
//                token=new SharedPreferencesUtils(HomeActivity.this).getToken();
//                headers.put("Authorization","Bearer "+ token);
//                return headers;            //   headers.put("Accept", "application/json");
//
//            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

        Intent intent = new Intent(getApplicationContext(), VerifyOtp.class);
//        intent.putExtra("id", 1);
//        intent.putExtra("name", "Demo");
        intent.putExtra("mobno", mobno);
//        intent.putExtra("email", "demo@gmail.com");
        startActivity(intent);
        Toast.makeText(this, "Otp Sent Successfully", Toast.LENGTH_SHORT).show();

    }
}
