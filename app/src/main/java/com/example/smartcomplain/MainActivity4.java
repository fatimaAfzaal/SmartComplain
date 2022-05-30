package com.example.smartcomplain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity4 extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().hide();

        Intent i = getIntent();
        String email = i.getStringExtra("Email");

        Button b = (Button) findViewById(R.id.button9);
        Button b1 = (Button) findViewById(R.id.button4);
        Button b2 = (Button) findViewById(R.id.button3);
        Button b3 = (Button) findViewById(R.id.button2);

        Intent intent = getIntent();
        String s = intent.getStringExtra("s");
        String ad = intent.getStringExtra("admin");
        if (ad != null) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                    intent.putExtra("emp", "emp");
                    intent.putExtra("Dept", b.getText().toString());
                    startActivity(intent);
                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                    intent.putExtra("emp", "emp");
                    intent.putExtra("Dept", b1.getText().toString());
                    startActivity(intent);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                    intent.putExtra("emp", "emp");
                    intent.putExtra("Dept", b2.getText().toString());
                    Toast.makeText(MainActivity4.this, "Dept"+ b2.getText().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                    intent.putExtra("emp", "emp");
                    intent.putExtra("Dept", b3.getText().toString());
                    startActivity(intent);
                }
            });


        } else {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (s.equals("Complain")) {
                        Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                        intent.putExtra("Complain", "Enter Complain here");
                        intent.putExtra("Email", email);
                        intent.putExtra("Dept", b.getText().toString());
                        startActivity(intent);
                    } else if (s.equals("Query")) {
                        Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                        intent.putExtra("Query", "Enter Query here");
                        intent.putExtra("Email", email);
                        intent.putExtra("Dept", b.getText().toString());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                        intent.putExtra("Resource", "Request resource here");
                        intent.putExtra("Email", email);
                        intent.putExtra("Dept", b.getText().toString());
                        startActivity(intent);
                    }

                }
            });

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (s.equals("Complain")) {
                        Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                        intent.putExtra("Complain", "Enter Complain here");
                        intent.putExtra("Email", email);
                        intent.putExtra("Dept", b1.getText().toString());
                        startActivity(intent);
                    } else if (s.equals("Query")) {
                        Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                        intent.putExtra("Query", "Enter Query here");
                        intent.putExtra("Email", email);
                        intent.putExtra("Dept", b1.getText().toString());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                        intent.putExtra("Resource", "Request resource here");
                        intent.putExtra("Email", email);
                        intent.putExtra("Dept", b1.getText().toString());
                        startActivity(intent);
                    }
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (s.equals("Complain")) {
                        Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                        intent.putExtra("Complain", "Enter Complain here");
                        intent.putExtra("Email", email);
                        intent.putExtra("Dept", b2.getText().toString());
                        startActivity(intent);
                    } else if (s.equals("Query")) {
                        Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                        intent.putExtra("Query", "Enter Query here");
                        intent.putExtra("Email", email);
                        intent.putExtra("Dept", b2.getText().toString());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                        intent.putExtra("Resource", "Request resource here");
                        intent.putExtra("Email", email);
                        intent.putExtra("Dept", b2.getText().toString());
                        startActivity(intent);
                    }
                }
            });

            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (s.equals("Complain")) {
                        Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                        intent.putExtra("Complain", "Enter Complain here");
                        intent.putExtra("Email", email);
                        intent.putExtra("Dept", b3.getText().toString());
                        startActivity(intent);
                    } else if (s.equals("Query")) {
                        Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                        intent.putExtra("Query", "Enter Query here");
                        intent.putExtra("Email", email);
                        intent.putExtra("Dept", b3.getText().toString());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                        intent.putExtra("Resource", "Request resource here");
                        intent.putExtra("Email", email);
                        intent.putExtra("Dept", b3.getText().toString());
                        startActivity(intent);
                    }
                }
            });
        }
    }
}