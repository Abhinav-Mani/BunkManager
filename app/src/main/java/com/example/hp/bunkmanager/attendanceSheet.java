package com.example.hp.bunkmanager;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class attendanceSheet extends AppCompatActivity {

    private FloatingActionButton b1;
    private RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private MenuInflater menuInflater;


    List<Subject> alldata;
    private AppDatabase db;
    private Dao dao;
    private static final String TAG = "attendanceSheet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_sheet);
        Log.e(TAG,"createed");
        init();
        Log.e(TAG,"initialized");
        set();
        Log.e(TAG,"all set");
    }
    private void init()
    {
        db=Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "Subject").allowMainThreadQueries().build();
        Log.e(TAG,"Room initialized");
       b1=findViewById(R.id.add);
        Log.e(TAG,"fab initialized");
       dao=db.dao();
        Log.e(TAG,"dao initialized");
        alldata=dao.allsubject();
        Log.e(TAG,"data recived initialized");

       recyclerView=findViewById(R.id.sheet);
        Log.e(TAG,"rv initialized");
       recyclerView.setHasFixedSize(false);
        Log.e(TAG,"false initialized");
       layoutManager = new LinearLayoutManager(this);
        Log.e(TAG,"layout manger initialized");
       recyclerView.setLayoutManager(layoutManager);
        Log.e(TAG,"layout manger set");
       adapter = new RecyclerViewAdapter(alldata);
        Log.e(TAG,"adapter initialized");
       recyclerView.setAdapter(adapter);
        Log.e(TAG,"adapter set");

    }
    private void set()
    {

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(attendanceSheet.this,add.class);
                startActivity(in);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.attendancemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.deleteall)
        {
            db.dao().Deleteall();
            RecyclerViewAdapter.subjectname.clear();
            adapter.notifyItemRangeRemoved(0,adapter.getItemCount());
            adapter.notifyDataSetChanged();


        }


        return super.onOptionsItemSelected(item);
    }
}
