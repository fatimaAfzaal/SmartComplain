package com.example.smartcomplain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity5 extends AppCompatActivity {

    int pending=0,positive=0,negative=0;
    int pending1=0,positive1=0,negative1=0;
    int pending2=0,positive2=0,negative2=0;
    String pen,pos,neg;
    String pen1,pos1,neg1;
    String pen2,pos2,neg2;
    int i=0,j=0,k=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        getSupportActionBar().hide();
        Intent in=getIntent();
        String email=in.getStringExtra("Email");



        String s;
        CardView card1 = (CardView) findViewById(R.id.card5);
        CardView card2 = (CardView) findViewById(R.id.card4);
        CardView card3 = (CardView)findViewById(R.id.card6);
        CardView b = (CardView) findViewById(R.id.cardview3);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity5.this, MainActivity3.class);
                intent.putExtra("s", "Query");
                intent.putExtra("Email",email);
                startActivity(intent);
            }
        });
        CardView c1 = (CardView) findViewById(R.id.cardview1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity5.this, MainActivity3.class);
                intent.putExtra("s", "Complain");
                intent.putExtra("Email",email);
                startActivity(intent);
            }
        });
        CardView c2 = (CardView) findViewById(R.id.cardview2);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity5.this, MainActivity3.class);
                intent.putExtra("s", "Resources");
                intent.putExtra("Email",email);
                startActivity(intent);
            }
        });



        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference databaseReference;
                databaseReference=firebaseDatabase.getReference().child("Complain");
                databaseReference.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot db:snapshot.getChildren()) {

                            String Email=db.child("Email").getValue(String.class);
                            if(Email.equals(email)){
                            String Feedback = db.child("Feedback").getValue(String.class);
                            if (Feedback.equals("Pending")) {
                                pending++;
                            } else if (Feedback.equals("Positive"))
                                positive++;
                            else
                                negative++;
                        }}

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity5.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) ;

                if(i==0){
                    Intent intent=new Intent(MainActivity5.this,MainActivity5.class);
                    intent.putExtra("i",i);
                    i++;
                }
                else if(i==1){
                    Intent intent=new Intent(MainActivity5.this,Feedback.class);
                    pen = String.valueOf(pending);
                    pos = String.valueOf(positive);
                    neg = String.valueOf(negative);
                    intent.putExtra("Pending",pen);
                    intent.putExtra("Positive",pos);
                    intent.putExtra("Negative",neg);
                    intent.putExtra("i",i);
                    intent.putExtra("Email",email);
                    intent.putExtra("c","Complain");
                    startActivity(intent);
                    //Toast.makeText(getActivity(), "Positive : "+pos+"\nPending : "+pen+"\nNegative : "+neg, Toast.LENGTH_SHORT).show();
                    i++;
                    pending=positive=negative=0;

                }
                else{
                    Toast.makeText(MainActivity5.this, "Refresh/Come again to check feedback again", Toast.LENGTH_SHORT).show();
                } }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference databaseReference;
                databaseReference = firebaseDatabase.getReference().child("Query");
                databaseReference.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot db : snapshot.getChildren()) {
                            String Email=db.child("Email").getValue(String.class);
                            if(Email.equals(email)){
                            String Feedback = db.child("Feedback").getValue(String.class);
                            if (Feedback.equals("Pending")) {
                                pending1++;
                            } else if (Feedback.equals("Positive"))
                                positive1++;
                            else
                                negative1++;
                        }}

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity5.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                if (j == 0) {
                    Intent intent = new Intent(MainActivity5.this, MainActivity5.class);
                    intent.putExtra("j", j);
                    j++;
                } else if (j == 1) {
                    Intent intent = new Intent(MainActivity5.this, Feedback.class);
                    pen1 = String.valueOf(pending1);
                    pos1 = String.valueOf(positive1);
                    neg1 = String.valueOf(negative1);
                    intent.putExtra("Pending", pen1);
                    intent.putExtra("Positive", pos1);
                    intent.putExtra("Negative", neg1);
                    intent.putExtra("j", j);
                    intent.putExtra("Email",email);
                    intent.putExtra("c","Query");
                    startActivity(intent);
                    //Toast.makeText(getActivity(), "Positive : " + pos + "\nPending : " + pen + "\nNegative : " + neg, Toast.LENGTH_SHORT).show();
                    j++;

                } else {
                    Toast.makeText(MainActivity5.this, "Refresh/Come again to check feedback again", Toast.LENGTH_SHORT).show();
                } }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference databaseReference;
                databaseReference = firebaseDatabase.getReference().child("Resource");
                databaseReference.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot db : snapshot.getChildren()) {
                            String Email=db.child("Email").getValue(String.class);
                            if(Email.equals(email)){
                            String Feedback = db.child("Feedback").getValue(String.class);
                            if (Feedback.equals("Pending")) {
                                pending2++;
                            } else if (Feedback.equals("Positive"))
                                positive2++;
                            else
                                negative2++;
                        }}

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity5.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                if (k == 0) {
                    Intent intent = new Intent(MainActivity5.this, MainActivity5.class);
                    intent.putExtra("k", k);
                    k++;
                } else if (k == 1) {
                    Intent intent = new Intent(MainActivity5.this, Feedback.class);
                    pen2 = String.valueOf(pending2);
                    pos2 = String.valueOf(positive2);
                    neg2 = String.valueOf(negative2);
                    intent.putExtra("Pending", pen2);
                    intent.putExtra("Positive", pos2);
                    intent.putExtra("Negative", neg2);
                    intent.putExtra("k", k);
                    intent.putExtra("Email",email);
                    intent.putExtra("c","Resource");
                    startActivity(intent);
                    //Toast.makeText(getActivity(), "Positive : " + pos + "\nPending : " + pen + "\nNegative : " + neg, Toast.LENGTH_SHORT).show();
                    k++;

                } else {
                    Toast.makeText(MainActivity5.this, "Refresh/Come again to check feedback again", Toast.LENGTH_SHORT).show();

                } }
        });


    }
}