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

    @Query("SELECT * FROM Tip WHERE LOWER(title) LIKE LOWER('%' || :title || '%')")
    List<Tip> filterByTitle(String title);

    @Query("SELECT COUNT(*) FROM Tip")
    int count();
}
