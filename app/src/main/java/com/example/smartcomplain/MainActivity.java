package com.example.smartcomplain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Locale;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    EditText etLoginEmail;
    EditText etLoginPassword;
    TextView tvRegisterHere;
    Button btnLogin;
    int flag;
    String Email;
    int count;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

        reset_alert=new AlertDialog.Builder(this);
        inflater=this.getLayoutInflater();

        etLoginEmail =(EditText) findViewById(R.id.etRegEmail);


        etLoginEmail =(EditText) findViewById(R.id.etRegEmail);
        etLoginPassword = findViewById(R.id.editTextTextPassword);
        tvRegisterHere = findViewById(R.id.tvLoginHere);
        btnLogin = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();

        TextView tvforgot=findViewById(R.id.textView);
        tvforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view=inflater.inflate(R.layout.reset,null);


                reset_alert.setTitle(" Reset Forget Password?")
                        .setMessage(" Enter your Email to get Password Reset link ")
                        .setPositiveButton(" Reset", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText email=view.findViewById(R.id.editTextEmailAddress);
                                if(email.getText().toString().isEmpty()){
                                    email.setError(" Required failed");
                                    return;
                                }
                                firebaseAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(MainActivity.this,"Reset Email Sent",Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }).setNegativeButton(" cancel",null)
                        .setView(view)
                        .create().show();
            }
        });

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });
        tvRegisterHere.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, register.class));
        });
    }

    private void loginUser() {
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etLoginEmail.setError("Email cannot be empty");
            etLoginEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etLoginPassword.setError("Password cannot be empty");
            etLoginPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference().child("employee");
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot db : snapshot.getChildren()) {
                                    String Dept=db.child("Dept").getValue(String.class);
                                    Email=db.child("Email").getValue(String.class);
                                    Toast.makeText(MainActivity.this, "Email "+Email, Toast.LENGTH_SHORT).show();
                                    if(etLoginEmail.getText ().toString().equals(Email)){
                                        count++;
                                        flag=1;
                                        break;
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


                        if(flag==1){
                                Intent intent1=new Intent(MainActivity.this, Exam.class);
                                intent1.putExtra("email" , etLoginEmail.getText ().toString());
                                startActivity(intent1);
                                flag=0;
                        }
                        else if(etLoginEmail.getText ().toString().equals("205274@aack.au.edu.pk")){
                            Intent intent1=new Intent(MainActivity.this, admin.class);
                            intent1.putExtra("email" , etLoginEmail.getText ().toString());
                            startActivity(intent1);
                        }
                        else if(etLoginEmail.getText ().toString().equals("fatima@gmail.com")) {
                            Intent intent1 = new Intent(MainActivity.this, Exam.class);
                            intent1.putExtra("email", etLoginEmail.getText().toString());
                            startActivity(intent1);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "flag "+flag+"count "+count, Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(MainActivity.this, MainMenu.class);
                            intent1.putExtra("email", etLoginEmail.getText().toString());

                            startActivity(intent1);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    protected  void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){

            if(FirebaseAuth.getInstance().getCurrentUser().getEmail().equals("205274@aack.au.edu.pk")){
                Intent i=new Intent(getApplicationContext(),admin.class);
                i.putExtra("email",FirebaseAuth.getInstance().getCurrentUser().getEmail());
                startActivity(i);
            }
            else if(FirebaseAuth.getInstance().getCurrentUser().getEmail().equals("fatima@gmail.com")){
                Intent i=new Intent(getApplicationContext(),Exam.class);
                i.putExtra("email",FirebaseAuth.getInstance().getCurrentUser().getEmail());
                startActivity(i);
            }
            else{
                Intent i=new Intent(getApplicationContext(),MainMenu.class);
                i.putExtra("email",FirebaseAuth.getInstance().getCurrentUser().getEmail());
                startActivity(i);
            }

            finish();
        }
    }


    public void onr(View view) {startActivity(new Intent(MainActivity.this, register.class));

    }


}

