package com.example.dicasnaturais;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dicasnaturais.daos.TipDao;
import com.example.dicasnaturais.models.Tip;

@androidx.room.Database(entities = {Tip.class}, version = 1)
public abstract class Database extends RoomDatabase {
    abstract TipDao tipDao();

    public static Database getConnection(Context context) {
        return Room.databaseBuilder(context, Database.class, "dicas_naturais").allowMainThreadQueries().build();
    }
}
