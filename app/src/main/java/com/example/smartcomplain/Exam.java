package com.example.smartcomplain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Exam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        getSupportActionBar().hide();

        CardView card1 = (CardView) findViewById(R.id.cardview3);
        CardView card2 = (CardView) findViewById(R.id.cardview1);
        CardView card3 = (CardView)findViewById(R.id.cardview2);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exam.this, MainActivity3.class);
                intent.putExtra("c", "Query");
                intent.putExtra("Dept","IT");
                startActivity(intent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exam.this, MainActivity3.class);
                intent.putExtra("c", "Complain");
                intent.putExtra("Dept","IT");
                startActivity(intent);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exam.this, MainActivity3.class);
                intent.putExtra("c", "Resource");
                intent.putExtra("Dept","IT");
                startActivity(intent);
            }
        });


        Button logout =(Button) findViewById(R.id.button5);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Exam.this, MainActivity.class));

            }
        });
    }
}