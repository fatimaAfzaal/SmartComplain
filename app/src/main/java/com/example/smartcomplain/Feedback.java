package com.example.smartcomplain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Feedback extends AppCompatActivity {
    TextView card1,card2,card3;
    CardView c1,c2,c3;
    TextView t1,t2,t3;
    String c;

    String p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().hide();

        card1=(TextView) findViewById(R.id.textView24);
        card2=(TextView) findViewById(R.id.textView25);
        card3=(TextView) findViewById(R.id.textView27);
        c1=(CardView) findViewById(R.id.c1);
        c2=(CardView) findViewById(R.id.c2);
        c3=(CardView) findViewById(R.id.c3);
        t1=(TextView) findViewById(R.id.textView23);
        t2=(TextView) findViewById(R.id.textView26);
        t3=(TextView) findViewById(R.id.textView28);

        Intent intent=getIntent();
        String positive=intent.getStringExtra("Positive");
        String negative=intent.getStringExtra("Negative");
        String pending=intent.getStringExtra("Pending");
        String email=intent.getStringExtra("Email");
        int i=intent.getIntExtra("i",1);
        int j=intent.getIntExtra("j",1);
        int k=intent.getIntExtra("k",1);
        c=intent.getStringExtra("c");
        if(i==1) {
            card1.setText(positive);
            card2.setText(negative);
            card3.setText(pending);
        }
        if(j==1) {
            card1.setText(positive);
            card2.setText(negative);
            card3.setText(pending);
        }
        if(k==1) {
            card1.setText(positive);
            card2.setText(negative);
            card3.setText(pending);
        }

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Feedback.this, MainActivity3.class);
                intent.putExtra("f",t1.getText().toString());
                intent.putExtra("Email",email);
                intent.putExtra("c",c);
                startActivity(intent);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Feedback.this, MainActivity3.class);
                intent.putExtra("f",t2.getText().toString());
                intent.putExtra("Email",email);
                intent.putExtra("c",c);
                startActivity(intent);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Feedback.this, MainActivity3.class);
                intent.putExtra("f",t3.getText().toString());
                intent.putExtra("Email",email);
                intent.putExtra("c",c);
                startActivity(intent);
            }
        });

    }
}