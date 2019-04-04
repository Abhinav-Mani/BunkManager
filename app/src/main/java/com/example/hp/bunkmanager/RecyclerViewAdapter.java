package com.example.hp.bunkmanager;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public static List<Subject> subjectname;
    private Context context;
    private String TAG ="RecyclerViewAdapter";
    public RecyclerViewAdapter(List<Subject> subjectname)
    {
        this.subjectname = subjectname;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single,viewGroup,false);
        MyViewHolder vh=new MyViewHolder(view);
        context=viewGroup.getContext();
        return vh;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.t1.setText(subjectname.get(i).SubjectName);
        myViewHolder.t2.setText("Attended:- "+subjectname.get(i).present);
        myViewHolder.t3.setText("Total:-"+subjectname.get(i).totalclass);
        DecimalFormat df=new DecimalFormat("#.##");
        DecimalFormat df2=new DecimalFormat("#");
        myViewHolder.t4.setText("Attendece:-"+(df.format((Double.valueOf(subjectname.get(i).present)/Double.valueOf(subjectname.get(i).totalclass))*100))+"%");
        double bc=0;
        double ac=0;


        if(((Double.valueOf(subjectname.get(i).present)/Double.valueOf(subjectname.get(i).totalclass))*100)>=75) {
            bc=(4.0/3.0*Double.valueOf(subjectname.get(i).present)-Double.valueOf(subjectname.get(i).totalclass));
            myViewHolder.t5.setText("You Can Safely BUNK " + df2.format(Math.floor(bc)) + " classes");
            myViewHolder.linearLayout.setBackgroundResource(R.color.safeZone);

        }
        else
        {
            ac=(3*Double.valueOf(subjectname.get(i).totalclass)-4*Double.valueOf(subjectname.get(i).present));
            myViewHolder.t5.setText("You Should ATTEND "+df2.format(Math.ceil(ac))+" ClASS WITHOUT FAIL");
            myViewHolder.linearLayout.setBackgroundResource(R.color.dangerZone);

        }
        myViewHolder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent ed=new Intent(v.getContext(),Edit.class);
                ed.putExtra("nm",subjectname.get(i).SubjectName);
                ed.putExtra("pres",subjectname.get(i).present);
                ed.putExtra("tot",subjectname.get(i).totalclass);
                v.getContext().startActivity(ed);


                return false;
            }
        });


        myViewHolder.bunk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject subupdate = new Subject();
                subupdate.SubjectName=subjectname.get(i).SubjectName;
                subupdate.present=String.valueOf(Integer.parseInt(subjectname.get(i).present));
                subupdate.totalclass=String.valueOf(Integer.parseInt(subjectname.get(i).totalclass)+1);
                AppDatabase appDatabase=Room.databaseBuilder(context,AppDatabase.class,"Subject").allowMainThreadQueries().build();
                appDatabase.dao().update(subupdate);
                subjectname.remove(i);
                subjectname.add(i,subupdate);
                notifyItemChanged(i);

            }
        });
        myViewHolder .attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject subupdate = new Subject();
                subupdate.SubjectName=subjectname.get(i).SubjectName;
                subupdate.present=String.valueOf(Integer.parseInt(subjectname.get(i).present)+1);
                subupdate.totalclass=String.valueOf(Integer.parseInt(subjectname.get(i).totalclass)+1);
                AppDatabase appDatabase=Room.databaseBuilder(context,AppDatabase.class,"Subject").allowMainThreadQueries().build();
                appDatabase.dao().update(subupdate);
                Log.e(TAG,"update done");
                subjectname.remove(i);
                subjectname.add(i,subupdate);
                notifyItemChanged(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectname.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3,t4,t5;
        Button bunk,attend;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.subnm);
            t2=itemView.findViewById(R.id.pres);
            t3=itemView.findViewById(R.id.tot);
            t4=itemView.findViewById(R.id.per);
            t5=itemView.findViewById(R.id.notificationboard);
            bunk=itemView.findViewById(R.id.bunk);
            attend=itemView.findViewById(R.id.attend);
            linearLayout=itemView.findViewById(R.id.single);

        }
    }
}
