package com.example.hp.bunkmanager;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@android.arch.persistence.room.Dao
public interface Dao
{

    @Query("SELECT * FROM subject")
    public List<Subject> allsubject();
    @Insert
    void inset(Subject sub);
    @Update
    void update(Subject subject);
    @Query("DELETE FROM Subject")
    void Deleteall();
    @Delete
    void Delete(Subject subject);

}
