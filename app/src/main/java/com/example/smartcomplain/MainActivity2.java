package com.example.smartcomplain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    TextView t;
    String str,s;
    Button button;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;

    private RequestQueue mRequestQue;
    private String URL = "https://fcm.googleapis.com/fcm/send";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        button = findViewById(R.id.button7);
        t = (TextView) findViewById(R.id.editTextTextPersonName3);

        Intent i=getIntent();
        String email=i.getStringExtra("Email");
        String dept=i.getStringExtra("Dept");
        //Toast.makeText(this, "Email"+email, Toast.LENGTH_SHORT).show();

        if (getIntent().hasExtra("category")) {
            Intent intent = new Intent(MainActivity2.this, MainMenu.class);
            intent.putExtra("category", getIntent().getStringExtra("category"));
            intent.putExtra("brandId", getIntent().getStringExtra("brandId"));
            intent.putExtra("Activity", "Activity2");
            startActivity(intent);

        }

        mRequestQue = Volley.newRequestQueue(this);
        FirebaseMessaging.getInstance().subscribeToTopic("news");

        Intent intent = getIntent();

        if ((intent.getStringExtra("Query")) != null) {
            s = "Query";
            str = "Query sent ";
            t.setHint(intent.getStringExtra("Query"));
        } else if ((intent.getStringExtra("Complain")) != null) {
            s = "Complain";
            str = "Complain sent";
            t.setHint(intent.getStringExtra("Complain"));
        } else {
            s = "Resource";
            str = "Resource request sent";
            t.setHint(intent.getStringExtra("Resource"));
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendNotification();

                Toast.makeText(MainActivity2.this, str, Toast.LENGTH_SHORT).show();

                HashMap<String, Object> m = new HashMap<String, Object>();
                m.put("Email", email);
                m.put("Dept",dept);
                Toast.makeText(MainActivity2.this, "Email "+email, Toast.LENGTH_SHORT).show();
                if((s!="Query" && s!="Resource"))
                    m.put("Complain", t.getText().toString());
                else if(s!="Complain" && s!="Resource")
                    m.put("Query", t.getText().toString());
                else
                    m.put("Resources",t.getText().toString());

                calendar=Calendar.getInstance();                //getting date and time
                simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy" +"\n\t\t\t\t\t\t\t\t\t\t\t"+
                        "HH:mm:ss");
                Date=simpleDateFormat.format(calendar.getTime());
                m.put("Date and Time", Date);


                m.put("Feedback", "Pending");
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference keyRef = rootRef.push();
                String key = keyRef.getKey();
                Intent intent1=new Intent(MainActivity2.this,MainActivity3.class);
                intent1.putExtra("Key",key);
                intent1.putExtra("s",s);
                intent1.putExtra("Email",email);
                startActivity(intent1);
                if(s!="Query" && s!="Resource")
                    FirebaseDatabase.getInstance().getReference().child("Complain").child(key).updateChildren(m).addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity2.this, "Your data is successfully added", Toast.LENGTH_SHORT).show();
                        }
                    });
                else if(s!="Complain" && s!="Resource")
                    FirebaseDatabase.getInstance().getReference().child("Query").child(key).updateChildren(m).addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity2.this, "Your data is successfully added", Toast.LENGTH_SHORT).show();
                        }
                    });

                else
                    FirebaseDatabase.getInstance().getReference().child("Resource").child(key).updateChildren(m).addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity2.this, "Your data is successfully added", Toast.LENGTH_SHORT).show();
                        }
                    });


            }
        });


    }


    public void onm(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    t.setText(result.get(0));
                }
                break;
        }
    }

    public void onc(View view) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
    private void sendNotification() {

        JSONObject mainObj = new JSONObject();
        try {
            mainObj.put("to","/topics/"+"news");
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title",s);
            notificationObj.put("body",t.getText().toString());

            JSONObject extraData = new JSONObject();
            extraData.put("brandId","puma");
            extraData.put("category","Shoes");



            mainObj.put("notification",notificationObj);
            mainObj.put("data",extraData);


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    mainObj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.d("MUR", "onResponse: ");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("MUR", "onError: "+error.networkResponse);
                }
            }
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAAIN0_EBE:APA91bEB-WGQ1remRw-9MfIjwAoitl9Of176XmvjgrzJJ51kLSz9v0LAbdooR1ON3-h65QT_MnKuhCmN0Om0W_Cc1dQjEtlApENmbzQgupTa0VyCC5NaMCRFFsUYy8mzaWCFW2OZAbLx");
                    return header;
                }
            };
            mRequestQue.add(request);
        }
        catch (JSONException e)

        {
            e.printStackTrace();
        }
    }

}