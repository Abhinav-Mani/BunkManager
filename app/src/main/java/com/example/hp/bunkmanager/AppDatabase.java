package com.example.hp.bunkmanager;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Subject.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract Dao dao();
}
