package com.example.hp.bunkmanager;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Subject
{
    @NonNull
    @PrimaryKey
    String SubjectName;
    String present;
    String totalclass;
}
