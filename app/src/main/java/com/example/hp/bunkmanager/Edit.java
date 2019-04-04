package com.example.hp.bunkmanager;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edit extends AppCompatActivity {
    EditText e1,e2,e3;
    String nm,pc,tot,nnm,npc,ntot;
    Button edit,delete;
    public Subject s,ns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
        assign();


    }
    private void init()
    {
        Intent in= getIntent();
        e1=findViewById(R.id.subjectNameEntryed);
        e2=findViewById(R.id.PresentDayed);
        e3=findViewById(R.id.ClassDayed);
        edit=findViewById(R.id.edit);
        delete=findViewById(R.id.delete);
        nm=in.getStringExtra("nm");
        pc=in.getStringExtra("pres");
        tot=in.getStringExtra("tot");
        s= new Subject();
        ns= new Subject();
    }
    private void assign()
    {
        e1.setText(nm);
        e2.setText(pc);
        e3.setText(tot);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.SubjectName=nm;
                Log.e("ak47","name update done");
                s.present=pc;
                Log.e("ak47","presentday update done");
                s.totalclass=tot;
                Log.e("ak47","es ready");
                Log.e("ak47","name="+nm);
                AppDatabase db=Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Subject").allowMainThreadQueries().build();
                db.dao().Delete(s);
                Intent intent=new Intent(Edit.this,attendanceSheet.class);
                Log.e("ak47","intent ready");
                startActivity(intent);
                Log.e("ak47","called");
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ak47","onclick called");
                nnm=e1.getText().toString();
                npc=e2.getText().toString();
                ntot=e3.getText().toString();
                Log.e("ak47","edittext value extracted");
                if(nm!=""&&pc!=""&&tot!="")
                {
                    Log.e("ak47","name="+nm);
                    s.SubjectName=nm;
                    Log.e("ak47","name update done");
                    s.present=pc;
                    Log.e("ak47","presentday update done");
                    s.totalclass=tot;
                    Log.e("ak47","es ready");
                    Log.e("ak47","name="+nm);
                    ns.SubjectName=nnm;
                    Log.e("ak47","name update done");
                    ns.present=npc;
                    Log.e("ak47","presentday update done");
                    ns.totalclass=ntot;
                    Log.e("ak47","es ready");
                }
                AppDatabase db=Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Subject").allowMainThreadQueries().build();
                Log.e("ak47","db done");
                if(nnm.equals(nm)) {
                    db.dao().update(ns);
                }
                else
                    {
                        db.dao().Delete(s);
                        db.dao().inset(ns);

                    }
                Log.e("ak47","update done");
                Intent intent=new Intent(Edit.this,attendanceSheet.class);
                Log.e("ak47","intent ready");
                startActivity(intent);
                Log.e("ak47","called");
            }
        });
    }

}
