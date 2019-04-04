package com.example.hp.bunkmanager;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add extends AppCompatActivity {
    private EditText e1,e2,e3;
    private Button b1;
    private String sub,p,t;
    private AppDatabase db;
    private static final String TAG = "add";
    private AppDatabase appDatabase;
    Subject s=new Subject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
        appDatabase=Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Subject").allowMainThreadQueries().build();
        set();
    }
    private void set()
    {

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub=e1.getText().toString();
                p=e2.getText().toString();
                t=e3.getText().toString();
                Log.e(TAG,"all text accesed");
                Log.e(TAG,"sub"+sub+p+t+"sub");
                if(sub!=""&&p!=""&&t!="")
                {
                    s.SubjectName = sub;
                    s.present = p;
                    s.totalclass = t;
                    Log.e(TAG, "row set");

                    appDatabase.dao().inset(s);


                    Log.e(TAG, "inserted");
                    //Error to be sorted
                    Intent intent = null;
                    intent = new Intent(add.this, attendanceSheet.class);
                    Log.e(TAG, "intent set");

                    try {
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(add.this, "failed", Toast.LENGTH_LONG).show();
                    }


                    Log.e(TAG, "inserted pased");
                }
                else
                {
                    Toast.makeText(add.this,"Every field is Mandatory",Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    private void init()
    {
        e1=findViewById(R.id.subjectNameEntry);
        e2=findViewById(R.id.PresentDay);
        e3=findViewById(R.id.ClassDay);
        b1=findViewById(R.id.create);

        db=Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "Subject").build();


    }
}
