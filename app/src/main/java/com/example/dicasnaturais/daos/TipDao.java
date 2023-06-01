package com.example.dicasnaturais.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.dicasnaturais.models.Tip;

import java.util.List;

@Dao
public interface TipDao {
    @Insert
    Long insert(Tip tip);

    @Update
    int update(Tip tip);

    @Delete
    int delete(Tip tip);

    @Query("SELECT * FROM Tip")
    List<Tip> list();
}