package com.example.database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText text1,text2,text3,text4;
    Button button1,button2;
    Student student;
    DatabaseReference reff;
    private long maxid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1=findViewById(R.id.editTextTextPersonName);
        text2=findViewById(R.id.editTextTextPersonName2);
        button1=findViewById(R.id.button);
        text3=findViewById(R.id.editTextTextPersonName3);
        text4=findViewById(R.id.editTextTextPersonName4);
        button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff= FirebaseDatabase.getInstance().getReference().child("Student").child("1");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name=snapshot.child("name").getValue().toString();
                        String cgpa=snapshot.child("cgpa").getValue().toString();
                        text3.setText(name);
                        text4.setText(cgpa);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student=new Student();
                reff= FirebaseDatabase.getInstance().getReference().child("Student");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        maxid=snapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                String name=text1.getText().toString().trim();
                float cgpa=Float.parseFloat(text2.getText().toString().trim());
                student.setName(name);
                student.setCgpa(cgpa);
                reff.child(String.valueOf(maxid+1)).setValue(student);
                Toast.makeText(MainActivity.this, "New Student Added", Toast.LENGTH_SHORT).show();
            }
        });

    }
}