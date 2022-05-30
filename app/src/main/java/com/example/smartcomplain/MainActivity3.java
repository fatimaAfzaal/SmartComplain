package com.example.smartcomplain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView Complain,Feedback;
    Button Update;
    FloatingActionButton fab;
    AlertDialog dialog;
    //int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();

        //AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Update feedback");
        //View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        fab=findViewById(R.id.floatingActionButton);
        Intent i=getIntent();
        String email=i.getStringExtra("Email");
        String dept=i.getStringExtra("Dept");
        String emp=i.getStringExtra("emp");


        Intent intent1 = getIntent();
        String key = intent1.getStringExtra("Key");
        String s = intent1.getStringExtra("s");
        String c = intent1.getStringExtra("c");
        String f = intent1.getStringExtra("f");
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        listView = findViewById(R.id.listview);
        firebaseDatabase = FirebaseDatabase.getInstance();
        if(s!=null)
        {
            fab.setVisibility(View.GONE);
        if (s.equals("Complain")) {
            databaseReference = firebaseDatabase.getReference().child("Complain");
        } else if (s.equals("Query")) {
            databaseReference = firebaseDatabase.getReference().child("Query");
        } else {
            databaseReference = firebaseDatabase.getReference().child("Resource");
        }
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot db : snapshot.getChildren()) {
                    String Email=db.child("Email").getValue(String.class);
                    String Complain = db.child("Complain").getValue(String.class);
                    String Query = db.child("Query").getValue(String.class);
                    String Resources = db.child("Resources").getValue(String.class);
                    String Feedback = db.child("Feedback").getValue(String.class);
                    String Time = db.child("Date and Time").getValue(String.class);

                    if (s.equals("Complain")) {
                        if(Email.equals(email)) {
                            arrayList.add("\nComplain : " + Complain + "\nFeedback: " + Feedback + "\nDate and Time: " + Time + "\n\n");
                            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity3.this, android.R.layout.simple_list_item_1, arrayList);
                            arrayAdapter.notifyDataSetChanged();
                            listView.setAdapter(arrayAdapter);
                            Toast.makeText(MainActivity3.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        }
                        } else if (s.equals("Query")) {
                        if(Email.equals(email)) {
                            arrayList.add("\nQuery : " + Query + "\nFeedback: " + Feedback + "\nDate and Time: " + Time + "\n\n");
                            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity3.this, android.R.layout.simple_list_item_1, arrayList);
                            arrayAdapter.notifyDataSetChanged();
                            listView.setAdapter(arrayAdapter);
                            Toast.makeText(MainActivity3.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if(Email.equals(email)) {
                            arrayList.add("\nResource : " + Resources + "\nFeedback: " + Feedback + "\nDate and Time: " + Time + "\n\n");
                            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity3.this, android.R.layout.simple_list_item_1, arrayList);
                            arrayAdapter.notifyDataSetChanged();
                            listView.setAdapter(arrayAdapter);
                            Toast.makeText(MainActivity3.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        }}

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity3.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

        if(f!=null) {
            fab.setVisibility(View.GONE);
            ArrayList<String> arrayList1 = new ArrayList<>();
            StringBuffer buffer1 = new StringBuffer();
            listView = findViewById(R.id.listview);
            firebaseDatabase = FirebaseDatabase.getInstance();

            if (c.equals("Complain")) {
                databaseReference = firebaseDatabase.getReference().child("Complain");
            } else if (c.equals("Query")) {
                databaseReference = firebaseDatabase.getReference().child("Query");
            } else {
                databaseReference = firebaseDatabase.getReference().child("Resource");
            }
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot db : snapshot.getChildren()) {

                        String Email=db.child("Email").getValue(String.class);
                        String Complain = db.child("Complain").getValue(String.class);
                        String Query = db.child("Query").getValue(String.class);
                        String Resources = db.child("Resources").getValue(String.class);
                        String Feedback = db.child("Feedback").getValue(String.class);
                        String Time = db.child("Date and Time").getValue(String.class);

                        if (c.equals("Complain") ) {
                            if(Feedback.equals(f)) {
                                if(Email.equals(email)) {
                            arrayList.add("\nComplain : " + Complain + "\nFeedback: " + Feedback + "\nDate and Time: " + Time + "\n\n");
                            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity3.this, android.R.layout.simple_list_item_1, arrayList);
                            arrayAdapter.notifyDataSetChanged();
                            listView.setAdapter(arrayAdapter);}}
                           // Toast.makeText(MainActivity3.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        } else if (c.equals("Query")) {
                            if(Feedback.equals(f)) {
                                if(Email.equals(email)) {
                                    //Toast.makeText(MainActivity3.this, "f" + f, Toast.LENGTH_SHORT).show();
                                    arrayList.add("\nQuery : " + Query + "\nFeedback: " + Feedback + "\nDate and Time: " + Time + "\n\n");
                                    ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity3.this, android.R.layout.simple_list_item_1, arrayList);
                                    arrayAdapter.notifyDataSetChanged();
                                    listView.setAdapter(arrayAdapter);
                                }}
                            //Toast.makeText(MainActivity3.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        } else {
                            if(Feedback.equals(f)){
                                if(Email.equals(email)) {
                            arrayList.add("\nResource : " + Resources + "\nFeedback: " + Feedback + "\nDate and Time: " + Time + "\n\n");
                            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity3.this, android.R.layout.simple_list_item_1, arrayList);
                            arrayAdapter.notifyDataSetChanged();
                            listView.setAdapter(arrayAdapter);
                                }
                            }
                            //Toast.makeText(MainActivity3.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(MainActivity3.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            /*builder.setView(view);
            dialog = builder.create();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    dialog.show();

                }
            });*/


        }

        if(dept!=null && emp==null){
            fab.setVisibility(View.GONE);
            ArrayList<String> arrayList1 = new ArrayList<>();
            StringBuffer buffer1 = new StringBuffer();
            listView = findViewById(R.id.listview);
            firebaseDatabase = FirebaseDatabase.getInstance();

            if (c.equals("Complain")) {
                databaseReference = firebaseDatabase.getReference().child("Complain");
            } else if (c.equals("Query")) {
                databaseReference = firebaseDatabase.getReference().child("Query");
            } else {
                databaseReference = firebaseDatabase.getReference().child("Resource");
            }
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot db : snapshot.getChildren()) {
                        String Dept=db.child("Dept").getValue(String.class);
                        String Email=db.child("Email").getValue(String.class);
                        String Complain = db.child("Complain").getValue(String.class);
                        String Query = db.child("Query").getValue(String.class);
                        String Resources = db.child("Resources").getValue(String.class);
                        String Feedback = db.child("Feedback").getValue(String.class);
                        String Time = db.child("Date and Time").getValue(String.class);

                        if (c.equals("Complain") ) {

                                if(dept.equals(Dept)) {
                                    arrayList.add("\nComplain : " + Complain + "\nFeedback: " + Feedback + "\nDate and Time: " + Time + "\n\n");
                                    ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity3.this, android.R.layout.simple_list_item_1, arrayList);
                                    arrayAdapter.notifyDataSetChanged();
                                    listView.setAdapter(arrayAdapter);}
                            // Toast.makeText(MainActivity3.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        } else if (c.equals("Query")) {
                                if(dept.equals(Dept)) {
                                   // Toast.makeText(MainActivity3.this, "f" + f, Toast.LENGTH_SHORT).show();
                                    arrayList.add("\nQuery : " + Query + "\nFeedback: " + Feedback + "\nDate and Time: " + Time + "\n\n");
                                    ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity3.this, android.R.layout.simple_list_item_1, arrayList);
                                    arrayAdapter.notifyDataSetChanged();
                                    listView.setAdapter(arrayAdapter);
                                }
                            //Toast.makeText(MainActivity3.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        } else {

                                if(dept.equals(Dept)) {
                                    arrayList.add("\nResource : " + Resources + "\nFeedback: " + Feedback + "\nDate and Time: " + Time + "\n\n");
                                    ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity3.this, android.R.layout.simple_list_item_1, arrayList);
                                    arrayAdapter.notifyDataSetChanged();
                                    listView.setAdapter(arrayAdapter);

                            }
                            //Toast.makeText(MainActivity3.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(MainActivity3.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }

        if(emp!=null){
            //if(count==0){
                ArrayList<String> arrayList1 = new ArrayList<>();
                StringBuffer buffer1 = new StringBuffer();
                listView = findViewById(R.id.listview);
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference().child("employee");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot db : snapshot.getChildren()) {
                            String Dept=db.child("Dept").getValue(String.class);
                            String Email=db.child("Email").getValue(String.class);

                            if(dept.equals(Dept)||dept.equals(Dept.toUpperCase(Locale.ROOT))||dept.equals(Dept.toLowerCase(Locale.ROOT))) {
                                arrayList1.add("\n " + Email);
                                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity3.this, android.R.layout.simple_list_item_1, arrayList1);
                                arrayAdapter.notifyDataSetChanged();
                                listView.setAdapter(arrayAdapter);}
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity3.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("Add employee");
            View v=getLayoutInflater().inflate(R.layout.custom_dialog,null);

            EditText eName,eAge;
            eName=v.findViewById(R.id.name);
            eAge=v.findViewById(R.id.age);
            Button submit=v.findViewById(R.id.submit);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //count++;
                    //Toast.makeText(MainActivity3.this, "Inside sub", Toast.LENGTH_SHORT).show();
                    HashMap<String, Object> m = new HashMap<String, Object>();
                    m.put("Email", eName.getText().toString());
                    m.put("Dept",eAge.getText().toString());
                    //Toast.makeText(MainActivity3.this, "Dept "+dept, Toast.LENGTH_SHORT).show();
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference keyRef = rootRef.push();
                    String key = keyRef.getKey();
                    FirebaseDatabase.getInstance().getReference().child("employee").child(key).setValue(m).addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void unused) {

                            Intent i=new Intent(MainActivity3.this,MainActivity3.class);
                            i.putExtra("Dept",dept);
                            i.putExtra("emp", "emp");
                            startActivity(i);

                            //Toast.makeText(MainActivity3.this, "Your data is successfully added", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.dismiss();
                }
            });

            builder1.setView(v);
            dialog = builder1.create();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.show();
                }
            });


            }
    }


}